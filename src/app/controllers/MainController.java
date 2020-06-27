package app.controllers;

import app.models.MainModel;
import app.views.ViewSkill;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;


public class MainController implements Initializable, Observer {

    private MainModel mainModel;
    private Node actualView;

    @FXML
    private GridPane mainContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mainModel = new MainModel();
        this.mainModel.addView(this);
        this.mainModel.notifyViews();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.print("Observer OK");
        mainContainer.getChildren().remove(actualView);
        try{
            String viewName = mainModel.getEtat().name().toLowerCase()+"View";
            System.out.println("ViewName : "+viewName);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/fxml/"+viewName+".fxml"));
            Node newView = loader.load();
            ((ViewSkill)loader.getController()).setModel(mainModel);
            mainContainer.add( newView, 0, 0);
            actualView = newView;
        }catch (Exception err){
            System.out.println("Problème de chargement de View en fonction de l'état du MainModel.");
        }

    }
}
