package Data;

import javafx.beans.property.SimpleIntegerProperty;

public class Registration {
    public SimpleIntegerProperty id;
    public SimpleIntegerProperty event_id;
    public SimpleIntegerProperty user_id;
    public SimpleIntegerProperty attendee_id;

    public Registration() {
        id = new SimpleIntegerProperty();
        event_id = new SimpleIntegerProperty();
        user_id = new SimpleIntegerProperty();
        attendee_id = new SimpleIntegerProperty();
    }

    public Registration(Integer id, Integer event_id, Integer user_id, Integer attendee_id) {
        if(this.id == null) {
            this.id = new SimpleIntegerProperty();
            this.event_id = new SimpleIntegerProperty();
            this.user_id = new SimpleIntegerProperty();
            this.attendee_id = new SimpleIntegerProperty();
        }
        this.id.set(id);
        this.event_id.set(event_id);
        this.user_id.set(user_id);
        this.attendee_id.set(attendee_id);
    }

    public int getRegistrationId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setRegistrationId(int id) {
        this.id.set(id);
    }

    public int getEventId() {
        return event_id.get();
    }

    public SimpleIntegerProperty eventIdProperty() {
        return event_id;
    }

    public void setEventId(int event_id) {
        this.event_id.set(event_id);
    }

    public int getUserId() {
        return user_id.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id.set(user_id);
    }

    public int getAttendeeId() {
        return attendee_id.get();
    }

    public SimpleIntegerProperty attendeeIdProperty() {
        return attendee_id;
    }

    public void setAttendeeId(int attendee_id) {
        this.attendee_id.set(attendee_id);
    }
}
