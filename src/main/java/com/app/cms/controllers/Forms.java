package com.app.cms.controllers;

import com.app.cms.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Forms {
    public Label records_tab;
    public Label form_tab;
    public ImageView membership_tab;
    public ImageView offering_tab;
    public ImageView attendance_tab;
    public ImageView tithe_tab;
    public Label membership_tab_text;
    public Label attendance_tab_text;
    public Label offering_tab_text;
    public Label tithe_tab_text;
    public ImageView logout_button;
    public Label logout_button_text;

    public void form_tab_onclick() {

    }

    public void records_tab_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("records.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) records_tab.getScene().getWindow();
        stage.getIcons().add(new Image("file:src/main/resources/com/app/cms/images/icon.png"));
        stage.setScene(scene);
        stage.setTitle("Home-Records");
    }

    public void membership_tab_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("membership-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) membership_tab.getScene().getWindow();
        stage.getIcons().add(new Image("file:src/main/resources/com/app/cms/images/icon.png"));
        stage.setScene(scene);
        stage.setTitle("Membership Form");
    }

    public void offering_tab_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("offering-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) offering_tab.getScene().getWindow();
        stage.getIcons().add(new Image("file:src/main/resources/com/app/cms/images/icon.png"));
        stage.setScene(scene);
        stage.setTitle("Offering-Form");
    }

    public void tithe_tab_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tithe-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) tithe_tab.getScene().getWindow();
        stage.getIcons().add(new Image("file:src/main/resources/com/app/cms/images/icon.png"));
        stage.setScene(scene);
        stage.setTitle("Tithe Form");
    }

    public void attendance_tab_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("attendance-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) attendance_tab.getScene().getWindow();
        stage.getIcons().add(new Image("file:src/main/resources/com/app/cms/images/icon.png"));
        stage.setScene(scene);
        stage.setTitle("Attendance Form");
    }

    public void membership_tab_onhover() {
        membership_tab.setFitWidth(60);
        membership_tab_text.setVisible(true);
    }

    public void membership_tab_outhover() {
        membership_tab.setFitWidth(100);
        membership_tab_text.setVisible(false);
    }

    public void offering_tab_onhover() {
        offering_tab.setFitWidth(60);
        offering_tab_text.setVisible(true);
    }

    public void offering_tab_outhover() {
        offering_tab.setFitWidth(100);
        offering_tab_text.setVisible(false);
    }

    public void tithe_tab_onhover() {
        tithe_tab.setFitWidth(60);
        tithe_tab_text.setVisible(true);
    }

    public void tithe_tab_outhover() {
        tithe_tab.setFitWidth(100);
        tithe_tab_text.setVisible(false);
    }

    public void attendance_tab_onhover() {
        attendance_tab.setFitWidth(60);
        attendance_tab_text.setVisible(true);
    }

    public void attendance_tab_outhover() {
        attendance_tab.setFitWidth(100);
        attendance_tab_text.setVisible(false);
    }

    public void logout_button_onhover() {
        logout_button.setFitWidth(30);
        logout_button_text.setVisible(true);
    }

    public void logout_button_outhover() {
        logout_button.setFitWidth(50);
        logout_button_text.setVisible(false);
    }

    public void logout_button_onclick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) membership_tab.getScene().getWindow();
            stage.getIcons().add(new Image("file:src/main/resources/com/app/cms/images/icon.png"));
            stage.setScene(scene);
            stage.setTitle("Login");
        }
    }
}
