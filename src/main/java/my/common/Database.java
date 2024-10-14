/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.common;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mygam
 */
public class Database {
    public static Connection getConnection(){
        Connection conn=null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn= DriverManager.getConnection("jdbc:sqlserver://May04:1433;databaseName=demodb","sa","123");
        }catch(Exception e){
            System.out.println("Loi: "+e.toString());
        }
        return conn;
    }
}