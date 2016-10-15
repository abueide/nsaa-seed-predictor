package main;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import util.Util;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void init() throws Exception {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new MainController().launch(primaryStage);
    }

    @Override
    public void stop(){
        try {
            FileUtils.cleanDirectory(new File(Util.getAppData() + "/csv_files/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
