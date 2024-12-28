package com.cinnamonshake.models;

public class User {
    private String username;
    private String realname;
    private String password;
    private String emailAddress;
    
    private BMS bms;

    public User(String username,  String password, String realname, String emailAddress) {
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public String toString(){
        return "Username = " +username +"\n"+
               "Realname = " +realname +"\n"+
               "Email Address = " +emailAddress;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public BMS getBms() {
        return bms;
    }
    public void setBms(BMS bms) {
        this.bms = bms;
    }
}