package com.andreafilice.lavorami;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executors;

public class InstagramShareHelper {

    private static final String TAG = "InstagramShareHelper";
    private static final String INSTAGRAM_PACKAGE = "com.instagram.android";

    /**
     * @param context Context dell'applicazione o Activity
     * @param level Livello raggiunto dall'utente
     * @param rankTitle Titolo/Grado associato al livello
     */
    public static void shareLevelUpStory(Context context, int level, String rankTitle) {
        if (context == null) return;

        Context appContext = context.getApplicationContext();

        View storyView = LayoutInflater.from(context).inflate(R.layout.story_level_up, null);

        TextView textLevelNumber = storyView.findViewById(R.id.story_level_number);
        TextView textLevel = storyView.findViewById(R.id.story_level_text);
        TextView textRank = storyView.findViewById(R.id.story_rank_text);

        if (textLevelNumber != null) {
            textLevelNumber.setText(String.valueOf(level));
        }
        if (textLevel != null) {
            textLevel.setText(context.getString(R.string.accountLevelShortFormat, level).toUpperCase());
        }
        if (textRank != null) {
            textRank.setText(rankTitle);
        }

        int width = 1080;
        int height = 1920;
        storyView.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        );
        storyView.layout(0, 0, width, height);

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        storyView.draw(canvas);

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                File imagesFolder = new File(appContext.getCacheDir(), "shared_images");
                if (!imagesFolder.exists() && !imagesFolder.mkdirs()) {
                    Log.e(TAG, "Impossibile creare la cartella per la condivisione delle immagini");
                }

                File imageFile = new File(imagesFolder, "story_level_up.png");
                try (FileOutputStream out = new FileOutputStream(imageFile)) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                }

                Uri contentUri = FileProvider.getUriForFile(
                        appContext,
                        appContext.getPackageName() + ".fileprovider",
                        imageFile
                );

                appContext.grantUriPermission(
                        INSTAGRAM_PACKAGE,
                        contentUri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                );

                Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
                intent.setDataAndType(contentUri, "image/png");
                intent.putExtra("source_asset_uri", contentUri);
                intent.putExtra("interactive_asset_uri", contentUri);
                intent.putExtra("top_background_color", "#121216");
                intent.putExtra("bottom_background_color", "#000000");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setPackage(INSTAGRAM_PACKAGE);

                if (!(context instanceof Activity)) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }

                new Handler(Looper.getMainLooper()).post(() -> {
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                    } else {
                        // Fallback: Condivisione generica
                        Intent chooserIntent = new Intent(Intent.ACTION_SEND);
                        chooserIntent.setType("image/png");
                        chooserIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                        chooserIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        if (!(context instanceof Activity)) {
                            chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }

                        if (chooserIntent.resolveActivity(context.getPackageManager()) != null) {
                            context.startActivity(Intent.createChooser(chooserIntent, context.getString(R.string.shareLavoraMi)));
                        } else {
                            Toast.makeText(context, context.getString(R.string.instagram_not_installed), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } catch (IOException e) {
                Log.e(TAG, "Errore nella generazione dell'immagine di condivisione", e);
                new Handler(Looper.getMainLooper()).post(() ->
                        Toast.makeText(appContext, appContext.getString(R.string.unknownErrorToast), Toast.LENGTH_SHORT).show()
                );
            }
        });
    }
}
