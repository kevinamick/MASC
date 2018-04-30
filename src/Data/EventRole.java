package Data;

public class EventRole {
    public Integer event_id;
    public Integer role_id;
    public double price;

    public EventRole() {}

    public EventRole(Integer event_id, Integer role_id, double price) {
        this.event_id = event_id;
        this.role_id = role_id;
        this.price = price;
    }
}
