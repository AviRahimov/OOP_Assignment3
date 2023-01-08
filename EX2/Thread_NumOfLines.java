import java.nio.file.*; // importing Path and Paths

/**
 * this class is a helper-class that extends JAVA's Thread class
 * used of counting number of lines inside of file via threads
 *
 * @authors Lior Vinman & Avraham Rahimov
 * @version 10 January 2023
 */
public class Thread_NumOfLines extends Thread {

    /**
     * this field is representing a file name
     */
    private String name;

    /**
     * this field is representing the total lines of all instances
     */
    private static int total;

    /**
     * standard get() method that returns total field
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     * standard constructor method that gets a file name
     * @param fileName - the name of the file
     */
    public Thread_NumOfLines(String fileName) {
        this.name = fileName;
    }

    /**
     * this method is counting number of lines in file, then updates total lines
     */
    @Override
    public void run() {
        Path file = Paths.get(this.name);
        try {
            total += Files.lines(file).count();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
