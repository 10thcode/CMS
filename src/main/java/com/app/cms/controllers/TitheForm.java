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
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TitheForm {
    public ImageView cancel_button;
    public Label cancel_button_text;
    public TextField membership_id;
    public TextField amount;
    public ChoiceBox<String> payment_mode;
    public TextField cashier;
    public ImageView save_button;
    public Label save_button_text;

    ObservableList<String> payment_mode_dropdown_elements = FXCollections.observableArrayList("-- Payment Mode --", "Mobile Money", "Cash", "Cheque");
    List<TextField> required_fields = new ArrayList<>();

    public void initialize(){
        payment_mode.getItems().addAll(payment_mode_dropdown_elements);
        payment_mode.setValue("-- Payment Mode --");

        required_fields.add(membership_id);
        required_fields.add(amount);
        required_fields.add(cashier);


    }

    public void save_button_onclick() throws IOException {



        if (isValid()){
            if (isNumeric(amount.getText())){

                String id = membership_id.getText();
                int Amount = Integer.parseInt(amount.getText());
                String choice = payment_mode.getValue().toString();
                String Cashier = cashier.getText();



                if (!payment_mode.getValue().toString().equals("-- Payment Mode --")){


                    String name = getFullName(id);

                    if (!name.equalsIgnoreCase("UNKNOWN")){

                        String alertString = "Name: " + name + "\nAmount: GHS " + String.valueOf(Amount) + "\nPayment mode: " + choice + "." + "\nDate: " + LocalDate.now().toString() + "\nCashier: " + Cashier + "." ;

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,alertString, ButtonType.YES, ButtonType.NO);



                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES){
                            //Store data in the database

                            String result = enterTithe(id, Amount, choice, Cashier);

                            if (result.equalsIgnoreCase("true")){
                                //When data is stored successfully
                                Alert success = new Alert(Alert.AlertType.INFORMATION,"Form has been saved successfully!");
                                success.showAndWait();
                                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forms.fxml"));
                                Scene scene = new Scene(fxmlLoader.load());
                                Stage stage = (Stage) save_button.getScene().getWindow();
                                stage.setScene(scene);
                                stage.setTitle("Home-Forms");
                            }


                        }
                    }



                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please select a valid payment mode.");
                    alert.showAndWait();



                }


            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Only numbers are allowed the amount field!");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill all required fields.");
            alert.showAndWait();
        }



    }

    public void cancel_button_onclick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING,"Are you sure you want to close this form?", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES){
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

    public void cancel_button_onhover() {
        cancel_button.setFitWidth(30);
        cancel_button_text.setVisible(true);
    }

    public void cancel_button_outhover() {
        cancel_button.setFitWidth(50);
        cancel_button_text.setVisible(false);
    }


    //=================HELPER METHODS=============


    public String enterTithe(String id, int amount, String mode, String cashier){

        String result = "";

        DbManipulation manipulation = new DbManipulation();
        String verificationQuery = String.format("SELECT MEMBERSHIP_ID FROM MEMBERSHIP WHERE MEMBERSHIP_ID = '%s'", id);

        try {
            ResultSet set = manipulation.retrieveData(verificationQuery);

            if (set != null) {
                while (set.next()) {
                    result = "true";
                }

            }
        }catch (Exception e){

        }

        String timeValue = "";
        String date = LocalDate.now().toString();

        try {

            timeValue = DateTimeFormatter.ofPattern("hh:mm a").format(LocalTime.now());
//            System.out.println(timeValue);
        }catch (Exception e){

        }

        if (result.equalsIgnoreCase("true")){
            DbManipulation dbManipulation = new DbManipulation();
            String Query = String.format("INSERT INTO TITHE VALUES('%s', '%s', '%s', '%s', '%s', '%s')", date, id, amount, mode, cashier, timeValue);
            try{
                dbManipulation.InsertIntoDb(Query);
            }catch (Exception e){

            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid Membership Id.");
            alert.setTitle("error");
            alert.showAndWait();
        }


        return result;


    }



    public boolean isValid(){

        boolean value = true;

       for (TextField field : required_fields){



           if (field.getText().isEmpty()){
               value = false;
           }
       }

       if (payment_mode.getValue().toString().isEmpty()){
           value = false;
       }


       return value;


    }

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


    public static String getFullName(String Id){

        ResultSet resultSet;

        DbManipulation manipulation = new DbManipulation();

        String Query = String.format("SELECT FIRST_NAME, SURNAME, OTHER_NAME FROM MEMBERSHIP WHERE MEMBERSHIP_ID = '%s'", Id);

        String fullName = "";

        try{

            resultSet = manipulation.retrieveData(Query);

            if (resultSet != null){
                while (resultSet.next()){

                    String firstname = resultSet.getString("FIRST_NAME");
                    String surname = resultSet.getString("SURNAME");
                    String otherName = resultSet.getString("OTHER_NAME");

                     fullName= firstname + " " + surname + " " + otherName;

                }

            }

        }catch (Exception e){
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setContentText("Incorrect Membership Id");
//                alert.showAndWait();
        }

        if (!fullName.isEmpty()){
            return fullName;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Incorrect Membership Id!");
            alert.showAndWait();
            return "UNKNOWN";
        }


    }






}
