import java.io.File;
import java.util.ArrayList;
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
        if(outputFile.exists()) {
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

	while(scanner.hasNext()) {
		lines.add(scanner.nextLine());
	}

	Assert.assertEquals(100, lines.size());
	Assert.assertEquals("Locker is open", lines.get(0));
    }
}