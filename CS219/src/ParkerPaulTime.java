/***************************************************
 * ParkerPaulTime.java
 * Paul Parker
 * <p>
 * validates and prints user input time
 ****************************************************/
public class ParkerPaulTime {

    private String error;
    private int hour;
    private int minute;

    //********************

    public ParkerPaulTime(String time) {
        error = null;
        String[] splitTime = time.split(":");
        if (splitTime.length != 2) {
            error = "Invalid or No separator entered";
            return;
        }

        if (!applyHour(splitTime[0])) {
            return;
        }

        if (!validateRange(hour, 0, 23, "Invalid hours entered: ")) {
            return;
        }

        if (!applyMinute(splitTime[1])) {
            return;
        }
        validateRange(minute, 0, 59, "Invalid minutes enter: ");
    }

    //********************

    //region Public Methods

    public void print() {
        int printableHour; // hour value to be printed
        String amPm; // am or pm value to be printed

        if (error != null) {
            System.out.println(error);
        } else {
            // convert 24h to 12 h
            amPm = hour >= 12 ? "PM" : "AM"; // am or pm value to be printed
            if (hour > 12) {
                printableHour = hour - 12;
            } else if (hour == 0) {
                printableHour = 12;
            } else {
                printableHour = hour;
            }
            System.out.printf("%1$02d:%2$02d %3$s%n", printableHour, minute, amPm);
        }
    }

    //endregion

    // region Helper Methods

    private boolean applyHour(String timeHour) {
        try {
            hour = Integer.parseInt(timeHour, 10);
            return true;
        } catch (NumberFormatException ex) {
            error = "Invalid input for time";
            return false;
        }
    }

    private boolean applyMinute(String timeMinute) {
        try {
            minute = Integer.parseInt(timeMinute, 10);
            return true;
        } catch (NumberFormatException ex) {
            error = "Invalid input for time";
            return false;
        }
    }

    private boolean validateRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            error = message + value;
            return false;
        }

        return true;
    }

    // endregion

} // end class ParkerPaulTime
