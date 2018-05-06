package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchoolDAO extends DAO {
    public ObservableList getSchools() {
        ObservableList<School> data = FXCollections.observableArrayList();
        try {
            open();

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
        } finally {
            close();
        }

        return null;
    }

    public School getSchool(Integer id) {
        try {
            open();

            String query = "SELECT * FROM masc.schools WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                School school = new School();
                school.setSchoolId(rs.getInt("id"));
                school.setSchoolName(rs.getString("name"));
                school.setRegionId(rs.getInt("region_id"));

                return school;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting school.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }

    public void updateSchool(School school) {
        try {
            open();

            String query = "UPDATE masc.schools SET region_id = ?, name = ? WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, school.getRegionId());
            stmt.setString(2, school.getSchoolName());
            stmt.setInt(3, school.getSchoolId());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Something went wrong when updating school.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }

    public void deleteSchool(School school) {
        try {
            open();

            String query = "DELETE FROM masc.schools WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, school.getSchoolId());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Something went wrong when deleting school.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }

    public void insertSchool(School school) {
        try {
            open();

            String query = "INSERT INTO masc.schools (name, region_id) VALUES (?,?);";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1, school.getSchoolName());
            stmt.setInt(2, school.getRegionId());

            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Something went wrong when inserting school.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }
}
