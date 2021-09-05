package com.app.cms.controllers;

import com.app.cms.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class OfferingRecords {
    public ImageView home_button;
    public Label home_button_text;

    public void home_button_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("records.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) home_button.getScene().getWindow();
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.setTitle("Home-Records");
        stage.setResizable(false);
    }

    public void home_button_onhover() {
        home_button.setFitWidth(30);
        home_button_text.setVisible(true);
    }

    public void home_button_outhover() {
        home_button.setFitWidth(50);
        home_button_text.setVisible(false);
    }
}
