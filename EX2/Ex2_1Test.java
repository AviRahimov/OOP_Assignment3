import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;

/**
 * test class for Ex2_1
 * @author Lior Vinman, Avraham Rahimov
 * @version 13 January 2023
 */
class Ex2_1Test {

    /**
     * test method for createTextFiles() in Ex2_1
     */
    @Test
    public void createTextFilesTest() {
        String[] arr = Ex2_1.createTextFiles(20000, 42, 100); // creating files
        for(String file : arr) {
            File f = new File(file);
            try {
                if(!f.exists()) {
                    Assert.assertFalse("cannot find file: " + file,true);
                }
            } catch (SecurityException e) {
                Assert.assertFalse(e.getMessage(), true);
            }
        }
        Ex2_1.deleteTextFiles(arr);
    }

    /**
     * test method for deleteTextFiles() in Ex2_1
     */
    @Test
    void deleteTextFilesTest() {
        String[] fileNames = Ex2_1.createTextFiles(20000, 42, 100);
        EX2_1.deleteTextFiles(fileNames);
        for(String file : fileNames) {
            File f = new File(file);
            try {
                if(f.exists()) {
                    Assert.assertFalse(file + " is exist but should be deleted!",true);
                }
            } catch (SecurityException e) {
                Assert.assertFalse(e.getMessage(), true);
            }
        }
    }

    /**
     * test method for getNumOfLines() in Ex2_1
     */
    @Test
    void getNumOfLinesTest() {
        String[] fileNames = Ex2_1.createTextFiles(10, 7, 2);
        int lines = Ex2_1.getNumOfLines(fileNames);
        Assert.assertEquals(12, lines);
    }

    /**
     * test method for getNumOfLinesThreads() in Ex2_1
     */
    @Test
    void getNumOfLinesThreadsTest() {
        String[] fileNames = Ex2_1.createTextFiles(10, 7, 2);
        int lines = Ex2_1.getNumOfLinesThreads(fileNames);
        Assert.assertEquals(12, lines);
    }

    /**
     * test method for getNumOfLinesThreadPool() in Ex2_1
     */
    @Test
    void getNumOfLinesThreadPoolTest() {
        String[] fileNames = Ex2_1.createTextFiles(10, 7, 2);
        int lines = Ex2_1.getNumOfLinesThreadPool(fileNames);
        Assert.assertEquals(12, lines);
    }
}
