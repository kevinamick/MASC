package Controllers;

import Data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class RegistrationFormController extends Controller {
    @FXML TextField r_event_id;
    @FXML TextField r_user_id;

    public Attendee attendee;
    public Registration registration;

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    public void back(ActionEvent event) {
        redirect("registration");
    }

    public void save(ActionEvent event) {
        RegistrationDAO registrationDao = new RegistrationDAO();
        AttendeeDAO attendeeDAO = new AttendeeDAO();

        if (this.registration == null) {
            this.registration = new Registration();
            this.registration.setEventId(Integer.parseInt(r_event_id.getText()));
            this.registration.setUserId(Integer.parseInt(r_user_id.getText()));
            this.registration.setAttendeeId(attendee.getAttendeeId());
            attendee.setAttendeeInvoiceId(attendeeDAO.getRegisteredAttendees().size()+1);
            attendeeDAO.updateAttendee(attendee);
            registrationDao.insertRegistration(this.registration);
        } else {
            this.registration.setEventId(Integer.parseInt(r_event_id.getText()));
            this.registration.setUserId(Integer.parseInt(r_user_id.getText()));
            this.registration.setAttendeeId(attendee.getAttendeeId());
            attendee.setAttendeeInvoiceId(attendeeDAO.getRegisteredAttendees().size()+1);
            attendeeDAO.updateAttendee(attendee);
            registrationDao.updateRegistration(this.registration);
        }

        redirect("advisor_dashboard");
    }
}
