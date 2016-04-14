/***************************************************
 * NullPlumbus.java
 * Paul Parker
 * <p>
 * null implementation of a plumbus
 ****************************************************/
public class NullPlumbus implements IPlumbus {

    public String getBrandName() {
        return null;
    }

    public double getPrice() {
        return 0.00;
    }

    public double getPlumbusRatio() {
        return 0.00;
    }
}
