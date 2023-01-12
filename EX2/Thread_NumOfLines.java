import java.io.IOException;
import java.nio.file.*; // importing Path and Paths
import java.util.concurrent.atomic.AtomicInteger;

/**
 * this class is a helper-class that extends JAVA's Thread class
 * used of counting number of lines inside of file via threads
 * @author Lior Vinman, Avraham Rahimov
 * @version 13 January 2023
 */
public class Thread_NumOfLines extends Thread {

    /**
     * this field is representing a file name
     */
    private String name;

    /**
     * this field is representing the total lines of all instances
     */
    private int total;

    /**
     * standard get() method that returns total field
     * @return total lines of all instances
     */
    public int getTotal() {
        return this.total;
    }

    /**
     * standard constructor method that gets a file name
     * @param fileName - the name of the file
     */
    public Thread_NumOfLines(String fileName) {
        this.name = fileName;
        this.total = 0;
    }

    /**
     * this method is counting number of lines in file, then updates total lines
     */
    @Override
    public void run()  {
        Path file = null;
        try {
            file = Paths.get(this.name);
            total = Math.toIntExact(Files.lines(file).count());
        } catch (InvalidPathException | IOException | SecurityException e) {
            e.printStackTrace();
        }
    }
}
