package app.controllers;

import app.models.MainModel;
import app.models.PawnModel;
import app.views.ViewSkill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PawnController implements Initializable, Observer, ViewSkill {

    private static final Color DEFAULTCOLOR = Color.WHITE;

    private PawnModel pawnModel;

    @FXML
    private Circle circle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setMainModel(MainModel model) {

    }


    @Override
    public void update(Observable observable, Object o) {
        Paint paint = (pawnModel.getEtat() == PawnModel.EtatPion.PLAYED) ? (pawnModel.getJoueur().getColor()) : (DEFAULTCOLOR);
        circle.setFill(paint);
    }

    /* ==================== */
    /*  GETTERS  & SETTERS  */
    /* ==================== */

    /**
     * Retourne le model du pion
     * @return Model assigné à la vue du pion
     */
    public PawnModel getPawnModel() {
        return pawnModel;
    }

    /**
     * Définit le model du pion
     * @param pawnModel Model à assigner à la vue du pion
     */
    public void setPawnModel(PawnModel pawnModel) {
        this.pawnModel = pawnModel;
        this.pawnModel.addView(this);
        this.pawnModel.notifyViews();
    }
}
