package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceDAO extends DAO {
    public Invoice getInvoice(Integer id) {
        try {
            open();

            String query = "SELECT * FROM masc.invoices WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setUserId(rs.getInt("user_id"));
                invoice.setEventId(rs.getInt("event_id"));

                return invoice;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting invoice.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }

    public ObservableList getInvoicesForUserEvent(User user, Event event) {
        ObservableList<Invoice> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM masc.invoices WHERE user_id = ? AND event_id = ?;";

        try {
            open();

            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, user.getUserId());
            stmt.setInt(2, event.getEventId());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();

                invoice.setId(rs.getInt("id"));
                invoice.setEventId(rs.getInt("event_id"));
                invoice.setUserId(rs.getInt("user_id"));

                data.add(invoice);
            }
            return data;
        } catch (Exception e) {
            System.err.println("Something went wrong when getting invoices");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }
}
