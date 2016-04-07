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
            chickens.clear();
            chickens.add(new ParkerPaulChicken());
            chickens.add(new ParkerPaulChicken());
            chickens.add(new ParkerPaulChicken());
            chickens.get(0).setSex(true);
            chickens.get(1).setSex(false);
            chickens.get(2).setSex(false);

            // cycle until there are not chickens or the chickens overwhelm the fox
            while (chickens.size() > 1 && chickens.size() < 10) {

                // grow all animals
                for (ParkerPaulChicken c : chickens) c.grow();
                foxy.grow();

                // multiply chickens
                ParkerPaulChicken.mate(chickens);

                // hunt chickens
                foxy.eat(chickens);

            }

            if (chickens.size() >= 10) {

            }
            System.out.println();

        } // end of for

    } // end of main

} // end of class
