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
    private final List<Object> combinedList = new ArrayList<>();
    private final String langCode;
    private final boolean showTranslateButton;
    public static final int TYPE_LAVORO = 0;
    public static final int TYPE_AD = 1;
    private final Object listLock = new Object();

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

    private void recomputeCombinedList() {
        synchronized (listLock) {
            combinedList.clear();
            if (eventList == null || eventList.isEmpty()) return;

            int adIndex = 0;
            for (int i = 0; i < eventList.size(); i++) {
                if (adIndex < adsList.size() && combinedList.size() > 0 && combinedList.size() % 7 == 5) {
                    combinedList.add(adsList.get(adIndex));
                    adIndex++;
                }
                combinedList.add(eventList.get(i));
            }

            while (adIndex < adsList.size() && adsList.get(adIndex) != null) {
                combinedList.add(adsList.get(adIndex));
                adIndex++;
            }
        }
    }

    public void setAdsList(List<NativeAd> ads) {
        synchronized (listLock) {
            this.adsList = (ads != null) ? ads : new ArrayList<>();
            recomputeCombinedList();
        }
        notifyDataSetChanged();
    }

    public void addAdsBatch(List<NativeAd> newAds) {
        if (newAds == null || newAds.isEmpty()) return;

        synchronized (listLock) {
            this.adsList.addAll(newAds);
            recomputeCombinedList();
        }
        notifyDataSetChanged();
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
        Object item = combinedList.get(position);
        Context ctx = holder.itemView.getContext();

        if (holder instanceof ViewHolder) {
            ViewHolder itemHolder = (ViewHolder) holder;
            EventDescriptor event = (EventDescriptor) item;

            itemHolder.cardImage.setImageResource(event.getCachedCardImageID());
            int textColor = ContextCompat.getColor(ctx, R.color.text_primary);
            ImageViewCompat.setImageTintList(itemHolder.cardImage, ColorStateList.valueOf(textColor));

            itemHolder.titleText.setText(event.getTitle());
            itemHolder.trattaText.setText(event.getRoads());
            itemHolder.startDateText.setText(event.getFormattedStartDate());
            itemHolder.endDateText.setText(event.getFormattedEndDate());
            itemHolder.companyText.setText(event.getCompany());

            String details = event.getDetails();
            boolean isImportant = details != null && details.contains("[LAVORO IMPORTANTE]");
            itemHolder.descriptionText.setText(isImportant ? details.replace("[LAVORO IMPORTANTE]", "").trim() : details);

            if (isImportant) {
                itemHolder.bannerImportante.setVisibility(View.VISIBLE);
                itemHolder.cardView.setStrokeWidth(dpToPx(ctx, 2));
                itemHolder.cardView.setStrokeColor(sColorRed);
                itemHolder.cardView.setCardElevation(dpToPx(ctx, 10));
            }
            else {
                itemHolder.bannerImportante.setVisibility(View.GONE);
                itemHolder.cardView.setStrokeWidth(0);
                itemHolder.cardView.setCardElevation(dpToPx(ctx, 4));
            }

            itemHolder.descriptionText.setVisibility(View.GONE);
            itemHolder.openCloseIcon.setRotation(-90f);

            itemHolder.itemView.setOnClickListener(v -> {
                boolean isExpanded = itemHolder.descriptionText.getVisibility() == View.VISIBLE;
                itemHolder.descriptionText.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
                itemHolder.openCloseIcon.animate().rotation(isExpanded ? -90f : 0f).setDuration(200).start();
                ActivityUtils.triggerFeedback(context);
            });

            int progress = calcolaPercentuale(event.getStartDateMillis(), event.getEndDateMillis());
            itemHolder.progressBar.setProgress(progress);
            itemHolder.progressBar.setProgressTintList(ColorStateList.valueOf(progress == 100 ? sColorGreen : sColorRed));

            itemHolder.translateBtn.setVisibility(showTranslateButton ? View.VISIBLE : View.GONE);
            bindChips(itemHolder, event);
            itemHolder.locationIcon.setVisibility((event.getLines() != null && event.getLines().length > 0) ? View.VISIBLE : View.GONE);
        }
        else if (holder instanceof AdViewHolder) ((AdViewHolder) holder).bindAd((NativeAd) item);
    }

    private void bindChips(ViewHolder holder, EventDescriptor item) {
        String[] lines = item.getLines();
        String chipKey = item.getCachedLinesString();
        String cachedKey = (String) holder.chipGroupLinee.getTag();

        if (chipKey.equals(cachedKey)) return;
        holder.chipGroupLinee.setTag(chipKey);

        Context ctx = holder.itemView.getContext();
        int lineCount = (lines != null) ? lines.length : 0;
        int childCount = holder.chipGroupLinee.getChildCount();

        for (int i = 0; i < Math.max(lineCount, childCount); i++) {
            if (i < lineCount) {
                String nomePulito = lines[i].trim();
                Chip chip;
                if (i < childCount) {
                    chip = (Chip) holder.chipGroupLinee.getChildAt(i);
                    chip.setVisibility(View.VISIBLE);
                }
                else {
                    chip = new Chip(ctx);
                    float density = ctx.getResources().getDisplayMetrics().density;
                    int heightPx = (int) (26 * density);
                    chip.setShapeAppearanceModel(chip.getShapeAppearanceModel().toBuilder().setAllCornerSizes(10f).build());
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
                    chip.setTextColor(sColorWhite);
                    chip.setCloseIconVisible(false);
                    chip.setClickable(false);
                    chip.setCheckable(false);
                    holder.chipGroupLinee.addView(chip);
                }

                if (!nomePulito.equals(chip.getText().toString())) {
                    chip.setText(nomePulito);
                    int[] cachedColors = item.getCachedChipColors();
                    int colorRes = (cachedColors != null && i < cachedColors.length) ? cachedColors[i] : R.color.OTHER_LINES;
                    chip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(ctx, colorRes)));

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
                    else chip.setChipIcon(null);
                }
            }
            else holder.chipGroupLinee.getChildAt(i).setVisibility(View.GONE);
        }
    }

    public static void translateStrings(View sheetView, EventDescriptor item, String cleanedDetails, String langCode, ShimmerFrameLayout loadingLayout) {
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
    public int getItemCount() {return combinedList.size();}
    @Override
    public int getItemViewType(int position) {return (combinedList.get(position) instanceof NativeAd) ? TYPE_AD : TYPE_LAVORO;}

    public void setFilteredList(List<EventDescriptor> filteredList) {
        List<EventDescriptor> newList = (filteredList != null) ? new ArrayList<>(filteredList) : new ArrayList<>();
        
        synchronized (listLock) {
            this.eventList = newList;
            recomputeCombinedList();
        }

        notifyDataSetChanged();
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