package model;

import data.FBGame;
import data.Team;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
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
    }

    public TableTabModel(List<FBGame> games, List<Team> teams) {
        Collections.sort(games, new Comparator<FBGame>() {
            public int compare(FBGame g1, FBGame g2) {
                return g1.getDate().compareTo(g2.getDate());
            }
        });
        this.games = new SimpleListProperty<>(FXCollections.observableArrayList(games));
        this.teams = new SimpleListProperty<>(FXCollections.observableArrayList(teams));
    }

    public void updateStats() {
        for (Team team : getTeams()) {
            team.setWins(0);
            team.setLosses(0);
            for (FBGame game : getGames()) {
                String debugGame = "";
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
        for (Team team : getTeams()) {
            team.setPoints(0);
            for (FBGame game : getGames()) {
                String debugTeam = "";
                if (game.getWinner().equalsIgnoreCase(team.getName())) {
                    if (game.getWinner().equalsIgnoreCase(debugTeam))
                        System.out.print(game.getWinner() + "(Tier: " + team.getTier() + ")" + " won vs " + game.getLoser() + ", tier " + getTeamByName(game.getLoser()).getTier() + " points went from " + team.getPoints() + " to ");
                    if (getTeamByName(game.getLoser()).getTier() == 1) {
                        team.setPoints(team.getPoints() + 50);
                    } else if (getTeamByName(game.getLoser()).getTier() == 2) {
                        team.setPoints(team.getPoints() + 47);
                    } else if (getTeamByName(game.getLoser()).getTier() == 3) {
                        team.setPoints(team.getPoints() + 44);
                    } else if (getTeamByName(game.getLoser()).getTier() == 4) {
                        team.setPoints(team.getPoints() + 41);
                    }
                    if (game.getWinner().equalsIgnoreCase(debugTeam))
                        System.out.println(team.getPoints());
                }
                if (game.getLoser().equalsIgnoreCase(team.getName())) {
                    if (game.getLoser().equalsIgnoreCase(debugTeam))
                        System.out.print(game.getLoser() + "(Tier: " + team.getTier() + ")" + " lost vs " + game.getWinner() + ", tier " + getTeamByName(game.getWinner()).getTier() + " points went from " + team.getPoints() + " to ");
                    if (getTeamByName(game.getWinner()).getTier() == 1) {
                        team.setPoints(team.getPoints() + 36);
                    } else if (getTeamByName(game.getWinner()).getTier() == 2) {
                        team.setPoints(team.getPoints() + 33);
                    } else if (getTeamByName(game.getWinner()).getTier() == 3) {
                        team.setPoints(team.getPoints() + 30);
                    } else if (getTeamByName(game.getWinner()).getTier() == 4) {
                        team.setPoints(team.getPoints() + 27);
                    }
                    if (game.getLoser().equalsIgnoreCase(debugTeam))
                        System.out.println(team.getPoints());
                }
            }

        }
    }

    public void updateSeeds(String classSize) {
        List<Team> allTeams = new ArrayList<>();
        List<Team> playoffTeams = new ArrayList<>();


//        System.out.println("Stuck 1");
        for (Team team : getTeams()) {
            team.setSeed(0);
            if (team.getClassSize().equalsIgnoreCase(classSize) || classSize.equalsIgnoreCase("all")) {
                allTeams.add(team);
            }
        }
//        System.out.println("Stuck 1");
        Collections.sort(allTeams, (c1, c2) -> {
            if (c1.getPoints() > c2.getPoints()) return -1;
            if (c1.getPoints() < c2.getPoints()) return 1;
            return 0;
        });
//        System.out.println("Stuck 2");
        for (Team team : allTeams) {
            if (team.isDistrictWinner()) {
                playoffTeams.add(team);
            }
        }
//        System.out.println("Stuck 3");
        int index = 0;
        while (playoffTeams.size() < 16) {
            System.out.println(allTeams.get(index).getName());
            if (!allTeams.get(index).isDistrictWinner()) {
                playoffTeams.add(allTeams.get(index));
            }
            index++;
        }
//        System.out.println("Stuck 4");
        Collections.sort(playoffTeams, (c1, c2) -> {
            if (c1.getPoints() > c2.getPoints()) return -1;
            if (c1.getPoints() < c2.getPoints()) return 1;
            return 0;
        });
        int i = 1;
//        System.out.println("Stuck 5");
        for (Team team : playoffTeams) {
            getTeamByName(team.getName()).setSeed(i++);
        }
    }


    public ObservableList<FBGame> getGames() {
        return games.get();
    }

    public void setGames(ObservableList<FBGame> games) {
        Collections.sort(games, new Comparator<FBGame>() {
            public int compare(FBGame g1, FBGame g2) {
                return g1.getDate().compareTo(g2.getDate());
            }
        });
        this.games.set(games);
    }

    public ListProperty<FBGame> gamesProperty() {
        return games;
    }

    public ObservableList<Team> getTeams() {
        return teams.get();
    }

    public void setTeams(ObservableList<Team> teams) {
        this.teams.set(teams);
        updateStats();
    }

    public ListProperty<Team> teamsProperty() {
        return teams;
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
}
