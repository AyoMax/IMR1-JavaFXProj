package app.models;

import java.util.Observable;
import java.util.Observer;

public class MainModel extends Observable {

    public enum EtatMain { MENU, PLAYERCONFIG, GAME, RANK }

    private EtatMain        etat;
    private PlateauModel    plateau;
    private PlayerModel[]   joueurs;
    private int             nbTour;

    public MainModel() {
        this.etat = EtatMain.MENU;
    }

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */

    public EtatMain nextState() {
        // TODO : ci-dessous: pas bon : depuis menu, on peut aller à playerconfig ou rank
        switch (etat) {
            case MENU:
                etat = EtatMain.PLAYERCONFIG;
                break;
            case PLAYERCONFIG:
                etat = EtatMain.GAME;
                break;
            case GAME:
                etat = EtatMain.RANK;
                break;
            case RANK:
                etat = EtatMain.MENU;
                break;

        }
        return etat;
    }

    public void getStateModel(EtatMain etatMain) {
        // TODO : ci-dessous: pas bon : depuis menu, on peut aller à playerconfig ou rank
        switch (etat) {
            case MENU:

                break;
            case PLAYERCONFIG:
                etat = EtatMain.GAME;
                break;
            case GAME:
                etat = EtatMain.RANK;
                break;
            case RANK:
                etat = EtatMain.MENU;
                break;
        }
        return
        this.notifyViews();
    }

    public void reinit() {
        this.setNbTour(0);
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
     * Retourne l'état de la vue principale
     * @return Etat de la vue principale
     */
    public EtatMain getEtat() {
        return etat;
    }

    /**
     * Définit l'état de la vue principale
     * @param etat Etat à assigner à la vue principale
     */
    public void setEtat(EtatMain etat) {
        this.etat = etat;
    }

    /**
     * Retourne le plateau de la partie
     * @return Plateau de la partie
     */
    public PlateauModel getPlateau() {
        return plateau;
    }

    /**
     * Définit le plateau de la partie
     * @param plateau Plateau à assigner à la partie
     */
    public void setPlateau(PlateauModel plateau) {
        this.plateau = plateau;
    }

    /**
     * Retourne les joueurs de la partie
     * @return Joueurs de la partie
     */
    public PlayerModel[] getJoueurs() {
        return joueurs;
    }

    /**
     * Définit les joueurs de la partie
     * @param joueurs Joueurs à assigner à la partie
     */
    public void setJoueurs(PlayerModel[] joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * Retourne le nombre de tour de la partie
     * @return Nombre de tour de la partie
     */
    public int getNbTour() {
        return nbTour;
    }

    /**
     * Définit le nombre de tour de la partie
     * @param nbTour Nombre de tour à assigner à la partie
     */
    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }
}
