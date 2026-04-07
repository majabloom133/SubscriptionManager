package com.subscription.management;
// Subscriber is a Data Carrier - only contains data but makes no choices on it's own

// Fields
public class Subscriber {
    private int id;
    private String email;
    private Plan plan;
    private boolean active;
    private int monthsRemaining;

    // Constructor
    public Subscriber(int id, String email, Plan plan, boolean active, int monthsRemaining) {
        this.id = id;
        this.email = email;
        this.plan = plan;
        this.active = active;
        this.monthsRemaining = monthsRemaining;
    }

    // Getters - to read private fields
    public int getId() { return id; }
    public String getEmail() { return email; }
    public Plan getPlan() { return plan; }
    public boolean isActive() { return active; }
    public int getMonthsRemaining() { return monthsRemaining; }


    // Setters - to modify private fields
    public void setActive(boolean active) { this.active = active; }
    public void setMonthsRemaining(int monthsRemaining) { this.monthsRemaining = monthsRemaining; }
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    // toString method - to display object better in console
@Override
    public String toString() {
        return "Subscriber: " + email + " | Plan: " + plan + " | Active: " + active + " | Months: " + monthsRemaining;
}
}
