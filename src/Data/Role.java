package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Role {
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;

    public Role() {
        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
    }

    public Role(SimpleIntegerProperty id, SimpleStringProperty name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object object) {
        int other;

        if (object instanceof Role) {
            other = ((Role)object).getId();
        } else {
            return false;
        }

        if (this.getId() != other) {
            return false;
        }
        return true;
    }
}
