package com.bilgeadam.util;

import java.sql.*;

public class JDBCHelper {

    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(JDBCConstant.URL, JDBCConstant.USERNAME, JDBCConstant.PASSWORD);
        return connection;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if(connection != null){
            connection.close();
            System.out.println("Connection kapatıldı");
        }
    }

    public static void closePreparedStatement(PreparedStatement stm) throws SQLException {
        if(stm != null){
            stm.close();
            System.out.println("PreparedStatement kapatıldı");
        }
    }
    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        if(resultSet != null){
            resultSet.close();
            System.out.println("ResultSet kapatıldı");
        }
    }

}
