import javax.swing.*;
import java.awt.*;
import java.util.Random;

/***************************************************
 * ParkerPaulUnit7.java
 * Paul Parker
 * <p>
 * Shows a random window color
 ****************************************************/
public class ParkerPaulUnit7 {

    private static final int RED = 0;
    private static final int WHITE = 1;
    private static final int YELLOW = 2;
    private static final int GREEN = 3;
    private static final int BLUE = 4;

    public static void main(String[] args) {
        int selection = new Random().nextInt(5); // random selection to base color on
        String colorName = getColorName(selection); // name of selected color
        Color color = getRandomColor(selection); // color for window
        String message = String.format("The following window color will be randomly chose from Red, White, Yellow, Green, Blue\r\n\r\nYour color will be: %1$s", colorName); // message for dialog
        JOptionPane.showMessageDialog(null, message);
    }

    private static String getColorName(int selection) {
        switch (selection) {
            case RED:
                return "RED";
            case WHITE:
                return "WHITE";
            case YELLOW:
                return "YELLOW";
            case GREEN:
                return "GREEN";
            case BLUE:
            default:
                return "BLUE";

        }
    }

    private static Color getRandomColor(int selection) {
        switch (selection) {
            case RED:
                return Color.RED;
            case WHITE:
                return Color.WHITE;
            case YELLOW:
                return Color.YELLOW;
            case GREEN:
                return Color.GREEN;
            case BLUE:
            default:
                return Color.BLUE;

        }
    }

}
