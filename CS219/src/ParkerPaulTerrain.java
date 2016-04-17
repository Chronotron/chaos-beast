/***************************************************
 * ParkerPaulTerrain.java
 * Paul Parker
 * <p>
 * class describing terrain real state
 ****************************************************/
public class ParkerPaulTerrain extends ParkerPaulRealState implements ParkerPaulTaxable {

    private double agroFraction; // fraction of area that can be used for agriculture
    private int numberOfTrees; // number of trees on the inside the terrain

    public ParkerPaulTerrain(double agroFraction, int numberOfTrees, String description, double area, double unitPrice) {
        super(description, area, unitPrice);
        setAgroFraction(agroFraction);
        setNumberOfTrees(numberOfTrees);
    }

    public double getAgroFraction() {
        return agroFraction;
    }

    public void setAgroFraction(double agroFraction) {
        if (agroFraction < 0.00 || agroFraction > 1) {
            return;
        }
        this.agroFraction = agroFraction;
    }

    public int getNumberOfTrees() {
        return numberOfTrees;
    }

    public void setNumberOfTrees(int numberOfTrees) {
        this.numberOfTrees = numberOfTrees;
    }

    @Override
    public double getTax() {
        double rate = 0.01; // percent of value that is taxable
        return ((getArea() * getUnitPrice() * getAgroFraction()) + (10 * getNumberOfTrees())) * rate;
    }

} // end class ParkerPaulTerrain
