package com.app.cms.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String user = "sa";
    private static final String password = "nemeziz99";
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=CMS";
    private static Connection con = null;


    /*
     * This function is used to get access
     * to the SQL Server or connect
     */
    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            con = DriverManager.getConnection(url , user , password);
        }
        catch (SQLException s){
            s.getErrorCode();
        }
        return  con;
    }


}
