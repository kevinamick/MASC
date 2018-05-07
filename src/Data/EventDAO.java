package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventDAO extends DAO {
    public Event getEventByID(Integer id) {
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
                event.setEventPrice(rs.getBigDecimal("price"));

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

    public Event getEventByName(String name) {
        try {
            open();

            String query = "SELECT * FROM masc.events WHERE name = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("id"));
                event.setEventName(rs.getString("name"));
                event.setEventPrice(rs.getBigDecimal("price"));

                return event;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting event.");
            System.err.println(e.getMessage());
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

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("id"));
                event.setEventName(rs.getString("name"));
                event.setEventPrice(rs.getBigDecimal("price"));

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
            String query = "UPDATE masc.events SET name = ?, price = ? WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1, event.getEventName());
            stmt.setBigDecimal(2,event.getEventPrice());
            stmt.setInt(3, event.getEventId());


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
            String query = "INSERT INTO masc.events (name,price) VALUES (?,?);";
            String event_roles_query =
                    "INSERT INTO masc.event_roles (event_id,role_id,price) VALUES (?,?,?);";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1, event.getEventName());
            stmt.setBigDecimal(2,event.getEventPrice());

            stmt.execute();

            ObservableList<Role> roles = new RoleDAO().getRoles();
            PreparedStatement event_role_stmt;

            for (Role role : roles) {
                event_role_stmt = database.prepareStatement(event_roles_query);
                event_role_stmt.setInt(1,
                        getEventByName(event.getEventName()).getEventId());
                event_role_stmt.setInt(2, role.getId());
                event_role_stmt.setBigDecimal(3, event.getEventPrice());
                event_role_stmt.execute();
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong when inserting event.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }
}
