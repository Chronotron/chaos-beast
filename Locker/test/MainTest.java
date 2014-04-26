import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class MainTest {

    private File outputFile;

    @Before
    public void setUp() throws Exception {
        outputFile = new File("output.txt");
        if(outputFile.exists()) {
            outputFile.delete();
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMain() throws Exception {
        // setup
        Main test = new Main();

        // pre-conditions
        Assert.assertFalse(outputFile.exists());

        // execute
        test.main(null);

        // post conditions
        Assert.assertTrue(outputFile.exists());
    }
}