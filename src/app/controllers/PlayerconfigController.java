package app.controllers;

import app.models.*;
import app.views.ViewSkill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.valueOf;

public class PlayerconfigController implements Initializable, ViewSkill {

    private MainModel mainModel;
    private PlayerconfigModel viewModel;
    private GameModel nextViewModel;

    private int nbPlayer;
    private VBox[] playerconfigNode;

    @FXML
    private HBox playerInitContainer;

    @FXML
    private Label errorLabel;

    @FXML
    private Spinner<Integer> rowSpinner;

    @FXML
    private Spinner<Integer> colSpinner;

    @FXML
    protected void onClickReturn(ActionEvent e){
        mainModel.setState(MainModel.MainState.MENU);
        mainModel.notifyViews();
    }

    @FXML
    protected void onClickValidate(ActionEvent e){
        String[] playerName = new String[nbPlayer];
        Color[] playerColor = new Color[nbPlayer];
        boolean err = false;
        String errMsg = "";

        var i = 0;
        // Vérification de toutes les données des joueurs avant insertion dans le Model Player
        while(i < nbPlayer & !err){
            System.out.println("i : "+i+" sur "+nbPlayer);
            // Récupération du nom du joueur
            playerName[i] = ((TextField)playerconfigNode[i].getChildren().get(1)).getText();
            System.out.println(playerName[i]);
            // Récupération de la couleur du joueur
            playerColor[i] = ((ColorPicker)playerconfigNode[i].getChildren().get(2)).getValue();
            System.out.println(playerColor[i]);


            // Les couleurs ne doivent pas être les couleurs de base du plateau
            if(playerColor[i].equals(Color.WHITE) || playerColor[i].equals(Color.BLUE)){
                err = true;
                errMsg = "Les couleurs de base blanches et bleues sont invalides.";
            }

            // Les couleurs ne doivent pas être les couleurs de base du plateau
            if(playerName[i].equals("")){
                err = true;
                errMsg = "Les pseudonymes ne doivent pas être vides.";
            }

            // Vérification du nom entre eux avant enregistrement
            var j = i;
            while(j != 0 && !err){
                System.out.println("i : "+i+" j : "+j);
                if(playerName[i].equals(playerName[j-1])){
                    err = true;
                    errMsg = "Les pseudonymes des joueurs doivent être différent.";
                }
                if(playerColor[i].equals(playerColor[j-1])){
                    err = true;
                    errMsg = "Les couleurs des joueurs doivent être différentes.";
                }
                j--;
            }
            i++;
        }

        // Contrôle de la taille du plateau choisi (pas trop petit pour un grand nombre de joueur)
        int nbRow = rowSpinner.getValue();
        int nbCol = colSpinner.getValue();

        if(nbRow*nbCol <= nbPlayer*4 ){
            // Chaque joueur doit avoir jouer au moins 4 fois sur le plateau pour pouvoir gagner, il faut donc que le nombre de pion jouable soit supérieur ou égale à 4 fois le nombre de joueur sinon c'est impossible de gagner
            err = true;
            errMsg = "Plateau trop petit pour le nombre de joueur";
        }

        if(err){
            errorLabel.setText(errMsg);
        }else{
            errorLabel.setText("Tout est bon");
            PlayerModel[] playerModel = new PlayerModel[nbPlayer];
            for(i = 0; i < nbPlayer; i++){
                playerModel[i] = PlayerModel.getInstance(playerName[i], playerColor[i]);
            }
            nextViewModel = mainModel.getGameModel();
            nextViewModel.reinit();
            nextViewModel.setPlayers(playerModel);
            nextViewModel.setBoard(new BoardModel(nbRow, nbCol));

            mainModel.setState(MainModel.MainState.GAME);
            mainModel.notifyViews();
        }
        System.out.println("Valider");

    }
    public void setMainModel(MainModel model){
        this.mainModel = model;
        this.init();
    }

    public void init(){
        System.out.println("------------------------------------");
        viewModel = mainModel.getPlayerconfigModel();
        this.nbPlayer = viewModel.getNbPlayer();
        System.out.println("Nombre de joueur : "+nbPlayer);

        this.playerconfigNode = new VBox[nbPlayer];

        try{
            for(var i = 0; i < nbPlayer; i++){
                playerconfigNode[i] = FXMLLoader.load(getClass().getResource("../views/fxml/playerconfigPartials.fxml"));
                ((Label)playerconfigNode[i].getChildren().get(0)).setText("Joueur : "+(i+1));
                playerInitContainer.getChildren().add( playerconfigNode[i]);
            }
            rowSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 20));
            colSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 20));
            rowSpinner.getValueFactory().setValue(6);
            colSpinner.getValueFactory().setValue(7);
        }catch(Exception err){
            System.out.println("Plantage des partials");
            System.out.println(err);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
