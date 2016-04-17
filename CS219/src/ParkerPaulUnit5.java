import java.util.ArrayList;
import java.util.Random;

/***************************************************
 * ParkerPaulUnit5.java
 * Paul Parker
 * <p>
 * class demonstrating polymorphism
 ****************************************************/
public class ParkerPaulUnit5 {
    public static void main(String[] args) {
        ArrayList<ParkerPaulTaxable> taxables = new ArrayList<ParkerPaulTaxable>(); // collection of taxables

        // initialize array
        for (int i = 0; i < 20; i++) {
            taxables.add(getRandomTaxable(getNumberBetween(1, 100), i));
        }

        // output table for each type of taxable item
        printTerrainTable(taxables);
        printBuildingTable(taxables);
        printBoatTable(taxables);
    }

    //region Boat Output methods

    private static void printBoatInfo(ParkerPaulBoat taxable) {
        System.out.println("TODO");
    }

    private static void printBoatHeader() {
        System.out.println("TODO");
    }

    private static void printBoatTable(ArrayList<ParkerPaulTaxable> taxables) {
        double totalTaxes = 0.00; // total taxes for items
        double minTaxes = -1.00; // min taxes for items
        double maxTaxes = 0.00; // max taxes for items
        int count = 0; // count of  items

        printBoatHeader();
        for (ParkerPaulTaxable taxable : taxables) {
            if ((taxable instanceof ParkerPaulBoat)) {
                // accumulate footer values
                double tax = taxable.getTax(); // tax for item
                totalTaxes += tax;
                minTaxes = getMinTax(minTaxes, tax);
                maxTaxes = Math.max(maxTaxes, tax);
                count++;
                // print info for item
                printBoatInfo((ParkerPaulBoat) taxable);
            }
        }
        printFooter(totalTaxes, minTaxes, maxTaxes, count);
    }

    //endregion

    //region Building Output methods

    private static void printBuildingInfo(ParkerPaulBuilding taxable) {
        System.out.println("TODO");
    }

    private static void printBuildingHeader() {
        System.out.println("TODO");
    }

    private static void printBuildingTable(ArrayList<ParkerPaulTaxable> taxables) {
        double totalTaxes = 0.00; // total taxes for items
        double minTaxes = -1.00; // min taxes for items
        double maxTaxes = 0.00; // max taxes for items
        int count = 0; // count of  items

        printBuildingHeader();
        for (ParkerPaulTaxable taxable : taxables) {
            if ((taxable instanceof ParkerPaulBuilding)) {
                // accumulate footer values
                double tax = taxable.getTax(); // tax for item
                totalTaxes += tax;
                minTaxes = getMinTax(minTaxes, tax);
                maxTaxes = Math.max(maxTaxes, tax);
                count++;
                // print info for item
                printBuildingInfo((ParkerPaulBuilding) taxable);
            }
        }
        printFooter(totalTaxes, minTaxes, maxTaxes, count);
    }

    //endregion

    //region Terrain Output methods

    private static void printTerrainInfo(ParkerPaulTerrain taxable, int count) {
        System.out.printf("%1$-25s%2$17.2f%3$10.2f%4$13.3f%5$8d%6$10.2f%n", count + " " + taxable.getDescription(), taxable.getArea(), taxable.getUnitPrice(), taxable.getAgroFraction(), taxable.getNumberOfTrees(), taxable.getTax());
    }

    private static void printTerrainHeader() {
        printDivider();
        System.out.printf("%1$-25s%2$17s%3$10s%4$13s%5$8s%6$10s%n", "# Description", "Area (sqf)", "$/sqf", "Frac.Agro", "#Trees", "Taxes");
        printDivider();
    }

    private static void printTerrainTable(ArrayList<ParkerPaulTaxable> taxables) {
        double totalTaxes = 0.00; // total taxes for items
        double minTaxes = -1.00; // min taxes for items
        double maxTaxes = 0.00; // max taxes for items
        int count = 0; // count of  items

        printTerrainHeader();
        for (ParkerPaulTaxable taxable : taxables) {
            if ((taxable instanceof ParkerPaulTerrain)) {
                // accumulate footer values
                double tax = taxable.getTax(); // tax for item
                totalTaxes += tax;
                minTaxes = getMinTax(minTaxes, tax);
                maxTaxes = Math.max(maxTaxes, tax);
                count++;
                // print info for item
                printTerrainInfo((ParkerPaulTerrain) taxable, count);
            }
        }
        printFooter(totalTaxes, minTaxes, maxTaxes, count);
    }

    //endregion

    private static double getMinTax(double currentMin, double tax) {
        if (currentMin < 0) {
            return tax;
        }

        return Math.min(currentMin, tax);
    }

    private static void printFooter(double totalTaxes, double minTaxes, double maxTaxes, int count) {
        count = count > 0 ? count : 1;
        printDivider();
        System.out.printf("%1$68s%2$15.2f%n", "Total taxes =", totalTaxes);
        System.out.printf("%1$68s%2$15.2f%n", "Minimum taxes =", minTaxes);
        System.out.printf("%1$68s%2$15.2f%n", "Maximum taxes =", maxTaxes);
        System.out.printf("%1$68s%2$15.2f%n", "Average taxes =", totalTaxes / count);
        printDivider();
        System.out.println();
    }

    private static void printDivider() {
        System.out.println("---------------------------------------------------------------------------------------");
    }

    /**
     * @param selection selection for determining type
     * @param index     index in the collection initialization
     * @return ParkerPaulTaxable
     */
    private static ParkerPaulTaxable getRandomTaxable(int selection, int index) {
        if (selection < 20) {
            return new ParkerPaulBoat("ParkerPaulBoat " + index, getNumberBetween(10, 50), getNumberBetween(1, 4));
        } else if (selection < 60) {
            return new ParkerPaulTerrain(getFraction(), getNumberBetween(0, 200), "ParkerPaulTerrain " + index, getNumberBetween(100, 10000), getNumberBetween(10, 1000));
        } else {
            return new ParkerPaulBuilding(getNumberBetween(100, 10000), getNumberBetween(1, 4), "ParkerPaulBuild " + index, getNumberBetween(100, 10000), getNumberBetween(10, 1000));
        }
    }

    /**
     * @return fractional double from 0.000 to 1.000
     */
    private static double getFraction() {
        return (double) getNumberBetween(0, 1000) / 1000.00;
    }

    /**
     * @param min minimum inclusive desired number
     * @param max maximum inclusive desired number
     * @return number between min and max
     */
    private static int getNumberBetween(int min, int max) {
        Random random = new Random(); // random number generator
        return random.nextInt((max + 1) - min) + min;
    }

} // end class ParkerPaulUnit5
