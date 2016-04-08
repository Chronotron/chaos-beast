import java.util.ArrayList;

/***************************************************
 * ParkerPaulUnit4.java
 * Paul Parker
 * <p>
 * Simulates chicken population and predation by foxes
 ****************************************************/
public class ParkerPaulUnit4 {

    public static void main(String[] args) {

        // run 10 simulations of chicken growth and fox hunting
        for (int count = 0; count < 10; count++) {
            ParkerPaulFox foxy = new ParkerPaulFox(); // fox that hunts chickens

            ArrayList<ParkerPaulChicken> chickens = new ArrayList<ParkerPaulChicken>(); // chickens that multiply

            // initialize chickens collection
            chickens.clear();
            chickens.add(new ParkerPaulChicken());
            chickens.add(new ParkerPaulChicken());
            chickens.add(new ParkerPaulChicken());
            chickens.get(0).setSex(true);
            chickens.get(1).setSex(false);
            chickens.get(2).setSex(false);

            // cycle until there are no chickens or the chickens overwhelm the fox
            while (chickens.size() > 1 && chickens.size() < 10) {

                // grow all animals
                for (ParkerPaulChicken c : chickens) c.grow();
                foxy.grow();

                // multiply chickens
                ParkerPaulChicken.mate(chickens);

                // hunt chickens
                foxy.eat(chickens);

            } // end of while

            // output results
            if (chickens.size() >= 10) {
                System.out.printf("Chickens win - Chicken Population: %1$d%n", chickens.size());
            } else {
                System.out.printf("Fox wins - Fox Weight (in chickens): %1$.2f%n", foxy.getWeightInChickens());
            }

        } // end of for

    } // end of main

} // end of class
