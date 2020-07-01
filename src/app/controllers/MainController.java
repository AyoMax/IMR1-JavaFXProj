package app.controllers;

import app.models.MainModel;
import app.views.ViewSkill;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;


public class MainController implements Initializable, Observer {

    private MainModel mainModel;
    private Node actualView;

    @FXML
    private BorderPane mainContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mainModel = new MainModel();
        this.mainModel.addView(this);
        this.mainModel.notifyViews();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Observer OK");
        mainContainer.getChildren().remove(actualView);
        try{
            String viewName = mainModel.getState().name().toLowerCase()+"View";
            System.out.println("ViewName : "+viewName);
            System.out.println("../views/fxml/"+viewName+".fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/fxml/"+viewName+".fxml"));
            Node newView = loader.load();
            ViewSkill newViewController = ((ViewSkill)loader.getController());
            newViewController.setMainModel(mainModel);
            mainContainer.setCenter(newView);
            actualView = newView;
        }catch (Exception err){
            System.out.println("Problème de chargement de View en fonction de l'état du MainModel.");
            System.out.println(err);
        }

    }
}
