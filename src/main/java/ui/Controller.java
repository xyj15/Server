package ui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;
import rmi.RemoteHelper;

import java.awt.event.ActionEvent;

/**
 * Created by 婧婧 on 2017/1/1.
 */
public class Controller {
    private static Parent root;
    private static Stage primaryStage;

    public static void setRoot(Parent root) {
        Controller.root = root;
    }


    public static void setPrimaryStage(Stage primaryStage) {
        Controller.primaryStage = primaryStage;
    }


}
