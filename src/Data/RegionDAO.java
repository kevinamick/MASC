package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionDAO extends DAO {
    public Region getRegion(Integer id) {
        try {
            open();

            String query = "SELECT * FROM masc.regions WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Region region = new Region();
                region.setId(rs.getInt("id"));
                region.setName(rs.getString("name"));
                region.setCode(rs.getString("code"));

                return region;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting user.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }

    public ObservableList getRegions() {
        ObservableList<Region> data = FXCollections.observableArrayList();
        try {
            open();

            String query = "SELECT * FROM masc.regions";
            PreparedStatement stmt = database.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Region region = new Region();
                region.setId(rs.getInt("id"));
                region.setName(rs.getString("name"));
                region.setCode(rs.getString("code"));

                data.add(region);
            }

            return data;
        } catch (Exception e) {
            System.err.println("Something went wrong when getting schools.");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            close();
        }

        return null;
    }
}
