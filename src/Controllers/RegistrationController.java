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

        col_aId.setCellValueFactory(
                new PropertyValueFactory<>("attendeeId")
        );
        col_aFName.setCellValueFactory(
                new PropertyValueFactory<>("attendeeFName")
        );
        col_aLName.setCellValueFactory(
                new PropertyValueFactory<>("attendeeLName")
        );
        col_aGender.setCellValueFactory(
                new PropertyValueFactory<>("attendeeGender")
        );
        col_aShirt.setCellValueFactory(
                new PropertyValueFactory<>("attendeeShirt")
        );
        col_aRole.setCellValueFactory(
                new PropertyValueFactory<>("attendeeRole")
        );
        col_aDiet.setCellValueFactory(
                new PropertyValueFactory<>("attendeeDiet")
        );

        DAO dao = new AttendeeDAO();

        ObservableList<Attendee> data = ((AttendeeDAO) dao).getUnregisteredAttendees();
        tbl_attendees.setItems(data);

        insertRegistrationButton();

    }
    public void back(ActionEvent event) {
        redirect("advisor_dashboard");
    }

    private void newRegistration(Attendee attendee) {
        RegistrationFormController controller = redirect("registration_form").getController();
        controller.setAttendee(attendee);
    }

    private void insertRegistrationButton() {
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
                return new RegistrationController.RegistrationButtonCell();
            }
        });

        tbl_attendees.getColumns().add(col_action);
    }

    //Define the button cell
    private class RegistrationButtonCell extends TableCell<Attendee, Boolean> {
        final Button cellButton = new Button("New Registration");

        RegistrationButtonCell() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    Attendee attendee = tbl_attendees.getItems().get(getTableRow().getIndex());

                   newRegistration(attendee);
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
