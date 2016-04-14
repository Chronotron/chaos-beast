/***************************************************
 * OffBrandPlumbus.java
 * Paul Parker
 * <p>
 * off brand version of a plumbus
 ****************************************************/
public class OffBrandPlumbus extends NullPlumbus {

    @Override
    public String getBrandName() {
        return "Plummbuss";
    }

    @Override
    public double getPrice() {
        return 1.00;
    }

}
