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

import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.sql.ResultSet;

public class TitheRecords {
    public ImageView home_button;
    public Label home_button_text;
    public TableView<TitheTableModel> TitheTable;
    public TableColumn<TitheTableModel, String> date;
    public TableColumn<TitheTableModel, String> member;
    public TableColumn<TitheTableModel, Integer> amount;
    public TableColumn<TitheTableModel, String> paymentMode;
    public TableColumn<TitheTableModel, String> officer;
    public TableColumn<TitheTableModel, String> time;

    public ObservableList<TitheTableModel> data = FXCollections.observableArrayList();


    @FXML
    public void initialize(){





        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        member.setCellValueFactory(new PropertyValueFactory<>("Member"));
        amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        paymentMode.setCellValueFactory(new PropertyValueFactory<>("Payment_mode"));
        officer.setCellValueFactory(new PropertyValueFactory<>("Officer"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));

        TitheTable.setItems(data);



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

    public TitheRecords() {


        DbManipulation dbManipulation = new DbManipulation();
        ResultSet resultSet;

        String Query = "SELECT * FROM TITHE";
        try {
            resultSet = dbManipulation.retrieveData(Query);
            if (resultSet != null){
                while (resultSet.next()){

                    String date = resultSet.getString("DATE");
                    String id = resultSet.getString("MEMBERSHIP_ID");
                    int amount = resultSet.getInt("AMOUNT");
                    String mode = resultSet.getString("PAYMENT_MODE");
                    String cashier = resultSet.getString("CASHIER");
                    String time = resultSet.getString("TIME");

                    data.addAll(new TitheTableModel(date, id, amount, mode, cashier, time));

                }
            }
        }catch (Exception e){
//                e.printStackTrace();
        }


    }





    //==========================TABLE MODEL CLASS=======================================


    public static class TitheTableModel{

        private SimpleStringProperty Date;
        private SimpleStringProperty Member;
        private SimpleIntegerProperty Amount;
        private SimpleStringProperty Payment_mode;
        private SimpleStringProperty Officer;
        private SimpleStringProperty Time;

        public TitheTableModel(String date, String member, int amount, String payment_mode, String officer, String time) {

            Date = new SimpleStringProperty(date);
            Member = new SimpleStringProperty(member);
            Amount = new SimpleIntegerProperty(amount);
            Payment_mode = new SimpleStringProperty(payment_mode);
            Officer = new SimpleStringProperty(officer);
            Time = new SimpleStringProperty(time);


        }

        public String getDate() {
            return Date.get();
        }

        public SimpleStringProperty dateProperty() {
            return Date;
        }

        public void setDate(String date) {
            this.Date.set(date);
        }

        public String getMember() {
            return Member.get();
        }

        public SimpleStringProperty memberProperty() {
            return Member;
        }

        public void setMember(String member) {
            this.Member.set(member);
        }

        public int getAmount() {
            return Amount.get();
        }

        public SimpleIntegerProperty amountProperty() {
            return Amount;
        }

        public void setAmount(int amount) {
            this.Amount.set(amount);
        }

        public String getPayment_mode() {
            return Payment_mode.get();
        }

        public SimpleStringProperty payment_modeProperty() {
            return Payment_mode;
        }

        public void setPayment_mode(String payment_mode) {
            this.Payment_mode.set(payment_mode);
        }

        public String getOfficer() {
            return Officer.get();
        }

        public SimpleStringProperty officerProperty() {
            return Officer;
        }

        public void setOfficer(String officer) {
            this.Officer.set(officer);
        }

        public String getTime() {
            return Time.get();
        }

        public SimpleStringProperty timeProperty() {
            return Time;
        }

        public void setTime(String time) {
            this.Time.set(time);
        }
    }



}
