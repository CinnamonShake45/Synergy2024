package com.cinnamonshake;
import com.cinnamonshake.services.UserMngmnt;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BMSsetupController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML private Label realnameLabel;
    @FXML private Label emailLabel;

    @FXML private TextField bmsidField;
    @FXML private TextField capacityField;
    @FXML private TextField cyclesField;
    @FXML private TextField typeField;
    @FXML private TextField companyField;

    @FXML private Label statusLabel;

    public void displayRealname(String str){
        realnameLabel.setText(str);
    }
    public void displayEmail(String str){
        emailLabel.setText(str);
    }
    public void displayStatus(String str){
        statusLabel.setText(str);
    }

    private UserMngmnt um = new UserMngmnt();

    public UserMngmnt getUm() {
        return um;
    }

    public void setUm(UserMngmnt um) {
        this.um = um;
    }

    public void setupDone(ActionEvent event) throws IOException{
        
        String bmsidString = bmsidField.getText();
        String capacityString = capacityField.getText();
        String cyclesString = cyclesField.getText();
        String typeString = typeField.getText();
        String companyString = companyField.getText();
        long bmsidLong; double capacityDouble; int cyclesInt;

        try{ 
            bmsidLong = Long.parseLong(bmsidString);
            capacityDouble = Double.parseDouble(capacityString);
            cyclesInt = Integer.parseInt(cyclesString); 
        } catch (NumberFormatException e) { 
            statusLabel.setText("Make sure only numerical values entered in BMS ID, Capacity and Rated Cycles of Battery.");
            return;
        }

        if(bmsidString.trim().equals("") || capacityString.trim().equals("") || cyclesString.trim().equals("") ||
         typeString.trim().equals("") || companyString.trim().equals("")) {

            statusLabel.setText("Please fill all the above details before clicking Done.");
            return;
        }

        String result = um.BMSsetup(bmsidLong, capacityDouble, cyclesInt, typeString, companyString);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        root = loader.load();

        DashboardController controller = loader.getController();
        controller.setUm(um);
        controller.displayRealname(um.getCurrentUser().getRealname());
        controller.displayEmail(um.getCurrentUser().getEmailAddress());
        controller.displayStatus(result);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cancelsetup(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        root = loader.load();

        DashboardController controller = loader.getController();
        controller.setUm(um);
        controller.displayRealname(um.getCurrentUser().getRealname());
        controller.displayEmail(um.getCurrentUser().getEmailAddress());
        controller.displayStatus("BMS Setup canceled.");

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
