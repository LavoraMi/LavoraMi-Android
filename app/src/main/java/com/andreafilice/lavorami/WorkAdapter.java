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
import androidx.recyclerview.widget.DiffUtil;  // FIX: aggiunto per sostituire notifyDataSetChanged()
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static Typeface sFontInterBold;
    private static final int sColorRed = Color.parseColor("#FD272D");
    private static final int sColorGreen = Color.parseColor("#16660e");
    private static int sColorWhite = -1;

    static Context context;
    private List<EventDescriptor> eventList;
    private static Typeface sFontMainNormal;
    private List<NativeAd> adsList;
    private final List<Integer> adPositions = new ArrayList<>();
    private final String langCode;
    private final boolean showTranslateButton;
    public static final int TYPE_LAVORO = 0;
    public static final int TYPE_AD = 1;

    public WorkAdapter(Context context, List<EventDescriptor> eventList) {
        String savedLang = DataManager.getStringData(DataKeys.KEY_DEFAULT_LANGUAGE, "Italiano");

        this.context = context;
        this.eventList = (eventList != null) ? eventList : new ArrayList<>();
        this.adsList = new ArrayList<>();
        this.langCode = savedLang.contains("English") ? "en" : savedLang.contains("Spanish") ? "es" : "it";

        this.showTranslateButton = langCode.equalsIgnoreCase("en") || langCode.equalsIgnoreCase("es") || DataManager.getBoolData(DataKeys.KEY_SHOW_TRANSLATE_BUTTON, false);

        if (sFontInterBold == null) {
            Typeface inter = ResourcesCompat.getFont(context, R.font.inter);
            sFontInterBold = Typeface.create(inter, Typeface.BOLD);
        }
        if (sColorWhite == -1)
            sColorWhite = ContextCompat.getColor(context, R.color.White);
    }

    public void setAdsList(List<NativeAd> ads) {
        this.adsList = (ads != null) ? ads : new ArrayList<>();
        recomputeAdPositions();
        notifyDataSetChanged();
    }

    public void addAdsBatch(List<NativeAd> newAds) {
        if (newAds == null || newAds.isEmpty()) return;

        this.adsList.addAll(newAds);

        recomputeAdPositions();
        notifyDataSetChanged();
    }

    private void recomputeAdPositions() {
        adPositions.clear();

        int count = getItemCount();
        for (int i = 0; i < count; i++) if (getItemViewType(i) == TYPE_AD) adPositions.add(i);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        LinearLayout bannerImportante;
        ImageView cardImage;
        ImageView openCloseIcon;
        ImageView locationIcon;
        TextView titleText, trattaText, startDateText, endDateText, companyText, descriptionText;
        ChipGroup chipGroupLinee;
        ProgressBar progressBar;
        Button translateBtn;

        public ViewHolder(View itemView) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

            int realPosition = getRealEventPosition(position);

            if (realPosition < 0 || realPosition >= eventList.size()) {
                itemHolder.itemView.setVisibility(View.GONE);
                return;
            }

            EventDescriptor item = eventList.get(realPosition);

            String finalStartDate = formattaData(item.getStartDate());
            String finalEndDate   = formattaData(item.getEndDate());
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
                itemHolder.cardView.setStrokeColor(sColorRed); // FIX 1: usa costante statica
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
                itemHolder.descriptionText.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
                itemHolder.openCloseIcon.animate().rotation(isExpanded ? -90f : 0f).setDuration(200).start();
                ActivityUtils.triggerFeedback(context);
            });

            int progressPercentage = calcolaPercentuale(item.getStartDateMillis(), item.getEndDateMillis());
            itemHolder.progressBar.setProgress(progressPercentage);
            itemHolder.progressBar.setProgressTintList(ColorStateList.valueOf(progressPercentage == 100 ? sColorGreen : sColorRed));

            itemHolder.translateBtn.setVisibility(showTranslateButton ? View.VISIBLE : View.GONE);

            final String detailsForTranslation = cleanedDetails;
            itemHolder.translateBtn.setOnClickListener(v -> {
                Context ctx = v.getContext();
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ctx);
                View sheetView = LayoutInflater.from(ctx).inflate(R.layout.item_sheet_translated, null);
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
                    cancelTerms.setOnClickListener(unused -> bottomSheetDialog.cancel());
                }
            });

            bindChips(itemHolder, item);
            itemHolder.locationIcon.setVisibility((item.getLines() != null && item.getLines().length > 0) ? View.VISIBLE : View.GONE);
        }
        else {
            AdViewHolder adHolder = (AdViewHolder) holder;
            int adIndex = Collections.binarySearch(adPositions, position);

            holder.setIsRecyclable(false);

            if (adIndex >= 0 && adIndex < adsList.size()) {
                adHolder.itemView.setVisibility(View.VISIBLE);

                ViewGroup.LayoutParams lp = adHolder.itemView.getLayoutParams();
                if (!(lp instanceof RecyclerView.LayoutParams) || lp.height != ViewGroup.LayoutParams.WRAP_CONTENT)
                    adHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                adHolder.bindAd(adsList.get(adIndex));
            }
            else adHolder.itemView.setVisibility(View.GONE);
        }
    }

    private int getRealEventPosition(int adapterPosition) {
        if (adsList == null || adsList.isEmpty()) return adapterPosition;
        if (eventList.size() >= 6 && adapterPosition > 4) return adapterPosition - 1;
        return adapterPosition;
    }

    private void bindChips(ViewHolder holder, EventDescriptor item) {
        String[] lines = item.getLines();
        String chipKey = (lines != null && lines.length > 0) ? String.join(",", lines) : "";
        String cachedKey = (String) holder.chipGroupLinee.getTag();

        if (chipKey.equals(cachedKey)) return;

        holder.chipGroupLinee.removeAllViews();
        holder.chipGroupLinee.setTag(chipKey);

        if (lines == null || lines.length == 0) return;

        Context ctx = holder.itemView.getContext();
        float density = ctx.getResources().getDisplayMetrics().density;
        int heightPx = (int) (26 * density);

        for (String nome : lines) {
            String nomePulito = nome.trim();
            Chip chip = new Chip(ctx);
            chip.setText(nomePulito);

            chip.setShapeAppearanceModel(chip.getShapeAppearanceModel().toBuilder().setAllCornerSizes(10f).build());

            if (nomePulito.contains("Filobus")) {
                chip.setChipIcon(ContextCompat.getDrawable(ctx, R.drawable.ic_bolt));
                chip.setChipIconTint(ColorStateList.valueOf(Color.WHITE));
                chip.setIconStartPadding(10);
            }
            else if (nomePulito.contains("N")) {
                chip.setChipIcon(ContextCompat.getDrawable(ctx, R.drawable.ic_dark));
                chip.setChipIconTint(ColorStateList.valueOf(Color.WHITE));
                chip.setIconStartPadding(10);
            }

            chip.setEnsureMinTouchTargetSize(false);
            chip.setChipMinHeight(heightPx);
            chip.setMinHeight(heightPx);
            chip.setChipStartPadding(0f);
            chip.setChipEndPadding(0f);
            chip.setTextStartPadding(15f);
            chip.setTextEndPadding(15f);
            chip.setChipStrokeWidth(0f);
            chip.setTextSize(13f);
            chip.setTypeface(sFontInterBold);

            int coloreLinea = StationDB.getLineColor(ctx, nomePulito);
            chip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(ctx, coloreLinea)));
            chip.setTextColor(sColorWhite);

            chip.setCloseIconVisible(false);
            chip.setClickable(false);
            chip.setCheckable(false);

            holder.chipGroupLinee.addView(chip);
        }
    }

    public static void translateStrings(View sheetView, EventDescriptor item, String cleanedDetails,
                                        String langCode, ShimmerFrameLayout loadingLayout) {
        MaterialButton btnCopy = sheetView.findViewById(R.id.btn_copy);
        TextView translatedTxt = sheetView.findViewById(R.id.translated_text);

        btnCopy.setVisibility(View.GONE);

        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ITALIAN)
                .setTargetLanguage(langCode.equalsIgnoreCase("es") ? TranslateLanguage.SPANISH : TranslateLanguage.ENGLISH)
                .build();

        Translator translator = Translation.getClient(options);
        DownloadConditions conditions = new DownloadConditions.Builder().build();

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

    public void setFilteredList(List<EventDescriptor> filteredList) {
        List<EventDescriptor> newList = (filteredList != null) ? filteredList : new ArrayList<>();

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new EventDiffCallback(this.eventList, newList));
        this.eventList = newList;
        recomputeAdPositions();

        diffResult.dispatchUpdatesTo(this);
    }

    private int calcolaPercentuale(long start, long end) {
        long now = System.currentTimeMillis();
        long totalDuration = end - start;
        if (totalDuration <= 0) return 100;
        long elapsed = now - start;
        double fraction = (double) elapsed / totalDuration;
        double clamped = Math.max(0.0, Math.min(fraction, 1.0));
        return (int) (clamped * 100);
    }

    private static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    private static class EventDiffCallback extends DiffUtil.Callback {
        private final List<EventDescriptor> oldList;
        private final List<EventDescriptor> newList;

        EventDiffCallback(List<EventDescriptor> oldList, List<EventDescriptor> newList) {
            this.oldList = (oldList != null) ? oldList : new ArrayList<>();
            this.newList = (newList != null) ? newList : new ArrayList<>();
        }

        @Override public int getOldListSize() { return oldList.size(); }
        @Override public int getNewListSize() { return newList.size(); }

        @Override
        public boolean areItemsTheSame(int oldPos, int newPos) {
            EventDescriptor o = oldList.get(oldPos);
            EventDescriptor n = newList.get(newPos);

            return o.getTitle() != null && o.getTitle().equals(n.getTitle()) && o.getStartDate() != null && o.getStartDate().equals(n.getStartDate());
        }

        @Override
        public boolean areContentsTheSame(int oldPos, int newPos) {return areItemsTheSame(oldPos, newPos);}
    }

    public static class AdViewHolder extends RecyclerView.ViewHolder {
        private final NativeAdView nativeAdView;
        private final TextView txtHeadline;
        private final TextView txtBody;
        private final Button btnCallToAction;
        private final ImageView imgIcon;
        private NativeAd boundAd;

        public AdViewHolder(View itemView) {
            super(itemView);
            nativeAdView = itemView.findViewById(R.id.native_ad_view);
            txtHeadline = itemView.findViewById(R.id.ad_headline);
            txtBody = itemView.findViewById(R.id.ad_body);
            btnCallToAction = itemView.findViewById(R.id.ad_call_to_action);
            imgIcon = itemView.findViewById(R.id.ad_app_icon);
        }

        public NativeAd getBoundAd() { return boundAd; }

        public void bindAd(NativeAd nativeAd) {
            if (boundAd == nativeAd) return;
            boundAd = nativeAd;

            if (sFontMainNormal == null) sFontMainNormal = ResourcesCompat.getFont(context, R.font.font_main);
            txtHeadline.setText(nativeAd.getHeadline());
            txtBody.setText(nativeAd.getBody());
            btnCallToAction.setText(R.string.installText);
            btnCallToAction.setTypeface(sFontMainNormal);

            if (nativeAd.getIcon() != null) {
                imgIcon.setImageDrawable(nativeAd.getIcon().getDrawable());
                imgIcon.setVisibility(View.VISIBLE);
            }
            else imgIcon.setVisibility(View.GONE);

            nativeAdView.setHeadlineView(txtHeadline);
            nativeAdView.setBodyView(txtBody);
            nativeAdView.setCallToActionView(btnCallToAction);
            nativeAdView.setIconView(imgIcon);
            nativeAdView.setNativeAd(nativeAd);
        }
    }
}