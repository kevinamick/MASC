package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventDAO extends DAO {
    public Event getEvent(Integer id) {
        try {
            open();

            String query = "SELECT * FROM masc.events WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("id"));
                event.setEventName(rs.getString("name"));

                return event;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting event.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }

    public ObservableList getEvents() {
        ObservableList<Event> data = FXCollections.observableArrayList();
        try {
            open();
            String query = "SELECT * FROM masc.events";
            PreparedStatement stmt = database.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("id"));
                event.setEventName(rs.getString("name"));

                data.add(event);
            }

            return data;
        } catch (Exception e) {
            System.err.println("Something went wrong when getting events.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }

    public void updateEvent(Event event) {
        try {
            open();
            String query = "UPDATE masc.events SET name = ? WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1, event.getEventName());
            stmt.setInt(2, event.getEventId());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Something went wrong when updating event.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }

    public void deleteEvent(Event event) {
        try {
            open();
            String query = "DELETE FROM masc.events WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, event.getEventId());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Something went wrong when deleting event.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }

    public void insertEvent(Event event) {
        try {
            open();
            String query = "INSERT INTO masc.events (name) VALUES (?);";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1, event.getEventName());

            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Something went wrong when inserting event.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }
}
