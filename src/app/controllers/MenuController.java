package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.valueOf;

public class MenuController implements Initializable {

    @FXML
    protected void onClickPlayerNumberChoice(ActionEvent e){
        Button targetButton = (Button) e.getTarget();
        int nbPlayer = valueOf(targetButton.getId());

        System.out.print(nbPlayer);

        // TODO : MenuModel.setNbPlayer();
        // Préviens le MenuModel du nombre de joueur
        // MenuModel.setNbPlayer();

        // TODO : MainModel.nextState();
        // Change l'application d'état, de menu vers Player Config
        //MainModel.nextState();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}