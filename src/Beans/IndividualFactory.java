package Beans;

public class IndividualFactory {
    private static IndividualFactory instance;
    private IndividualFactory() {}

    public static IndividualFactory getInstance(){
        if (instance == null){
            instance = new IndividualFactory();
        }
        return instance;
    }

    protected void Init(String type){
        // code
    }

    public Individual getIndividual(String type){
        Individual ind = null;
        ind = new Plateau();
        return ind;
    }

    public Individual getIndividual(String type, Individual father){
        Individual ind = null;
        ind = new Plateau((Plateau) father);
        return ind;
    }

    public Individual getIndividual(String type, Individual father, Individual mother){
        Individual ind = null;
        // code
        return ind;
    }
}
