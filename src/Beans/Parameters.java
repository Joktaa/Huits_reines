package Beans;

import java.lang.Math;

public class Parameters {
    public static int individualsNb = 20;
    public static int generationMaxNb = 10000000;
    public static int initialGenesNb = 10;
    public static int minFitness = 0;

    public static double mutationsRate = 0.10;
    public static double mutationsAddRate = 0.20;
    public static double mutationsDeleteRate = 0.10;
    public static double crossoverRate = 0; // pourcentage

    public static int randomGenerator(int min, int max){
        int res = (int)(Math.random()*(max-min+1)+min);
        return res;
    }
}
