package controller;

import data.FBGame;
import data.Team;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    FileTab tab;
    TableTabModel tableTabModel;

    public TableTabController(FileTab tab, TableTabModel tableTabModel) {
        this.tab = tab;
        this.tableTabModel = tableTabModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        CSVParser parser = new CSVParser(tab.getFiles());

        tableTabModel.setGames(FXCollections.observableArrayList(parser.getGames()));
        tableTabModel.setTeams(FXCollections.observableArrayList(parser.getTeams()));
        standingsTable.setItems(tableTabModel.getTeams());
        gamesTable.setItems(tableTabModel.getGames());
        gamesTable.setEditable(true);


    }

}
