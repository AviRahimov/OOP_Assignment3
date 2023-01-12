import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TaskTest {
    @Test
    public void testCall() throws ExecutionException, InterruptedException {
        Task<String> task = new Task<>(() -> "Hello World!");
        String result = task.call();
        assertEquals("Hello World!", result);
    }

    @Test
    public void testCreateTask() throws ExecutionException, InterruptedException {
        Task<String> task = Task.createTask(() -> "Hello World!", TaskType.IO);
        String result = task.call();
        assertEquals("Hello World!", result);
    }

    @Test
    public void testCompareTo() {
        Task<String> task1 = Task.createTask(() -> "Hello World!", TaskType.COMPUTATIONAL);
        Task<String> task2 = Task.createTask(() -> "Hello World!", TaskType.IO);
        assertTrue(task1.compareTo(task2) < 0);
    }

    @Test
    public void testGetPrior() {
        Task<String> task = Task.createTask(() -> "Hello World!", TaskType.COMPUTATIONAL);
        assertEquals(1, task.getPrior());
    }
}