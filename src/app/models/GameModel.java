package app.models;

public class GameModel extends Model{

    private BoardModel      board;
    private PlayerModel[]   players;
    private int             nbTour;

    private PlayerModel winner;

    public GameModel() {
        this.nbTour = 1;
    }

    public int getNbPlayer(){
        return players.length;
    }

    public PlayerModel getCurrentPlayer(){
        int nbPlayer = getNbPlayer();

        System.out.println(nbPlayer);
        System.out.println((nbTour-1) % nbPlayer);
        return players[(nbTour-1) % nbPlayer];
    }

    public void nextTurn(){
        this.nbTour++;
    }

    public void reinit() {
        this.setNbTour(1);
    }

    /* ==================== */
    /*  GETTERS  & SETTERS  */
    /* ==================== */
    /**
     * Retourne le gagnant de la partie
     * @return gagnant de la partie
     */
    public PlayerModel getWinner() {
        return winner;
    }
    /**
     * Retourne le gagnant de la partie
     * @return gagnant de la partie
     */
    public void setWinner(PlayerModel winner){
        this.winner = winner;
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
