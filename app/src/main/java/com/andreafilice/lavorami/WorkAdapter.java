package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.EventDescriptor.formattaData;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class WorkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static Context context;
    private List<EventDescriptor> eventList;
    private final String langCode;

    public WorkAdapter(Context context, List<EventDescriptor> eventList) {
        String savedLang = DataManager.getStringData(DataKeys.KEY_DEFAULT_LANGUAGE, "Italiano");

        this.context = context;
        this.eventList = (eventList!=null) ? eventList : new ArrayList<>();
        this.langCode = savedLang.contains("English") ? "en" : savedLang.contains("Spanish") ? "es" : "it";
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        LinearLayout bannerImportante;
        ImageView cardImage;
        ImageView openCloseIcon;
        ImageView locationIcon;
        TextView titleText, trattaText, startDateText, endDateText,companyText, descriptionText;
        ChipGroup chipGroupLinee;
        ProgressBar progressBar;
        Button translateBtn;

        public ViewHolder(View itemView){
            super(itemView);
            cardView = (MaterialCardView) itemView;
            bannerImportante = itemView.findViewById(R.id.bannerImportante);
            cardImage = itemView.findViewById(R.id.iconEvent);
            openCloseIcon = itemView.findViewById(R.id.open_close_descriprion);
            locationIcon = itemView.findViewById(R.id.iconLuogo);
            titleText = itemView.findViewById(R.id.txtTitle);
            trattaText = itemView.findViewById(R.id.txtRoute);
            startDateText = itemView.findViewById(R.id.txtStartDate);
            endDateText = itemView.findViewById(R.id.txtEndDate);
            companyText = itemView.findViewById(R.id.txtOperator);
            descriptionText = itemView.findViewById(R.id.txtDescription);
            chipGroupLinee = itemView.findViewById(R.id.chipGroupLinee);
            progressBar = itemView.findViewById(R.id.progressBarDate);
            translateBtn = itemView.findViewById(R.id.btnTranslate);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lavoro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        EventDescriptor item = eventList.get(position);

        String finalStartDate = formattaData(item.getStartDate());
        String finalEndDate = formattaData(item.getEndDate());

        int color = ContextCompat.getColor(itemHolder.itemView.getContext(), R.color.text_primary);

        ImageViewCompat.setImageTintList(itemHolder.cardImage, ColorStateList.valueOf(color));
        itemHolder.cardImage.setImageResource(item.getCardImageID());

        itemHolder.titleText.setText(item.getTitle());
        itemHolder.trattaText.setText(item.getRoads());
        itemHolder.startDateText.setText(finalStartDate);
        itemHolder.endDateText.setText(finalEndDate);
        itemHolder.companyText.setText(item.getCompany());

        boolean isImportant = item.getDetails() != null && item.getDetails().contains("[LAVORO IMPORTANTE]");
        String cleanedDetails = isImportant ? item.getDetails().replace("[LAVORO IMPORTANTE]", "").trim() : item.getDetails();

        itemHolder.descriptionText.setText(cleanedDetails);

        if (isImportant) {
            itemHolder.bannerImportante.setVisibility(View.VISIBLE);
            itemHolder.cardView.setStrokeColor(Color.parseColor("#FD272D"));
            itemHolder.cardView.setStrokeWidth(dpToPx(itemHolder.itemView.getContext(), 2));
            itemHolder.cardView.setCardElevation(dpToPx(itemHolder.itemView.getContext(), 10));
        }
        else {
            itemHolder.bannerImportante.setVisibility(View.GONE);
            itemHolder.cardView.setStrokeWidth(0);
            itemHolder.cardView.setCardElevation(dpToPx(itemHolder.itemView.getContext(), 4));
        }

        itemHolder.descriptionText.setVisibility(View.GONE);
        itemHolder.openCloseIcon.setImageResource(R.drawable.ic_down);

        itemHolder.itemView.setOnClickListener(v -> {
            boolean isExpanded = itemHolder.descriptionText.getVisibility() == View.VISIBLE;
            itemHolder.descriptionText.setVisibility((isExpanded) ? View.GONE: View.VISIBLE);
            itemHolder.openCloseIcon.animate().rotation(isExpanded ? -90f : 0f).setDuration(200).start();
            ActivityUtils.triggerFeedback(context);
        });

        int progressPercentage = calcolaPercentuale(item.getStartDate(), item.getEndDate());
        itemHolder.progressBar.setProgress(progressPercentage);

        itemHolder.progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor((progressPercentage == 100) ? "#16660e" : "#FD272D")));

        itemHolder.chipGroupLinee.removeAllViews();
        itemHolder.translateBtn.setVisibility((langCode.equalsIgnoreCase("en") || langCode.equalsIgnoreCase("es") || DataManager.getBoolData(DataKeys.KEY_SHOW_TRANSLATE_BUTTON, false)) ? View.VISIBLE : View.GONE);

        final String detailsForTranslation = cleanedDetails;
        itemHolder.translateBtn.setOnClickListener(v -> {
            //*VARIABLES
            /// In this section of the code, we initialize some components that we will use later in the code.
            Context context = v.getContext();
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            View sheetView = LayoutInflater.from(context).inflate(R.layout.item_sheet_translated, null);
            ShimmerFrameLayout loadingLayout = sheetView.findViewById(R.id.loadingLayout);
            LinearLayout layoutDefault = sheetView.findViewById(R.id.layoutDefault);
            LinearLayout layoutTerms = sheetView.findViewById(R.id.layoutPrivacy);
            Button acceptTerms = sheetView.findViewById(R.id.btnContinue);
            Button cancelTerms = sheetView.findViewById(R.id.btnCancel);
            TextView downloadingText = sheetView.findViewById(R.id.textDownloading);
            boolean isAcceptingTerms = DataManager.getBoolData(DataKeys.KEY_DOWNLOAD_POLICIES, false);

            loadingLayout.startShimmer();
            bottomSheetDialog.setContentView(sheetView);

            bottomSheetDialog.show();

            if(isAcceptingTerms) {
                layoutTerms.setVisibility(View.GONE);
                layoutDefault.setVisibility(View.VISIBLE);
                downloadingText.setVisibility(View.GONE);

                translateStrings(sheetView, item, detailsForTranslation, langCode, loadingLayout);
            }
            else {
                layoutDefault.setVisibility(View.GONE);
                layoutTerms.setVisibility(View.VISIBLE);

                acceptTerms.setOnClickListener(view -> {
                    layoutDefault.setVisibility(View.VISIBLE);
                    layoutTerms.setVisibility(View.GONE);
                    downloadingText.setVisibility(View.VISIBLE);
                    DataManager.saveBoolData(DataKeys.KEY_DOWNLOAD_POLICIES, true);

                    translateStrings(sheetView, item, detailsForTranslation, langCode, loadingLayout);
                });

                cancelTerms.setOnClickListener(unusued -> {bottomSheetDialog.cancel();});
            }
        });

        List<String> lineeRaw = Arrays.asList(item.getLines());
        if (!lineeRaw.isEmpty()) {
            for (String nome : lineeRaw) {
                String nomePulito = nome.trim();
                Context context = itemHolder.itemView.getContext();
                Chip chip = new Chip(context);
                chip.setText(nomePulito);

                ShapeAppearanceModel cornerRadius = chip.getShapeAppearanceModel()
                        .toBuilder()
                        .setAllCornerSizes(10f)
                        .build();

                if(nomePulito.contains("Filobus")){
                    chip.setChipIcon(ContextCompat.getDrawable(context, R.drawable.ic_bolt));
                    chip.setChipIconTint(ColorStateList.valueOf(Color.WHITE));
                    chip.setIconStartPadding(10);
                }
                else if(nomePulito.contains("N")){
                    chip.setChipIcon(ContextCompat.getDrawable(context, R.drawable.ic_dark));
                    chip.setChipIconTint(ColorStateList.valueOf(Color.WHITE));
                    chip.setIconStartPadding(10);
                }

                chip.setShapeAppearanceModel(cornerRadius);

                float density = context.getResources().getDisplayMetrics().density;
                int heightPx = (int) (26 * density);
                chip.setEnsureMinTouchTargetSize(false);
                chip.setChipMinHeight((float) heightPx);
                chip.setMinHeight(heightPx);

                chip.setChipStartPadding(0f);
                chip.setChipEndPadding(0f);
                chip.setTextStartPadding(15f);
                chip.setTextEndPadding(15f);
                chip.setChipStrokeWidth(0f);

                chip.setTextSize(13f);
                Typeface interMedium = ResourcesCompat.getFont(context, R.font.inter);
                Typeface interMediumBold = Typeface.create(interMedium, Typeface.BOLD);
                chip.setTypeface(interMediumBold);

                int coloreLinea = StationDB.getLineColor(context, nomePulito);
                int coloreEffettivo = ContextCompat.getColor(context, coloreLinea);
                int coloreTestoEffettivo = ContextCompat.getColor(context, R.color.White);

                chip.setChipBackgroundColor(ColorStateList.valueOf(coloreEffettivo));
                chip.setTextColor(coloreTestoEffettivo);

                chip.setCloseIconVisible(false);
                chip.setClickable(false);
                chip.setCheckable(false);

                itemHolder.chipGroupLinee.addView(chip);
                itemHolder.locationIcon.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void translateStrings(View sheetView, EventDescriptor item, String cleanedDetails, String langCode, ShimmerFrameLayout loadingLayout){
        MaterialButton btnCopy = sheetView.findViewById(R.id.btn_copy);
        TextView translatedTxt = sheetView.findViewById(R.id.translated_text);

        btnCopy.setVisibility(View.GONE);

        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ITALIAN)
                .setTargetLanguage((langCode.equalsIgnoreCase("es")) ? TranslateLanguage.SPANISH : TranslateLanguage.ENGLISH)
                .build();

        Translator translator = Translation.getClient(options);

        DownloadConditions conditions = new DownloadConditions.Builder()
                .build();

        translator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(unused -> {
                    translator.translate(item.getTitle()).addOnSuccessListener(title -> {
                        translator.translate(cleanedDetails).addOnSuccessListener(details -> {
                            String finalText = title + "\n\n" + details + "\n\n" + context.getString(R.string.roads) + item.getRoads() + "\n\n" + context.getString(R.string.linesInvolved) + item.getStringLines();
                            loadingLayout.setVisibility(View.GONE);
                            translatedTxt.setVisibility(View.VISIBLE);
                            translatedTxt.setText(finalText);
                            btnCopy.setVisibility(View.VISIBLE);
                            DataManager.saveBoolData(DataKeys.KEY_DOWNLOAD_POLICIES, true);
                        });
                    }).addOnFailureListener(e -> {
                        loadingLayout.setVisibility(View.GONE);
                        translatedTxt.setVisibility(View.VISIBLE);
                        translatedTxt.setText(context.getString(R.string.errorTranslating));
                    });
                });

        btnCopy.setOnClickListener(viewClick -> {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("traduzione", translatedTxt.getText());
            btnCopy.setIcon(ContextCompat.getDrawable(context, R.drawable.ic_checkmark_single));
            btnCopy.setText(context.getString(R.string.translationCopied));
            clipboard.setPrimaryClip(clip);

            new Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
                if (btnCopy != null) {
                    btnCopy.setIconResource(R.drawable.ic_clipboard);
                    btnCopy.setText(context.getString(R.string.copyTranslation));
                }
            }, 4000);
        });
    }

    @Override
    public int getItemCount(){return eventList.size();}

    private int calcolaPercentuale(String startDateStr, String endDateStr) {
        long start = getDateMillis(startDateStr);
        long end = getDateMillis(endDateStr);
        long now = System.currentTimeMillis();

        long totalDuration = end - start;
        if (totalDuration <= 0) return 100;

        long elapsed = now - start;
        double fraction = (double) elapsed / totalDuration;
        double clamped = Math.max(0.0, Math.min(fraction, 1.0));
        return (int) (clamped * 100);
    }

    private long getDateMillis(String dateString) {
        if (dateString == null) return 0;
        String serverFormat = "yyyy-MM-dd'T'HH:mm:ss'+01:00'";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(serverFormat, Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date date = sdf.parse(dateString);
            return (date != null) ? date.getTime() : 0;

        }
        catch (Exception e) {
            return 0;
        }
    }

    public void setFilteredList(List<EventDescriptor> filteredList) {
        this.eventList = (filteredList != null) ? filteredList : new ArrayList<>();
        notifyDataSetChanged();
    }

    private static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}