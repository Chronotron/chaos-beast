import java.util.ArrayList;

/***************************************************
 * PolymorphismDiscussion.java
 * Paul Parker
 * <p>
 * Demonstrates the concept of Polymorphism
 ****************************************************/
public class PolyMorphismDiscussion {
    public static void main(String[] args) {
        NullPlumbus nullPlumbus = new NullPlumbus(); // standard null plumbus created as such
        IPlumbus nameBrandPlumbus = new NameBrandPlumbus("The Original Plumbus", 5.99); // plumbus interface created as a NameBrandPlumbus
        NullPlumbus offBrandPlumbus = new OffBrandPlumbus(); // a null plubmus created as an off brand plumbus
        ArrayList<IPlumbus> plumbuses = new ArrayList<IPlumbus>(); // collection of the plumbus interface

        // add all plumbuses to the array
        plumbuses.add(nullPlumbus);
        plumbuses.add(nameBrandPlumbus);
        plumbuses.add(offBrandPlumbus);

        for (IPlumbus plumbus : plumbuses) {
            System.out.println("Plumbus Diagnostic");
            System.out.println("**************************");
            String name = plumbus.getBrandName() != null ? plumbus.getBrandName() : "<NONE>";
            double plumbusRatio = plumbus.getPlumbusRatio();
            System.out.printf("Brand name: %s%n", name);
            System.out.printf("Price: %.2f%n", plumbus.getPrice());
            System.out.printf("Plumbus ratio: %.2f%n", plumbusRatio);

            if (plumbusRatio >= 90.00) {
                System.out.println("This is a true plumbus");
            } else if (plumbus instanceof NullPlumbus) {
                System.out.println("We've found a null plumbus");
            }
            System.out.println("**************************");
            System.out.println("");
        }
    }
}
