/***************************************************
 * ParkerPaulBuilding.java
 * Paul Parker
 * <p>
 * class describing building real state
 ****************************************************/
public class ParkerPaulBuilding extends ParkerPaulRealState implements ParkerPaulTaxable {

    private double dwellingArea; // the built area of the Building in square feet
    private int numberOfStories; // the number of stories in the Building

    public ParkerPaulBuilding(double dwellingArea, int numberOfStories,String description, double area, double unitPrice) {
        super(description, area, unitPrice);
        setDwellingArea(dwellingArea);
        setNumberOfStories(numberOfStories);
    }

    public double getDwellingArea() {
        return dwellingArea;
    }

    public void setDwellingArea(double dwellingArea) {
        this.dwellingArea = dwellingArea;
    }

    public int getNumberOfStories() {
        return numberOfStories;
    }

    public void setNumberOfStories(int numberOfStories) {
        this.numberOfStories = numberOfStories;
    }

    @Override
    public double getTax() {
        double areaRate = 0.01; // percent of area value that is taxable
        double dwellingRate = 0.02; // percent of dwelling area value that is taxable
        double areaTaxValue = getArea() * getUnitPrice() * areaRate; // value for area
        double dwellingTaxValue = getDwellingArea() * getUnitPrice() * dwellingRate * 3; // value for dwelling area
        return areaTaxValue + dwellingTaxValue;
    }

} // end class ParkerPaulBuilding
