package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Andrew Bueide on 10/2/16.
 */
public class MainController implements Initializable{
    @FXML
    MenuItem download;
    public MainController(){};

    public MainController(String file){}

    public void launch(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("view/main.fxml"));
        loader.setController(this);
        Parent root;
        try {
            root = loader.load();
            primaryStage.setTitle("NSAA Seed Predictor by Andrew Bueide");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        download.setOnAction(e -> new DownloadController().display());
    }
}
