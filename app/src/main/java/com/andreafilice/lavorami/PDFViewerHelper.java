package com.andreafilice.lavorami;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class PDFViewerHelper {
    private static final String TAG = "PDFViewerHelper";
    private static final String CACHE_DIR = "pdf_cache";
    private static final long MAX_CACHE_AGE = 30 * 24 * 60 * 60 * 1000;

    private final Context context;

    public PDFViewerHelper(Context context) {
        this.context = context;
    }

    public String generatePDFViewerHTML(String pdfUrl) {
        String encodedUrl = encodeURLForHTML(pdfUrl);

        return "<!DOCTYPE html>" +
                "<html>" +
                    "<head>" +
                        "<meta charset='utf-8'>" +
                        "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
                        "<title>PDF Viewer</title>" +
                        "<style>" +
                        "body { margin: 0; padding: 0; background: #f5f5f5; font-family: sans-serif; }" +
                        "#container { width: 100%; height: 100vh; display: flex; flex-direction: column; }" +
                        "#toolbar { background: #323232; color: white; padding: 10px; display: flex; " +
                        "gap: 10px; align-items: center; font-size: 14px; box-shadow: 0 2px 4px rgba(0,0,0,0.2); }" +
                        "#toolbar button { background: #1a1a1a; color: white; border: none; padding: 8px 12px; " +
                        "border-radius: 4px; cursor: pointer; font-size: 12px; }" +
                        "#toolbar button:hover { background: #404040; }" +
                        "#toolbar button:disabled { opacity: 0.5; cursor: not-allowed; }" +
                        "#canvas-container { flex: 1; overflow: auto; display: flex; justify-content: center; " +
                        "align-items: flex-start; background: #f5f5f5; }" +
                        "canvas { max-width: 100%; margin: 10px auto; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }" +
                        "#loading { text-align: center; padding: 20px; font-size: 16px; color: #666; }" +
                        "#error { color: #d32f2f; padding: 20px; text-align: center; }" +
                        "</style>" +
                    "</head>" +
                    "<body>" +
                        "<div id='container'>" +
                            "<div id='toolbar'>" +
                                "<button id='prev' onclick='prevPage()'>← Pagina precedente</button>" +
                                "<span id='pageInfo'>Pagina <span id='currentPage'>1</span> di <span id='totalPages'>0</span></span>" +
                                "<button id='next' onclick='nextPage()'>Pagina successiva →</button>" +
                                "<input type='range' id='zoomSlider' min='50' max='200' value='100' " +
                                "onchange='setZoom(this.value)' style='flex: 1; max-width: 150px; cursor: pointer;'>" +
                                "<span id='zoomLevel'>100%</span>" +
                            "</div>" +
                            "<div id='canvas-container'>" +
                                "<div id='loading'>Caricamento PDF...</div>" +
                            "</div>" +
                        "</div>" +
                        "<script src='https://cdnjs.cloudflare.com/ajax/libs/pdf.js/3.11.174/pdf.min.js'></script>" +
                        "<script>" +
                        "const pdfjsLib = window['pdfjs-dist/build/pdf'];" +
                        "pdfjsLib.GlobalWorkerOptions.workerSrc = 'https://cdnjs.cloudflare.com/ajax/libs/pdf.js/3.11.174/pdf.worker.min.js';" +
                        "let currentPage = 1;" +
                        "let totalPages = 0;" +
                        "let pdfDoc = null;" +
                        "let currentZoom = 100;" +
                        "const pdfUrl = '" + encodedUrl + "';" +
                        "const container = document.getElementById('canvas-container');" +
                        "" +
                        "function loadPDF() {" +
                        "  pdfjsLib.getDocument(pdfUrl).promise" +
                        "    .then(pdf => {" +
                        "      pdfDoc = pdf;" +
                        "      totalPages = pdf.numPages;" +
                        "      document.getElementById('totalPages').textContent = totalPages;" +
                        "      renderPage(currentPage);" +
                        "    })" +
                        "    .catch(error => {" +
                        "      console.error('Errore caricamento PDF:', error);" +
                        "      container.innerHTML = '<div id=\"error\">Errore nel caricamento del PDF. Prova ad aprirlo nel browser esterno.</div>';" +
                        "    });" +
                        "}" +
                        "" +
                        "function renderPage(pageNum) {" +
                        "  if (!pdfDoc) return;" +
                        "  " +
                        "  if (pageNum < 1 || pageNum > totalPages) return;" +
                        "  currentPage = pageNum;" +
                        "  " +
                        "  pdfDoc.getPage(currentPage).then(page => {" +
                        "    const scale = currentZoom / 100;" +
                        "    const viewport = page.getViewport({ scale });" +
                        "    " +
                        "    const canvas = document.createElement('canvas');" +
                        "    const context = canvas.getContext('2d');" +
                        "    canvas.width = viewport.width;" +
                        "    canvas.height = viewport.height;" +
                        "    " +
                        "    container.innerHTML = '';" +
                        "    container.appendChild(canvas);" +
                        "    " +
                        "    const renderContext = {" +
                        "      canvasContext: context," +
                        "      viewport: viewport" +
                        "    };" +
                        "    " +
                        "    page.render(renderContext).promise.then(() => {" +
                        "      document.getElementById('currentPage').textContent = currentPage;" +
                        "      updateButtons();" +
                        "    });" +
                        "  });" +
                        "}" +
                        "" +
                        "function prevPage() {" +
                        "  if (currentPage > 1) renderPage(currentPage - 1);" +
                        "}" +
                        "" +
                        "function nextPage() {" +
                        "  if (currentPage < totalPages) renderPage(currentPage + 1);" +
                        "}" +
                        "" +
                        "function setZoom(zoomValue) {" +
                        "  currentZoom = parseInt(zoomValue);" +
                        "  document.getElementById('zoomLevel').textContent = currentZoom + '%';" +
                        "  renderPage(currentPage);" +
                        "}" +
                        "" +
                        "function updateButtons() {" +
                        "  document.getElementById('prev').disabled = currentPage <= 1;" +
                        "  document.getElementById('next').disabled = currentPage >= totalPages;" +
                        "}" +
                        "" +
                        "// Pinch-to-zoom support" +
                        "let lastDistance = 0;" +
                        "document.addEventListener('touchmove', function(e) {" +
                        "  if (e.touches.length === 2) {" +
                        "    e.preventDefault();" +
                        "    const touch1 = e.touches[0];" +
                        "    const touch2 = e.touches[1];" +
                        "    const distance = Math.hypot(" +
                        "      touch1.clientX - touch2.clientX," +
                        "      touch1.clientY - touch2.clientY" +
                        "    );" +
                        "    " +
                        "    if (lastDistance > 0) {" +
                        "      if (distance > lastDistance) {" +
                        "        setZoom(Math.min(200, currentZoom + 10));" +
                        "        document.getElementById('zoomSlider').value = Math.min(200, currentZoom + 10);" +
                        "      } else if (distance < lastDistance) {" +
                        "        setZoom(Math.max(50, currentZoom - 10));" +
                        "        document.getElementById('zoomSlider').value = Math.max(50, currentZoom - 10);" +
                        "      }" +
                        "    }" +
                        "    lastDistance = distance;" +
                        "  }" +
                        "}, { passive: false });" +
                        "" +
                        "document.addEventListener('touchend', function() {" +
                        "  lastDistance = 0;" +
                        "});" +
                        "" +
                        "// Carica il PDF al caricamento della pagina" +
                        "window.addEventListener('load', loadPDF);" +
                        "</script>" +
                    "</body>" +
                "</html>";
    }

    public void downloadPDFIfNeeded(String pdfUrl, PDFDownloadCallback callback) {
        Handler mainHandler = new android.os.Handler(android.os.Looper.getMainLooper());

        new Thread(() -> {
            try {
                File cacheDir = new File(context.getCacheDir(), CACHE_DIR);
                if (!cacheDir.exists()) cacheDir.mkdirs();

                String filename = hashString(pdfUrl) + ".pdf";
                File cachedFile = new File(cacheDir, filename);

                if (cachedFile.exists() && (System.currentTimeMillis() - cachedFile.lastModified()) < MAX_CACHE_AGE) {
                    Log.d(TAG, "PDF caricato dalla cache: " + filename);
                    mainHandler.post(() -> callback.onSuccess(cachedFile.getAbsolutePath()));
                    return;
                }

                Log.d(TAG, "Scaricando PDF da: " + pdfUrl);
                HttpURLConnection connection = (HttpURLConnection) new URL(pdfUrl).openConnection();
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(15000);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try (InputStream input = connection.getInputStream();
                         FileOutputStream output = new FileOutputStream(cachedFile)) {

                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = input.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }
                        output.flush();

                        Log.d(TAG, "PDF cachéato con successo: " + filename);
                        mainHandler.post(() -> callback.onSuccess(cachedFile.getAbsolutePath()));
                    }
                }
                else {
                    Log.w(TAG, "Errore download: " + connection.getResponseCode());
                    mainHandler.post(() -> {
                        try {callback.onError(new Exception("HTTP " + connection.getResponseCode()));}
                        catch (IOException e) {throw new RuntimeException(e);}
                    });
                }
            }
            catch (Exception e) {
                Log.e(TAG, "Errore durante il download del PDF", e);
                mainHandler.post(() -> callback.onError(e));
            }
        }).start();
    }

    private String encodeURLForHTML(String url) {
        return url.replace("\\", "\\\\")
            .replace("'", "\\'")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r");
    }

    private String hashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        }
        catch (Exception e) {
            Log.e(TAG, "Errore nell'hash", e);
            return String.valueOf(input.hashCode());
        }
    }

    public void clearOldCache() {
        new Thread(() -> {
            try {
                File cacheDir = new File(context.getCacheDir(), CACHE_DIR);
                if (cacheDir.exists()) {
                    File[] files = cacheDir.listFiles();
                    if (files != null) {
                        long now = System.currentTimeMillis();
                        for (File file : files) {
                            if ((now - file.lastModified()) > MAX_CACHE_AGE)
                                if (file.delete()) Log.d(TAG, "Cache vecchio eliminato: " + file.getName());
                        }
                    }
                }
            }
            catch (Exception e) {Log.e(TAG, "Errore nella pulizia della cache", e);}
        }).start();
    }

    public interface PDFDownloadCallback {
        void onSuccess(String pdfPath);
        void onError(Exception e);
    }
}