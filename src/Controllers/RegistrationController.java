package Controllers;

import Data.Attendee;
import Data.Registration;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegistrationController extends Controller {

    @FXML
    TableView<Registration> tbl_attendees;
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

        //TODO: Implement Attendee DAO with method for get all unregistered attendees

    }
    public void back(ActionEvent event) {
        redirect("advisor_dashboard");
    }
}
