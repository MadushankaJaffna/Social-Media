package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import db.DBConnection;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestoreAndBacupController {
    public JFXProgressBar progressBar;
    public JFXButton btn_BacupData;
    public JFXButton btn_restoreData;
    public AnchorPane root;

    public void initialize() {
        this.progressBar.setVisible(false);
    }

    public void btn_RestoreOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser =new FileChooser();
        fileChooser.setTitle("Restore the DB Backup");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL File","*.sql"));
        File file = fileChooser.showOpenDialog(this.root.getScene().getWindow());
        if(file != null) {
            String[] executeCMD;
            if(DBConnection.password.length()>0) {
                executeCMD = new String[]{"mysql", "-h" + DBConnection.ip, "-u" + DBConnection.user, "-p" + DBConnection.password,"--port",DBConnection.port, DBConnection.db, "-e", "source " + file.getAbsolutePath()};
            }
            else{
                executeCMD = new String[]{"mysql", "-h" + DBConnection.ip, "-u" + DBConnection.user,"--port"+DBConnection.port, DBConnection.db, "-e", "source " + file.getAbsolutePath()};
            }

            this.progressBar.setVisible(true);


            Task task = new Task() {
                @Override
                protected Void call() throws Exception {
                    Process process = Runtime.getRuntime().exec(executeCMD);
                    int exitCode = process.waitFor();
                    if (exitCode != 0) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                        br.lines().forEach(System.out::println);

                        throw new RuntimeException("wede kachal");
                    } else {return null;}
                }
            };
            task.setOnSucceeded(event -> {

                this.progressBar.setVisible(false);
                new Alert(Alert.AlertType.INFORMATION, "Restored Successfully").show();
            });
            task.setOnFailed(event -> {
               this.progressBar.setVisible(false);
                new Alert(Alert.AlertType.INFORMATION, "Restore failed").show();
            });
            new Thread(task).start();

        }

    }

    public void btn_BackupOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Save the DB Backup");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL File","*.sql"));
        File file = fileChooser.showSaveDialog(this.root.getScene().getWindow());
        if(file!=null){
           // this.root.getScene().setCursor(Cursor.WAIT);
            this.progressBar.setVisible(true);

            Task task = new Task() {
                @Override
                protected Object call() throws Exception {
                    Process process = Runtime.getRuntime().exec("mysqldump -h" + DBConnection.ip + " -u" + DBConnection.user + " -p" + DBConnection.password + " " + DBConnection.db + " --result-file " + file.getAbsolutePath() + ((file.getAbsolutePath().endsWith(".sql")) ? "" : ".sql"));
                    int exitCode = 0;
                    exitCode = process.waitFor();
                    if (exitCode != 0) {

                        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                        br.lines().forEach(System.out::println);
                        throw new RuntimeException("Something Wrong");
                    }else{
                        return null;
                    }
                }
            };
            task.setOnSucceeded(event -> {
               // this.root.getScene().setCursor(Cursor.DEFAULT);
                this.progressBar.setVisible(false);
                new Alert(Alert.AlertType.INFORMATION, "Backup Successfully").show();
            });
            task.setOnFailed(event -> {
               // this.root.getScene().setCursor(Cursor.DEFAULT);
                this.progressBar.setVisible(false);
                new Alert(Alert.AlertType.INFORMATION, "Backup failed").show();
            });
            new Thread(task).start();

        }

    }

    public void btn_backToHome(ActionEvent actionEvent) {
        URL resource = this.getClass().getResource("/viwe/payment.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(resource);
        } catch (IOException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong", ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }


}
