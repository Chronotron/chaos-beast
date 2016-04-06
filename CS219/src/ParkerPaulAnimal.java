/***************************************************
 * ParkerPaulAnimal.java
 * Paul Parker
 * <p>
 * base class describing an animal
 ****************************************************/
public class ParkerPaulAnimal {
    private String name; // name of the animal
    private int age; // age in days
    private double weight; // weight of the animal
    private boolean maleSex; // if it is male

    //**************************************

    public ParkerPaulAnimal() {
        age = 1;
    }

    //**************************************

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean getSex() {
        return maleSex;
    }

    public double getWeight() {
        return weight;
    }

    public void grow() {
        age++;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(boolean maleSex) {
        this.maleSex = maleSex;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

} // end ParkerPaulAnimal class
