package app.models;

import java.util.Observable;
import java.util.Observer;

public class BoardModel extends Model {

    private           int nbCol;
    private           int nbRow;
    private PionModel[][] pions;

    public BoardModel() {
        this.nbCol = 7;
        this.nbRow = 6;
        this.pions = new PionModel[nbCol][nbRow];

        this.generatePions();
    }

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */

    /**
     *
     */
    private void generatePions() {
        for (int i = 0; i < nbCol; i++) {
            for (int j = 0; j < nbRow; j++) {
                this.pions[i][j] = new PionModel();
            }
        }
    }

    /**
     *
     * @param joueur
     * @param colIndex
     */
    public void joueurPlayColumn(PlayerModel joueur, int colIndex) throws Error {

        int lastPlayRowIndex = findLastPionPlayedInCol(colIndex);

        if (lastPlayRowIndex != 0) {
            this.pions[colIndex][lastPlayRowIndex - 1].setJoueur(joueur);
            this.pions[colIndex][lastPlayRowIndex - 1].setEtat(PionModel.EtatPion.PLAYED);
        } else {
            // TODO : Définir une erreur pour indiquer qu'on ne peut plus jouer de pion dans la colonne appelée.
            throw new Error();
        }

    }

    /**
     * Trouve le dernier pion joué dans la colonne donnée
     * @param colIndex Numéro de la colonne à analyser
     * @return rowIndex : Numéro de la ligne dans laquelle chercher le dernier pion joué
     */
    private int findLastPionPlayedInCol(int colIndex) {
        int rowIndex = 0;

        while (this.pions[colIndex][rowIndex].getEtat() != PionModel.EtatPion.PLAYED) {
            rowIndex++;
        }

        return rowIndex;
    }


    /* ==================== */
    /*  GETTERS  & SETTERS  */
    /* ==================== */

    /**
     * Retourne le nombre de colonnes du plateau de jeu.
     * @return Nombre de colonnes du plateau de jeu
     */
    public int getNbCol() {
        return nbCol;
    }

    /**
     * Définit le nombre de colonnes du plateau de jeu.
     * @param nbCol Nombre de colonnes à assigner au plateau de jeu
     */
    public void setNbCol(int nbCol) {
        if (nbCol > 0) {
            this.nbCol = nbCol;
        }
    }

    /**
     * Retourne le nombre de lignes du plateau de jeu.
     * @return Nombre de lignes du plateau de jeu
     */
    public int getNbRow() {
        return nbRow;
    }

    /**
     * Définit le nombre de lignes du plateau de jeu.
     * @param nbRow Nombre de lignes à assigner au plateau de jeu
     */
    public void setNbRow(int nbRow) {
        if (nbRow > 0) {
            this.nbRow = nbRow;
        }
    }

    /**
     *
     * @return
     */
    public PionModel[][] getPions() {
        return pions;
    }

    /**
     *
     * @param pions
     */
    public void setPions(PionModel[][] pions) {
        this.pions = pions;
    }
}
