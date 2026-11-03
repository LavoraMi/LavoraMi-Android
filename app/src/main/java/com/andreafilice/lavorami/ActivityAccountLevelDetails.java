package com.andreafilice.lavorami;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ActivityAccountLevelDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_level_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> {
                ActivityUtils.triggerFeedback(this);
                finish();
            });
        }

        setupBadgesUI();
    }

    private void setupBadgesUI() {
        List<Badge> allBadges = BadgeManager.getAllBadges(this);
        int totalCount = BadgeManager.getTotalBadgesCount();

        List<Badge> unlockedBadges = new ArrayList<>();
        List<Badge> lockedBadges = new ArrayList<>();

        for (Badge b : allBadges) {
            if (b.isUnlocked()) {
                unlockedBadges.add(b);
            } else {
                lockedBadges.add(b);
            }
        }

        int unlockedCount = unlockedBadges.size();

        TextView unlockedBadgesSubtitle = findViewById(R.id.unlockedBadgesSubtitle);
        if (unlockedBadgesSubtitle != null) {
            unlockedBadgesSubtitle.setText(getString(R.string.account_badges_unlocked_subtitle, unlockedCount, totalCount));
        }

        LinearLayout containerUnlockedBadges = findViewById(R.id.containerUnlockedBadges);
        TextView emptyUnlockedText = findViewById(R.id.emptyUnlockedText);

        if (containerUnlockedBadges != null) {
            containerUnlockedBadges.removeAllViews();
            if (unlockedBadges.isEmpty()) {
                if (emptyUnlockedText != null) {
                    emptyUnlockedText.setVisibility(View.VISIBLE);
                }
            } else {
                if (emptyUnlockedText != null) {
                    emptyUnlockedText.setVisibility(View.GONE);
                }
                LayoutInflater inflater = LayoutInflater.from(this);
                for (Badge badge : unlockedBadges) {
                    View badgeView = inflater.inflate(R.layout.item_badge, containerUnlockedBadges, false);
                    ImageView badgeImage = badgeView.findViewById(R.id.badgeImage);
                    TextView badgeName = badgeView.findViewById(R.id.badgeName);

                    if (badgeImage != null) {
                        badgeImage.setImageResource(badge.getIconResId());
                        badgeImage.setColorFilter(ContextCompat.getColor(this, R.color.redMetro));
                    }
                    if (badgeName != null) {
                        badgeName.setText(badge.getName());
                    }

                    badgeView.setOnClickListener(v -> {
                        ActivityUtils.triggerFeedback(this);
                        showBadgeDialog(badge);
                    });

                    containerUnlockedBadges.addView(badgeView);
                }
            }
        }

        GridLayout gridLockedBadges = findViewById(R.id.gridLockedBadges);
        if (gridLockedBadges != null) {
            gridLockedBadges.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(this);
            for (Badge badge : lockedBadges) {
                View badgeView = inflater.inflate(R.layout.item_badge, gridLockedBadges, false);
                ImageView badgeImage = badgeView.findViewById(R.id.badgeImage);
                TextView badgeName = badgeView.findViewById(R.id.badgeName);

                if (badgeImage != null) {
                    badgeImage.setImageResource(badge.getIconResId());
                    badgeImage.setColorFilter(ContextCompat.getColor(this, R.color.White));
                }
                if (badgeName != null) {
                    badgeName.setText(badge.getName());
                }

                badgeView.setOnClickListener(v -> {
                    ActivityUtils.triggerFeedback(this);
                    showBadgeDialog(badge);
                });

                gridLockedBadges.addView(badgeView);
            }
        }
    }

    private void showBadgeDialog(Badge badge) {
        if (badge == null) return;

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_badge_layout, null);
        ImageView dialogBadgeImage = dialogView.findViewById(R.id.dialogBadgeImage);
        TextView dialogBadgeName = dialogView.findViewById(R.id.dialogBadgeName);
        TextView dialogBadgeStatus = dialogView.findViewById(R.id.dialogBadgeStatus);
        TextView dialogBadgeDescription = dialogView.findViewById(R.id.dialogBadgeDescription);
        LinearLayout dialogProgressContainer = dialogView.findViewById(R.id.dialogProgressContainer);
        ProgressBar dialogProgressBar = dialogView.findViewById(R.id.dialogProgressBar);
        TextView dialogProgressText = dialogView.findViewById(R.id.dialogProgressText);
        Button btnClose = dialogView.findViewById(R.id.btn_close_badge_dialog);

        if (dialogBadgeImage != null) {
            dialogBadgeImage.setImageResource(badge.getIconResId());
            if (badge.isUnlocked()) {
                dialogBadgeImage.setColorFilter(ContextCompat.getColor(this, R.color.redMetro));
            } else {
                dialogBadgeImage.setColorFilter(ContextCompat.getColor(this, R.color.White));
            }
        }

        if (dialogBadgeName != null) {
            dialogBadgeName.setText(badge.getName());
        }

        if (dialogBadgeStatus != null) {
            if (badge.isUnlocked()) {
                dialogBadgeStatus.setText(getString(R.string.badge_status_unlocked));
                dialogBadgeStatus.setTextColor(ContextCompat.getColor(this, R.color.redMetro));
            } else {
                dialogBadgeStatus.setText(getString(R.string.badge_status_locked));
                dialogBadgeStatus.setTextColor(ContextCompat.getColor(this, R.color.subtitle));
            }
        }

        if (dialogBadgeDescription != null) {
            dialogBadgeDescription.setText(badge.getDescription());
        }

        if (badge.getMaxProgress() > 1) {
            if (dialogProgressContainer != null) dialogProgressContainer.setVisibility(View.VISIBLE);
            if (dialogProgressBar != null) {
                dialogProgressBar.setMax(badge.getMaxProgress());
                dialogProgressBar.setProgress(badge.getCurrentProgress());
            }
            if (dialogProgressText != null) {
                dialogProgressText.setText(getString(R.string.badge_progress_format, badge.getCurrentProgress(), badge.getMaxProgress()));
            }
        } else {
            if (dialogProgressContainer != null) dialogProgressContainer.setVisibility(View.GONE);
        }

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        dialog.show();

        if (btnClose != null) {
            btnClose.setOnClickListener(v -> dialog.dismiss());
        }
    }
}
