package app.models;

public class MainModel extends Model {

    public enum EtatMain { MENU, PLAYERCONFIG, GAME, RANK }

    private EtatMain        etat;
    private BoardModel board;
    private PlayerModel[]   players;
    private int             nbTour;

    public MainModel() {
        this.etat = EtatMain.MENU;
    }

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */

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
    }

    public void reinit() {
        this.setNbTour(0);
    }



    /* ==================== */
    /*  GETTERS  & SETTERS  */
    /* ==================== */
    /**
     * Retourne l'état de la vue principale
     * @return Etat de la vue principale
     */
    public EtatMain getState() {
        return etat;
    }

    /**
     * Définit l'état de la vue principale
     * @param etat Etat à assigner à la vue principale
     */
    public void setState(EtatMain etat) {
        this.etat = etat;
    }

    /**
     * Retourne le plateau de la partie
     * @return Plateau de la partie
     */
    public BoardModel getBoard() {
        return board;
    }

    /**
     * Définit le plateau de la partie
     * @param board Plateau à assigner à la partie
     */
    public void setBoard(BoardModel board) {
        this.board = board;
    }

    /**
     * Retourne les joueurs de la partie
     * @return Joueurs de la partie
     */
    public PlayerModel[] getPlayers() {
        return players;
    }

    /**
     * Définit les joueurs de la partie
     * @param players Joueurs à assigner à la partie
     */
    public void setPlayers(PlayerModel[] players) {
        this.players = players;
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
