import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable; // importing Callable interface

/**
 * this class is a helper-class that implements JAVA's callable interface
 * used of counting number of lines inside of file using thread pool
 * @author Lior Vinman, Avraham Rahimov
 * @version 10 January 2023
 */
public class ThreadPool_NumOfLines implements Callable<Integer> {

    /**
     * this field is representing a file name
     */
    private String name;

    /**
     * standard constructor method that gets a file name
     * @param fileName - the name of the file
     */
    public ThreadPool_NumOfLines(String fileName) {
        this.name = fileName;
    }

    /**
     * this method is counting number of lines in file
     * @return the number of lines in file
     * @exception if error occurred when reading new lines
     */
    @Override
    public Integer call() throws Exception {
        int lines = 0;
        Path file = null;
        try {
            file = Paths.get(this.name);
            lines = Math.toIntExact(Files.lines(file).count());
        } catch (InvalidPathException | ArithmeticException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
