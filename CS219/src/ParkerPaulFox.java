import java.util.ArrayList;
import java.util.Random;

/***************************************************
 * ParkerPaulFox.java
 * Paul Parker
 * <p>
 * class describing a fox
 ****************************************************/
public class ParkerPaulFox extends ParkerPaulAnimal {

    //********************

    public ParkerPaulFox() {
        Random random = new Random(); // random number generator
        setAge(random.nextInt((900 - 400) + 1) + 400);
        setWeight(random.nextInt((60 - 30) + 1) + 30);
    }

    //********************

    public void eat(ArrayList<ParkerPaulChicken> chickens) {
        // TODO:PAP
    }

}
