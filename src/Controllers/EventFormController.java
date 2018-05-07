package Controllers;

import Data.Event;
import Data.EventDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class EventFormController extends Controller {
    @FXML TextField name;
    @FXML TextField price;

    public Event event;

    public void setEvent(Event event) {
        this.event = event;
    }

    public void fillForm() {
        name.setText(event.getEventName());
        price.setText(event.getEventPrice().toString());
    }

    public void back(ActionEvent event) {
        redirect("manage_events");
    }

    public void save(ActionEvent event) {
        EventDAO eventDao = new EventDAO();

        if (this.event == null) {
            this.event = new Event();
            this.event.setEventName(name.getText());
            this.event.setEventPrice(new BigDecimal(price.getText()));
            eventDao.insertEvent(this.event);
        } else {
            this.event.setEventName(name.getText());
            this.event.setEventPrice(new BigDecimal(price.getText()));
            eventDao.updateEvent(this.event);
        }

        redirect("manage_events");
    }
}
