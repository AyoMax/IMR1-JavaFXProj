package app.controllers;

import app.models.BoardModel;
import app.models.PawnModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class BoardController implements Initializable, Observer {

    BoardModel boardModel;

    @FXML
    GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // TODO handler sur la gridPane --> onclick récupération de la column et appelle de la méthode du BoardModel

    @Override
    public void update(Observable observable, Object o) {
        // Récupérations des pions
        PawnModel[][] pawns = boardModel.getPawns();

        // Placement des pions
        for (int i = 0; i < pawns.length; i++) {
            for (int j = 0; j < pawns[i].length; j++) {
                try {
                    // Création de la vue du pion
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/fxml/pawnView.fxml"));
                    Node pawnView     = loader.load();
                    // Association du model à la vue du pion
                    PawnController pawnController = loader.getController();
                    pawnController.setPawnModel(pawns[i][j]);
                    // Ajout du pion dans la vue du plateau
                    gridPane.add(pawnView, i ,j);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* ==================== */
    /*  GETTERS  & SETTERS  */
    /* ==================== */

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void setBoardModel(BoardModel boardModel) {
        this.boardModel = boardModel;
        this.boardModel.addView(this);
        this.boardModel.notifyViews();
    }
}
