package app.controllers;

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

public class MenuController implements Initializable, ViewSkill {

    private MainModel parentModel;
    private Model viewModel;
    private PlayerconfigModel nextViewModel;

    @FXML
    protected void onClickPlayerNumberChoice(ActionEvent e){
        Button targetButton = (Button) e.getTarget();
        int nbPlayer = valueOf(targetButton.getId());

        System.out.print(nbPlayer);

        // TODO : MenuModel.setNbPlayer();
        // Préviens le MenuModel du nombre de joueur
        nextViewModel = parentModel.getPlayerconfigModel();
        nextViewModel.setNbPlayer(nbPlayer);

        // TODO : MainModel.nextState();
        // Change l'application d'état, de menu vers Player Config
        parentModel.setState(MainModel.MainState.PLAYERCONFIG);
        parentModel.notifyViews();
    }
    @FXML
    protected void onClickRankingButton(ActionEvent e){
        parentModel.setState(MainModel.MainState.RANK);
        parentModel.notifyViews();
    }


    public void setMainModel(MainModel model){
        this.parentModel = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}