package Data;

import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDAO extends DAO{

    public void insertRegistration(Registration registration) {
        try {
            open();
            String query = "INSERT INTO masc.registrations (event_id,user_id,attendee_id) VALUES (?,?,?);";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, registration.getEventId());
            stmt.setInt(2, registration.getUserId());
            stmt.setInt(3, registration.getAttendeeId());

            stmt.execute();

        } catch (SQLException e) {
            System.err.println("Something went wrong when inserting Registration.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }

    public void updateRegistration(Registration registration) {

    }
}
