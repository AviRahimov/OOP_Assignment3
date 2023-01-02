import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Ex2_1 {
// We need to run the 3 functions except the first one and compare between their complexity time
// and, we also need to create a UML diagram for this class.
    /**
     *
     * @param n
     * @param seed
     * @param bound
     * @return
     * @throws IOException
     */
    public static String[] createTextFiles(int n, int seed, int bound) throws IOException {
        String files[] = new String[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(bound);
            FileWriter file = new FileWriter("file" + i);
        }
        return files;
    }

    /**
     *
     * @param fileNames
     * @return
     * @throws IOException
     */
    public static int getNumOfLines(String[] fileNames) throws IOException {
        int count_lines = 0;
        for(String file_name : fileNames){
            Path file = Paths.get(file_name);
            count_lines+=Files.lines(file).count();
        }
        return count_lines;
    }

    /**
     *
     * @param fileNames
     * @return
     */
//    public int getNumOfLinesThreads(String[] fileNames){
//     Thread_NumOfLines thread_lines = new Thread_NumOfLines();
//    }

    /**
     *
     * @param fileNames
     * @return
     */
//    public int getNumOfLinesThreadPool(String[] fileNames){
//
//    }
}
