package app.models;

public class BoardModel extends Model {

    public enum CheckDirection {
        HORIZONTAL      (1, 0),
        VERTICAL        (0, 1),
        DIAGLEFTRIGHT   (1, 1),
        DIAGRIGHTLEFT   (1, -1);

        private int decalCol;
        private int decalRow;

        CheckDirection(int decalCol, int decalRow) {
            this.decalCol = decalCol;
            this.decalRow = decalRow;
        }

        public int decalCol() { return this.decalCol; };
        public int decalRow() { return this.decalRow; };
    };

    private           int nbCol;
    private           int nbRow;
    private PawnModel[][] pawns;

    public BoardModel(int nbRow, int nbCol) {
        this.nbCol = nbCol;
        this.nbRow = nbRow;
        this.pawns = new PawnModel[nbCol][nbRow];

        this.generatePawns();
    }

    /* =========== */
    /*  FONCTIONS  */
    /* =========== */

    /**
     * Instancie la grille de pion.
     */
    private void generatePawns() {
        for (int i = 0; i < nbCol; i++) {
            for (int j = 0; j < nbRow; j++) {
                this.pawns[i][j] = new PawnModel();
            }
        }
    }

    /**
     * Place le pion d'un joueur dans une colonne donnée et indique si le joueur a gagné
     * @param player Joueur qui place un pion
     * @param colIndex Indice de la colonne dans laquelle placer le pion
     * @return true si le joueur a gagné, false sinon
     */
    public boolean playerPlayColumn(PlayerModel player, int colIndex) throws Error {

        int lastPlayRowIndex = findLastPawnPlayedInCol(colIndex);

        if (lastPlayRowIndex != 0) {
            this.pawns[colIndex][lastPlayRowIndex - 1].setJoueur(player);
            this.pawns[colIndex][lastPlayRowIndex - 1].setEtat(PawnModel.EtatPion.PLAYED);
        } else {
            // TODO : Définir une erreur pour indiquer qu'on ne peut plus jouer de pion dans la colonne appelée.
            throw new Error();
        }

        return checkWin(player, colIndex, lastPlayRowIndex - 1);
    }

    /**
     * Trouve le dernier pion joué dans la colonne donnée
     * @param colIndex Numéro de la colonne à analyser
     * @return rowIndex : Numéro de la ligne dans laquelle chercher le dernier pion joué
     */
    private int findLastPawnPlayedInCol(int colIndex) {
        int rowIndex = 0;

        while (this.pawns[colIndex].length > rowIndex && this.pawns[colIndex][rowIndex] != null && this.pawns[colIndex][rowIndex].getEtat() != PawnModel.EtatPion.PLAYED) {
            rowIndex++;
        }

        return rowIndex;
    }

    /**
     * Vérifie si le joueur qui a joué a gagné.
     * @param cPlayer Joueur qui a joué
     * @param cCol Colonne dans lequel le joueur a joué
     * @param cRow Ligne dans laquelle le joueur a joué
     * @return true si le joueur a gagné, false sinon
     */
    private boolean checkWin(PlayerModel cPlayer, int cCol, int cRow) {
        for (CheckDirection direction : CheckDirection.values()) {
            // Si 4 pions sont alignés dans une direction données à partir du pion joué,
            //      alors le joueur a gagné
            if (checkDirection(cPlayer, cCol, cRow, direction)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Vérifie si un joueur a gagné selon une direction donnée, en partant d'un emplacement ou il a joué.
     * @param cPlayer Joueur dont on vérifie la victoire
     * @param origineCol Colonne de départ de la vérification
     * @param origineRow Ligne de départ de la vérification
     * @param direction Direction à vérifier
     * @return true si le joueur a gagné selon la direction donnée, false sinon
     */
    private boolean checkDirection(PlayerModel cPlayer, int origineCol, int origineRow, CheckDirection direction) {
        int compteur = 1;

        // Vérification à droite/en bas du pion d'origine
        int i = 1;
        System.out.println("B1 Col : "+ (origineCol + i * direction.decalCol));
        System.out.println("B1 Row : "+ (origineCol + i * direction.decalRow));
        while ((origineCol + i * direction.decalCol) >= 0
                && (origineRow + i * direction.decalRow) >= 0
                && pawns.length > (origineCol + i * direction.decalCol)
                && pawns[origineCol + i * direction.decalCol].length > (origineRow + i * direction.decalRow)
                && pawns[origineCol + i * direction.decalCol][origineRow + i * direction.decalRow].getJoueur() == cPlayer) {
            i++;
            System.out.println("B1 Col : "+ (origineCol + i * direction.decalCol));
            System.out.println("B1 Row : "+ (origineCol + i * direction.decalRow));
            compteur++;
        }

        // Vérification à gauche/en haut du pion d'origne
        int j = 1;
        System.out.println("B2 Col : "+ (origineCol + i * direction.decalCol));
        System.out.println("B2 Row : "+ (origineCol + i * direction.decalRow));
        while ((origineCol - j * direction.decalCol) >= 0
                && (origineRow - j * direction.decalRow) >= 0
                && pawns.length > (origineCol - j * direction.decalCol)
                && pawns[origineCol - j * direction.decalCol].length > (origineRow - j * direction.decalRow)
                && pawns[origineCol - j * direction.decalCol][origineRow - j * direction.decalRow].getJoueur() == cPlayer) {
            j++;
            System.out.println("B2 Col : "+ (origineCol + i * direction.decalCol));
            System.out.println("B2 Row : "+ (origineCol + i * direction.decalRow));
            compteur++;
        }

        return compteur >= 4;
    }

    /**
     * Vérifie si tout les pions du plateau ont été joués.
     * @return true si tout les pions on été joués, false sinon
     */
    public boolean checkFull() {
        for (PawnModel pawnCol[] : this.pawns) {
            if (pawnCol[0].getEtat() != PawnModel.EtatPion.PLAYED) {
                return false;
            }
        }
        return true;
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
     * Retourne la grille de pion du plateau.
     * @return Grille de pion du plateau
     */
    public PawnModel[][] getPawns() {
        return pawns;
    }

    /**
     * Définit la grille de pion du plateau.
     * @param pawns Grille de pion à assigner au plateau
     */
    public void setPawns(PawnModel[][] pawns) throws Error {
        if (this.pawns.length == this.nbCol) {
            this.pawns = pawns;
        } else {
            // TODO définir une erreur si une grille d'une mauvaise taille est passée en paramètre
            throw new Error();
        }
    }
}
