package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class InvoiceRow {
    public SimpleIntegerProperty count;
    public SimpleStringProperty name;
    public BigDecimal price;

    public InvoiceRow() {
        this.count = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
    }

    public Integer getCount() {
        return count.get();
    }

    public void setCount(Integer count) {
        this.count.set(count);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return this.price.multiply(new BigDecimal(this.getCount()));
    }
}
