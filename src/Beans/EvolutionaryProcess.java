package Beans;

import java.util.ArrayList;
import java.util.Comparator;

public class EvolutionaryProcess {
    protected ArrayList<Individual> population;
    protected int generationNb = 0;
    protected IIHM program = null;
    protected double bestFitness;
    protected String problem;

    public EvolutionaryProcess(IIHM _program, String _problem){
        program = _program;
        problem = _problem;
        IndividualFactory.getInstance().Init(problem);
        population = new ArrayList<Individual>();
        for (int i = 0; i < Parameters.individualsNb; i++){
            population.add(IndividualFactory.getInstance().getIndividual(problem));
        }
    }

    private void Survival(ArrayList<Individual> newGeneration){
        population = newGeneration;
    }

    private Individual Selection()
    {
        int totalRanks = (Parameters.individualsNb - 1) * (Parameters.individualsNb + 1) / 2;
        int rand = Parameters.randomGenerator(0, totalRanks);

        int indIndex = 0;
        int nbParts = Parameters.individualsNb;
        int totalParts = 0;
        
        while(totalParts < rand){
            indIndex++;
            totalParts += nbParts;
            nbParts--;
        }

        population.sort(Comparator.comparing(Individual::getFitness));
		return population.get(indIndex);
    }

    public void Run(){
        bestFitness = Parameters.minFitness + 1;
        while (generationNb < Parameters.generationMaxNb && bestFitness > Parameters.minFitness)
        {
            // Evaluation
            for (Individual ind : population) {
                ind.Evaluate();
            }

            // Meilleur individu
            population.sort(Comparator.comparing(Individual::getFitness));
            Individual bestInd = population.get(0);
            program.PrintBestIndividual(bestInd, generationNb);
            bestFitness = bestInd.fitness;

            // Sélection et reproduction
            ArrayList<Individual> newGeneration = new ArrayList<Individual>();
            newGeneration.add(bestInd);
            for (int i = 0; i < Parameters.individualsNb - 1; i++){
                if(Parameters.randomGenerator(0, 100) < Parameters.crossoverRate){
                    // Choisir parents
                    Individual father = Selection();
                    Individual mother = Selection();

                    // Reproduction
                    newGeneration.add(IndividualFactory.getInstance().getIndividual(problem, father, mother));
                } 
                else {
                    // Choisir parents
                    Individual father = Selection();

                    // Reproduction
                    newGeneration.add(IndividualFactory.getInstance().getIndividual(problem, father));
                }
            }
                
            // Survie
            Survival(newGeneration);

            generationNb++;
        }
    }
}
