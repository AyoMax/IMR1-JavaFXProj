package app.models;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.*;

public class PlayerModel extends Model implements Comparable<PlayerModel>, Serializable {

    static private final long serialVersionUID = 225;

    /**
     * Map des joueurs du jeu, indexés par leur nom.
     */
    static public HashMap<String, PlayerModel> players = new HashMap<>();
    /**
     * Chemin vers le fichier d'enregistrement des joueurs.
     */
    static public String saveFilepath = "src/data.txt";

    private String name;
    private String color; // /!\ On enregistre la couleur sous forme de String et non de Color, car on souhaite serialized notre PlayerModel, or Color n'implémente pas Serializable
    private int score;

    /* =============== */
    /*  CONSTRUCTEURS  */
    /* =============== */

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
        if (players.size() == 0) initPlayersFromFile();

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

    /**
     * Additionne au score du joueur un entier.
     * @param i Entier à additionner au score du joueur
     */
    public void increaseScore(int i) {
        this.setScore(score + i);
    }

    @Override
    public int compareTo(PlayerModel player) {
        return player.getScore() - this.score;
    }

    /**
     * Retourne les joueurs triés par nombre de victoire descendant
     * @return Joueurs triés par nombre de victoire descendant
     */
    static public ArrayList<PlayerModel> sortPlayer() {
        // Récupération de tous les joueurs enregistrés
        ArrayList<PlayerModel> players = new ArrayList<>(PlayerModel.players.values());

        // Trie des joueurs par score
        players.sort(new Comparator<>() {
            @Override
            public int compare(PlayerModel player1, PlayerModel player2) {
                return player1.compareTo(player2);
            }
        });

        return players;
    }

    @Override
    public String toString() {
        return "PlayerModel{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", score=" + score +
                '}';
    }

    /* DATA FILE MANAGEMENT */
    /**
     * Récupère les joueurs dans le fichier de sauvegarde
     */
    static private void initPlayersFromFile() {
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
     * Retourne les joueurs enregistrés dans le fichier de sauvegarde.
     * @return Joueurs enregistrés dans le fichier de sauvegarde
     */
    static public HashMap<String, PlayerModel> getPlayers() {
        if (players.size() == 0) initPlayersFromFile();
        return players;
    }

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
