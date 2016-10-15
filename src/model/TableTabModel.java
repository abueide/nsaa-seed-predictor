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

    public TableTabModel() {
        this.games = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.teams = new SimpleListProperty<>(FXCollections.observableArrayList());
        updateStats();
    }

    public TableTabModel(List<FBGame> games, List<Team> teams) {
        Collections.sort(games, new Comparator<FBGame>() {
            public int compare(FBGame g1, FBGame g2) {
                return g1.getDate().compareTo(g2.getDate());
            }
        });
        this.games = new SimpleListProperty<>(FXCollections.observableArrayList(games));
        this.teams = new SimpleListProperty<>(FXCollections.observableArrayList(teams));
        updateStats();

    }

    public void updateStats() {
        for (FBGame game : getGames()) {
            for (Team team : getTeams()) {
                if ((game.getTeam1().equalsIgnoreCase(team.getName()) || game.getTeam2().equalsIgnoreCase(team.getName())) && !game.getWinner().equalsIgnoreCase("Unknown")) {
                    if (!game.getWinner().equalsIgnoreCase(game.getTeam1()) && !game.getWinner().equalsIgnoreCase(game.getTeam2())) {

                    } else if (game.getWinner().equalsIgnoreCase(team.getName())) {
                        team.setWins(team.getWins() + 1);
                        /*if(getTeamByName(game.getLoser()).getTier() == 1){
                            team.setPoints(team.getPoints() + 50);
                        } else if(getTeamByName(game.getLoser()).getTier() == 2){
                            team.setPoints(team.getPoints() + 47);
                        }else if(getTeamByName(game.getLoser()).getTier() == 3){
                            team.setPoints(team.getPoints() + 44);
                        }else if(getTeamByName(game.getLoser()).getTier() == 4){
                            team.setPoints(team.getPoints() + 41);
                        }*/
                    } else {
                        team.setLosses(team.getLosses() + 1);
                        /*if(getTeamByName(game.getWinner()).getTier() == 1){
                            team.setPoints(team.getPoints() + 36);
                        } else if(getTeamByName(game.getWinner()).getTier() == 2){
                            team.setPoints(team.getPoints() + 33);
                        }else if(getTeamByName(game.getWinner()).getTier() == 3){
                            team.setPoints(team.getPoints() + 30);
                        }else if(getTeamByName(game.getWinner()).getTier() == 4){
                            team.setPoints(team.getPoints() + 37);
                        }*/
                    }
                }
                team.updateStats();
            }
        }
        /*for(FBGame game : getGames()){
            for(Team team : getTeams()){
                if (game.getWinner().equalsIgnoreCase(team.getName())){
                    if(getTeamByName(game.getLoser()).getTier() == 1){
                        team.setPoints(team.getPoints() + 36);
                    } else if(getTeamByName(game.getLoser()).getTier() == 2){
                        team.setPoints(team.getPoints() + 33);
                    }else if(getTeamByName(game.getLoser()).getTier() == 3){
                        team.setPoints(team.getPoints() + 30);
                    }else if(getTeamByName(game.getLoser()).getTier() == 4){
                        team.setPoints(team.getPoints() + 37);
                    }
                }
                if(game.getLoser().equalsIgnoreCase(team.getName())){
                    if(getTeamByName(game.getWinner()).getTier() == 1){
                        team.setPoints(team.getPoints() + 36);
                    } else if(getTeamByName(game.getWinner()).getTier() == 2){
                        team.setPoints(team.getPoints() + 33);
                    }else if(getTeamByName(game.getWinner()).getTier() == 3){
                        team.setPoints(team.getPoints() + 30);
                    }else if(getTeamByName(game.getWinner()).getTier() == 4){
                        team.setPoints(team.getPoints() + 37);
                    }
                }
            }
        }*/
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
        System.out.println(s);
        for (Team team : getTeams()) {
            if (team.getName().equalsIgnoreCase(s)) {
                return team;
            }
        }
        System.out.println("Unfound Team: \"" + s + "\"");
        Team test = new Team();
        test.setName("Lincoln Pius X");
        return test;
    }
}
