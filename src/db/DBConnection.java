package db;

import cript.LikerCript;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sun.security.util.Password;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbconnection;
    private Connection connection;

    public static String ip;
    public static String port;
    public static String db;
    public static String user;
    public static String password;



    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");


            Properties properties = new Properties();
            File file = new File("resources/application.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
            fileInputStream.close();

            String Ip  = LikerCript.decode(properties.getProperty("liker.ip"),"0707");
            ip=Ip;
            String Port=LikerCript.decode(properties.getProperty("liker.port"),"0707");
            port=Port;
            String Db=LikerCript.decode(properties.getProperty("liker.db"),"0707");
            db=Db;
            String User=LikerCript.decode(properties.getProperty("hibernate.connection.username"),"0707");
            user=User;
            String Password=LikerCript.decode(properties.getProperty("hibernate.connection.password"),"0707");
            password= Password;
            String Url = properties.getProperty("hibernate.connection.url");


            connection = DriverManager.getConnection(""+Url, ""+User, ""+Password);
            PreparedStatement pstm = connection.prepareStatement("SHOW TABLES");
            ResultSet resultSet = pstm.executeQuery();
            if (!resultSet.next()){
                File deScriptFile = new File("../Cupidbew/src/likerScript.sql");
                if(!deScriptFile.exists()){
                    new Alert(Alert.AlertType.WARNING,"DataBase is not Exist").show();
                    throw new RuntimeException("Unable to find database");
                }
                else{
                    StringBuilder sb = new StringBuilder();
                    BufferedReader brDBscript = new BufferedReader(new InputStreamReader(new FileInputStream(deScriptFile)));
                    brDBscript.lines().forEach(s -> sb.append(s));
                    brDBscript.close();
                    System.out.println(sb.toString());
                    pstm=connection.prepareStatement(sb.toString());
                    pstm.execute();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance(){
        return (dbconnection == null) ? (dbconnection=new DBConnection()) : dbconnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
