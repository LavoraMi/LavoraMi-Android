package com.andreafilice.lavorami;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class DialogHelper {
    public static void createDefaultDialog(Context context, @NotNull String titleText, @NotNull String depsText) {
        /// In this method, we create a default dialog with the default action of the button (close dialog).
        /// @PARAMETERS
        /// Context context is the Activity Context where to show the dialog
        /// @NotNull String titleText is the main text of the dialog (the bigger one).
        /// @NotNull String depsText is the description text of the dialog, long and exhaustive.

        //*VARIABLES
        /// In this section, we have all the variables for our dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.info_dialog_layout, null);
        TextView text_title = dialogView.findViewById(R.id.text_title);
        TextView text_description = dialogView.findViewById(R.id.text_description);

        //*SETUP POPUP
        /// Set-up popup texts and variables
        text_title.setText(titleText);
        text_description.setText(depsText);

        AlertDialog dialog = new AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true)
            .create();
        dialog.show();

        if (dialog.getWindow() != null) dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button btnClose = dialogView.findViewById(R.id.btn_close_dialog);
        btnClose.setOnClickListener(v1 -> dialog.dismiss());
    }

    public static void createCustomDialog(Context context, @NotNull String titleText, @NotNull String depsText, Runnable toExecute) {
        /// In this method, we create a default dialog with the default action of the button (close dialog).
        /// @PARAMETERS
        /// Context context is the Activity Context where to show the dialog
        /// String titleText is the main text of the dialog (the bigger one).
        /// String depsText is the description text of the dialog, long and exhaustive.
        /// Runnable toExecute is the action to perform when the user clicks on "OK" button.

        //*VARIABLES
        /// In this section, we have all the variables for our dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.info_dialog_layout, null);
        TextView text_title = dialogView.findViewById(R.id.text_title);
        TextView text_description = dialogView.findViewById(R.id.text_description);

        //*SETUP POPUP
        /// Set-up popup texts and variables
        text_title.setText(titleText);
        text_description.setText(depsText);

        AlertDialog dialog = new AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true)
            .create();
        dialog.show();

        if (dialog.getWindow() != null) dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button btnClose = dialogView.findViewById(R.id.btn_close_dialog);
        btnClose.setOnClickListener(v1 -> toExecute.run());
    }

    public static void createDefaultAnswerDialog(Context context, @NotNull String titleText, @NotNull String depsText, Runnable onContinue) {
        /// In this method, we create a dialog with two buttons: Cancel or Continue.
        /// @PARAMETERS
        /// Context context is the Activity Context where to show the dialog
        /// @NotNull String titleText is the main text of the dialog (the bigger one).
        /// @NotNull String depsText is the description text of the dialog, long and exhaustive.
        /// Runnable onContinue is the action to perform when the user clicks on "Continue" button.

        //*VARIABLES
        /// In this section, we have all the variables for our dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.info_dialog_double_layout, null);
        TextView text_title = dialogView.findViewById(R.id.text_title);
        TextView text_description = dialogView.findViewById(R.id.text_description);
        Button btn_close_dialog_cancel = dialogView.findViewById(R.id.btn_close_dialog_cancel);
        Button btn_close_dialog_confirm = dialogView.findViewById(R.id.btn_close_dialog_confirm);

        //*SETUP POPUP
        /// Set-up popup texts and variables
        text_title.setText(titleText);
        text_description.setText(depsText);

        AlertDialog dialog = new AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true)
            .create();
        dialog.show();

        if (dialog.getWindow() != null) dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));

        btn_close_dialog_cancel.setOnClickListener(v -> dialog.dismiss());
        btn_close_dialog_confirm.setOnClickListener(v -> {
            onContinue.run();
            dialog.dismiss();
        });
    }

    public static void createCustomAnswerDialog(Context context, @NotNull String titleText, @NotNull String depsText, @NotNull String buttonConfirmText, Runnable onContinue) {
        /// In this method, we create a dialog with two buttons: Cancel or Continue.
        /// @PARAMETERS
        /// Context context is the Activity Context where to show the dialog
        /// @NotNull String titleText is the main text of the dialog (the bigger one).
        /// @NotNull String depsText is the description text of the dialog, long and exhaustive.
        /// @NotNull String buttonConfirmText is the text to insert into the confirm button.
        /// Runnable onContinue is the action to perform when the user clicks on "Continue" button.

        //*VARIABLES
        /// In this section, we have all the variables for our dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.info_dialog_double_layout, null);
        TextView text_title = dialogView.findViewById(R.id.text_title);
        TextView text_description = dialogView.findViewById(R.id.text_description);
        Button btn_close_dialog_cancel = dialogView.findViewById(R.id.btn_close_dialog_cancel);
        Button btn_close_dialog_confirm = dialogView.findViewById(R.id.btn_close_dialog_confirm);

        //*SETUP POPUP
        /// Set-up popup texts and variables
        text_title.setText(titleText);
        text_description.setText(depsText);
        btn_close_dialog_confirm.setText(buttonConfirmText);

        AlertDialog dialog = new AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true)
            .create();
        dialog.show();

        if (dialog.getWindow() != null) dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));

        btn_close_dialog_cancel.setOnClickListener(v -> dialog.dismiss());
        btn_close_dialog_confirm.setOnClickListener(v -> {
            onContinue.run();
            dialog.dismiss();
        });
    }
}
