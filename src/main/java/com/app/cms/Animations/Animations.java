package com.app.cms.Animations;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Animations {

    public static void OnMouseEnteredForButtons(Button button){

        button.setStyle("-fx-background-color: rgb(100, 200, 50)");

    }

    public static void OnMouseExitedForButtons(Button button){

        button.setStyle("-fx-background-color:  #00bcd4");

    }

    public static void OnClickForTextFields(TextField textField){

        textField.setStyle("-fx-background-color:  #00bcd4");

    }


    public static void OnMouseEnteredForTextFields(TextField textField){

//        textField.setStyle("-fx-background-color: rgb(100, 200, 50)");
        textField.setStyle("-fx-border-width: 4px");
        textField.setStyle("-fx-border-color: rgb(100, 200, 50)");
        textField.setStyle("-fx-border-radius: 5px");
        textField.setStyle("-fx-background-radius: 5px");

    }

    public static void OnMouseExitedForTextFields(TextField textField){

        textField.setStyle("-fx-border-width: 4px");
        textField.setStyle("-fx-border-color:  #00bcd4");
        textField.setStyle("-fx-border-radius: 5px");
        textField.setStyle("-fx-background-radius: 5px");

    }




}
