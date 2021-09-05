package com.app.cms.controllers;

import com.app.cms.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePassword {

    public TextField username;
    public PasswordField old_password;
    public PasswordField new_password;
    public Button submit_button;
    public Label login;
    public PasswordField confirm_new_password;

    public void submit_button_onclick() throws IOException {
        //Check username and old password from the database

        if (new_password.getText().length() < 8){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters");
            alert.showAndWait();
        }else if (!confirm_new_password.getText().equals(new_password.getText())){
            Alert mismatch = new Alert(Alert.AlertType.ERROR, "Password mismatch");
            mismatch.showAndWait();
        }else {
            //Change password

            Alert success = new Alert(Alert.AlertType.INFORMATION, "Successfully changed password! \nYou can now login with your new password.");
            success.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) login.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
        }
    }

    public void login_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) login.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login");
    }
}
