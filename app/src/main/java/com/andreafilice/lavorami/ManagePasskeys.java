package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andreafilice.lavorami.supabase.PasskeyManager;
import com.andreafilice.lavorami.supabase.SupabaseAPI;
import com.andreafilice.lavorami.supabase.SupabaseAuthenticator;
import com.andreafilice.lavorami.supabase.SupabaseModels;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManagePasskeys extends AppCompatActivity {
    SessionManager sessionManager;
    SupabaseAPI api;
    PasskeyManager passkeyManager;
    PasskeyAdapter adapter;
    RecyclerView recyclerViewPasskeys;
    ShimmerFrameLayout progressBar;
    LinearLayout tvEmpty;
    CardView btnAddPasskey;
    boolean operationInProgress = false;

    private String SupabaseANON, SupabaseURL;
    private final ArrayList<SupabaseModels.PasskeyItem> passkeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_passkeys);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            finish();
            return;
        }

        //*SUPABASE INIT
        /// Initialize Supabase componenst and get MetaDatas.
        SupabaseANON = getMetaData(this, "supabaseANON");
        SupabaseURL = getMetaData(this, "supabaseURL");

        if (SupabaseURL == null) {
            Toast.makeText(this, getString(R.string.connectionErrorToast), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        OkHttpClient client = new OkHttpClient.Builder()
            .authenticator(new SupabaseAuthenticator(this, SupabaseANON, SupabaseURL))
            .build();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SupabaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

        api = retrofit.create(SupabaseAPI.class);
        passkeyManager = new PasskeyManager(this, api, SupabaseANON);

        ImageButton backBtn = findViewById(R.id.backBtn);
        recyclerViewPasskeys = findViewById(R.id.rvPasskeys);
        progressBar = findViewById(R.id.passkeysProgress);
        tvEmpty = findViewById(R.id.tvPasskeysEmpty);
        btnAddPasskey = findViewById(R.id.btnAddPasskeyHere);

        backBtn.setOnClickListener(v -> finish());

        adapter = new PasskeyAdapter(passkeys, new PasskeyAdapter.Listener() {
            @Override
            public void onDelete(SupabaseModels.PasskeyItem item) {confirmDelete(item);}
        });

        recyclerViewPasskeys.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPasskeys.setAdapter(adapter);

        btnAddPasskey.setOnClickListener(v -> addPasskey());

        loadPasskeys();
    }

    static String safeName(SupabaseModels.PasskeyItem item) {return item.friendly_name == null ? "" : item.friendly_name;}
    private String bearer() {return "Bearer " + sessionManager.getToken();}

    private void showLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        progressBar.startShimmer();

        if (loading) {
            recyclerViewPasskeys.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.GONE);
        }
        else
            progressBar.stopShimmer();
    }

    private void refreshListVisibility() {
        boolean empty = passkeys.isEmpty();
        recyclerViewPasskeys.setVisibility(empty ? View.GONE : View.VISIBLE);
        tvEmpty.setVisibility(empty ? View.VISIBLE : View.GONE);
    }

    private void loadPasskeys() {
        showLoading(true);

        api.listPasskeys(SupabaseANON, bearer()).enqueue(new Callback<ArrayList<SupabaseModels.PasskeyItem>>() {
            @Override
            public void onResponse(Call<ArrayList<SupabaseModels.PasskeyItem>> call, Response<ArrayList<SupabaseModels.PasskeyItem>> response) {
                showLoading(false);

                if (response.isSuccessful() && response.body() != null) {
                    passkeys.clear();
                    passkeys.addAll(response.body());
                    adapter.notifyDataSetChanged();

                    refreshListVisibility();
                }
                else {
                    Toast.makeText(ManagePasskeys.this, getString(R.string.loadingPasskeysFailedToast), Toast.LENGTH_SHORT).show();
                    refreshListVisibility();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SupabaseModels.PasskeyItem>> call, Throwable t) {
                showLoading(false);
                Toast.makeText(ManagePasskeys.this, getString(R.string.connectionErrorToast), Toast.LENGTH_SHORT).show();

                refreshListVisibility();
            }
        });
    }

    private void addPasskey() {
        if (operationInProgress) return;
        operationInProgress = true;

        passkeyManager.register(this, new PasskeyManager.Result<Void>() {
            @Override
            public void onSuccess(Void result) {
                operationInProgress = false;
                Toast.makeText(ManagePasskeys.this, getString(R.string.passkeyCreatedSuccesfully), Toast.LENGTH_SHORT).show();

                loadPasskeys();
            }

            @Override
            public void onError(String error) {
                operationInProgress = false;

                if (PasskeyManager.ERROR_CANCELLED.equals(error)) return;
                if ("webauthn_credential_exists".equals(error))
                    Toast.makeText(ManagePasskeys.this, getString(R.string.passkeyAlreadyExist), Toast.LENGTH_SHORT).show();
                else if ("too_many_passkeys".equals(error))
                    Toast.makeText(ManagePasskeys.this, getString(R.string.tooManyPasskeysToast), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ManagePasskeys.this, getString(R.string.unknownErrorToast), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void confirmDelete(SupabaseModels.PasskeyItem item) {
        Runnable onConfirm = new Runnable() {
            @Override
            public void run() {deletePasskey(item);}
        };

        DialogHelper.createDefaultAnswerDialog(this, getString(R.string.areYouSurePopUp), getString(R.string.areYouSurePasskeyPopUp), onConfirm);
    }

    private void deletePasskey(SupabaseModels.PasskeyItem item) {
        if (operationInProgress) return;
        operationInProgress = true;

        api.deletePasskey(SupabaseANON, bearer(), item.id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                operationInProgress = false;

                if (response.isSuccessful()) {
                    int index = passkeys.indexOf(item);
                    if (index >= 0) {
                        passkeys.remove(index);
                        adapter.notifyItemRemoved(index);
                    }

                    refreshListVisibility();
                    Toast.makeText(ManagePasskeys.this, getString(R.string.passkeySuccesfullyDeletedToast), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(ManagePasskeys.this, getString(R.string.unknownErrorToast), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                operationInProgress = false;
                Toast.makeText(ManagePasskeys.this, getString(R.string.connectionErrorToast), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //*ADAPTER
    /// This adapter shows each Passkey with: name (renamable), creation date, last use and the rename/delete actions.
    static class PasskeyAdapter extends RecyclerView.Adapter<PasskeyAdapter.ViewHolder> {
        interface Listener {
            void onDelete(SupabaseModels.PasskeyItem item);
        }

        private final ArrayList<SupabaseModels.PasskeyItem> items;
        private final Listener listener;

        PasskeyAdapter(ArrayList<SupabaseModels.PasskeyItem> items, Listener listener) {
            this.items = items;
            this.listener = listener;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView passkeyName, passkeyCreatedAt;
            ImageView passkeyDeleteBtn;

            ViewHolder(@NonNull View view) {
                super(view);
                passkeyName = view.findViewById(R.id.tvPasskeyName);
                passkeyCreatedAt = view.findViewById(R.id.tvPasskeyCreated);
                passkeyDeleteBtn = view.findViewById(R.id.btnDeletePasskey);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_passkey, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            SupabaseModels.PasskeyItem item = items.get(position);
            Context context = viewHolder.itemView.getContext();

            String shownName = safeName(item);
            viewHolder.passkeyName.setText(shownName.isEmpty() ? "Passkey" : shownName);
            viewHolder.passkeyCreatedAt.setText(context.getString(R.string.createdAtPasskey) + formatDate(item.created_at));

            viewHolder.passkeyDeleteBtn.setOnClickListener(v -> listener.onDelete(item));
        }

        @Override
        public int getItemCount() {return items.size();}

        private static String formatDate(String iso) {
            if (iso == null || iso.length() < 10) return "-";

            String[] parts = iso.substring(0, 10).split("-");
            if (parts.length != 3) return iso;

            return parts[2] + "/" + parts[1] + "/" + parts[0];
        }
    }
}