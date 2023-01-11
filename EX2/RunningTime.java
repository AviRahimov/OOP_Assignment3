/**
 * this class is for calculating running time of each function of counting number of lines
 * @author Lior Vinman, Avraham Rahimov
 * @version 13 January 2023
 */
public class RunningTime {

    /**
     *
     * @param n - number of files
     * @param seed - for random usage
     * @param bound - maximux lines in each file
     */
    public static boolean CalculateRunningTime(int n, int seed, int bound) {

        /*
         * linesI - holding lines number for every function to check that all function calculated correctly
         * startI - holding starting time, before calling function
         * endI - holding ending time, after calling function
         * intervalI - holding the difference startI - endI, i.e. the time interval (running time) of function calling
         * fileNames - array that holding the files
         */
        int linesA = 0, linesB = 0, linesC = 0;
        double startA = 0, endA = 0, intervalA = 0,
                startB = 0, endB = 0, intervalB = 0,
                startC = 0, endC = 0, intervalC = 0;
        String[] fileNames = null;

        fileNames = Ex2_1.createTextFiles(n, seed, bound);

        /* calculating time of regular lines check */
        startA = System.currentTimeMillis(); // before calling function
        linesA = Ex2_1.getNumOfLines(fileNames); // calling the function itself
        endA = System.currentTimeMillis(); // after calling function
        intervalA = (endA - startA); // calculating time interval

        /* calculating time of lines check using threads only */
        startB = System.currentTimeMillis();
        linesB = Ex2_1.getNumOfLinesThreads(fileNames);
        endB = System.currentTimeMillis();
        intervalB = (endB - startB);

        /* calculating time of lines check using thread-pool */
        startC = System.currentTimeMillis();
        linesC = Ex2_1.getNumOfLinesThreadPool(fileNames);
        endC = System.currentTimeMillis();
        intervalC = (endC - startC);

        if(linesA != linesB || linesA != linesC || linesB != linesC) { // checking that lines are calculated correctly
            System.out.println("ERROR-OCCURRED: lines doesn't match!");
            Ex2_1.deleteTextFiles(fileNames);
            return false;
        } else {
            System.out.println("regular calculating: " + intervalA + " [ms]");
            System.out.println("using threads: " + intervalB + " [ms]");
            System.out.println("using thread-pool: " + intervalC + " [ms]");
            Ex2_1.deleteTextFiles(fileNames);
            return true;
        }
    }
}
