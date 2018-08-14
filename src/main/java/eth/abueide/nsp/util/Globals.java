package eth.abueide.nsp.util;

import java.util.prefs.Preferences;

/**
 * Created by Andrew Bueide on 5/20/16.
 */
public class Globals {

    //Preference Keys
    public static final String APPDATA_DIR = "APPDATA_DIR";
    public static final String nullclient_NODE = "/abueide/nsaa-seed-predictor";

    public static final Preferences PREF = Preferences.userRoot().node(nullclient_NODE);

}
