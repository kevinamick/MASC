package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendeeDAO extends DAO {
    public ObservableList getUnregisteredAttendees() {
        ObservableList<Attendee> data = FXCollections.observableArrayList();
        try {
            open();

            String query = "SELECT * FROM masc.attendees WHERE invoice_id IS NULL;";
            PreparedStatement stmt = database.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Attendee attendee = new Attendee();
                attendee.setAttendeeId(rs.getInt("id"));
                attendee.setAttendeeFname(rs.getString("fname"));
                attendee.setAttendeeLname(rs.getString("lname"));
                attendee.setAttendeeGender(rs.getString("gender"));
                attendee.setAttendeeShirt(rs.getString("shirt"));
                attendee.setAttendeeRoleId(rs.getInt("role_id"));
                attendee.setAttendeeDietPrefs(rs.getString("diet_prefs"));

                data.add(attendee);
            } else {
                return null;
            }

            return data;
        } catch (SQLException e) {
            System.err.println("Something Happened when getting unregistered attendees");
            System.err.println(e.getMessage());
        }

        return null;
    }
}
