import db.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Appinitializer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            Logger logger = Logger.getLogger("");
            FileHandler fileHandler = new FileHandler("error.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);


            DBConnection.getInstance().getConnection();

            Parent root = FXMLLoader.load(getClass().getResource("viwe/loginForm.fxml"));
            primaryStage.setTitle("Liker-Social Media");
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.show();

        }catch (Exception e){

            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong Please Contact IT team", ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }


    public static void main(String[] args) {
        launch(args);
        System.out.println("Sutting Down the connection");
        try {
            DBConnection.getInstance().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
