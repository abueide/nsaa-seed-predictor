package data;

import java.net.URL;

/**
 * Created by Andrew Bueide on 10/10/16.
 */
public class FBClass {

    String name;
    URL url;

    public FBClass(String name, URL url) {
        this.name = name;
        this.url = url;
    }

    public URL getUrl() {
        return this.url;
    }

    public String toString() {
        return name;
    }
}
