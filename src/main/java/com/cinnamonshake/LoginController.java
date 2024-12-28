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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController { 
    UserMngmnt um = new UserMngmnt();

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    public void displayStatus(String str){
        statusLabel.setText(str);
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException{
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(username.trim().equals("") || password.trim().equals("")){
            statusLabel.setText("Please fill all the above details then click Login.");
            return;
        }

        String status = um.loginUser(username, password);
        statusLabel.setText(status);
        if (um.getCurrentUser() == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        root = loader.load();

        String bmsResult;
        if(um.getCurrentUser().getBms() == null)
            bmsResult = "You should setup your BMS to view the Graphs.";
        else
            bmsResult = "Click on any of the buttons under View Graph to see the respective graphs";
    
        DashboardController controller = loader.getController();
        controller.setUm(um);
        controller.displayRealname(um.getCurrentUser().getRealname());
        controller.displayEmail(um.getCurrentUser().getEmailAddress());
        controller.displayStatus(bmsResult);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void register(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
