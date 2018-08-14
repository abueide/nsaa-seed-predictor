package eth.abueide.nsp.util.javafx;

import eth.abueide.nsp.controller.TableTabController;
import eth.abueide.nsp.model.TableTabModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

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
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("tabletab.fxml"));
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
