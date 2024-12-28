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

public class RegisterController {

    private UserMngmnt um = new UserMngmnt();

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    
    @FXML private Label statusLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void register(ActionEvent event) throws IOException{
        String username = usernameField.getText();
        String password = passwordField.getText();
        String realname = firstNameField.getText() + " " +lastNameField.getText();
        String email = emailField.getText();

        if(username.trim().equals("") || password.trim().equals("") || realname.trim().equals("") || email.trim().equals("")){
            statusLabel.setText("Please fill all the above details then click Register.");
            return;
        }

        String status = um.registerUser(username, realname, password, email);
        statusLabel.setText(status);
        if (um.getCurrentUser() == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        root = loader.load();

        DashboardController controller = loader.getController();
        controller.setUm(um);
        controller.displayRealname(um.getCurrentUser().getRealname());
        controller.displayEmail(um.getCurrentUser().getEmailAddress());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void login(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
