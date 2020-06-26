package app.models;

import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class JoueurModel extends Observable {

    private String name;
    private Color color;
    private int score;

    public JoueurModel(String name, Color color) {
        this.name = name;
        this.color = color;
        this.score = 0;
    }

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
     * Retourne le nom du joueur.
     * @return Nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du joueur.
     * @param name Nom à assigner au joueur
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la couleur du joueur.
     * @return Couleur du joueur
     */
    public Color getColor() {
        return color;
    }

    /**
     * Définit la couleur du joueur.
     * @param color Couleur à assigner au joueur
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Retourne le score du joueur.
     * @return Score du joueur
     */
    public int getScore() {
        return score;
    }

    /**
     * Définit le score du joueur
     * @param score Score à assigner au joueur
     */
    public void setScore(int score) {
        if (score >= 0) {
            this.score = score;
        } else {
            throw new Error();
        }
    }
}
