package eth.abueide.nsp.model;


import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Tab;

import java.util.List;

/**
 * Created by Andrew Bueide on 10/12/16.
 */
public class MainModel {

    SimpleListProperty<Tab> tabs;

    public MainModel() {
        tabs = new SimpleListProperty<Tab>(FXCollections.observableArrayList());
    }

    public MainModel(List<Tab> tabs) {
        tabs = new SimpleListProperty<Tab>(FXCollections.observableArrayList(tabs));
    }

    public void addTab(Tab tab) {
        tabs.add(tab);
    }

    public void deleteTab(Tab tab) {
        this.tabs.remove(tab);
    }


    public SimpleListProperty<Tab> getTabs() {
        return tabs;
    }
}
