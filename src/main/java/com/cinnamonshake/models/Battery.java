package com.cinnamonshake.models;
public class Battery {

    private long batteryID;
    private double batteryCapacity;
    private int ratedCycles;
    private String batteryType;
    private String batteryCompany;
    
    public Battery(long batteryID, double batteryCapacity, int ratedCycles, String batteryType, String batteryCompany) {
        this.batteryID = batteryID;
        this.batteryCapacity = batteryCapacity;
        this.ratedCycles = ratedCycles;
        this.batteryType = batteryType;
        this.batteryCompany = batteryCompany;
    }

    @Override
    public String toString(){
        return "Battery ID: "+batteryID
            +"\nBattery Capacity: "+batteryCapacity
            +"\nBattery Rated Cycles: "+ratedCycles
            +"\nBattery Type: "+batteryType
            +"\nBattery Company: "+batteryCompany;
    }
    public long getBatteryID() {
        return batteryID;
    }
    public void setBatteryID(int batteryID) {
        this.batteryID = batteryID;
    }
    public double getBatteryCapacity() {
        return batteryCapacity;
    }
    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
    public int getRatedCycles() {
        return ratedCycles;
    }
    public void setRatedCycles(int ratedCycles) {
        this.ratedCycles = ratedCycles;
    }
    public String getBatteryType() {
        return batteryType;
    }
    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }
    public String getBatteryCompany() {
        return batteryCompany;
    }
    public void setBatteryCompany(String batteryCompany) {
        this.batteryCompany = batteryCompany;
    }
}