/***************************************************
 * NameBrandPlumbus.java
 * Paul Parker
 * <p>
 * name brand version of a plumbus
 ****************************************************/
public class NameBrandPlumbus extends NullPlumbus {
    private String brandName; // brand name of the plumbus
    private double price; // price of the plumbus
    private double plumbusRatio; // the plumbus ratio

    public NameBrandPlumbus(String brandName, double price) {
        this.brandName = brandName;
        this.price = price;
        this.plumbusRatio = 100.00;
    }

    @Override
    public String getBrandName(){
        return brandName;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getPlumbusRatio() {
        return plumbusRatio;
    }

}
