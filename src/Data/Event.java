package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Event {
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;
    public BigDecimal price;

    public Event() {
        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new BigDecimal(BigInteger.TEN);
    }

    public BigDecimal getEventPrice() {
        return price;
    }

    public void setEventPrice(BigDecimal price) {
        this.price = price;
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

    public String toString() {
        return this.name.get();
    }
}
