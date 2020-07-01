package app.controllers;

import app.models.GameModel;
import app.models.MainModel;
import app.models.PlayerModel;
import app.views.ViewSkill;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class GameController implements Initializable, ViewSkill, Observer {

    private MainModel mainModel;
    private GameModel viewModel;

    @FXML
    private Label nbTourLabel;

    @FXML
    private Label currentPlayerNumber;

    @FXML
    private Label currentPlayerName;

    @FXML
    private Circle playerColor;

    @FXML
    private GridPane boardContainer;

    @FXML
    private VBox playerlistContainer;

    @FXML
    protected void onClickExitGame(ActionEvent e){
        mainModel.restGameModel();
        mainModel.setState(MainModel.MainState.MENU);
        mainModel.notifyViews();
    }


    @Override
    public void setMainModel(MainModel model) {
        mainModel = model;
        this.init();
    }

    public void init(){
        System.out.println("Initialisation du mainModel.");
        viewModel = mainModel.getGameModel();
        viewModel.addView(this);
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/fxml/boardView.fxml"));
            Node boardNode     = loader.load();
            BoardController boardController = loader.getController();
            boardController.setBoardModel(viewModel);
            boardContainer.getChildren().add(boardNode);
            System.out.println("Affichage du plateau ?.");
            refresh();
        }catch(Exception err){
            System.out.println("Erreur lors du chargement des composants internes de la vue Game.");
            err.printStackTrace();
        }
    }



    public void update(Observable o, Object arg){
        refresh();
    }
    public void addRankingBtn(){
        Button rankingBtn = new Button();
        rankingBtn.setText("Classement");
        playerlistContainer.getChildren().add(rankingBtn);
        rankingBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                mainModel.restGameModel();
                mainModel.setState(MainModel.MainState.RANK);
                mainModel.notifyViews();
            }
        });
    }
    public void refresh(){
        if(viewModel.getBoard().checkFull()){
            playerlistContainer.getChildren().remove(currentPlayerNumber);
            playerlistContainer.getChildren().remove(playerColor);
            playerlistContainer.getChildren().remove(currentPlayerName);
            nbTourLabel.setText("Match nul !");
            addRankingBtn();
        }else if(viewModel.getWinner() != null){
            playerlistContainer.getChildren().remove(currentPlayerNumber);
            playerlistContainer.getChildren().remove(playerColor);
            nbTourLabel.setText("Victoire !");
            currentPlayerName.setText("Gagnant : " +viewModel.getWinner().getName());
            addRankingBtn();
            PlayerModel.saveInFile();

        }else{
            nbTourLabel.setText("Tour : "+ viewModel.getNbTour());
            currentPlayerNumber.setText("Joueur : "+ (1+((viewModel.getNbTour()-1) % viewModel.getNbPlayer()))+" sur "+ viewModel.getNbPlayer());
            currentPlayerName.setText("Pseudo : " +viewModel.getCurrentPlayer().getName());
            playerColor.setFill(viewModel.getCurrentPlayer().getColor());
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation du plateau.");

    }
}
