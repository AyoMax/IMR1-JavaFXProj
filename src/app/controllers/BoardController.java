package app.controllers;

import app.models.BoardModel;
import app.models.GameModel;
import app.models.PawnModel;
import app.models.PlayerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class BoardController implements Initializable, Observer {

    private GameModel gameModel;
    private BoardModel boardModel;

    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("View plateau");
    }

    // TODO handler sur la gridPane --> onclick récupération de la column et appelle de la méthode du BoardModel

    @Override
    public void update(Observable observable, Object o) {
        refresh();
    }

    public void refresh(){
        // Récupérations des pions
        PawnModel[][] pawns = boardModel.getPawns();

        // Placement des pions
        for (int i = 0; i < pawns.length; i++) {
            for (int j = 0; j < pawns[i].length; j++) {
                try {
                    // Création de la vue du pion
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/views/fxml/pawnView.fxml"));
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

    @FXML
    private void onClickBoard(MouseEvent e){
        // Vérification qu'il n'y ait pas de vainqueur pour continuer la partie (Provisoire --> on essaiera de unbind l' eventHandler
        if(gameModel.getWinner() == null){
            Node source = (Node)e.getTarget();
            Integer colIndex = GridPane.getColumnIndex(source);
            if(colIndex == null && source instanceof Circle){
                colIndex = GridPane.getColumnIndex(((Circle)source).getParent());
            }
            if(colIndex != null){
                try{
                    PlayerModel currentPlayer = gameModel.getCurrentPlayer();
                    Boolean checkVictory = boardModel.playerPlayColumn(currentPlayer, colIndex);
                    System.out.println(checkVictory);
                    if(checkVictory){
                        gameModel.setWinner(currentPlayer);
                        gridPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, this::onClickBoard);
                    }else{
                        gameModel.nextTurn();
                    }
                    gameModel.notifyViews();
                }catch(Error err){
                    System.out.println("Colonne pleine.");
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

    public void setBoardModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.boardModel = gameModel.getBoard();
        this.boardModel.addView(this);
        this.boardModel.notifyViews();
        refresh();
    }
}
