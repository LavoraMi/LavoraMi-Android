package com.andreafilice.lavorami;

public class Badge {
    private final String id;
    private final String name;
    private final String description;
    private final int iconResId;
    private final int currentProgress;
    private final int maxProgress;
    private final boolean isUnlocked;

    public Badge(String id, String name, String description, int iconResId, int currentProgress, int maxProgress) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.iconResId = iconResId;
        this.currentProgress = Math.min(Math.max(0, currentProgress), maxProgress);
        this.maxProgress = maxProgress;
        this.isUnlocked = this.currentProgress >= maxProgress;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIconResId() {
        return iconResId;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }
}
