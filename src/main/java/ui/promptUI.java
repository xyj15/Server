package ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rmi.RemoteHelper;

import javax.swing.*;

/**
 * Created by 婧婧 on 2017/1/1.
 */
public class promptUI extends Application {


    @Override
    public void start (Stage primaryStage) throws Exception {
        primaryStage.setTitle("请皇上过目");
        Parent root = FXMLLoader.load(getClass().getResource("prompt.fxml"));
        
        Controller.setRoot(root);
        Controller.setPrimaryStage(primaryStage);
        Scene myScene = new Scene(root,410,193);
        primaryStage.setResizable(false);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        new  RemoteHelper();
        launch(args);

    }
}
