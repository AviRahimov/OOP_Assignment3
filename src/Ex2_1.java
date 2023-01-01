import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ex2_1 {

    public static String[] createTextFiles(int n, int seed, int bound){
        String files[] = new String[n];
        for (int i = 0; i < n; i++) {
            File file = new File("file_" + i);
            files[i] = file.getName();
        }
        return files;
    }
    public static int getNumOfLines(String[] fileNames) throws IOException {
        int count_lines = 0;
        for(String file_name : fileNames){
            Path file = Paths.get(file_name);
            count_lines+=Files.lines(file).count();
        }
        return count_lines;
    }
//    public int getNumOfLinesThreads(String[] fileNames){
//     Thread_NumOfLines thread_lines = new Thread_NumOfLines();
//    }
}
