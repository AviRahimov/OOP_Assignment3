import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Lior Vinman, Avraham Rahimov
 * @version 13 January 2023
 */
public class Ex2_1 {

    /**
     * this function creates files
     * @param n - number of files that should be created
     * @param seed - for better random scattering
     * @param bound - upper bound for maximum random number
     * @return array with file names
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] files = new String[n];
        Random rand = new Random(seed);
        int randomLines = 0;
        for(int i = 1; i <= n; i++) {
            do {
                randomLines = rand.nextInt(bound);
            } while (randomLines == 0);
            try {
                File f = new File("file_" + i);
                PrintWriter out = new PrintWriter(f);
                for(int j = 0; j < randomLines; j++) {
                    out.println("Hello World");
                }
                out.close();
                files[i - 1] = f.getName();
            } catch (FileNotFoundException | SecurityException e) {
                e.printStackTrace();
            }
        }
        return files;
    }

    /**
     * this function deletes files
     * @param fileNames - array of file names that should be deleted
     */
    public static void deleteTextFiles(String[] fileNames) {
        for (String file : fileNames) {
            File f = new File(file);
            try {
                f.delete();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * this function counts num of lines in file
     * @param fileNames - array of file names
     * @return the total num of lines from all files
     */
    public static int getNumOfLines(String[] fileNames) {
        int linesCount = 0;
        for(String file : fileNames) {
            Path pathToFile = Paths.get(file);
            try {
                linesCount += Files.lines(pathToFile).count();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return linesCount;
    }

    /**
     * this function counts num of lines in file, using threads
     * @param fileNames - array of file names
     * @return the total num of lines from all files
     */
    public static int getNumOfLinesThreads(String[] fileNames) {
        int numLines = 0;
        for(String file : fileNames) {
            Thread_NumOfLines th = new Thread_NumOfLines(file);
            th.run();
            numLines += th.getTotal();
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
        ExecutorService pool = null;
        try {
            pool = Executors.newFixedThreadPool(fileNames.length);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        for(String file : fileNames) {
            Callable<Integer> th = new ThreadPool_NumOfLines(file);
            try {
                Future<Integer> use = pool.submit(th);
                total += use.get();
            } catch (RejectedExecutionException | NullPointerException |
                    CancellationException | ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            pool.shutdown();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return total;
    }
}
