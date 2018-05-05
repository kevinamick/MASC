package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Event {
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;

    public Event() {
        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
    }

    public int getEventId() {
        return id.get();
    }

    public void setEventId(int id) {
        this.id.set(id);
    }

    public String getEventName() {
        return name.get();
    }

    public void setEventName(String name) {
        this.name.set(name);
    }
}
