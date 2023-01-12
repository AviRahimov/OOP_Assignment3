import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import java.io.File;

/**
 * test class for Ex2_1, the first two methods should run first just to assure that is no error in creating and deleting files
 * @author Lior Vinman, Avraham Rahimov
 * @version 13 January 2023
 */
class Ex2_1Test {

    /**
     * test method for createTextFiles() in Ex2_1
     */
    @Test
    @Order(1)
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
    @Order(2)
    void deleteTextFilesTest() {
        String[] fileNames = Ex2_1.createTextFiles(20000, 42, 100);
        Ex2_1.deleteTextFiles(fileNames);
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
        int lines = 0;
        String[] fileNames = null;

        fileNames = Ex2_1.createTextFiles(10, 7, 2);
        lines = Ex2_1.getNumOfLines(fileNames);
        Ex2_1.deleteTextFiles(fileNames);
        Assert.assertEquals(12, lines);

        fileNames = Ex2_1.createTextFiles(10, 15, 20);
        lines = Ex2_1.getNumOfLines(fileNames);
        Ex2_1.deleteTextFiles(fileNames);
        Assert.assertEquals(110, lines);

        fileNames = Ex2_1.createTextFiles(10, 6000, 35);
        lines = Ex2_1.getNumOfLines(fileNames);
        Ex2_1.deleteTextFiles(fileNames);
        Assert.assertEquals(212, lines);
    }

    /**
     * test method for getNumOfLinesThreads() in Ex2_1
     */
    @Test
    void getNumOfLinesThreadsTest() {
        int lines = 0;
        String[] fileNames = null;

        fileNames = Ex2_1.createTextFiles(10, 7, 2);
        lines = Ex2_1.getNumOfLinesThreads(fileNames);
        Ex2_1.deleteTextFiles(fileNames);
        Assert.assertEquals(12, lines);

        fileNames = Ex2_1.createTextFiles(10, 15, 20);
        lines = Ex2_1.getNumOfLinesThreads(fileNames);
        Ex2_1.deleteTextFiles(fileNames);
        Assert.assertEquals(110, lines);

        fileNames = Ex2_1.createTextFiles(10, 6000, 35);
        lines = Ex2_1.getNumOfLinesThreads(fileNames);
        Ex2_1.deleteTextFiles(fileNames);
        Assert.assertEquals(212, lines);

    }

    /**
     * test method for getNumOfLinesThreadPool() in Ex2_1
     */
    @Test
    void getNumOfLinesThreadPoolTest() {
        int lines = 0;
        String[] fileNames = null;

        fileNames = Ex2_1.createTextFiles(10, 7, 2);
        lines = Ex2_1.getNumOfLinesThreadPool(fileNames);
        Ex2_1.deleteTextFiles(fileNames);
        Assert.assertEquals(12, lines);

        fileNames = Ex2_1.createTextFiles(10, 15, 20);
        lines = Ex2_1.getNumOfLinesThreadPool(fileNames);
        Ex2_1.deleteTextFiles(fileNames);
        Assert.assertEquals(110, lines);

        fileNames = Ex2_1.createTextFiles(10, 6000, 35);
        lines = Ex2_1.getNumOfLinesThreadPool(fileNames);
        Ex2_1.deleteTextFiles(fileNames);
        Assert.assertEquals(212, lines);
    }
}
