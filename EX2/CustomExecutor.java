import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * A custom thread pool class that defines a method for submitting a generic task as described in
 * the section 1 to a priority queue, and a method for submitting a generic task created by a
 * Callable<V> and a Type, passed as arguments.
 */
public class CustomExecutor extends ThreadPoolExecutor{
    private static final PriorityBlockingQueue<Runnable> priorityBlockingQueue = new PriorityBlockingQueue<>();
    private static final int MinNumOfThreads = (Runtime.getRuntime().availableProcessors())/2;
    private static final int MaxNumOfThreads = (Runtime.getRuntime().availableProcessors())-1;
    private int LowestPriority = 10;
    private int [] TasksPriority;
    private int CurrentMaxPriority;
    public CustomExecutor(){
        super(MinNumOfThreads, MaxNumOfThreads, 300, TimeUnit.MILLISECONDS, priorityBlockingQueue);
        TasksPriority = new int[LowestPriority];
    }
    /**
     * @param task representing the task that the executor need to execute.
     * @param <V> a generic data type that will be return at the end of the task submission
     * @return Future data type that hold the result of the submission method and, when we write Future.get() it will
     * return the output of the result.
     */
    public <V> Future submit(Task<V> task){
        try {
            TasksPriority[task.getPrior()-1]++;
            CallToRunAdapter callToRunAdapter = new CallToRunAdapter(task, task.getPrior());
            super.execute(callToRunAdapter);
            return callToRunAdapter;
        }
        catch (Exception NullPointerException){
            throw NullPointerException;
        }
    }

    /**
     * @param callable the callable operation that we want to execute.
     * @param taskType the task type(priority) of the task that will be added to the method.
     * @return a Future representing pending completion of the task by sending it to the first submit method.
     */
    public <V> Future submit(Callable<V> callable, TaskType taskType){

        return this.submit(Task.createTask(callable, taskType));
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

    /**
     * These can be used to
     * manipulate the execution environment.
     * for example, reinitializing ThreadLocals, gathering statistics, or adding log entries.
     * @param thread the thread that will run task {@code r}
     * @param runnable the task that will be executed
     */
    @Override
    public void beforeExecute(Thread thread, Runnable runnable){
        try {
            TasksPriority[((CallToRunAdapter<?>)runnable).getPrior()-1]--;
        } catch (Exception e) {
            throw new NullPointerException("You are trying to access null object!!!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomExecutor that = (CustomExecutor) o;
        return LowestPriority == that.LowestPriority && CurrentMaxPriority == that.CurrentMaxPriority && Arrays.equals(TasksPriority, that.TasksPriority);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(LowestPriority, CurrentMaxPriority);
        result = 31 * result + Arrays.hashCode(TasksPriority);
        return result;
    }

    /**
     *
     * @return the maximum priority in the queue in O(1) time & space because we run over the Lowest priority that
     * it's 10 as default the O(10)=O(1) in time complexity.
     * complexity.
     */
    public int getCurrentMax(){
        for (int i = 0; i < LowestPriority; i++) {
            if(this.TasksPriority[i]>0)
                return i+1;
        }
        return 0;
    }

    /**
     * After finishing of all tasks submitted to the executor, or if an exception is thrown, wait
     * the alive time of the idle thread multiplying by the task priority length.
     * we used this time because we want to illustrate the KeepAliveTime method, in our assignment we need to set
     * the KeepAliveTime to 0.3 seconds so, we set it for every cell in the task priority.
     */
    public void gracefullyTerminate(){
        try {
            super.shutdown();
            super.awaitTermination(this.getKeepAliveTime(TimeUnit.MILLISECONDS)*TasksPriority.length, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.shutdown();
    }

    @Override
    public String toString() {
        return "CustomExecutor{" +
                ", TasksPriority=" + Arrays.toString(TasksPriority) +
                ", CurrentMaxPriority=" + CurrentMaxPriority +
                '}';
    }
}
