package app.controllers;

import app.models.GameModel;
import app.models.MainModel;
import app.models.PlayerconfigModel;
import app.views.ViewSkill;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class GameController implements Initializable, ViewSkill, Observer {

    MainModel mainModel;
    GameModel viewModel;

    @FXML
    GridPane boardContainer;

    @FXML
    VBox playerlistContainer;

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
        }catch(Exception err){
            System.out.println("Erreur lors du chargement des composants internes de la vue Game.");
            err.printStackTrace();
        }


    }

    public void update(Observable o, Object arg){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation du plateau.");
    }
}
