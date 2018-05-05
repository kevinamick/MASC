package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EventDAO extends DAO {
    public ObservableList getEvents() {
        ObservableList<Event> data = FXCollections.observableArrayList();
        try {
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
            System.err.println("Something went wrong when getting schools.");
            System.err.println(e.getMessage());
        }

        return null;
    }
}
