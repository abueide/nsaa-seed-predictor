package data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Andrew Bueide on 10/12/16.
 */
public class Team {
    SimpleStringProperty name, record, classSize;
    SimpleIntegerProperty wins, losses, tier, points;
    SimpleDoubleProperty winPercentage;


    public Team() {
        this.name = new SimpleStringProperty();
        this.record = new SimpleStringProperty();
        this.classSize = new SimpleStringProperty("unknown");
        this.wins = new SimpleIntegerProperty();
        this.losses = new SimpleIntegerProperty();
        this.tier = new SimpleIntegerProperty();
        this.points = new SimpleIntegerProperty();
        this.winPercentage = new SimpleDoubleProperty();
        setWins(0);
        setLosses(0);
    }

    public void updateStats() {
        updateRecord();
        updateWinPercentage();
        updateTier();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String s) {
        name.set(s);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getWins() {
        return wins.get();
    }

    public SimpleIntegerProperty winsProperty() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins.set(wins);
        updateRecord();
        updateWinPercentage();
    }

    public int getLosses() {
        return losses.get();
    }

    public SimpleIntegerProperty lossesProperty() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses.set(losses);
        updateRecord();
        updateWinPercentage();
    }

    public double getWinPercentage() {
        return winPercentage.get();
    }

    public SimpleDoubleProperty winPercentageProperty() {
        return winPercentage;
    }

    public void setWinPercentage(double winPercentage) {
        this.winPercentage.set(winPercentage);
    }

    public void updateWinPercentage() {
        if (getWins() + getLosses() != 0) {
            setWinPercentage(((double) getWins() / (getWins() + getLosses()) * 100));
        } else {
            setWinPercentage(0.0);
        }
    }

    public String getRecord() {
        return record.get();
    }

    public SimpleStringProperty recordProperty() {
        return record;
    }

    public void setRecord(String record) {
        this.record.set(record);
    }

    public void updateRecord() {
        setRecord(getWins() + "W - " + getLosses() + "L");
    }

    public void updateTier() {
        if (getWinPercentage() > 0.77) {
            setTier(1);
        } else if (getWinPercentage() > 0.55) {
            setTier(2);
        } else if (getWinPercentage() > 0.33) {
            setTier(3);
        } else {
            setTier(4);
        }
    }

    public int getTier() {
        return tier.get();
    }

    public SimpleIntegerProperty tierProperty() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier.set(tier);
    }

    public int getPoints() {
        return points.get();
    }

    public SimpleIntegerProperty pointsProperty() {
        return points;
    }

    public void setPoints(int points) {
        this.points.set(points);
    }

    public String getClassSize() {
        return classSize.get();
    }

    public SimpleStringProperty classSizeProperty() {
        return classSize;
    }

    public void setClassSize(String classSize) {
        this.classSize.set(classSize);
    }
}
