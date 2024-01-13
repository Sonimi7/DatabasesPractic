package objects;

public class Group {

    private long id;
    private String name;
    private int id_curator;

    public Group(String name, int id_curator) {
        this.name = name;
        this.id_curator = id_curator;
    }

    public Group(long id, String name, int id_curator) {
        this.id = id;
        this.name = name;
        this.id_curator = id_curator;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameGroup() {
        return name;
    }

    public void seNameGroup(String type) {
        this.name = name;
    }

    public int getId_curator() {
        return id_curator;
    }

    public void setId_curator(int id_group) {
        this.id_curator = id_curator;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", id_curator='" + id_curator + '\'' +
                '}';
    }
}
