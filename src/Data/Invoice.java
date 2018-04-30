package Data;

public class Invoice {
    public Integer id;
    public Integer user_id;
    public Integer event_id;

    public Invoice() {}

    public Invoice(Integer id, Integer user_id, Integer event_id) {
        this.id = id;
        this.user_id = user_id;
        this.event_id = event_id;
    }
}
