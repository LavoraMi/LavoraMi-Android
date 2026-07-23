package com.andreafilice.lavorami;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogHelper {
    public static void createDefaultDialog(Context context, String titleText, String depsText) throws EmptyDialogParameterException {
        /// In this method, we create a default dialog with the default action of the button (close dialog).
        /// @PARAMETERS
        /// Context context is the Activity Context where to show the dialog
        /// String titleText is the main text of the dialog (the bigger one).
        /// String depsText is the description text of the dialog, long and exhaustive.

        //*VARIABLES
        /// In this section, we have all the variables for our dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.info_dialog_layout, null);
        TextView text_title = dialogView.findViewById(R.id.text_title);
        TextView text_description = dialogView.findViewById(R.id.text_description);

        //*SETUP POPUP
        /// Set-up popup texts and variables
        if(titleText.isEmpty() || depsText.isEmpty()) throw new EmptyDialogParameterException();
        else {
            text_title.setText(titleText);
            text_description.setText(depsText);
        }

        AlertDialog dialog = new AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true)
            .create();
        dialog.show();

        if (dialog.getWindow() != null) dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button btnClose = dialogView.findViewById(R.id.btn_close_dialog);
        btnClose.setOnClickListener(v1 -> dialog.dismiss());
    }

    public static void createCustomDialog(Context context, String titleText, String depsText, Runnable toExecute) throws EmptyDialogParameterException {
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
        if(titleText.isEmpty() || depsText.isEmpty()) throw new EmptyDialogParameterException();
        else {
            text_title.setText(titleText);
            text_description.setText(depsText);
        }

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .setCancelable(true)
                .create();
        dialog.show();

        if (dialog.getWindow() != null) dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button btnClose = dialogView.findViewById(R.id.btn_close_dialog);
        btnClose.setOnClickListener(v1 -> toExecute.run());
    }
}
