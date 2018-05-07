package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AttendeeDAO extends DAO {
    public ObservableList<Attendee> getUnregisteredAttendeesByUser(Integer id) {
        ObservableList<Attendee> data = FXCollections.observableArrayList();
        try {
            open();

            String query = "SELECT a.id, a.fname, a.lname, a.gender, a.shirt, a.role_id, a.diet_prefs " +
                    "FROM masc.attendees AS a, masc.registrations As r " +
                    "WHERE a.id = r.attendee_id AND a.invoice_id IS NULL AND r.user_id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Attendee attendee = new Attendee();

                attendee.setAttendeeId(rs.getInt("id"));
                attendee.setAttendeeFname(rs.getString("fname"));
                attendee.setAttendeeLname(rs.getString("lname"));
                attendee.setAttendeeGender(rs.getString("gender"));
                attendee.setAttendeeShirt(rs.getString("shirt"));
                attendee.setAttendeeRoleId(rs.getInt("role_id"));
                attendee.setAttendeeDietPrefs(rs.getString("diet_prefs"));

                data.add(attendee);
            }

            return data;
        } catch (SQLException e) {
            System.err.println("Something Happened when getting unregistered attendees");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public void assignInvoiceToUnregisteredUserAttendees(Integer user_id, Integer invoice_id) {
        try {
            open();
            String query = "UPDATE masc.attendees SET invoice_id = ? WHERE id IN (" +
                    "SELECT a.id " +
                    "FROM (SELECT * FROM masc.attendees) AS a, masc.registrations AS r " +
                    "WHERE a.id = r.attendee_id AND a.invoice_id IS NULL AND r.user_id = ?);";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, invoice_id);
            stmt.setInt(2, user_id);

            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Something went wrong when updating attendee invoice ids.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }

    public Attendee getAttendee(Integer id) {
        try {
            open();

            String query = "SELECT * FROM masc.attendees WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

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

                return attendee;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting attendee.");
            System.err.println(e.getMessage());
        } finally {
            close();
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
                    "gender = ?, shirt = ?, role_id = ?, diet_prefs = ? WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1,attendee.getAttendeeFname());
            stmt.setString(2, attendee.getAttendeeLname());
            stmt.setString(3, attendee.getAttendeeGender());
            stmt.setString(4, attendee.getAttendeeShirt());
            stmt.setInt(5, attendee.getAttendeeRoleId());
            stmt.setString(6, attendee.getAttendeeDietPrefs());
            stmt.setInt(7, attendee.getAttendeeId());


            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Something went wrong when updating Attendee.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }

    public void deleteAttendee(Attendee attendee) {
        try {
            open();

            String query = "DELETE FROM masc.attendees WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, attendee.getAttendeeId());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Something went wrong when deleting attendee.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }

    public Integer insertAttendee(Attendee attendee) {
        try {
            open();

            String query = "INSERT INTO masc.attendees (fname, lname, gender, shirt, role_id, diet_prefs) VALUES (?,?,?,?,?,?);";
            PreparedStatement stmt = database.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, attendee.getAttendeeFname());
            stmt.setString(2, attendee.getAttendeeLname());
            stmt.setString(3, attendee.getAttendeeGender());
            stmt.setString(4, attendee.getAttendeeShirt());
            stmt.setInt(5, attendee.getAttendeeRoleId());
            stmt.setString(6, attendee.getAttendeeDietPrefs());

            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when inserting attendee.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }
}
