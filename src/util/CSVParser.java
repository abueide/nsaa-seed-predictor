package util;

import data.FBGame;
import data.Team;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Bueide on 10/12/16.
 */
public class CSVParser {
    List<File> files;

    public CSVParser(File file) {
        this.files = new ArrayList<>();
        files.add(file);
    }

    public CSVParser(List<File> files) {
        this.files = new ArrayList<>();
        this.files.addAll(files);
    }


    public List<FBGame> getGames() {
        List<FBGame> games = new ArrayList<>();
        Reader in = null;

        for (File file : files) {
            try {
                in = new FileReader(file);
                Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
                String team1 = "unknown";
                for (CSVRecord record : records) {
                    if (record.get(0).contains("(")) {
                        team1 = record.get(0).split(" \\(")[0];
                    }

                    if (record.get(0).contains("/") && record.get(0).length() <= 10) {
                        String date = record.get(0), team2 = record.get(1), result = "Unplayed", wl = record.get(6);
                        if (wl.contains("W")) {
                            result = team1;
                        } else if (wl.contains("L")) {
                            if (team2.contains("@")) {
                                result = team2.split("@ ")[1];
                            } else {
                                result = team2;
                            }
                        }
                        if (team2.contains("@")) {
                            games.add(new FBGame(date, team1, team2, result));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return games;
    }

    public List<Team> getTeams() {

        List<Team> teams = new ArrayList<>();
        Reader in = null;
        for (File file : files) {
            try {
                in = new FileReader(file);
                Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
                Team team = new Team();
                team.setName("thisteamshouldnotappear");
                for (CSVRecord record : records) {
                    if (record.get(0).contains("(")) {
                        team = new Team();
                        team.setName(record.get(0).split(" \\(")[0]);
                        if (!team.getName().equalsIgnoreCase("thisteamshouldnotappear")) {
                            teams.add(team);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (File file : files) {
            try {
                in = new FileReader(file);
                Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
                for (CSVRecord record : records) {
                    if (record.get(0).contains("/") && record.get(0).length() <= 10) {
                        for (Team team : teams) {
                            String opponent = record.get(1);
                            if (opponent.contains("@")) {
                                opponent = opponent.split("@ ")[1];
                            }
                            if (opponent.equalsIgnoreCase(team.getName())) {
                                team.setClassSize(record.get(2));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return teams;
    }

    public void getTeam(File file) {
        List<Team> teams = new ArrayList<>();
        Reader in = null;
        try {
            in = new FileReader(file);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
            Team team = new Team();
            team.setName("thisteamshouldnotappear");
            for (CSVRecord record : records) {
                if (record.get(0).contains("(")) {
                    if (!team.getName().equalsIgnoreCase("thisteamshouldnotappear")) {
                        teams.add(team);
                    }
                    team = new Team();
                    team.setName(record.get(0).split(" \\(")[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
