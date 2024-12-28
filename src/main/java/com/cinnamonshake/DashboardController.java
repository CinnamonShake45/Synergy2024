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
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class DashboardController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Label realnameLabel;
    @FXML private Label emailLabel;
    @FXML private Label statusLabel;
    @FXML private WebView graphWebView;

    public void displayStatus(String str){
        statusLabel.setText(str);
    }
    public void displayRealname(String str){
        realnameLabel.setText(str);
    }
    public void displayEmail(String str){
        emailLabel.setText(str);
    }


    private UserMngmnt um = new UserMngmnt();

    public UserMngmnt getUm() {
        return um;
    }
    public void setUm(UserMngmnt um) {
        this.um = um;
    }

    public void updatepass(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updatePassword.fxml"));
        root = loader.load();        

        UpdatePasswordController controller = loader.getController();
        controller.setUm(um);
        controller.displayRealname(um.getCurrentUser().getRealname());
        controller.displayEmail(um.getCurrentUser().getEmailAddress());
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void setupbms(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BMSsetup.fxml"));
        root = loader.load();        

        String bmsResult;
        if(um.getCurrentUser().getBms() == null)
            bmsResult = "Welcome to BMS Setup screen.";
        else
            bmsResult = "If you wish to change only the battery details, then use your current BMS id.";

        BMSsetupController controller = loader.getController();
        controller.setUm(um);
        controller.displayRealname(um.getCurrentUser().getRealname());
        controller.displayEmail(um.getCurrentUser().getEmailAddress());
        controller.displayStatus(bmsResult);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();        

        LoginController controller = loader.getController();
        controller.displayStatus("Successfully logged out!");
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void viewSoC(ActionEvent event) throws IOException{
        if(um.getCurrentUser().getBms() == null){
            statusLabel.setText("The Graph couldn't be displayed as the BMS is not setup!");
            return;
        }

        String channelID = um.getCurrentUser().getBms().getBmsID()+""; 
        String title = "Battery+State+Of+Charge";
        String fieldNum = "5";
        String html = "<html><head><title>Iframe Example</title></head><body><iframe width=\"450\" height=\"260\" style=\"border: 1px solid #cccccc;\" src=\"https://thingspeak.com/channels/"+channelID+"/charts/"+fieldNum+"?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title="+title+"\"></iframe></body></html>";
        graphWebView.getEngine().loadContent(html);
        
        displayStatus("Currently Displaying graph for Battery State of Charge.");
    }
    public void viewBatVolt(ActionEvent event) throws IOException{
        
        if(um.getCurrentUser().getBms() == null){
            statusLabel.setText("The Graph couldn't be displayed as the BMS is not setup!");
            return;
        }

        String channelID = um.getCurrentUser().getBms().getBmsID()+""; 
        String title = "Battery+Voltage";
        String fieldNum = "4";
        String html = "<html><head><title>Iframe Example</title></head><body><iframe width=\"450\" height=\"260\" style=\"border: 1px solid #cccccc;\" src=\"https://thingspeak.com/channels/"+channelID+"/charts/"+fieldNum+"?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title="+title+"\"></iframe></body></html>";
        graphWebView.getEngine().loadContent(html);
        
        displayStatus("Currently Displaying graph for Battery Voltage.");
    }
    public void viewCharVolt(ActionEvent event) throws IOException{
        
        if(um.getCurrentUser().getBms() == null){
            statusLabel.setText("The Graph couldn't be displayed as the BMS is not setup!");
            return;
        }

        String channelID = um.getCurrentUser().getBms().getBmsID()+""; 
        String title = "Charger+Voltage";
        String fieldNum = "3";
        String html = "<html><head><title>Iframe Example</title></head><body><iframe width=\"450\" height=\"260\" style=\"border: 1px solid #cccccc;\" src=\"https://thingspeak.com/channels/"+channelID+"/charts/"+fieldNum+"?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title="+title+"\"></iframe></body></html>";
        graphWebView.getEngine().loadContent(html);
        
        displayStatus("Currently Displaying graph for Charger Voltage.");
    }
    public void viewLoadCurr(ActionEvent event) throws IOException{
        
        if(um.getCurrentUser().getBms() == null){
            statusLabel.setText("The Graph couldn't be displayed as the BMS is not setup!");
            return;
        }

        String channelID = um.getCurrentUser().getBms().getBmsID()+""; 
        String title = "Load+Current";
        String fieldNum = "2";
        String html = "<html><head><title>Iframe Example</title></head><body><iframe width=\"450\" height=\"260\" style=\"border: 1px solid #cccccc;\" src=\"https://thingspeak.com/channels/"+channelID+"/charts/"+fieldNum+"?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title="+title+"\"></iframe></body></html>";
        graphWebView.getEngine().loadContent(html);

        
        displayStatus("Currently Displaying graph for Load Current.");
    }
    public void viewCharCurr(ActionEvent event) throws IOException{
        
        if(um.getCurrentUser().getBms() == null){
            statusLabel.setText("The Graph couldn't be displayed as the BMS is not setup!");
            return;
        }

        String channelID = um.getCurrentUser().getBms().getBmsID()+""; 
        String title = "Charger+Current";
        String fieldNum = "1";
        String html = "<html><head><title>Iframe Example</title></head><body><iframe width=\"450\" height=\"260\" style=\"border: 1px solid #cccccc;\" src=\"https://thingspeak.com/channels/"+channelID+"/charts/"+fieldNum+"?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title="+title+"\"></iframe></body></html>";
        graphWebView.getEngine().loadContent(html);

        displayStatus("Currently Displaying graph for Charger Current.");
    }

}
