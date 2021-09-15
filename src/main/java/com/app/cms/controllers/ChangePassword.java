package com.app.cms.controllers;

import com.app.cms.Animations.Animations;
import com.app.cms.DbManipulation.DbManipulation;
import com.app.cms.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class ChangePassword {

    public TextField username;
    public PasswordField old_password;
    public PasswordField new_password;
    public Button submit_button;
    public Label login;
    public PasswordField confirm_new_password;

    public void submit_button_onclick() throws IOException {
        //Check username and old password from the database

        String user = username.getText();
        String pass = old_password.getText();
        String newPass = new_password.getText();

        String verdict = checkCredentials(user, pass, newPass);

        if (new_password.getText().length() < 6){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters");
            alert.showAndWait();
        }else if (!confirm_new_password.getText().equals(new_password.getText()) && !verdict.equals("success")){
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

    //-----------------------HELPER METHODS---------------------------------

    private String checkCredentials(String user, String pass, String nPass){


        DbManipulation dbManipulation = new DbManipulation();
        ResultSet r;
        String result = "";

        String query = String.format("SELECT username, password from LOGIN_TABLE WHERE username = '%s' and password = '%s'", user, pass ) ;
        try{

            r = dbManipulation.retrieveData(query);
            if (r != null){
                while (r.next()){
                    String QueryToUpdate = String.format("UPDATE LOGIN_TABLE SET password = '%s' WHERE username = '%s'", nPass, user);
                    dbManipulation.InsertIntoDb(QueryToUpdate);
                    result = "success";
                }
            }

        }catch (Exception e){

        }

        return result;

    }

    public void SubmitEntered(MouseEvent mouseEvent) {
       Animations.OnMouseEnteredForButtons(submit_button);
    }

    public void SubmitExited(MouseEvent mouseEvent) {
        Animations.OnMouseExitedForButtons(submit_button);
    }
}
