package app.models;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.HashMap;

public class PlayerModel extends Model implements Comparable<PlayerModel>, Serializable {

    static final long serialVersionUID = 225;

    static public HashMap<String, PlayerModel> players = new HashMap<>();
    static public String saveFilepath = "src/data.txt";

    private String name;
    private String color; // /!\ On enregistre la couleur sous forme de String et non de Color, car on souhaite serialized notre PlayerModel, or Color n'implémente pas Serializable
    private int score;

    public PlayerModel() {
        this.name = null;
        this.setColor(new Color(0,0,0,1));
        this.score = 0;
    }

    public PlayerModel(String name) {
        this.name = name;
        this.setColor(new Color(0,0,0,1));
        this.score = 0;
    }

    public PlayerModel(String name, Color color) {
        this.name = name;
        this.setColor(color);
        this.score = 0;
    }

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */

    static public PlayerModel getInstance(String name, Color color) {
        if (players.size() == 0) initJoueursFromFile();

        if (players.size() != 0 && players.containsKey(name)) {
            players.get(name).setColor(color);
            System.out.println("Get an old player");
            return players.get(name);
        } else {
            PlayerModel newPlayer = new PlayerModel(name, color);
            players.put(name, newPlayer);
            System.out.println("Get a new player");
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

    @Override
    public String toString() {
        return "PlayerModel{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", score=" + score +
                '}';
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
        return Color.web(color);
    }

    /**
     * Définit la couleur du joueur.
     * @param color Couleur à assigner au joueur
     */
    public void setColor(Color color) {
        this.color = color.toString();
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
