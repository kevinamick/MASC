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

    public ObservableList<InvoiceRow> getInvoiceRows(Integer id) {
        ObservableList<InvoiceRow> data = FXCollections.observableArrayList();

        String query = "SELECT COUNT(*) as count, R.name, ER.price " +
                "FROM masc.attendees as A, masc.roles as R, masc.event_roles AS ER, masc.invoices AS I " +
                "WHERE I.id = ? AND A.invoice_id = I.id AND ER.role_id = A.role_id AND ER.event_id = I.id AND A.role_id = R.id AND A.invoice_id = I.id " +
                "GROUP BY A.role_id;";

        try {
            open();

            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                InvoiceRow invoiceRow = new InvoiceRow();

                invoiceRow.setCount(rs.getInt("count"));
                invoiceRow.setName(rs.getString("name"));
                invoiceRow.setPrice(rs.getBigDecimal("price"));

                data.add(invoiceRow);
            }

            return data;
        } catch (Exception e) {
            System.err.println("Something went wrong when getting attendees for invoice.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }

    public ObservableList<Attendee> getAttendees(Integer id) {
        ObservableList<Attendee> data = FXCollections.observableArrayList();

        String query = "SELECT * FROM masc.attendees WHERE invoice_id = ?;";

        try {
            open();

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
                attendee.setAttendeeInvoiceId(rs.getInt("invoice_id"));

                data.add(attendee);
            }
            return data;
        } catch (Exception e) {
            System.err.println("Something went wrong when getting attendees for invoice.");
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
