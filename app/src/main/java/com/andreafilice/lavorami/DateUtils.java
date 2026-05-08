package com.andreafilice.lavorami;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    private static final ThreadLocal<SimpleDateFormat> SDF = ThreadLocal.withInitial(() -> {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+01:00'", Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format;
    });

    public static long toMillis(String dateString) {
        if (dateString == null) return 0;

        try {
            Date date = SDF.get().parse(dateString);
            return date != null ? date.getTime() : 0;
        }
        catch (Exception e) { return 0; }
    }
}
