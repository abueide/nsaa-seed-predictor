package data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

/**
 * Created by Andrew Bueide on 10/12/16.
 */
public class Team {
    SimpleStringProperty name, record, classSize;
    SimpleIntegerProperty wins, losses, tier, points;
    SimpleDoubleProperty winPercentage;
    boolean districtWinner = false;


    public Team() {
        this.name = new SimpleStringProperty();
        this.record = new SimpleStringProperty();
        this.classSize = new SimpleStringProperty("unknown");
        this.wins = new SimpleIntegerProperty(0);
        this.losses = new SimpleIntegerProperty(0);
        this.tier = new SimpleIntegerProperty(0);
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
        updateStats();
    }

    public int getLosses() {
        return losses.get();
    }

    public SimpleIntegerProperty lossesProperty() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses.set(losses);
        updateStats();
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
            double winPercentage = (double) getWins() / (getWins() + getLosses()) * 100;
            setWinPercentage((double) (Math.round(winPercentage * 100)) / 100);
//            setWinPercentage(((double) getWins() / (getWins() + getLosses()) * 100));
        } else {
            setWinPercentage(0);
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
        if (getWinPercentage() > 77) {
            setTier(1);
        } else if (getWinPercentage() > 55) {
            setTier(2);
        } else if (getWinPercentage() > 33) {
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

    public static Team getTeamByName(String s, List<Team> teams) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(s)) {
                return team;
            }
        }
        //System.out.println("Unfound Team: \"" + s + "\"");
        Team team = new Team();
        team.setName(s);
        return team;
    }

    public boolean isDistrictWinner() {
        return districtWinner;
    }

    public void setDistrictWinner(boolean districtWinner) {
        this.districtWinner = districtWinner;
    }
}
