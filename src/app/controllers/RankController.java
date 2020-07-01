package app.controllers;

import app.models.MainModel;
import app.models.PlayerModel;
import app.views.ViewSkill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RankController implements Initializable, ViewSkill {

    private MainModel mainModel;

    @FXML
    TableView<PlayerModel> tableView;

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */

    public void init() {
        if (PlayerModel.getPlayers().size() > 0) {
            ObservableList<PlayerModel> data = FXCollections.observableArrayList();
            data.addAll(PlayerModel.sortPlayer());
            tableView.setItems(data);

            // Définition de la colonne des noms
            TableColumn<PlayerModel, String> nameCol  = new TableColumn<>("Joueur");
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

            // Définition de la colonne des scores
            TableColumn<PlayerModel, String> scoreCol = new TableColumn<>("Nombre de victoire");
            scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

            tableView.getColumns().setAll(nameCol, scoreCol);
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } else {
            System.out.println("Aucun joueurs enregistrés");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onClickMenuButton(ActionEvent actionEvent) {
        mainModel.setState(MainModel.MainState.MENU);
        mainModel.notifyViews();
    }

    /* ==================== */
    /*  GETTERS  & SETTERS  */
    /* ==================== */

    @Override
    public void setMainModel(MainModel model) {
        this.mainModel = model;
        this.init();
    }
}
