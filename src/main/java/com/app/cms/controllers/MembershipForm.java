package com.app.cms.controllers;

import com.app.cms.Animations.Animations;
import com.app.cms.DbManipulation.DbManipulation;
import com.app.cms.Main;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MembershipForm {
    public ImageView cancel_button;
    public ImageView save_button;
    public Label save_button_text;
    public Label cancel_button_text;
    public TextField FirstName;
    public TextField MembershipId;
    public TextField Surname;
    public TextField OtherName;
    public DatePicker DateOfBirth;
    public TextField PhoneNumber;
    public TextField Residence;



    List<TextField> required_fields = new ArrayList<>();

    int getValue = 0;

    @FXML
    public void initialize(){

        required_fields.add(MembershipId);
        required_fields.add(FirstName);
        required_fields.add(Surname);
        required_fields.add(OtherName);
        required_fields.add(PhoneNumber);
        required_fields.add(Residence);

        MembershipId.setText(generateId());


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


        if (isValid()){

            if (isValidMobileNo(PhoneNumber.getText())){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to save this form?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES){
                    //Store data in the database

                    String memId = MembershipId.getText();
                    String fName = FirstName.getText();
                    String surname = Surname.getText();
                    String oName = OtherName.getText();
                    String dob = DateOfBirth.getEditor().getText();
                    String pNumber = PhoneNumber.getText();
                    String residence = Residence.getText();

                    insertIntoMembership(memId, fName, surname, oName, dob, pNumber, residence);

                    //When data is stored successfully
                    Alert success = new Alert(Alert.AlertType.INFORMATION,"Form has been saved successfully!");
                    success.showAndWait();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forms.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = (Stage) cancel_button.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Home-Forms");
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter a valid phone number");
                alert.showAndWait();
            }


        }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please fill required fields");
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



    //===================================HELPER METHODS============================

    public boolean isValid(){

        boolean valid_state = true;
        for (TextField field: required_fields){

            if (field.getText().isEmpty()){
                valid_state = false;
            }

        }

        valid_state = !DateOfBirth.getEditor().getText().isEmpty();


        return valid_state;
    }


    public static boolean isValidMobileNo(String str)
    {

        Pattern ptrn = Pattern.compile("(0/91)?[0-9][0-9]{9}");

        Matcher match = ptrn.matcher(str);

        return (match.find() && match.group().equals(str));
    }

    public void insertIntoMembership(String id, String fName, String sName, String oName, String Dob, String pNumber, String residence){



        DbManipulation dbManipulation = new DbManipulation();
        String Query = "INSERT INTO MEMBERSHIP VALUES(" + "'" + id + "'" + ", " + "'" + fName + "'" + ", " + "'" + sName + "'" + ", " + "'" + oName + "'" + ", " + "'" + Dob + "'" + ", " + "'" + pNumber + "'" + ", " + "'" + residence + "'" + ")";
        try{
            dbManipulation.InsertIntoDb(Query);
        }catch (Exception e){

        }

    }


    public void generateNumberFromDb(String PassQuery){

        try{

            DbManipulation dbManipulation = new DbManipulation();
            ResultSet set = dbManipulation.retrieveData(PassQuery);

            if (set != null){
                while (set.next()){

                    getValue = Integer.parseInt(set.getString(1));

                }
            }

        }catch (Exception e){
//            e.printStackTrace();
        }

    }

    public String generateId(){

        generateNumberFromDb("SELECT COUNT(MEMBERSHIP_ID) +1 FROM MEMBERSHIP");

        String id = "MEM" + new SimpleDateFormat("ddMMyyy").format(new Date()) + getValue;

        return id;

    }


}
