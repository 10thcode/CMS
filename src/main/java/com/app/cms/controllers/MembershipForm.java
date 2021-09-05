package com.app.cms.controllers;

import com.app.cms.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MembershipForm {
    public ImageView cancel_button;
    public ImageView save_button;
    public Label save_button_text;
    public Label cancel_button_text;

    public void cancel_button_onclick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Do you want to close this form?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forms.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) cancel_button.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Home-Forms");
        }
    }

    public void save_button_onhover() {
        save_button.setFitWidth(30);
        save_button_text.setVisible(true);

    }

    public void save_button_outhover() {
        save_button.setFitWidth(50);
        save_button_text.setVisible(false);
    }

    public void save_button_onclick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to save this form?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES){
            //Store data in the database

            //When data is stored successfully
            Alert success = new Alert(Alert.AlertType.INFORMATION,"Form has been saved successfully!");
            success.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forms.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) cancel_button.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Home-Forms");
        }
    }

    public void cancel_button_onhover() {
        cancel_button.setFitWidth(30);
        cancel_button_text.setVisible(true);
    }

    public void cancel_button_outhover() {
        cancel_button.setFitWidth(50);
        cancel_button_text.setVisible(false);
    }
}
