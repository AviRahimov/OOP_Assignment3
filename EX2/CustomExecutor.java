import java.util.concurrent.*;

/**
 * A custom thread pool class that defines a method for submitting a generic task as described in
 * the section 1 to a priority queue, and a method for submitting a generic task created by a
 * Callable<V> and a Type, passed as arguments.
 */
public class CustomExecutor extends ThreadPoolExecutor{
    private static final PriorityBlockingQueue<Runnable> task_queue = new PriorityBlockingQueue<>();
    private static final int MinNumOfThreads = (Runtime.getRuntime().availableProcessors())/2;
    private static final int MaxNumOfThreads = (Runtime.getRuntime().availableProcessors())-1;
    private int CurrentMaxPriority;
    public CustomExecutor(){
        super(MinNumOfThreads, MaxNumOfThreads, 300, TimeUnit.MILLISECONDS, task_queue);
    }

    /**
     *
     * @return the maximum priority in the queue in O(1) time & space
     * complexity.
     */
    public int getCurrentMax(){
        return this.CurrentMaxPriority;
    }

    /**
     * After finishing of all tasks submitted to the executor, or if an exception is thrown, terminate
     * the executor.
     */
    public void gracefullyTerminate(){
        super.shutdown();
    }

    /**
     *
     * @param callable representing the task that the executor need to execute.
     * @return Future data type that hold the result of the submission method and, when we write Future.get() it will
     * return the output of the result.
     * @param <V> a generic data type that will be return at the end of the task submission
     */
    public <V> Future<V> submit(Task<V> task){
        if (task == null){
            throw new NullPointerException();
        }
        this.CurrentMaxPriority = task.getPrior();
        return super.submit(task);
    }

    /**
     * @param callable the callable operation that we want to execute.
     * @param taskType the task type(priority) of the task that will be added to the method.
     * @return a Future representing pending completion of the task by sending it to the first submit method.
     */
    public <V> Future submit(Callable<V> callable, TaskType taskType){

        return super.submit(Task.createTask(callable, taskType));
    }

    /**
     * Submits a value-returning task for execution and returns a Future representing the pending results of the task.
     * The Future's get method will return the task's result upon successful completion.
     * @param callable the callable operation that we want to execute.
     * @return a Future representing pending completion of the task by sending it to the first submit method.
     */
    public <V> Future<V> submit(Callable<V> callable){
        return this.submit(new Task<>(callable));
    }

}
