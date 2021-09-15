package com.app.cms.controllers;

import com.app.cms.DbManipulation.DbManipulation;
import com.app.cms.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class AttendanceRecords {
    public ImageView home_button;
    public Label home_button_text;
    public TableView<AttendanceTableModel> AttendanceTable;
    public TableColumn<AttendanceTableModel, String> date;
    public TableColumn<AttendanceTableModel, String> service;
    public TableColumn<AttendanceTableModel, Integer> attendance;


    public ObservableList<AttendanceTableModel> data = FXCollections.observableArrayList();

    @FXML
    public void initialize(){

        date.setCellValueFactory( new PropertyValueFactory<>("DateValue"));
        service.setCellValueFactory( new PropertyValueFactory<>("ServiceChoice"));
        attendance.setCellValueFactory( new PropertyValueFactory<>("Attendance"));


        AttendanceTable.setItems(data);

    }

    public void home_button_onclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("records.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) home_button.getScene().getWindow();
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.setTitle("Home-Records");
        stage.setResizable(false);
    }

    public void home_button_onhover() {
        home_button.setFitWidth(30);
        home_button_text.setVisible(true);
    }

    public void home_button_outhover() {
        home_button.setFitWidth(50);
        home_button_text.setVisible(false);
    }

    public AttendanceRecords() {

        DbManipulation dbManipulation = new DbManipulation();

        String Query = "SELECT * FROM ATTENDANCE";
        try{
            ResultSet resultSet = dbManipulation.retrieveData(Query);

            if (resultSet != null){

                while(resultSet.next()){

                    String date = resultSet.getString("DATE");
                    String service = resultSet.getString("SERVICE");
                    int number = resultSet.getInt("NUMBER");

                    data.addAll( new AttendanceTableModel(date, service, number));


                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //==================================TABLE MODEL CLASS==========================================


    public static class AttendanceTableModel {

        private SimpleStringProperty DateValue;
        private SimpleStringProperty ServiceChoice;
        private SimpleIntegerProperty Attendance;



        public AttendanceTableModel(String dateValue, String serviceChoice, int attendance) {

            DateValue = new SimpleStringProperty(dateValue);
            ServiceChoice = new SimpleStringProperty(serviceChoice);
            Attendance = new SimpleIntegerProperty(attendance);

        }

        public String getDateValue() {
            return DateValue.get();
        }

        public SimpleStringProperty dateValueProperty() {
            return DateValue;
        }

        public void setDateValue(String dateValue) {
            this.DateValue.set(dateValue);
        }

        public String getServiceChoice() {
            return ServiceChoice.get();
        }

        public SimpleStringProperty serviceChoiceProperty() {
            return ServiceChoice;
        }

        public void setServiceChoice(String serviceChoice) {
            this.ServiceChoice.set(serviceChoice);
        }

        public int getAttendance() {
            return Attendance.get();
        }

        public SimpleIntegerProperty attendanceProperty() {
            return Attendance;
        }

        public void setAttendance(int attendance) {
            this.Attendance.set(attendance);
        }
    }


}
