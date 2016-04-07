import java.util.ArrayList;
import java.util.Random;

/***************************************************
 * ParkerPaulChicken.java
 * Paul Parker
 * <p>
 * class describing a chicken
 ****************************************************/
public class ParkerPaulChicken extends ParkerPaulAnimal {


    //********************

    public ParkerPaulChicken() {
        Random random = new Random();
        setAge(1);
        setSex(random.nextInt(2) == 0);
    }

    //********************

    @Override
    public void grow() {
        double currentWeight = getWeight(); // the current weight of the chicken
        double weightIncrease = currentWeight * 0.01; // 1% of the current weight
        setWeight(currentWeight + weightIncrease);
        super.grow();
    }

    public static void mate(ArrayList<ParkerPaulChicken> chickens) {
        Random chickenGenerator = new Random(); // random number generator for generating chickens

        // guard clause - no more chickens or only 1 chicken
        if (chickens.isEmpty() || chickens.size() == 1) {
            return;
        }

        // check if mating is successful
        if (!isMatingSuccessful(chickens)) {
            return;
        }

        // add a random number of chickens
        for (int i = 0; i < chickenGenerator.nextInt(5); i++) {
            chickens.add(new ParkerPaulChicken());
        }
    }

    //region Helper Methods

    private static boolean isChickenOldEnoughToMate(ParkerPaulChicken chicken) {
        return chicken.getAge() > 1;
    }

    private static boolean isMatingSuccessful(ArrayList<ParkerPaulChicken> chickens) {
        ArrayList<ParkerPaulChicken> secondList; // list for selecting second chicken from
        Random chickenSelector = new Random(); // random number generator for selecting chickens
        ParkerPaulChicken initialChicken = chickens.get(chickenSelector.nextInt(chickens.size()));
        ParkerPaulChicken secondChicken; // second chicken for mating

        // check if the first chicken is old enough to mate
        if (!isChickenOldEnoughToMate(initialChicken)) {
            return false;
        }

        // initialize remove first chicken from second list
        secondList = new ArrayList<ParkerPaulChicken>(chickens);
        secondList.remove(initialChicken);

        // find a second chicken from second list
        secondChicken = secondList.get(chickenSelector.nextInt(secondList.size()));

        // second chicken isn't old enough or is the same gender as the first
        if(!isChickenOldEnoughToMate(secondChicken) || (secondChicken.getSex() == initialChicken.getSex())) {
            return false;
        }

        return true;
    }

    // endregion

} // end ParkerPaulChicken class
