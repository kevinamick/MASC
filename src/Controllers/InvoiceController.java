package Controllers;

import Data.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.math.BigDecimal;

public class InvoiceController extends Controller {
    @FXML Text invoice_id;
    @FXML Text event_name;
    @FXML TableView<InvoiceRow> tbl_invoice_rows;
    @FXML TableColumn<InvoiceRow, String> row_role;
    @FXML TableColumn<InvoiceRow, BigDecimal> row_price;
    @FXML TableColumn<InvoiceRow, Integer> row_count;
    @FXML TableColumn<InvoiceRow, BigDecimal> row_total;
    @FXML Text total;

    public Invoice invoice;
    public Event event;
    public User user;

    @FXML
    void initialize() {
        assert tbl_invoice_rows != null : "fx:id=\"tbl_invoice_rows\" was not injected";

        row_role.setCellValueFactory(new PropertyValueFactory<>("name"));
        row_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        row_count.setCellValueFactory(new PropertyValueFactory<>("count"));
        row_total.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

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

        ObservableList<InvoiceRow> rows = invoice.getInvoiceRows();
        tbl_invoice_rows.setItems(rows);
        total.setText(getTotal(rows).toString());
    }

    public BigDecimal getTotal(ObservableList<InvoiceRow> rows) {
        BigDecimal total;
        total = rows.stream().map(InvoiceRow::getTotal).reduce(BigDecimal.ZERO,BigDecimal::add);

        return total;
    }

    public void back(ActionEvent event) {
        AdvisorDashboardController controller = redirect("advisor_dashboard").getController();
        controller.setEvent(this.event);
        controller.setUser(user);
        controller.loadData();
    }
}
