import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Thread_NumOfLines extends Thread{
    String file_name;
    static int count_lines;
    public Thread_NumOfLines(String file_name){
        this.file_name = file_name;
    }

    @Override
    public void run() {
            Path file = Paths.get(file_name);
        try {
            count_lines+= Files.lines(file).count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}