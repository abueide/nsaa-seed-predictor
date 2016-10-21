package controller;

import data.FBGame;
import data.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.TableTabModel;
import util.CSVParser;
import util.javafx.FileTab;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Andrew Bueide on 10/12/16.
 */
public class TableTabController implements Initializable {
    @FXML
    TableView<FBGame> gamesTable;
    @FXML
    TableView<Team> standingsTable;
    @FXML
    TableColumn teamCol;
    @FXML
    TableColumn dateCol;
    @FXML
    TableColumn gameCol;
    @FXML
    TableColumn winnerCol;
    @FXML
    TableColumn recordCol;
    @FXML
    TableColumn winPercentCol;
    @FXML
    TableColumn pointsCol;
    @FXML TableColumn districtWinner;
    @FXML
    ComboBox classCombo;
    @FXML
    TextField filter;

    FileTab tab;
    TableTabModel tableTabModel;



    public TableTabController(FileTab tab, TableTabModel tableTabModel) {
        this.tab = tab;
        this.tableTabModel = tableTabModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //        CSVParser parser = new CSVParser(tab.getFiles().get(1));
        CSVParser parser = new CSVParser(tab.getFiles());


        tableTabModel.setGames(FXCollections.observableArrayList(parser.getGames()));
        tableTabModel.setTeams(FXCollections.observableArrayList(parser.getTeams()));

        FilteredList<Team> filteredTeams = new FilteredList<>(tableTabModel.getTeams(), p -> true);
        FilteredList<FBGame> filteredGames = new FilteredList<>(tableTabModel.getGames(), p -> true);


        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Team> sortedTeams = new SortedList<>(filteredTeams);
        SortedList<FBGame> sortedGames = new SortedList<FBGame>(filteredGames);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedTeams.comparatorProperty().bind(standingsTable.comparatorProperty());
        sortedGames.comparatorProperty().bind(gamesTable.comparatorProperty());


        standingsTable.setItems(sortedTeams);
        gamesTable.setItems(sortedGames);
        gamesTable.setEditable(true);
        gamesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for(TableColumn column : gamesTable.getColumns()){
            column.prefWidthProperty().bind(gamesTable.widthProperty().divide(3));
        }
        for(TableColumn column : standingsTable.getColumns()) {
            String s = column.getText();
//            if (s.equalsIgnoreCase("district winner") || s.equalsIgnoreCase("seed")) {
//                column.prefWidthProperty().bind(standingsTable.widthProperty().divide(1 / 10));
//            } else {
                column.prefWidthProperty().bind(standingsTable.widthProperty().divide(6));
//            }
        }

        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredTeams.setPredicate(team -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (team.getName().toLowerCase().contains(lowerCaseFilter) && team.getClassSize().equalsIgnoreCase(classCombo.getSelectionModel().getSelectedItem().toString())) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
            filteredGames.setPredicate(game -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (
                        game.getTitle().toLowerCase().contains(lowerCaseFilter) && (
                        tableTabModel.getTeamByName(game.getTeam1()).getClassSize().equalsIgnoreCase(classCombo.getSelectionModel().getSelectedItem().toString()) ||
                        tableTabModel.getTeamByName(game.getTeam2()).getClassSize().equalsIgnoreCase(classCombo.getSelectionModel().getSelectedItem().toString())
                )) {
                    return true;
                }
                return false;
            });
        });

        classCombo.setOnAction(e -> {
            String newValue = filter.getText();
            filteredTeams.setPredicate(team -> {
                boolean isCorrectClass = team.getClassSize().equalsIgnoreCase(classCombo.getSelectionModel().getSelectedItem().toString()) || classCombo.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("All");
                if (newValue == null || newValue.isEmpty() && isCorrectClass) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (team.getName().toLowerCase().contains(lowerCaseFilter) && isCorrectClass)   {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
            filteredGames.setPredicate(game -> {
                boolean isCorrectClass = tableTabModel.getTeamByName(game.getTeam1()).getClassSize().equalsIgnoreCase(classCombo.getSelectionModel().getSelectedItem().toString()) ||
                        tableTabModel.getTeamByName(game.getTeam2()).getClassSize().equalsIgnoreCase(classCombo.getSelectionModel().getSelectedItem().toString()) ||
                        classCombo.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("All");
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (game.getTitle().toLowerCase().contains(lowerCaseFilter) && isCorrectClass) {
                    return true;
                }
                return false;
            });
        });

        districtWinner.setCellValueFactory(
                new PropertyValueFactory<Team, CheckBoxListCell<Boolean>>("districtWinner")
        );

        teamCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("name")
        );
        dateCol.setCellValueFactory(
                new PropertyValueFactory<FBGame, Date>("date")
        );
        gameCol.setCellValueFactory(
                new PropertyValueFactory<FBGame, String>("title")
        );
        winnerCol.setCellValueFactory(
                new PropertyValueFactory<FBGame, String>("winner")
        );

        recordCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("record")
        );
        winPercentCol.setCellValueFactory(
                new PropertyValueFactory<Team, Double>("winPercentage")
        );
        pointsCol.setCellValueFactory(
                new PropertyValueFactory<Team, Integer>("points")
        );

        winnerCol.setCellFactory(TextFieldTableCell.forTableColumn());
        winnerCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<FBGame, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<FBGame, String> t) {
                        ((FBGame) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setWinner(t.getNewValue());
                        tableTabModel.updateStats();
                    }
                });
        DateFormat displayFormat = new SimpleDateFormat("MM/dd/yy");

        dateCol.setCellFactory(column -> {
            return new TableCell<FBGame, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(displayFormat.format(item));
                    }
                }
            };
        });



//        for(Team team : tableTabModel.getTeams()){
//            System.out.println(team.getTier());
//        }
        //tableTabModel.setVisibleGames(parser.getTeam(tab.getFiles().get(1)));

        classCombo.setItems(FXCollections.observableArrayList("All", "A", "B", "C1", "C2", "D1", "D2", "D6"));
//        classCombo.setOnAction(e -> {
//            tableTabModel.setClassVisible(classCombo.getSelectionModel().getSelectedItem().toString());
//        });

    }

}
