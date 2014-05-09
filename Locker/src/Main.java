import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("output.txt");
        if(file.exists()) {
            file.delete();
        }
        FileWriter writer = new FileWriter(file, true);

        boolean[] lockers = new boolean[100];
        for (int i = 0; i < lockers.length; i++) {
            lockers[i] = false;
        }

        int[] students = new int[100];
        for (int i = 0; i < students.length; i++) {
            students[i] = i + 1;
        }

        for (int student : students) {

            for (int j = 0; j < lockers.length; j++) {
                if(((j + 1) % student) == 0){
                    lockers[j] = !lockers[j];
                }
            }

        }

        for(boolean locker : lockers){
            String openClosed = locker ? "open" : "closed";
            writer.write(String.format("Locker is %s\n", openClosed));
        }
        writer.close();
    }

}
