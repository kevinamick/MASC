package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SchoolDAO extends DAO {
    public ObservableList getSchools() {
        ObservableList<School> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM masc.schools";
            PreparedStatement stmt = database.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                School school = new School();
                school.setSchoolId(rs.getInt("id"));
                school.setSchoolName(rs.getString("name"));
                school.setRegionId(rs.getInt("region_id"));

                data.add(school);
            }

            return data;
        } catch (Exception e) {
            System.err.println("Something went wrong when getting schools.");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
