package Controllers;

import Data.Region;
import Data.RegionDAO;
import Data.School;
import Data.SchoolDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SchoolFormController extends Controller {
    @FXML ChoiceBox<Region> region;
    @FXML TextField name;

    public School school;

    @FXML
    public void initialize() {
        // get region list
        RegionDAO regionDao = new RegionDAO();

        // set region list
        region.getItems().clear();
        region.getItems().addAll(regionDao.getRegions());
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void fillForm() {
        region.getSelectionModel().select(school.getRegion());
        name.setText(school.getSchoolName());
    }

    public void back(ActionEvent event) {
        redirect("manage_schools");
    }

    public void setValues() {
        school.setRegionId(region.getValue().getId());
        school.setSchoolName(name.getText());
    }

    public void save(ActionEvent event) {
        SchoolDAO schoolDao = new SchoolDAO();

        if (school == null) {
            school = new School();
            setValues();
            schoolDao.insertSchool(school);
        } else {
            setValues();
            schoolDao.updateSchool(school);
        }

        redirect("manage_schools");
    }
}
