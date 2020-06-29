package app.models;

import java.util.Arrays;
import java.util.Comparator;

public class RankModel extends Model {

    public RankModel() {
        super();
    }

    /**
     * Retourne les joueurs triés par nombre de victoire descendant
     * @return Joueurs triés par nombre de victoire descendant
     */
    public PlayerModel[] sortPlayer() {
        // Récupération de tous les joueurs enregistrés
        PlayerModel[] players = (PlayerModel[]) PlayerModel.players.values().toArray();

        // Trie des joueurs par score
        Arrays.sort(players, new Comparator<>() {
            @Override
            public int compare(PlayerModel player1, PlayerModel player2) {
                return player1.compareTo(player2);
            }
        });

        return players;
    }

}
