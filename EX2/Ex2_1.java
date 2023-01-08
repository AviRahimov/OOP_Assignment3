import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

/**
 * TODO:
 * uml diagrams, complexity of 2,3,4, readme
 */
public class Ex2_1 {
    /**
     * this function creates files
     * @param n - number of files that should be created
     * @param seed - for better random
     * @param bound - upper bound for maximum random number
     * @return
     * @throws IOException
     */
    public static String[] createTextFiles(int n, int seed, int bound) throws IOException {
        String files[] = new String[n];
        Random rand = new Random(seed);
        int x = 0;
        for (int i = 0; i < n; i++) {
            do {
                x = rand.nextInt(bound);
            } while (x == 0);
            try {
                File file = new File("file" + i);
                PrintWriter out = new PrintWriter(file);
                for (int j = 0; j < x; j++) {
                    out.println("Hello World" + i);
                }
                out.close();
                files[i] = file.getName();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files;
    }

    /**
     * this function deletes the files that function createTextFiles() creates
     * @param fileNames - array of file names that should be deleted
     * @return true iff all files has been deleted successfully, false else
     */
    public static boolean deleteTextFiles(String[] fileNames) {
        File file = null;
        for (String fileName : fileNames) {
            file = new File(fileName);
            if(!file.delete()) {
                return false;
            }
        }
        return true;
    }


    /**
     * this function counts num of lines in file
     * @param fileNames - array of file names
     * @return the total num of lines from all files
     * @throws IOException
     */
    public int getNumOfLines(String[] fileNames) throws IOException {
        int count_lines = 0;
        for(String file_name : fileNames){
            Path file = Paths.get(file_name);
            count_lines+=Files.lines(file).count();
        }
        return count_lines;
    }

    /**
     * this function counts num of lines in file, using threads
     * @param fileNames - array of file names
     * @return the total num of lines from all files
     */
    public int getNumOfLinesThreads(String[] fileNames) {
        int numLines = 0;
        Thread_NumOfLines th = null;
        for(String file : fileNames) {
            th = new Thread_NumOfLines(file);
            th.run();
            numLines = th.getTotal();
        }
        return numLines;
    }

    /**
     * this function counts num of lines in file, using threadPool
     * @param fileNames - array of file names
     * @return the total num of lines from all files
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int total = 0;
        ExecutorService pool = Executors.newFixedThreadPool(fileNames.length);
        Callable<Integer> th = null;
        Future<Integer> use = null;
        for(String file : fileNames) {
            th = new ThreadPool_NumOfLines(file);
            use = pool.submit(th);
            try {
                total += use.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        return total;
    }
}
