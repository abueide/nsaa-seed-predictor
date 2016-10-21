package util.javafx;

import controller.TableTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import model.TableTabModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Andrew Bueide on 10/12/16.
 */
public class FileTab extends Tab {
    List<File> files;

    public FileTab(List<File> files) {
        super("New Tab");
        this.files = files;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/tabletab.fxml"));
            TableTabController tableTabController = new TableTabController(this, new TableTabModel());
            loader.setController(tableTabController);
            this.setContent(loader.load());
            this.setClosable(true);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
