package com.example.habbittracker;

public class Statistic {
    private int maxStrike;
    private int daysInTracker;
    private int habitsComplete;
    private int currentStrike;
    public boolean checkDay = false;
    public boolean checkStrike = false;

    Statistic(int maxStrike, int daysInTracker, int habitsComplete, int currentStrike) {
        this.maxStrike = maxStrike;
        this.daysInTracker = daysInTracker;
        this.habitsComplete = habitsComplete;
        this.currentStrike = currentStrike;
    }
    Statistic() {
        this.maxStrike = 0;
        this.daysInTracker = 0;
        this.habitsComplete = 0;
        this.currentStrike = 0;
    }

    public void setDaysInTracker(int daysInTracker) {
        this.daysInTracker = daysInTracker;
    }

    public void setHabitsComplete(int habitsComplete) {
        this.habitsComplete = habitsComplete;
    }

    public void setMaxStrike(int maxStrike) {
        this.maxStrike = maxStrike;
    }

    public int getDaysInTracker() {
        return daysInTracker;
    }

    public int getHabitsComplete() {
        return habitsComplete;
    }

    public int getMaxStrike() {
        return maxStrike;
    }

    public void setCurrentStrike(int currentStrike) {
        this.currentStrike = currentStrike;
    }

    public int getCurrentStrike() {
        return currentStrike;
    }
}
