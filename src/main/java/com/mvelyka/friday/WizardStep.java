package com.mvelyka.friday;

public enum WizardStep {
    MANUFACTURER("Wähle dein Auto aus"),
    MODEL("Wähle dein Automodell"),
    TYPE("Welche Form hat das Auto?"),
    FUEL("Was tankst du?"),
    HORSE_POWER("Wie viele PS hat dein Auto?"),
    HSN_TSN_ID("Ist dein Auto dabei?");

    private String stepTitle;

    WizardStep(String stepTitle) {
        this.stepTitle = stepTitle;
    }

    public String getTitle() {
        return stepTitle;
    }
}
