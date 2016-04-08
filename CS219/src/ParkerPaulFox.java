import java.util.ArrayList;
import java.util.Random;

/***************************************************
 * ParkerPaulFox.java
 * Paul Parker
 * <p>
 * class describing a fox
 ****************************************************/
public class ParkerPaulFox extends ParkerPaulAnimal {

    private Random random; // random number generator
    private double weightInChickens; // weight in chickens eat in a lifetime

    //********************

    public ParkerPaulFox() {
        random = new Random();
        setAge(random.nextInt((900 - 400) + 1) + 400);
        setWeight(random.nextInt((60 - 30) + 1) + 30);
        weightInChickens = 0.00;
    }

    //********************

    /**
     * randomly selects a chick from collection and adds
     * a portion of the chickens weight to this animals
     * weight
     *
     * @param chickens collection of chickens for eating
     */
    public void eat(ArrayList<ParkerPaulChicken> chickens) {
        /// guard clause - no chickens to eat
        if (chickens.isEmpty()) {
            return;
        }

        // guard clause - didn't catch a chicken
        if (!canCatchChicken()) {
            return;
        }

        ParkerPaulChicken chicken = chickens.get(random.nextInt(chickens.size()));
        fattenUp(chicken.getWeight());
        chickens.remove(chicken);
    }

    /**
     * gets the weight in chickens eaten in a lifetime
     * @return returns weight in chickens eaten in a lifetime as a double
     */
    public double getWeightInChickens() {
        return weightInChickens;
    }

    //region Helper Methods

    private boolean canCatchChicken() {
        int chance = random.nextInt(100) + 1; // chance a chicken is caught
        return chance <= 70;
    }

    private void fattenUp(double weight) {
        weightInChickens += weight;
        setWeight(getWeight() + weight);
    }

    //endregion

} // end ParkerPaulFox class
