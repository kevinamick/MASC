package Data;

public class Event {
    public Integer id;
    public String name;

    public Event() {}

    public Event(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
