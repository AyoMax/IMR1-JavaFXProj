package app;


import app.models.PlayerModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = FXMLLoader.load(getClass().getResource("views/fxml/Main.fxml"));
        primaryStage.setTitle("Puissance 4");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();

//        System.out.println("before create : " + PlayerModel.players.size());
//        PlayerModel pm = PlayerModel.getInstance("bidule", new Color(0,1,0,1));
//        System.out.println("after create : " + PlayerModel.players.size());
//        PlayerModel.saveInFile();
    }


    public static void main(String[] args) {
        launch(args);
    }
}