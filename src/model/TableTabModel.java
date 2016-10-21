package model;

import data.FBGame;
import data.Team;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Andrew Bueide on 10/12/16.
 */
public class TableTabModel {
    ListProperty<FBGame> games;
    ListProperty<Team> teams;
    ListProperty<FBGame> visibleGames;
    ListProperty<Team> visibleTeams;

    public TableTabModel() {
        this.games = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.teams = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.visibleGames = this.games;
        this.visibleTeams = this.teams;
    }

    public TableTabModel(List<FBGame> games, List<Team> teams) {
        Collections.sort(games, new Comparator<FBGame>() {
            public int compare(FBGame g1, FBGame g2) {
                return g1.getDate().compareTo(g2.getDate());
            }
        });
        this.games = new SimpleListProperty<>(FXCollections.observableArrayList(games));
        this.teams = new SimpleListProperty<>(FXCollections.observableArrayList(teams));
        this.visibleGames = this.games;
        this.visibleTeams = this.teams;
    }

    public void updateStats() {
        for (Team team : getTeams()) {
            team.setWins(0);
            team.setLosses(0);
            for (FBGame game : getGames()) {
                String temp = "";
                if ((game.getTeam1().equalsIgnoreCase(team.getName()) || game.getTeam2().equalsIgnoreCase(team.getName())) && !game.getWinner().equalsIgnoreCase("Unknown")) {
                    if (!game.getWinner().equalsIgnoreCase(game.getTeam1()) && !game.getWinner().equalsIgnoreCase(game.getTeam2())) {

                    } else if (game.getWinner().equalsIgnoreCase(team.getName())) {
                        team.setWins(team.getWins() + 1);
                    } else {
                        team.setLosses(team.getLosses() + 1);
                    }
                }
            }
        }
        for(Team team : getTeams()){
            team.setPoints(0);
            for(FBGame game : getGames()){
                String temp = "testing variable";
                if (game.getWinner().equalsIgnoreCase(team.getName())){
                    if(game.getWinner().equalsIgnoreCase(temp))
                        System.out.print(game.getWinner() + "(Tier: " + team.getTier() + ")" + " won vs " + game.getLoser() + ", tier " + getTeamByName(game.getLoser()).getTier() + " points went from " + team.getPoints() + " to ");
                    if(getTeamByName(game.getLoser()).getTier() == 1){
                        team.setPoints(team.getPoints() + 50);
                    } else if(getTeamByName(game.getLoser()).getTier() == 2){
                        team.setPoints(team.getPoints() + 47);
                    }else if(getTeamByName(game.getLoser()).getTier() == 3){
                        team.setPoints(team.getPoints() + 44);
                    }else if(getTeamByName(game.getLoser()).getTier() == 4){
                        team.setPoints(team.getPoints() + 41);
                    }
                    if(game.getWinner().equalsIgnoreCase(temp))
                        System.out.println(team.getPoints());
                }
                if(game.getLoser().equalsIgnoreCase(team.getName())){
                    if(game.getLoser().equalsIgnoreCase(temp))
                        System.out.print(game.getLoser() + "(Tier: " + team.getTier() + ")"  + " lost vs " + game.getWinner() + ", tier " + getTeamByName(game.getWinner()).getTier() + " points went from " + team.getPoints() + " to ");
                    if(getTeamByName(game.getWinner()).getTier() == 1){
                        team.setPoints(team.getPoints() + 36);
                    } else if(getTeamByName(game.getWinner()).getTier() == 2){
                        team.setPoints(team.getPoints() + 33);
                    }else if(getTeamByName(game.getWinner()).getTier() == 3){
                        team.setPoints(team.getPoints() + 30);
                    }else if(getTeamByName(game.getWinner()).getTier() == 4){
                        team.setPoints(team.getPoints() + 27);
                    }
                    if(game.getLoser().equalsIgnoreCase(temp))
                        System.out.println(team.getPoints());
                }
            }

        }
    }


    public ObservableList<FBGame> getGames() {
        return games.get();
    }

    public ListProperty<FBGame> gamesProperty() {
        return games;
    }

    public void setGames(ObservableList<FBGame> games) {
        Collections.sort(games, new Comparator<FBGame>() {
            public int compare(FBGame g1, FBGame g2) {
                return g1.getDate().compareTo(g2.getDate());
            }
        });
        this.games.set(games);
        updateStats();
    }

    public ObservableList<Team> getTeams() {
        return teams.get();
    }

    public ListProperty<Team> teamsProperty() {
        return teams;
    }

    public void setTeams(ObservableList<Team> teams) {
        this.teams.set(teams);
        updateStats();
    }

    public Team getTeamByName(String s) {
        for (Team team : getTeams()) {
            if (team.getName().equalsIgnoreCase(s)) {
//                System.out.println(team.getName() + " : " + team.getTier());
                return team;
            }
        }
        //System.out.println("Unfound Team: \"" + s + "\"");
        Team team = new Team();
        team.setName(s);
        return team;
    }

    public ObservableList<FBGame> getVisibleGames() {
        return visibleGames.get();
    }

    public ListProperty<FBGame> visibleGamesProperty() {
        return visibleGames;
    }

    public void setVisibleGames(ObservableList<FBGame> visibleGames) {
        this.visibleGames.set(visibleGames);
    }

    public ObservableList<Team> getVisibleTeams() {
        return visibleTeams.get();
    }

    public ListProperty<Team> visibleTeamsProperty() {
        return visibleTeams;
    }

    public void setVisibleTeams(ObservableList<Team> visibleTeams) {
        this.visibleTeams.set(visibleTeams);
    }

    public void addVisibleTeam(Team team){
        this.visibleTeams.add(team);
    }

    public void setClassVisible(String classSize){
        setVisibleTeams(FXCollections.observableArrayList());
        for(Team team : getTeams()){
            if(team.getClassSize().equalsIgnoreCase(classSize)){
                addVisibleTeam(team);
            }
        }
    }
}
