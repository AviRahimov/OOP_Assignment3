import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 *
 * @param <T> a generic class that adapt a callable type to a runnable type because when we use the execute method
 *            we need to pass into it a runnable type but the task is a caalable then, we need an adapter that will
 *           "convert" the callable type to a runnable.
 */
public class CallToRunAdapter<T> extends FutureTask<T> implements Comparable<CallToRunAdapter> {
    // the prior variable representing the priority of the current task.
    private int prior;
    public CallToRunAdapter(Callable<T> operation, int prior){
        super(operation);
        this.prior = prior;
    }

    public int getPrior() {
        return prior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallToRunAdapter<?> that = (CallToRunAdapter<?>) o;
        return prior == that.prior;
    }
    @Override
    public String toString() {
        return "FutureToRunAdapter{" +
                "prior=" + prior +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(prior);
    }

    @Override
    public int compareTo(CallToRunAdapter o) {
        return Integer.compare(this.prior, o.prior);
    }
}
