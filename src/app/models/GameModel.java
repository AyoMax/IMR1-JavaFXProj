package app.models;

public class GameModel extends Model{

    private BoardModel      board;
    private PlayerModel[]   players;
    private int             nbTour;

    public GameModel() {

    }

    public void reinit() {

        this.setNbTour(0);
    }

    /* ==================== */
    /*  GETTERS  & SETTERS  */
    /* ==================== */

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
