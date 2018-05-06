package Controllers;

import Data.Event;
import Data.Invoice;
import Data.InvoiceDAO;
import Data.User;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class AdvisorDashboardController extends Controller {
    private User user;
    private Event event;

    @FXML
    private TableView<Invoice> tbl_invoices;
    @FXML private TableColumn<Invoice,Integer> col_iId;
    private ObservableList<Invoice> data;
    private InvoiceDAO dao;

    @FXML
    void initialize() {
        assert tbl_invoices != null : "fx:id=\"tbl_invoices\" was not injected";
    }

    public void loadData() {
        col_iId.setCellValueFactory(new PropertyValueFactory<>("id"));

        dao = new InvoiceDAO();

        data = dao.getInvoicesForUserEvent(user, event);
        tbl_invoices.setItems(data);

        insertViewButton();
    }

    public void viewInvoice(Invoice invoice) {
        InvoiceController controller = redirect("invoice").getController();
        controller.setInvoice(invoice);
        controller.setEvent(event);
        controller.setUser(user);
        controller.fillInvoice();
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUser(User user) { this.user = user; }

    public void back(ActionEvent event) {
        redirect("login");
    }

    public void newRegistration(ActionEvent event) {
        redirect("registration");
    }

    private void insertViewButton() {
        TableColumn col_action = new TableColumn<>("");
        col_action.setSortable(false);

        col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Invoice, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Invoice, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        col_action.setCellFactory(new Callback<TableColumn<Invoice, Boolean>, TableCell<Invoice, Boolean>>() {
            @Override
            public TableCell<Invoice, Boolean> call(TableColumn<Invoice, Boolean> p) {
                return new ViewButtonCell();
            }
        });

        tbl_invoices.getColumns().add(col_action);
    }

    //Define the button cell
    private class ViewButtonCell extends TableCell<Invoice, Boolean> {
        final Button cellButton = new Button("View Invoice");

        ViewButtonCell() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    InvoiceDAO invoiceDao = new InvoiceDAO();

                    Invoice invoice = invoiceDao.getInvoice(tbl_invoices.getItems().get(getTableRow().getIndex()).getId());
                    viewInvoice(invoice);
                }
            });
        }

        // Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) { setGraphic(cellButton); }
        }
    }
}
