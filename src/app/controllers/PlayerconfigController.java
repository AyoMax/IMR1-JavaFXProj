package app.controllers;

import app.models.GameModel;
import app.models.MainModel;
import app.models.Model;
import app.models.PlayerconfigModel;
import app.views.ViewSkill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.valueOf;

public class PlayerconfigController implements Initializable, ViewSkill {

    MainModel parentModel;
    PlayerconfigModel viewModel;
    GameModel nextViewModel;

    @FXML
    protected void onClickValidate(ActionEvent e){
        System.out.println("Valider");

        // TODO : MenuModel.setNbPlayer();
        // Préviens le MenuModel du nombre de joueur
        //nextViewModel = parentModel.getPlayerconfigModel();

        // TODO : MainModel.nextState();
        // Change l'application d'état, de menu vers Player Config
        //parentModel.setState(MainModel.MainState.PLAYERCONFIG);
        //parentModel.notifyViews();
    }
    public void setParentModel(MainModel model){

        this.parentModel = model;
        this.init();

    }

    public void init(){
        System.out.println("------------------------------------");
        viewModel = parentModel.getPlayerconfigModel();
        int nbPlayer = viewModel.getNbPlayer();
        System.out.println("Nombre de joueur : "+nbPlayer);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
