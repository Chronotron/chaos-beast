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

    //********************

    public ParkerPaulFox() {
        random = new Random();
        setAge(random.nextInt((900 - 400) + 1) + 400);
        setWeight(random.nextInt((60 - 30) + 1) + 30);
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

    //region Helper Methods

    private boolean canCatchChicken() {
        int chance = random.nextInt(71); // chance a chicken is caught
        return chance <= 70;
    }

    private void fattenUp(double weight) {
        setWeight(getWeight() + weight);
    }

    //endregion

} // end ParkerPaulFox class
