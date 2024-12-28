package com.cinnamonshake.services;

public class testServices {
    public static void main(String[] args){
        UserMngmnt um = new UserMngmnt();
        System.out.println(um.loginUser("shikhardixit09","cinnamon08"));
        System.out.println(um.getCurrentUser().getBms().getBattery());
    }
}
