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
import javafx.stage.Stage;

public class UpdatePasswordController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Label realnameLabel;
    @FXML private Label emailLabel;
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

    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;

    public void updatepass(ActionEvent event) throws IOException{
        String oldpassString = oldPasswordField.getText();
        String newpassString = newPasswordField.getText();

        if(oldpassString.trim().equals("") || newpassString.trim().equals("")){
            displayStatus("Please make sure the Password Fields are not empty or have whitespaces only and try again.");
            return;
        }
        if(oldpassString.equals(newpassString)){
            displayStatus("New Password is same as old one, please choose a different password or press Cancel.");
            return;
        }
        if(!oldpassString.equals(um.getCurrentUser().getPassword())){
            displayStatus("Please make sure you are entering your current password in Old Password field, then try again.");
            return;
        }

        String result = um.passwordReset(oldpassString, newpassString);

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
    public void cancel(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        root = loader.load();

        DashboardController controller = loader.getController();
        controller.setUm(um);
        controller.displayRealname(um.getCurrentUser().getRealname());
        controller.displayEmail(um.getCurrentUser().getEmailAddress());
        controller.displayStatus("Password change canceled.");

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
