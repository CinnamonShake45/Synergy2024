package com.cinnamonshake.models;

public class BMS {

    private long bmsID;
    private Battery battery;
    private String feedLocation;
    
    public BMS(long bmsID){
        this.bmsID = bmsID;
        feedLocation = "/data/Battery Data/"+bmsID+"/feed.csv";
    }

    @Override
    public String toString(){
        return String.valueOf(bmsID);
    }
    public long getBmsID() {
        return bmsID;
    }
    public void setBmsID(long bmsID) {
        this.bmsID = bmsID;
    }
    public Battery getBattery() {
        return battery;
    }
    public void setBattery(Battery battery) {
        this.battery = battery;
    }
    public String getFeedLocation() {
        return feedLocation;
    }
    public void setFeedLocation(String feedLocation) {
        this.feedLocation = feedLocation;
    }
}