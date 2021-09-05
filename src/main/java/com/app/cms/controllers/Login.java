package com.app.cms.controllers;

import com.app.cms.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    public TextField username;
    public PasswordField password;
    public Label change_password;
    public Button login_button;

    public void change_password_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("change-password.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) login_button.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Change Password");
    }

    public void login_button_onclick() throws IOException {
        if (username.getText().isBlank() || password.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Both 'Username' and 'Password' required.");
            alert.showAndWait();
        } else if (username.getText().equals("admin") && password.getText().equals("admin")) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forms.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) login_button.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Home-Forms");
        } else {
            Alert incorrect = new Alert(Alert.AlertType.ERROR, "Incorrect username or password");
            incorrect.showAndWait();
        }
    }
}
