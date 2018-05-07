package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class Invoice {
    public SimpleIntegerProperty id;
    public SimpleIntegerProperty user_id;
    public SimpleIntegerProperty event_id;

    public Invoice() {
        id = new SimpleIntegerProperty();
        user_id = new SimpleIntegerProperty();
        event_id = new SimpleIntegerProperty();
    }

    public Event getEvent() {
        EventDAO eventDao = new EventDAO();

        return eventDao.getEventByID(this.getEventId());
    }

    public ObservableList<InvoiceRow> getInvoiceRows() {
        InvoiceDAO invoiceDao = new InvoiceDAO();

        return invoiceDao.getInvoiceRows(this.getId());
    }

    public ObservableList<Attendee> getAttendees() {
        InvoiceDAO invoiceDao = new InvoiceDAO();

        return invoiceDao.getAttendees(this.getId());
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getUserId() {
        return user_id.get();
    }

    public void setUserId(int user_id) {
        this.user_id.set(user_id);
    }

    public int getEventId() {
        return event_id.get();
    }

    public void setEventId(int event_id) {
        this.event_id.set(event_id);
    }

    public Invoice(SimpleIntegerProperty id, SimpleIntegerProperty user_id, SimpleIntegerProperty event_id) {
        this.id = id;
        this.user_id = user_id;
        this.event_id = event_id;
    }
}
