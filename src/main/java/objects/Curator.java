package objects;

public class Curator {

    private long id;
    private String fio;

    public Curator(String fio) {
        this.fio = fio;
    }

    public Curator(long id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFioCurator() {
        return fio;
    }

    public void setFioCurator(String type) {
        this.fio = fio;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                '}';
    }
}
