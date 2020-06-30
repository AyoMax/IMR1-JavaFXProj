package app.controllers;

import app.models.MainModel;
import app.models.PlayerModel;
import app.views.ViewSkill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RankController implements Initializable, ViewSkill {

    private MainModel mainModel;

    @FXML
    TableView<String> tableView;

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[][] tableDatas = PlayerModel.getRankData();

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(tableDatas));
        data.remove(0);//remove titles from data

        /*
        for (int i = 0; i < staffArray[0].length; i++) {
            TableColumn tc = new TableColumn(staffArray[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            table.getColumns().add(tc);
        }*/

        // tableView.setItems(data);
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
    }
}
