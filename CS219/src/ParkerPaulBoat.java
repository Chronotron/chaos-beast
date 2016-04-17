/***************************************************
 * ParkerPaulBoat.java
 * Paul Parker
 * <p>
 * class describing a taxable boat
 ****************************************************/
public class ParkerPaulBoat implements  ParkerPaulTaxable {

    private String description; // a String that names and or describe the Boat
    private double footage; // length of the boat in feet;
    private int numberOfCabins; // the number of cabins in the Boat

    public ParkerPaulBoat(String description, double footage, int numberOfCabins) {
        this.description = description;
        this.footage = footage;
        this.numberOfCabins = numberOfCabins;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFootage() {
        return footage;
    }

    public void setFootage(double footage) {
        this.footage = footage;
    }

    public int getNumberOfCabins() {
        return numberOfCabins;
    }

    public void setNumberOfCabins(int numberOfCabins) {
        this.numberOfCabins = numberOfCabins;
    }

    @Override
    public double getTax() {
        return (100 * getFootage()) + (500 * getNumberOfCabins());
    }

} // end class ParkerPaulBoat
