import java.util.concurrent.Callable;

/**
 * A generic task with a Type that returns a result and may throw an exception.
 * Each task has a priority used for scheduling inferred from the integer value of the task's Type.
 * @param <T> the task data type.
 */
public class Task<T> implements Callable<T>,Comparable<Task<T>>{
    private Callable<T> operation;
    private int prior;

    private Task(Callable<T> operation,TaskType type)
    {
        this.operation = operation;
        this.prior = type.getPriorityValue();
    }
    public Task(Callable<T> operation)
    {
        this.operation = operation;
        this.prior = 2;
    }

    /**
     * creating a new task to execute.
     * @param operation the operation(mission) to do.
     * @param type the priority of the task.
     * @return a new task that will be created in the private constructor.
     */
    public static Task createTask(Callable operation, TaskType type)
    {
        return new Task(operation,type);
    }

    /**
     * creating a new task to execute without a priority for the task that means that the priority will be the
     * default.
     * @param operation the operation(mission) to do.
     * @return a new task that will be created in the public constructor.
     */
    public static Task createTask(Callable operation)
    {
        return new Task(operation);
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     * @return a generic type that represent the computed result.
     * @Throws Exception if unable to compute a result.
     */
    @Override
    public T call(){
        try {
            return this.operation.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param ToBeCompared the object to be compared.
     * @return the value 0 if the tasks task type are equal,
     * a value less than 0 if the current priority is less than the priority of the compared task
     * and a value greater than 0 if the priority of the compared task is less than the current priority.
     */
    @Override
    public int compareTo(Task<T> ToBeCompared) {
        return Integer.compare(this.prior, ToBeCompared.prior);
    }
    public int getPrior() {
        return prior;
    }
}
