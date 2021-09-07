package com.app.cms.DbManipulation;

import com.app.cms.DbConnection.DbConnection;

import java.sql.*;

public class DbManipulation {

    private Connection con;
    private Statement statement;
    private PreparedStatement pStatement;
    private ResultSet resultSet;


    public  void InsertIntoDb(String query){
        //
        con = DbConnection.getConnection();
        try {
            statement = con.createStatement();
            statement.execute(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }



    // Retrieve Data
    public ResultSet retrieveData(String query){

        con = DbConnection.getConnection();

        if (con != null){

            try {
                statement = con.createStatement();
                resultSet = statement.executeQuery(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        return resultSet;
    }


}
