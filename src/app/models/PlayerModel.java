package app.models;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.HashMap;

public class PlayerModel extends Model implements Comparable<PlayerModel> {

    static public HashMap<String, PlayerModel> players = new HashMap<>();
    static public String saveFilepath = "src/data.txt";

    private String name;
    private Color color;
    private int score;

    public PlayerModel(String name) {
        this.name = name;
        this.color = new Color(0,0,0,1);
        this.score = 0;
    }

    public PlayerModel(String name, Color color) {
        this.name = name;
        this.color = color;
        this.score = 0;
    }

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */

    static public PlayerModel getInstance(String name, Color color) {
        if (players.size() == 0) initJoueursFromFile();

        if (players.size() != 0 && players.containsKey(name)) {
            players.get(name).setColor(color);
            System.out.println("oldPlayer");
            return players.get(name);
        } else {
            PlayerModel newPlayer = new PlayerModel(name, color);
            players.put(name, newPlayer);
            System.out.println("newPlayer");
            return newPlayer;
        }
    }

    @Override
    public int compareTo(PlayerModel player) {
        return this.score - player.getScore();
    }

    /* DATA FILE MANAGEMENT */
    /**
     * Récupère les joueurs dans le fichier de sauvegarde
     */
    static private void initJoueursFromFile() {
        FileInputStream fin = null;
        ObjectInputStream oin = null;

        try {
            fin = new FileInputStream(PlayerModel.saveFilepath);
            oin = new ObjectInputStream(fin);
            PlayerModel.players = (HashMap<String, PlayerModel>) oin.readObject();
        } catch (EOFException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oin != null) {
                try {
                    oin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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
            oout.writeObject(players);
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
