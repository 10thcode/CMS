package com.app.cms.controllers;

import com.app.cms.DbManipulation.DbManipulation;
import com.app.cms.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class OfferingForm {
    public ImageView cancel_button;
    public ImageView save_button;
    public Label save_button_text;
    public Label cancel_button_text;
    public DatePicker date;
    public ChoiceBox<String> service;
    public TextField amount;

    DbManipulation dbManipulation = new DbManipulation();

    ObservableList<String> service_dropdown_elements = FXCollections.observableArrayList("-- Service --","First Service",
            "Second Service", "Third Service", "Fourth Service");
    public void initialize(){
        service.getItems().addAll(service_dropdown_elements);
        service.setValue("-- Service --");
        date.getEditor().setText(LocalDate.now().toString());
    }

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


        if (isNumeric(amount.getText()) && date.getEditor().getText() != null && !service.getValue().equalsIgnoreCase("-- Service --")){


            String Date = date.getEditor().getText();
            String Service = service.getValue().toString();
            int Amount = Integer.parseInt(amount.getText());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to save this form?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES){
                //Store data in the database

                insertOffering(Date, Service, Amount);

                //When data is stored successfully
                Alert success = new Alert(Alert.AlertType.INFORMATION,"Form has been saved successfully!");
                success.showAndWait();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forms.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) save_button.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Home-Forms");
            }


        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please enter valid data");
            alert.showAndWait();
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

    //--------------HELPER METHODS--------------------

    public static boolean isNumeric(String toBeChecked) {
        if (toBeChecked == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(toBeChecked);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }


    private void insertOffering(String date, String service, int amount){

        String Query = "INSERT INTO OFFERING VALUES(" + "'" + date + "'" + ", "+ "'" + service + "'" + ", "+ amount + ") " ;
        try {
            dbManipulation.InsertIntoDb(Query);
        }catch (Exception e){
//            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Could not insert data");
            alert.showAndWait();
        }

    }




}
