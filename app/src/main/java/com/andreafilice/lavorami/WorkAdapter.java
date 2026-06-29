package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.EventDescriptor.formattaData;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

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
    private List<NativeAd> adsList;
    private final String langCode;
    private static final int TYPE_LAVORO = 0;
    private static final int TYPE_AD = 1;

    public WorkAdapter(Context context, List<EventDescriptor> eventList) {
        String savedLang = DataManager.getStringData(DataKeys.KEY_DEFAULT_LANGUAGE, "Italiano");

        this.context = context;
        this.eventList = (eventList!=null) ? eventList : new ArrayList<>();
        this.adsList = new ArrayList<>();
        this.langCode = savedLang.contains("English") ? "en" : savedLang.contains("Spanish") ? "es" : "it";
    }

    public void setAdsList(List<NativeAd> ads) {
        this.adsList = (ads != null) ? ads : new ArrayList<>();
        notifyDataSetChanged();
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (viewType == TYPE_AD) {
            View adView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_pubblicita, parent, false);
            return new AdViewHolder(adView);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lavoro, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_LAVORO) {
            ViewHolder itemHolder = (ViewHolder) holder;

            int adCountBefore = 0;
            for (int i = 0; i < position; i++) if (getItemViewType(i) == TYPE_AD) adCountBefore++;
            int realPosition = position - adCountBefore;
            
            if (position > 4 && adsList != null && !adsList.isEmpty()) realPosition = position - 1;

            if (realPosition >= eventList.size()) {
                itemHolder.itemView.setVisibility(View.GONE);
                return;
            }

            EventDescriptor item = eventList.get(realPosition);

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
                itemHolder.descriptionText.setVisibility((isExpanded) ? View.GONE : View.VISIBLE);
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

                if (isAcceptingTerms) {
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

                    if (nomePulito.contains("Filobus")) {
                        chip.setChipIcon(ContextCompat.getDrawable(context, R.drawable.ic_bolt));
                        chip.setChipIconTint(ColorStateList.valueOf(Color.WHITE));
                        chip.setIconStartPadding(10);
                    }
                    else if (nomePulito.contains("N")) {
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
        else {
            AdViewHolder adHolder = (AdViewHolder) holder;

            int adCount = 0;
            for (int i = 0; i <= position; i++) if (getItemViewType(i) == TYPE_AD) adCount++;
            int adIndex = adCount - 1;

            if (adIndex >= 0 && adIndex < adsList.size()) {
                adHolder.itemView.setVisibility(View.VISIBLE);
                adHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                adHolder.bindAd(adsList.get(adIndex));
            }
            else {
                adHolder.itemView.setVisibility(View.GONE);
                adHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
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
    public int getItemCount() {
        if (eventList == null || eventList.isEmpty()) return 0;
        if (adsList == null || adsList.isEmpty()) return eventList.size();
        if (eventList.size() >= 6) return eventList.size() + 1;

        int totalSlots = (eventList.size() / 7) + 1;
        int maxAds = Math.min(totalSlots, adsList.size());

        return eventList.size() + maxAds;
    }

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
        catch (Exception e) {return 0;}
    }

    public void setFilteredList(List<EventDescriptor> filteredList) {
        this.eventList = (filteredList != null) ? filteredList : new ArrayList<>();
        notifyDataSetChanged();
    }

    private static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    @Override
    public int getItemViewType(int position) {
        if (adsList == null || adsList.isEmpty()) return TYPE_LAVORO;
        if (position == 4 && !adsList.isEmpty() && eventList.size() >= 6) return TYPE_AD;
        if (position == getItemCount() - 1 && adsList.size() > 0) return TYPE_AD;

        if (position % 8 == 7 && position != 7) {
            int adIndex = (position + 1) / 8 - 1;
            if (adIndex < adsList.size()) return TYPE_AD;
        }
        return TYPE_LAVORO;
    }

    public static class AdViewHolder extends RecyclerView.ViewHolder {

        private final NativeAdView nativeAdView;
        private final TextView txtHeadline;
        private final TextView txtBody;
        private final Button btnCallToAction;
        private final ImageView imgIcon;

        public AdViewHolder(View itemView) {
            super(itemView);

            nativeAdView = (NativeAdView) itemView.findViewById(R.id.native_ad_view);
            txtHeadline = itemView.findViewById(R.id.ad_headline);
            txtBody = itemView.findViewById(R.id.ad_body);
            btnCallToAction = itemView.findViewById(R.id.ad_call_to_action);
            imgIcon = itemView.findViewById(R.id.ad_app_icon);
        }

        public void bindAd(NativeAd nativeAd) {

            txtHeadline.setText(nativeAd.getHeadline());
            txtBody.setText(nativeAd.getBody());
            btnCallToAction.setText(nativeAd.getCallToAction());

            if (nativeAd.getIcon() != null) {
                imgIcon.setImageDrawable(nativeAd.getIcon().getDrawable());
                imgIcon.setVisibility(View.VISIBLE);
            }
            else
                imgIcon.setVisibility(View.GONE);

            nativeAdView.setHeadlineView(txtHeadline);
            nativeAdView.setBodyView(txtBody);
            nativeAdView.setCallToActionView(btnCallToAction);
            nativeAdView.setIconView(imgIcon);

            nativeAdView.setNativeAd(nativeAd);
        }
    }
}