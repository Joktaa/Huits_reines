package Beans;

public class Program implements IIHM {
    public static void main(String[] args){
        Program p = new Program();
        p.Run();
    }

    public void Run(){
        EvolutionaryProcess EP = new EvolutionaryProcess(this, "Problème des huits dames");
        EP.Run();
    }

    @Override
    public void PrintBestIndividual(Individual individual, int generation) {
        System.out.println(generation + "->" + individual);
    }
}
