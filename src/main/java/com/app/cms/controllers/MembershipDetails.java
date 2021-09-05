package com.app.cms.controllers;

import com.app.cms.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MembershipDetails {
    public ImageView back_button;
    public ImageView delete_button;
    public Label delete_button_text;
    public ImageView edit_button;
    public Label edit_button_text;
    public Label back_button_text;

    public void back_button_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("membership-records.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Membership Records");
    }

    public void back_button_onhover() {
        back_button.setFitWidth(30);
        back_button_text.setVisible(true);
    }

    public void back_button_outhover() {
        back_button.setFitWidth(50);
        back_button_text.setVisible(false);
    }

    public void edit_button_onhover() {
        edit_button.setFitWidth(30);
        edit_button_text.setVisible(true);
    }

    public void edit_button_outhover() {
        edit_button.setFitWidth(50);
        edit_button_text.setVisible(false);
    }

    public void delete_button_outhover() {
        delete_button.setFitWidth(50);
        delete_button_text.setVisible(false);
    }

    public void delete_button_onhover() {
        delete_button.setFitWidth(30);
        delete_button_text.setVisible(true);
    }

    public void delete_button_onclick() {
    }

    public void edit_button_onclick() {
    }
}
