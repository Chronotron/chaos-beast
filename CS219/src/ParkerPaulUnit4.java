import java.util.ArrayList;

/***************************************************
 * ParkerPaulUnit4.java
 * Paul Parker
 * <p>
 * Simulates chicken population and predation by foxes
 ****************************************************/
public class ParkerPaulUnit4 {

    public static void main(String [] args) {

        for(int count=0; count<10; count++)  {

            ParkerPaulFox foxy = new ParkerPaulFox();

            ArrayList< ParkerPaulChicken > chickens =
                    new ArrayList<ParkerPaulChicken>();
            chickens.clear();
            chickens.add(new ParkerPaulChicken());
            chickens.add(new ParkerPaulChicken ());
            chickens.add(new ParkerPaulChicken ());
            chickens.get(0).setSex(true);
            chickens.get(1).setSex(false);
            chickens.get(2).setSex(false);

            while (chickens.size() >1 && chickens.size() < 10) {
                for (ParkerPaulChicken c:chickens) c.grow();
                foxy.grow();

                ParkerPaulChicken.mate(chickens);
                foxy.eat(chickens);

            }

            //INCLUDE CODE FOR OUTPUT HERE.

        } // end of for

    } // end of main

} // end of class
