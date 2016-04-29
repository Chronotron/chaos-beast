import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/***************************************************
 * ParkerPaulUnit7.java
 * Paul Parker
 * <p>
 * Shows a random window color
 ****************************************************/
public class ParkerPaulUnit7 extends JFrame {

    // region Color Constants

    private static final int RED = 0;
    private static final int WHITE = 1;
    private static final int YELLOW = 2;
    private static final int GREEN = 3;
    private static final int BLUE = 4;

    // endregion

    private JLabel inputLabel; // label for name input
    private JLabel resultLabel; // label to display result
    private JTextField nameInput; // input for name

    private ParkerPaulUnit7(Color backgroundColor, Color labelColor) {
        setTitle("Color Changing Frame");
        setSize(500, 125);
        setLayout(new FlowLayout());
        getContentPane().setBackground(backgroundColor);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // initialize controls
        inputLabel = new JLabel("What is your name: ");
        inputLabel.setForeground(labelColor);

        resultLabel = new JLabel();
        resultLabel.setForeground(labelColor);
        resultLabel.setVisible(false);

        nameInput = new JTextField(10);
        nameInput.addActionListener(new Listener());

        add(inputLabel);
        add(nameInput);
        add(resultLabel);

        // center on screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        setVisible(true);
    }

    public static void main(String[] args) {
        int selection = new Random().nextInt(5); // random selection to base color on
        String colorName = getColorName(selection); // name of selected color
        Color color = getColor(selection); // color for window
        Color labelColor = getLabelColor(selection); // color for label
        String message = String.format("The following window color will be randomly chose from Red, White, Yellow, Green, Blue\r\n\r\nYour color will be: %1$s", colorName); // message for dialog
        JOptionPane.showMessageDialog(null, message);
        new ParkerPaulUnit7(color, labelColor);
    }

    // region Helper Methods

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

    private static Color getColor(int selection) {
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

    private static Color getLabelColor(int selection) {
        switch (selection) {
            case WHITE:
            case YELLOW:
                return Color.BLACK;
            case GREEN:
                return Color.BLUE;
            case RED:
            case BLUE:
            default:
                return Color.WHITE;

        }
    }
    // endregion

    // region Helper Classes

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // guard clause - wait for real input
            if(nameInput.getText().length() == 0) {
                return;
            }

            inputLabel.setVisible(false);
            nameInput.setVisible(false);
            resultLabel.setText(String.format("Thanks for playing %s", nameInput.getText()));
            resultLabel.setVisible(true);
        }
    }

    // endregion

} // end class ParkerPaulUnit7
