package com.andreafilice.lavorami;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;

public class ModalUtils {

    public static void showCustomAlert(Context context, String title, String message, String positiveText, String negativeText, Runnable onPositive, Runnable onNegative) {
        View dialogView = LayoutInflater.from(context)
                .inflate(R.layout.modal_main, null);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        GradientDrawable background = new GradientDrawable();
        background.setShape(GradientDrawable.RECTANGLE);
        background.setCornerRadius(24 * context.getResources().getDisplayMetrics().density);
        background.setColor(ContextCompat.getColor(context, R.color.background_app));

        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(background);

        ((TextView) dialogView.findViewById(R.id.dialogTitle)).setText(title);
        ((TextView) dialogView.findViewById(R.id.dialogMessage)).setText(message);
        ((MaterialButton) dialogView.findViewById(R.id.dialogBtnPositive)).setText(positiveText);
        ((MaterialButton) dialogView.findViewById(R.id.dialogBtnNegative)).setText(negativeText);

        dialogView.findViewById(R.id.dialogBtnPositive).setOnClickListener(v -> {
            if (onPositive != null) onPositive.run();
            dialog.dismiss();
        });

        dialogView.findViewById(R.id.dialogBtnNegative).setOnClickListener(v -> {
            if (onNegative != null) onNegative.run();
            dialog.dismiss();
        });

        dialog.show();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(
                    (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90),
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
    }

    public static void showCustomAlert(Context context, String title, String message, String positiveText, Runnable onPositive) {
        View dialogView = LayoutInflater.from(context)
                .inflate(R.layout.modal_main, null);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .setCancelable(true)
                .create();

        GradientDrawable background = new GradientDrawable();
        background.setShape(GradientDrawable.RECTANGLE);
        background.setCornerRadius(24 * context.getResources().getDisplayMetrics().density);
        background.setColor(ContextCompat.getColor(context, R.color.background_app));

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(background);
        }

        ((TextView) dialogView.findViewById(R.id.dialogTitle)).setText(title);
        ((TextView) dialogView.findViewById(R.id.dialogMessage)).setText(message);
        ((MaterialButton) dialogView.findViewById(R.id.dialogBtnPositive)).setText(positiveText);

        dialogView.findViewById(R.id.dialogBtnNegative).setVisibility(View.GONE);

        dialogView.findViewById(R.id.dialogBtnPositive).setOnClickListener(v -> {
            if (onPositive != null) onPositive.run();
            dialog.dismiss();
        });

        dialog.show();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(
                    (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90),
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
    }
}