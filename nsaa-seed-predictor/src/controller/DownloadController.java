package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Andrew Bueide on 10/3/16.
 */
public class DownloadController implements Initializable {

    public DownloadController(){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

        public void display() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("view/download.fxml"));
        loader.setController(this);
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Download file");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
