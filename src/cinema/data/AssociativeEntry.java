package cinema.data;

import cinema.service.DatabaseConstants;

public class AssociativeEntry implements Identifiable {
    protected long id, firstId, secondId;

    public AssociativeEntry(long id, long firstId, long secondId) {
        this.id = id;
        this.firstId = firstId;
        this.secondId = secondId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFirstId() {
        return firstId;
    }

    public void setFirstId(long firstId) {
        this.firstId = firstId;
    }

    public long getSecondId() {
        return secondId;
    }

    public void setSecondId(long secondId) {
        this.secondId = secondId;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += id + DatabaseConstants.SEPARATOR;
        ret += firstId + DatabaseConstants.SEPARATOR;
        ret += secondId;

        return ret;
    }
}
