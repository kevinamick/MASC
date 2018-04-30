package Data;

public class Registration {
    public Integer id;
    public Integer event_id;
    public Integer user_id;
    public Integer attendee_id;

    public Registration() {}

    public Registration(Integer id, Integer event_id, Integer user_id, Integer attendee_id) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.attendee_id = attendee_id;
    }
}
