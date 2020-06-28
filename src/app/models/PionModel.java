package app.models;

import java.util.Observable;
import java.util.Observer;

public class PionModel extends Model {

    public enum EtatPion {UNPLAYED, PLAYED};

    private    EtatPion etat;
    private PlayerModel joueur;

    public PionModel() {
        this.etat = EtatPion.UNPLAYED;
        this.joueur = null;
    }

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */



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

        this.notifyViews();
    }

    /**
     * Retourne le joueur du pion.
     * @return Joueur du pion
     */
    public PlayerModel getJoueur() {
        return joueur;
    }

    /**
     * Définit le joueur du pion.
     * @param joueur Joueur à assigner au pion
     */
    public void setJoueur(PlayerModel joueur) {
        this.joueur = joueur;
    }
}
