import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

    private File outputFile;

    @Before
    public void setUp() throws Exception {
        outputFile = new File("output.txt");
        if (outputFile.exists()) {
            outputFile.delete();
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMain() throws Exception {
        // pre-conditions
        Assert.assertFalse(outputFile.exists());

        // execute
        Main.main(null);

        // post conditions
        Assert.assertTrue(outputFile.exists());
        Scanner scanner = new Scanner(outputFile);
        ArrayList<String> lines = new ArrayList();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        Assert.assertEquals(100, lines.size());
        Assert.assertEquals("Locker is open", lines.get(0)); //First
        Assert.assertEquals("Locker is closed", lines.get(1)); /// Second
        Assert.assertEquals("Locker is closed", lines.get(2)); // Third
        Assert.assertEquals("Locker is open", lines.get(3)); // Fourth
        Assert.assertEquals("Locker is closed", lines.get(4)); // Fifth
        Assert.assertEquals("Locker is closed", lines.get(5)); // Sixth
        Assert.assertEquals("Locker is closed", lines.get(6)); // Seventh
        Assert.assertEquals("Locker is closed", lines.get(7)); // Eighth
        Assert.assertEquals("Locker is open", lines.get(8)); // Ninth
        Assert.assertEquals("Locker is closed", lines.get(9)); // Tenth

        Iterator<String> itr = lines.iterator();
        int openLinesCount = 0;

        while(itr.hasNext())
        {
            if(itr.next().equals("Locker is open"))
            {
                openLinesCount++;
            }
        }

        Assert.assertEquals(10, openLinesCount);
    }
}