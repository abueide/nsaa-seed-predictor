package eth.abueide.nsp;

import eth.abueide.nsp.controller.MainController;
import eth.abueide.nsp.util.Util;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new MainController().launch(primaryStage);
    }

    @Override
    public void stop() {
        try {
            FileUtils.cleanDirectory(new File(Util.getAppData() + "/csv_files/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
