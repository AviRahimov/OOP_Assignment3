import java.util.concurrent.*;

public class CustomExecutor{
    private PriorityBlockingQueue<Runnable> task_queue;
    private final int MinNumOfThreads;
    private final int MaxNumOfThreads;
    private ThreadPoolExecutor executor;
    public CustomExecutor(){
        this.task_queue = new PriorityBlockingQueue<>();
        this.MinNumOfThreads = (Runtime.getRuntime().availableProcessors())/2;
        this.MaxNumOfThreads = (Runtime.getRuntime().availableProcessors())-1;
        this.executor = new ThreadPoolExecutor(MinNumOfThreads, MaxNumOfThreads, 300, TimeUnit.MILLISECONDS, task_queue);
    }
    public int getCurrentMax(){
//        Task task = null;
//        try {
//            task = (Task) executor.getQueue().take();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        return task.prior;
        return 0;
    }
    public void gracefullyTerminate(){
        executor.shutdown();
    }
    public <V> Future<V> submit(Task task){
        return executor.submit(task);
    }
    public <V> Future<V> submit(Callable<V> callable, TaskType taskType){
        return executor.submit(new Task<V>(callable, taskType));
    }
    public <V> Future<V> submit(Callable<V> callable){
        return executor.submit(new Task<V>(callable));
    }

}
