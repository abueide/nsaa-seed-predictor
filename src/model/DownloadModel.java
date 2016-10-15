package model;

import data.FBClass;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Andrew Bueide on 10/10/16.
 */
public class DownloadModel {
    private ListProperty<FBClass> fbClasses;

    public DownloadModel() {
        fbClasses = new SimpleListProperty<>(FXCollections.observableArrayList());
        //fbClasses.addAll("Test", "Test 1", "Test 2");
        try {
            fbClasses.addAll(
                    new FBClass("A", new URL("http://nsaahome.org/calculate/exports/showclassfbA.csv")),
                    new FBClass("B", new URL("http://nsaahome.org/calculate/exports/showclassfbB.csv")),
                    new FBClass("C1", new URL("http://nsaahome.org/calculate/exports/showclassfbC1.csv")),
                    new FBClass("C2", new URL("http://nsaahome.org/calculate/exports/showclassfbC2.csv")),
                    new FBClass("D1", new URL("http://nsaahome.org/calculate/exports/showclassfbD1.csv")),
                    new FBClass("D2", new URL("http://nsaahome.org/calculate/exports/showclassfbD2.csv")),
                    new FBClass("D6", new URL("http://nsaahome.org/calculate/exports/showclassfbD6.csv"))
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void addOption() {
        try {
            fbClasses.add(new FBClass("Option", new URL("http://nsaahome.org/calculate/exports/showclassfbB.csv")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public ListProperty<FBClass> getFbClasses() {
        return this.fbClasses;
    }
}
