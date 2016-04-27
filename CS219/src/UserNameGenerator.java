import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/***************************************************
 * UserNameGenerator.java
 * Paul Parker
 * <p>
 * Demonstrates inheritance
 ****************************************************/
public class UserNameGenerator extends JFrame {

    private JTextField firstNameField; // first name input
    private JTextField lastNameField; // last name input
    private JLabel generatedName; // the generated user name

    public UserNameGenerator() {
        setTitle("User Name Generator");
        setSize(250, 125);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addControls();
        // center on screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        setVisible(true);
    }

    private void addControls() {
        InputListener inputListener = new InputListener(); // listener for user input
        JLabel firstName = new JLabel("First Name:"); // non-interactive fist name label
        JLabel lastName = new JLabel("Last Name:"); // non-interactive last name label
        JLabel userName = new JLabel("Your User Name: "); // non-interactive user name label
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        generatedName = new JLabel();

        // load controls into frame
        add(firstName);
        add(firstNameField);
        firstNameField.addKeyListener(inputListener);
        add(lastName);
        add(lastNameField);
        lastNameField.addKeyListener(inputListener);
        add(userName);
        add(generatedName);
    }

    public static void main(String[] args) {
        new UserNameGenerator();
    }

    private class InputListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            String firstNameString = firstNameField.getText();
            String lastNameString = lastNameField.getText();
            // if it is long enough extract the first letter of the first name
            if (firstNameString.length() != 0) {
                firstNameString = firstNameString.substring(0, 1);
            }
            // remove characters which cannot be used in a username
            generatedName.setText((firstNameString + lastNameString).replaceAll("[^A-Za-z0-9]", "").replaceAll(" ", "").toUpperCase());
        }
    }

} // end class UserNameGenerator
