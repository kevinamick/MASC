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

    public ObservableList getRegisteredAttendees() {
        ObservableList<Attendee> data = FXCollections.observableArrayList();
        try {
            open();

            String query = "SELECT * FROM masc.attendees WHERE invoice_id IS NOT NULL;";
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
            System.out.println("Data: " + data.size());
            return data;
        } catch (SQLException e) {
            System.err.println("Something Happened when getting unregistered attendees");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public void updateAttendee(Attendee attendee) {
        try {
            open();
            String query = "UPDATE masc.attendees SET fname = ?, lname = ?," +
                    "gender = ?, shirt = ?, role_id = ?, diet_prefs = ?, invoice_id = ? WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1,attendee.getAttendeeFname());
            stmt.setString(2, attendee.getAttendeeLname());
            stmt.setString(3, attendee.getAttendeeGender());
            stmt.setString(4, attendee.getAttendeeShirt());
            stmt.setInt(5, attendee.getAttendeeRoleId());
            stmt.setString(6, attendee.getAttendeeDietPrefs());
            stmt.setInt(7, attendee.getAttendeeInvoiceId());
            stmt.setInt(8, attendee.getAttendeeId());


            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Something went wrong when updating Attendee.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }
}
