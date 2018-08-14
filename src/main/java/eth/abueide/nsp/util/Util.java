package eth.abueide.nsp.util;


import java.io.File;
import java.io.IOException;

/**
 * Created by Andrew Bueide on 5/20/16.
 */
public class Util {

    public static String getAppData() {
        String osName = System.getProperty("os.name").toUpperCase();
        String appDataDir = System.getProperty("user.dir");

        if (osName.contains("WIN"))
            appDataDir = System.getenv("APPDATA");

        else if (osName.contains("MAC"))
            appDataDir = System.getProperty("user.home") + "/Library/Application Support/";

        else if (osName.contains("NUX"))
            appDataDir = System.getProperty("user.home") + "/.config/";

        return appDataDir + "abueide/nsaa-seed-predictor/";
    }

    public static File createNewFile(String dir, String ext) {
        File file = new File(dir + "new_file" + ext);
        int i = 0;
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        while (file.exists()) {
            file = new File(dir + "new_file(" + i++ + ")" + ext);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to creat new file");
        }
        return file;
    }
}
