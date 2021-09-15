package com.app.cms.controllers;

import com.app.cms.DbManipulation.DbManipulation;
import com.app.cms.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class MembershipRecords {

    public ImageView home_button;
    public Label home_button_text;
    public TextField search_bar;
    public ImageView search_button;
    public ListView<String> listView;

    public ObservableList<String> listOfMembers = FXCollections.observableArrayList();

    @FXML
    public void initialize(){

        showMemberDetails();
        listView.getItems().addAll(listOfMembers);

    }


    public void home_button_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("records.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) home_button.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home-Records");
    }

    public void home_button_onhover() {
        home_button.setFitWidth(30);
        home_button_text.setVisible(true);
    }

    public void home_button_outhover() {
        home_button.setFitWidth(50);
        home_button_text.setVisible(false);
    }

    //==================================HELPER METHODS==============================


    public void showMemberDetails(){

        DbManipulation dbManipulation = new DbManipulation();
        ResultSet resultSet;

        String Query = "SELECT * FROM MEMBERSHIP";
        try{

            resultSet = dbManipulation.retrieveData(Query);
            if (resultSet != null){
                while (resultSet.next()){

                    String id = resultSet.getString("MEMBERSHIP_ID");
                    String fName = resultSet.getString("FIRST_NAME");
                    String lName = resultSet.getString("SURNAME");
                    String oName = resultSet.getString("OTHER_NAME");
                    String dob = resultSet.getString("DATE_OF_BIRTH");
                    String number = resultSet.getString("PHONE_NUMBER");
                    String residence = resultSet.getString("RESIDENCE");

                    listView.getItems().clear();

                    listOfMembers.addAll(fName + "  " + lName ) ;

//                    listView.getItems().addAll(listOfMembers);


                }
            }

        }catch (Exception e){
//            e.printStackTrace();
        }


    }

    

    public void showMembers(){



        DbManipulation dbManipulation = new DbManipulation();
        ResultSet resultSet;

        String Query = "SELECT * FROM MEMBERSHIP";
        try{

            resultSet = dbManipulation.retrieveData(Query);
            if (resultSet != null){
                while (resultSet.next()){

                    String id = resultSet.getString("MEMBERSHIP_ID");
                    String fName = resultSet.getString("FIRST_NAME");
                    String lName = resultSet.getString("SURNAME");
                    String oName = resultSet.getString("OTHER_NAME");
                    String dob = resultSet.getString("DATE_OF_BIRTH");
                    String number = resultSet.getString("PHONE_NUMBER");
                    String residence = resultSet.getString("RESIDENCE");





                    listView.getItems().addAll(fName + " " + lName);


                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void search_list(MouseEvent mouseEvent) {

//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Hello");
//        alert.setContentText("Can you see me?");
//        alert.showAndWait();

//        listView.getItems().add("me");

        if (search_bar.getText().isEmpty()){

            listOfMembers.clear();

            listView.getItems().clear();

            showMembers();



        }else {


            DbManipulation dbManipulation = new DbManipulation();
            ResultSet resultSet;
            String searchItem = search_bar.getText();

            String Query = String.format("SELECT * FROM MEMBERSHIP WHERE MEMBERSHIP_ID = '%S'", searchItem);

            try{

                resultSet = dbManipulation.retrieveData(Query);

                if (resultSet != null){


                    listView.getItems().clear();
                    listOfMembers.clear();

                    while (resultSet.next()){
                        String id = resultSet.getString("MEMBERSHIP_ID");
                        String fName = resultSet.getString("FIRST_NAME");
                        String lName = resultSet.getString("SURNAME");
                        String oName = resultSet.getString("OTHER_NAME");
                        String dob = resultSet.getString("DATE_OF_BIRTH");
                        String number = resultSet.getString("PHONE_NUMBER");
                        String residence = resultSet.getString("RESIDENCE");



                        listOfMembers.addAll( fName + "  " + lName) ;
                        listView.getItems().addAll(listOfMembers);
                    }
                }


            }catch (Exception e){
//                e.printStackTrace();
            }
        }





    }
}
