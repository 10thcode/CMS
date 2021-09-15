package com.app.cms.controllers;

import com.app.cms.Main;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Splashscreen extends Thread   {


    @FXML
    private AnchorPane pane;



    public void initialize(){

        this.start();

    }

    @Override
    public void run(){

        try {

            sleep(3000);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//        stage.setTitle("Login");
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image("file:src/main/resources/com/app/cms/images/icon.png"));
                    stage.setScene(scene);
                    stage.setResizable(false);
//                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();

                    pane.getScene().getWindow().hide();
                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }


    }




    }




