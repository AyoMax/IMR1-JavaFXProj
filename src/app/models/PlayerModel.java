package app.models;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class PlayerModel extends Model {

    static private HashMap<String, PlayerModel> joueurs;
    static public String saveFilepath = "src/data.txt";

    private String name;
    private Color color;
    private int score;

    public PlayerModel(String name) {
        if (PlayerModel.joueurs == null) initJoueursFromFile();

        // TODO : éventuelle récupération du joueur dans le fichier
    }

    public PlayerModel(String name, Color color) {
        this.name = name;
        this.color = color;
        this.score = 0;
    }

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */

    /* DATA FILE MANAGEMENT */
    /**
     * Récupère les joueurs dans le fichier de sauvegarde
     */
    static private void initJoueursFromFile() {
        FileInputStream fin = null;

        try {
            fin = new FileInputStream(PlayerModel.saveFilepath);
            ObjectInputStream oin = new ObjectInputStream(fin);
            joueurs = (HashMap<String, PlayerModel>) oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enregistre les joueurs dans le fichier de sauvegarde
     */
    static public void saveInFile() {

        File saveFile = new File(PlayerModel.saveFilepath);

        try {
            FileOutputStream fout = new FileOutputStream(PlayerModel.saveFilepath);
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(joueurs);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
