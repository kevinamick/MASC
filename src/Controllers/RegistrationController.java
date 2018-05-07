package Controllers;

import Data.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class RegistrationController extends Controller {
    @FXML
    TableView<Attendee> tbl_attendees;
    @FXML
    TableColumn<Attendee,SimpleIntegerProperty> col_aId;
    @FXML
    TableColumn<Attendee, SimpleStringProperty> col_aFName;
    @FXML
    TableColumn<Attendee, SimpleStringProperty> col_aLName;
    @FXML
    TableColumn<Attendee, SimpleStringProperty> col_aGender;
    @FXML
    TableColumn<Attendee, SimpleStringProperty> col_aShirt;
    @FXML
    TableColumn<Attendee, SimpleIntegerProperty> col_aRole;
    @FXML
    TableColumn<Attendee, SimpleStringProperty> col_aDiet;

    @FXML
    public void initialize() {
        assert tbl_attendees != null : "fx:id=\"tbl_attendees\" was not injected";

        col_aId.setCellValueFactory(new PropertyValueFactory<>("attendeeId"));
        col_aFName.setCellValueFactory(new PropertyValueFactory<>("attendeeFname"));
        col_aLName.setCellValueFactory(new PropertyValueFactory<>("attendeeLname"));
        col_aGender.setCellValueFactory(new PropertyValueFactory<>("attendeeGender"));
        col_aShirt.setCellValueFactory(new PropertyValueFactory<>("attendeeShirt"));
        col_aRole.setCellValueFactory(new PropertyValueFactory<>("attendeeRole"));
        col_aDiet.setCellValueFactory(new PropertyValueFactory<>("attendeeDiet"));
    }

    public void loadData() {
        AttendeeDAO attendeeDao = new AttendeeDAO();

        ObservableList<Attendee> data = attendeeDao.getUnregisteredAttendeesByUser(user.getUserId());
        tbl_attendees.setItems(data);

        insertEditButton();
        insertDeleteButton();
    }

    private void editAttendee(Attendee attendee) {
        AttendeeFormController controller = redirect("attendee_form").getController();
        controller.setAttendee(attendee);
        controller.setEvent(event);
        controller.setUser(user);
        controller.loadData();
        controller.fillForm();
    }

    private void deleteAttendee(Attendee attendee) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Delete '" + attendee.getFullName() + "'? This will delete all data associated with this attendee.",
                ButtonType.YES,
                ButtonType.CANCEL
        );

        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            AttendeeDAO attendeeDao = new AttendeeDAO();

            attendeeDao.deleteAttendee(attendee);

            RegistrationController controller = redirect("registration").getController();
            controller.setUser(user);
            controller.setEvent(event);
            controller.loadData();
        }
    }

    public void back(ActionEvent event) {
        AdvisorDashboardController controller = redirect("advisor_dashboard").getController();
        controller.setUser(this.user);
        controller.setEvent(this.event);
        controller.loadData();
    }

    public void newAttendee(ActionEvent event) {
        AttendeeFormController controller = redirect("attendee_form").getController();
        controller.setEvent(this.event);
        controller.setUser(this.user);
        controller.loadData();
    }

    public void completeRegistration(ActionEvent event) {
        // create new invoice
        Invoice invoice = new Invoice();
        invoice.setEventId(this.event.getEventId());
        invoice.setUserId(this.user.getUserId());

        InvoiceDAO invoiceDao = new InvoiceDAO();
        Integer invoice_id = invoiceDao.insertInvoice(invoice);
        invoice.setId(invoice_id);

        // assign attendees to new invoice
        AttendeeDAO attendeeDao = new AttendeeDAO();
        attendeeDao.assignInvoiceToUnregisteredUserAttendees(user.getUserId(), invoice.getId());

        // redirect to invoice
        InvoiceController controller = redirect("invoice").getController();
        controller.setInvoice(invoice);
        controller.setEvent(this.event);
        controller.setUser(this.user);
        controller.fillInvoice();
    }

    private void newRegistration(Attendee attendee) {
        RegistrationFormController controller = redirect("registration_form").getController();
        controller.setAttendee(attendee);
    }

    private void insertEditButton() {
        TableColumn col_action = new TableColumn<>("");
        col_action.setSortable(false);

        col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Attendee, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Attendee, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        col_action.setCellFactory(new Callback<TableColumn<Attendee, Boolean>, TableCell<Attendee, Boolean>>() {
            @Override
            public TableCell<Attendee, Boolean> call(TableColumn<Attendee, Boolean> p) {
                return new RegistrationController.EditButtonCell();
            }
        });

        tbl_attendees.getColumns().add(col_action);
    }

    private void insertDeleteButton() {
        TableColumn col_action = new TableColumn<>("");
        col_action.setSortable(false);

        col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Attendee, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Attendee, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        col_action.setCellFactory(new Callback<TableColumn<Attendee, Boolean>, TableCell<Attendee, Boolean>>() {
            @Override
            public TableCell<Attendee, Boolean> call(TableColumn<Attendee, Boolean> p) {
                return new RegistrationController.DeleteButtonCell();
            }
        });

        tbl_attendees.getColumns().add(col_action);
    }

    //Define the button cell
    private class EditButtonCell extends TableCell<Attendee, Boolean> {
        final Button cellButton = new Button("Edit");

        EditButtonCell() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    AttendeeDAO attendeeDao = new AttendeeDAO();

                    Attendee attendee = attendeeDao.getAttendee(tbl_attendees.getItems().get(getTableRow().getIndex()).getAttendeeId());

                    editAttendee(attendee);
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

    //Define the button cell
    private class DeleteButtonCell extends TableCell<Attendee, Boolean> {
        final Button cellButton = new Button("Delete");

        DeleteButtonCell() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    AttendeeDAO attendeeDao = new AttendeeDAO();

                    Attendee attendee = attendeeDao.getAttendee(tbl_attendees.getItems().get(getTableRow().getIndex()).getAttendeeId());

                    deleteAttendee(attendee);
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
