package data;

import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrew Bueide on 10/12/16.
 */
public class FBGame {
    Date date;
    SimpleStringProperty team1, team2;
    SimpleStringProperty title;
    SimpleStringProperty winner;

    public FBGame() {

    }

    public FBGame(String date, String team1, String team2, String winner) {
        DateFormat sourceFormat = new SimpleDateFormat("MM/dd/yy");
        try {
            this.date = sourceFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.team1 = new SimpleStringProperty(team1);
        this.team2 = new SimpleStringProperty(team2);
        this.title = new SimpleStringProperty(team1 + " " + team2);
        this.winner = new SimpleStringProperty(winner);

        if (getTeam1().contains("@")) {
            setTeam1(getTeam1().split("@ ")[1]);
        }
        if (getTeam2().contains("@")) {
            setTeam2(getTeam2().split("@ ")[1]);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTeam1() {
        return team1.get();
    }

    public SimpleStringProperty team1Property() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1.set(team1);
    }

    public String getTeam2() {
        return team2.get();
    }

    public SimpleStringProperty team2Property() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2.set(team2);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getWinner() {
        return winner.get();
    }

    public SimpleStringProperty winnerProperty() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner.set(winner);
    }

    public String getLoser() {
        if (getTeam1().equalsIgnoreCase(getWinner())) {
            return getTeam2();
        } else if (getTeam2().equalsIgnoreCase(getWinner())) {
            return getTeam1();
        } else {
            return "unknown";
        }
    }

}
