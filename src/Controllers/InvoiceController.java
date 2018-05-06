package Controllers;

import Data.Event;
import Data.Invoice;
import Data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class InvoiceController extends Controller {
    @FXML Text invoice_id;
    @FXML Text event_name;

    public Invoice invoice;
    public Event event;
    public User user;

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void fillInvoice() {
        invoice_id.setText(Integer.toString(invoice.getId()));
        event_name.setText(invoice.getEvent().getEventName());
    }

    public void back(ActionEvent event) {
        AdvisorDashboardController controller = redirect("advisor_dashboard").getController();
        controller.setEvent(this.event);
        controller.setUser(user);
        controller.loadData();
    }
}
