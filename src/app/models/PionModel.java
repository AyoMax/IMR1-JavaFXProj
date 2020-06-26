package app.models;

import java.util.Observable;
import java.util.Observer;

public class PionModel extends Observable {

    public enum EtatPion {UNPLAYED, PLAYED};

    private EtatPion etat;
    private JoueurModel joueur;

    public PionModel() {
        this.etat = EtatPion.UNPLAYED;
        this.joueur = null;
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
     * Retourne l'état du pion.
     * @return Etat du pion
     */
    public EtatPion getEtat() {
        return etat;
    }

    /**
     * Définit l'état du pion.
     * @param etat Etat à assigner au pion
     */
    public void setEtat(EtatPion etat) {
        this.etat = etat;
    }

    /**
     * Retourne le joueur du pion.
     * @return Joueur du pion
     */
    public JoueurModel getJoueur() {
        return joueur;
    }

    /**
     * Définit le joueur du pion.
     * @param joueur Joueur à assigner au pion
     */
    public void setJoueur(JoueurModel joueur) {
        this.joueur = joueur;
    }
}
