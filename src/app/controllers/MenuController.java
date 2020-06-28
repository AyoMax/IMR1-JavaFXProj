package app.controllers;

import app.models.MainModel;
import app.models.Model;
import app.views.ViewSkill;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.valueOf;

public class MenuController implements Initializable, ViewSkill {

    MainModel viewModel;
    @FXML
    protected void onClickPlayerNumberChoice(ActionEvent e){
        Button targetButton = (Button) e.getTarget();
        int nbPlayer = valueOf(targetButton.getId());

        System.out.print(nbPlayer);

        // TODO : MenuModel.setNbPlayer();
        // Préviens le MenuModel du nombre de joueur
        //model.setNbPlayer();

        // TODO : MainModel.nextState();
        // Change l'application d'état, de menu vers Player Config
        //MainModel.nextState();
    }
    public void setViewModel(Model model){
        this.viewModel = (MainModel) model;
    }
    public void setModelForControllerInteraction(Model model){
        this.playerconfigModel = (PlayercongigModel) model;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}