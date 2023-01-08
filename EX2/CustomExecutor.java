import java.util.concurrent.*;

public class CustomExecutor{
    private final PriorityBlockingQueue<Runnable> task_queue;
    private final int MinNumOfThreads;
    private final int MaxNumOfThreads;
    private int CurrentMaxPriority;
    private final ThreadPoolExecutor executor;
    public CustomExecutor(){
        this.task_queue = new PriorityBlockingQueue<>();
        this.MinNumOfThreads = (Runtime.getRuntime().availableProcessors())/2;
        this.MaxNumOfThreads = (Runtime.getRuntime().availableProcessors())-1;
        this.executor = new ThreadPoolExecutor(MinNumOfThreads, MaxNumOfThreads, 300, TimeUnit.MILLISECONDS, task_queue);
    }
    public int getCurrentMax(){
        return this.CurrentMaxPriority;
    }
    public void gracefullyTerminate(){
        executor.shutdown();
    }
    public <V> Future<V> submit(Task<V> task){
        this.CurrentMaxPriority = task.prior;
        return executor.submit(task);
    }
    public <V> Future<V> submit(Callable<V> callable, TaskType taskType){
        return this.submit(new Task<>(callable, taskType));
    }
    public <V> Future<V> submit(Callable<V> callable){
        return this.submit(new Task<>(callable));
    }

}
