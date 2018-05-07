package Controllers;

import Data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AttendeeFormController extends Controller {
    @FXML TextField fname;
    @FXML TextField lname;
    @FXML TextField gender;
    @FXML TextField shirt;
    @FXML TextField diet_prefs;
    @FXML ChoiceBox<Role> role;

    public Attendee attendee;

    @FXML
    public void initialize() {

    }

    public void loadData() {
        // get role list
        RoleDAO roleDao = new RoleDAO();
        role.getItems().addAll(roleDao.getRolesByEvent(event.getEventId()));
    }

    public void fillForm() {
        fname.setText(attendee.getAttendeeFname());
        lname.setText(attendee.getAttendeeLname());
        gender.setText(attendee.getAttendeeGender());
        shirt.setText(attendee.getAttendeeShirt());
        diet_prefs.setText(attendee.getAttendeeDietPrefs());
        role.getSelectionModel().select(attendee.getRole());
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    public void back(ActionEvent event) {
        RegistrationController controller = redirect("registration").getController();
        controller.setEvent(this.event);
        controller.setUser(this.user);
        controller.loadData();
    }

    public void setValues() {
        attendee.setAttendeeFname(fname.getText());
        attendee.setAttendeeLname(lname.getText());
        attendee.setAttendeeGender(gender.getText());
        attendee.setAttendeeShirt(shirt.getText());
        attendee.setAttendeeDietPrefs(diet_prefs.getText());
        attendee.setAttendeeRoleId(role.getValue().getId());
    }

    public void save(ActionEvent event) {
        AttendeeDAO attendeeDao = new AttendeeDAO();

        if (attendee == null) {
            // insert new attendee
            attendee = new Attendee();
            setValues();
            Integer attendee_id = attendeeDao.insertAttendee(attendee);

            // insert new registration using attendee_id
            RegistrationDAO registrationDao = new RegistrationDAO();

            Registration registration = new Registration();
            registration.setAttendeeId(attendee_id);
            registration.setEventId(this.event.getEventId());
            registration.setUserId(this.user.getUserId());

            registrationDao.insertRegistration(registration);
        } else {
            // update existing attendee
            setValues();
            attendeeDao.updateAttendee(attendee);
        }

        RegistrationController controller = redirect("registration").getController();
        controller.setEvent(this.event);
        controller.setUser(this.user);
        controller.loadData();
    }
}
