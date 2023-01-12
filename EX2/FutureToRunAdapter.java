import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureToRunAdapter<T> extends FutureTask<T> implements Comparable<FutureToRunAdapter> {

    private int prior;
    public FutureToRunAdapter(Callable<T> operation, int prior){
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
        FutureToRunAdapter<?> that = (FutureToRunAdapter<?>) o;
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
    public int compareTo(FutureToRunAdapter o) {
        return Integer.compare(this.prior, o.prior);
    }
}
