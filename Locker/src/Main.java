public class Main {

    public static void main(String[] args) {
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

        for(boolean locker : lockers) System.out.println(locker);
    }
}
