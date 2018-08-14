package eth.abueide.nsp.controller;

import eth.abueide.nsp.model.MainModel;
import eth.abueide.nsp.util.Util;
import eth.abueide.nsp.util.javafx.FileTab;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Andrew Bueide on 10/2/16.
 */
public class MainController implements Initializable {
    @FXML
    MenuItem download;
    @FXML
    TabPane tabPane;
    @FXML
    MenuItem open;

    MainModel mainModel = new MainModel();
    Stage primaryStage;

    List<URL> classSchedules = new ArrayList<>();

    public MainController() {

    }

    ;

    public MainController(String file) {
    }

    public void launch(Stage primaryStage) {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("main.fxml"));
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
        try {
            classSchedules.add(new URL("https://nsaahome.org/calculate/exports/showclassfbA.csv"));
            classSchedules.add(new URL("https://nsaahome.org/calculate/exports/showclassfbB.csv"));
            classSchedules.add(new URL("https://nsaahome.org/calculate/exports/showclassfbC1.csv"));
            classSchedules.add(new URL("https://nsaahome.org/calculate/exports/showclassfbC2.csv"));
            classSchedules.add(new URL("https://nsaahome.org/calculate/exports/showclassfbD1.csv"));
            classSchedules.add(new URL("https://nsaahome.org/calculate/exports/showclassfbD2.csv"));
            classSchedules.add(new URL("https://nsaahome.org/calculate/exports/showclassfbD6.csv"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        tabPane.getTabs().setAll(mainModel.getTabs());
        mainModel.getTabs().addListener((observable, oldValue, newValue) -> {
            tabPane.getTabs().setAll(newValue);
        });
        download.setOnAction(e -> {
//            File file = new DownloadController(new DownloadModel()).display();
//            FileTab tab = new FileTab(file);
//            mainModel.addTab(tab);
            mainModel.addTab(new FileTab(downloadFiles(classSchedules)));
        });
        open.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select CSV file");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
            chooser.setInitialDirectory(new File(Util.getAppData()));
            File file = chooser.showOpenDialog(primaryStage);
            List<File> files = new ArrayList<>();
            files.add(file);
            mainModel.addTab(new FileTab(files));
        });

    }

    public File downloadFile(URL url) {
        File downloaded_File = null;
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
        return downloaded_File;
    }

    public List<File> downloadFiles(List<URL> urls) {
        List<File> downloaded_Files = new ArrayList<>();
        for (URL url : urls) {
            if (url.getFile().toLowerCase().endsWith(".csv")) {
                try {
                    File downloaded_File = Util.createNewFile(Util.getAppData() + "/csv_files/", ".csv");
                    FileUtils.copyURLToFile(url, downloaded_File);
                    downloaded_Files.add(downloaded_File);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("File does not end with .csv");
            }
        }
        return downloaded_Files;
    }
}
