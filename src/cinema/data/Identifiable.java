package cinema.data;

public interface Identifiable extends Comparable<Identifiable> {
    public abstract long getId();

    @Override
    default int compareTo(Identifiable other) {
        if (this.getId() == other.getId()) {
            return 0;
        }

        if (this.getId() < other.getId()) {
            return -1;
        }

        return 1;
    }
}
