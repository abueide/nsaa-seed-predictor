package eth.abueide.nsp.controller;

import eth.abueide.nsp.data.FBClass;
import eth.abueide.nsp.model.DownloadModel;
import eth.abueide.nsp.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Andrew Bueide on 10/3/16.
 */
public class DownloadController implements Initializable {

    @FXML
    ComboBox<FBClass> classCombo;
    @FXML
    Button openClassButton;
    DownloadModel downloadModel;

    File downloaded_File;


    public DownloadController(DownloadModel downloadModel) {
        this.downloadModel = downloadModel;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classCombo.setItems(downloadModel.getFbClasses());
        openClassButton.setOnAction(e -> downloadModel.addOption());
        classCombo.getSelectionModel().selectFirst();
        openClassButton.setOnAction(e -> downloadAndOpen(classCombo.getSelectionModel().getSelectedItem().getUrl()));
    }

    public void downloadAndOpen(URL url) {
        if (url.getFile().toLowerCase().endsWith(".csv")) {
            try {
                downloaded_File = Util.createNewFile(Util.getAppData() + "/csv_files/", ".csv");
                FileUtils.copyURLToFile(url, downloaded_File);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not end with .csv");
        }
        openClassButton.getScene().getWindow().hide();
    }

    public File display() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("download.fxml"));
        loader.setController(this);
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Download file");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return downloaded_File;
    }

}
