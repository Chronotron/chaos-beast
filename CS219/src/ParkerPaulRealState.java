/***************************************************
 * ParkerPaulRealState.java
 * Paul Parker
 * <p>
 * abstract class describing a real state
 ****************************************************/
public abstract class ParkerPaulRealState {
    private String description; // description of the real state
    private double area; // area the state takes up in square feet
    private double unitPrice; // price per unit

    public ParkerPaulRealState(String description, double area, double unitPrice) {
        this.description = description;
        this.area = area;
        this.unitPrice = unitPrice;
    } // end constructor

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
} // end class ParkerPaulRealState
