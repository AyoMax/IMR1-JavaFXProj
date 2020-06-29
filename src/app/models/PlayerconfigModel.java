package app.models;

public class PlayerconfigModel extends Model{
    private int nbPlayer;
    private int minimalGridSize;

    public PlayerconfigModel(){}

    public void setNbPlayer(int nb){
        this.nbPlayer = nb;
        System.out.println("Nombre de joueur : "+nb);
    }
    public int getNbPlayer(){
        return this.nbPlayer;
    }
}
