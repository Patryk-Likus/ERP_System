package sample.controller;

import sample.factory.PopupFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private PopupFactory popupFactory;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    public LoginController(){
         popupFactory = new PopupFactory();
    }

    public void initialize(URL location, ResourceBundle resources) {
        initializeExitButton();
        initializeLoginButton();
    }

    private void initializeLoginButton() {
        loginButton.setOnAction((x) ->{
            performAuthentication();
        });
    }

    private void performAuthentication() {
        Stage waitingPopUp = popupFactory.createWaitingPopUp("Connecting to the server...");
        waitingPopUp.show();
        String login = loginTextField.getText();
        String password = passwordTextField.getText();

        System.out.println("login " + login + " password " + password);
    }

    private void initializeExitButton() {
        exitButton.setOnAction((x) -> {
            getStage().close();
        });
    }

    private Stage getStage(){
        return (Stage)loginAnchorPane.getScene().getWindow();
    }
}
