package com.andreafilice.lavorami;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class EventDescriptor {
    protected String title;
    protected String titleIcon;
    protected String typeOfTransport;
    protected String roads;
    protected String[] lines;
    protected String startDate;
    protected String endDate;
    protected String details;
    protected String company;
    protected boolean eventTerminated;
    private static final SimpleDateFormat SERVER_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault());
    private static final SimpleDateFormat DISPLAY_FORMAT = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

    static {SERVER_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));}

    public EventDescriptor(String title, String titleIcon, String typeOfTransport, String roads, String[] lines, String startDate, String endDate, String details, String company) {
        this.title = title;
        this.titleIcon = titleIcon;
        this.typeOfTransport = typeOfTransport;
        this.roads = roads;
        this.lines = lines;
        this.startDate = startDate;
        this.endDate = endDate;
        this.details = details;
        this.company = company;
        this.eventTerminated = (calcolaPercentuale(startDate, endDate) == 100) ? true : false;
    }

    public int getCardImageID() {
        return getTitleIconID(this.titleIcon);
    }

    public int getTitleIconID(String swiftIconName){
        switch(swiftIconName) {
            case "arrow.branch":
                return R.drawable.ic_branch;
            case "clock.fill":
                return R.drawable.ic_clock;
            case "clock.badge.fill":
                return R.drawable.ic_clock_badge;
            case "clock.badge.exclamationmark.fill":
                return R.drawable.ic_clock_exclamation;
            case "exclamationmark.triangle.fill":
                return R.drawable.warning_triangle_filled;
            case "hand.raised.fill":
                return R.drawable.baseline_back_hand_24;
            case "door.sliding.left.hand.closed":
                return R.drawable.ic_elevator;
            case "arrow.trianglehead.2.counterclockwise":
                return R.drawable.ic_counterclockwise;
            case "point.topleft.down.to.point.bottomright.curvepath.fill":
                return R.drawable.ic_extension_line;
            case "mappin.slash":
                return R.drawable.ic_map_slash;
            case "point.bottomleft.forward.to.arrow.triangle.uturn.scurvepath":
                return R.drawable.ic_variazione_corse;
            case "bolt.horizontal.fill":
                return R.drawable.ic_bolt_horizontal;
            default:
                return R.drawable.empty_image;
        }
    }

    public String getTitle() {return title;}
    public String getTypeOfTransport() {return typeOfTransport;}
    public String getRoads() {return roads;}
    public String[] getLines() {return lines;}
    public String getStringLines(){return String.join(", ", lines);}
    public String getStartDate() {return startDate;}
    public String getEndDate() {return endDate;}
    public String getDetails() {return details;}
    public String getCompany() {return company;}
    public boolean isEventTerminated() {return eventTerminated;}

    public static String formattaData(String initialDate) {
        if (initialDate == null) return null;

        try {
            Date finalDate = SERVER_FORMAT.parse(initialDate);
            return DISPLAY_FORMAT.format(finalDate);
        }
        catch (Exception e) {
            e.printStackTrace();
            return initialDate;
        }
    }

    public int calcolaPercentuale(String startDateStr, String endDateStr) {
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

    public long getDateMillis(String dateString) {
        if (dateString == null) return 0;

        try {
            Date date = SERVER_FORMAT.parse(dateString);
            return (date != null) ? date.getTime() : 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
