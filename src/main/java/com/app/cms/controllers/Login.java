package com.app.cms.controllers;

import com.app.cms.DbManipulation.DbManipulation;
import com.app.cms.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String user = username.getText();
        String pass = password.getText();
        String verdict = verifyDetails(user, pass);
        if (username.getText().isBlank() || password.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Both 'Username' and 'Password' required.");
            alert.showAndWait();
        } else if (verdict.equalsIgnoreCase("true")) {
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

    //-----------------------HELPER METHODS BELOW----------------------------------

    private String verifyDetails(String user, String pass){

        DbManipulation dbManipulation = new DbManipulation();
        ResultSet r;
        String result = "";

        String query = String.format("SELECT username, password from LOGIN_TABLE WHERE username = '%s' and password = '%s'", user, pass ) ;
        try{

            r = dbManipulation.retrieveData(query);
            if (r != null){
                while (r.next()){
                    result = "true";
                }
            }

        }catch (Exception e){

        }

        return result;


    }

}
