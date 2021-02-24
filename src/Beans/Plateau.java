package Beans;

public class Plateau extends Individual {
    boolean[][] genome = new boolean[8][8];
    
    public Plateau(){
        int i = 0;
        int j = 0;
        
        // Pour chaque ligne
        for (i = 0; i < 8; i++){
            // Pour chaque colonne
            for (j = 0; j < 8; j++){
                // Mettre la case à faux
                genome[i][j] = false;
            }
        }

        // Pour chaque ligne
        for (i = 0; i < 8; i++){
            // Mettre aléatoirement une case vrai
            int rand = Parameters.randomGenerator(0, 7);
            genome[i][rand] = true;
        }
    }

    public Plateau(Plateau father){
        // Prendre le génome du père et le muter
        genome = father.genome;
        Mutate();
    }

    @Override
    public String toString() {
        String gen = fitness + " : \n";
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                gen += genome[i][j]?1:0;
            }
            gen += "\n";
        }
        return gen;
    }

    @Override
    protected void Mutate() {
        int i = 0;
        int j = 0;
        int current_j = 0;

        // Pour chaque case
        for (i = 0; i < 8; i++){
            for (j = 0; j < 8; j++){
                // Sauvegarde de la case où il y a une reine
                // et on l'enlève
                if (genome[i][j]){
                    current_j = j;
                    genome[i][j] = false;
                }
            }
            int rand = Parameters.randomGenerator(0, 2);
            // Si la case est proche de la fin du plateau, 
            // on la décale, sinon on la bouge ou non de 
            // manière aléatoire d'une case à gauche ou à
            //  droite.
            if (current_j == 0){
                genome[i][current_j + 1] = true;
            }
            else if (current_j == 7){
                genome[i][current_j - 1] = true;
            }
            else if (rand == 0){
                genome[i][current_j - 1] = true;
            }
            else if (rand == 1){
                genome[i][current_j + 1] = true;
            }
            else if (rand == 2){
                genome[i][current_j] = true;
            }
        }
    }

    @Override
    protected double Evaluate() {
        int i = 0;
        int j = 0;

        // Pour chaque reine, on ajoute 1 à la fitness si
        // elle mate une reine en diagonal ou en vertical
        for (i = 0; i < 8; i++){
            for (j = 0; j < 8; j++){
                if (genome[i][j]){
                    if (checkVertical(j) == false){
                        fitness++;
                    }
                    if (checkDiagonal(i, j) == false){
                        fitness++;
                    }
                }
            }
        }

        return fitness;
    }
    
    private boolean checkVertical(int j){
        int i = 0;
        int nbQueen = 0;

        for (i = 0; i < 8; i++){
            if (genome[i][j]){
                nbQueen++;
            }
        }

        return nbQueen>1?false:true;
    }

    private boolean checkDiagonal(int x, int y){
        int i = x;
        int j = y;
        int nbQueen = 0;

        // Vérification haut droite
        while (i > 0 && j > 0){
            i--;
            j--;

            if (genome[i][j]){
                nbQueen++;
            }
        }

        // Vérification bas gauche
        i =x;
        j = y;
        while (i < 7 && j < 7){
            i++;
            j++;

            if (genome[i][j]){
                nbQueen++;
            }
        }

        // Vérification haut gauche
        i =x;
        j = y;
        while (i > 0 && j < 7){
            i--;
            j++;

            if (genome[i][j]){
                nbQueen++;
            }
        }

        // Vérification bas droite
        i =x;
        j = y;
        while (i < 7 && j > 0){
            i++;
            j--;

            if (genome[i][j]){
                nbQueen++;
            }
        }

        return nbQueen>0?false:true;
    }
}
