package app.models;

public class MainModel extends Model {

    public enum MainState { MENU, PLAYERCONFIG, GAME, RANK }

    private MainState       state;

    // Sous-modèle
    private Model               menuModel;
    private PlayerconfigModel   playerconfigModel;
    private GameModel           gameModel;
    private Model               rankModel;




    public MainModel() {
        this.state = MainState.MENU;
    }

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */


    public Model getMenuModel(){
        return null;
    }

    public PlayerconfigModel getPlayerconfigModel(){
        if(this.playerconfigModel == null){
            this.playerconfigModel = new PlayerconfigModel();
        }
        return this.playerconfigModel;
    }
    public GameModel getGameModel(){
        if(this.gameModel == null){
            this.gameModel = new GameModel(); //TODO : GameModel
        }
        return this.gameModel;
    }
    public Model getRankModel(){
        if(this.rankModel == null){
            this.rankModel = new PlayerconfigModel(); //TODO : RankModel
        }
        return this.rankModel;
    }


    /* ==================== */
    /*  GETTERS  & SETTERS  */
    /* ==================== */
    /**
     * Retourne l'état de la vue principale
     * @return Etat de la vue principale
     */
    public MainModel.MainState getState() {
        return state;
    }

    /**
     * Définit l'état de la vue principale
     * @param etat Etat à assigner à la vue principale
     */
    public void setState(MainModel.MainState etat) {
        this.state = etat;
    }



}
