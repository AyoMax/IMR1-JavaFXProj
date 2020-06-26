package app.models;

import java.util.Observable;
import java.util.Observer;

public class PlateauModel extends Observable {

    private int nbCol;
    private int nbRow;

    public PlateauModel() {
        this.nbCol = 7;
        this.nbRow = 6;
    }

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */

    /* ============ */
    /*  OBSERVABLE  */
    /* ============ */
    public void addView(Observer vue) {
        addObserver(vue);
    }

    public void removeView(Observer vue) {
        deleteObserver(vue);
    }

    public void notifyViews() {
        setChanged();
        notifyObservers();
    }

    /* ==================== */
    /*  GETTERS  & SETTERS  */
    /* ==================== */

    /**
     * Retourne le nombre de colonnes du plateau de jeu.
     * @return Nombre de colonnes du plateau de jeu
     */
    public int getNbCol() {
        return nbCol;
    }

    /**
     * Définit le nombre de colonnes du plateau de jeu.
     * @param nbCol Nombre de colonnes à assigner au plateau de jeu
     */
    public void setNbCol(int nbCol) {
        if (nbCol > 0) {
            this.nbCol = nbCol;
        }
    }

    /**
     * Retourne le nombre de lignes du plateau de jeu.
     * @return Nombre de lignes du plateau de jeu
     */
    public int getNbRow() {
        return nbRow;
    }

    /**
     * Définit le nombre de lignes du plateau de jeu.
     * @param nbRow Nombre de lignes à assigner au plateau de jeu
     */
    public void setNbRow(int nbRow) {
        if (nbRow > 0) {
            this.nbRow = nbRow;
        }
    }
}
