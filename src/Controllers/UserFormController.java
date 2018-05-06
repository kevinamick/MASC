package Controllers;

import Data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserFormController extends Controller {
    @FXML TextField fname;
    @FXML TextField lname;
    @FXML TextField email;
    @FXML ChoiceBox<School> school;
    @FXML PasswordField password;
    @FXML ChoiceBox<UserType> utype;

    public User user;

    @FXML
    public void initialize() {
        // get school list
        SchoolDAO schoolDao = new SchoolDAO();

        // set school list
        school.getItems().clear();
        school.getItems().addAll(schoolDao.getSchools());

        // get utype list
        UserTypeDAO utypeDao = new UserTypeDAO();

        // set utype list
        utype.getItems().clear();
        utype.getItems().addAll(utypeDao.getUserTypes());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void fillForm() {
        fname.setText(user.getFname());
        lname.setText(user.getLname());
        email.setText(user.getEmail());
        school.getSelectionModel().select(user.getSchool());
        password.setText(user.getPassword());
        utype.getSelectionModel().select(user.getUserType());
    }

    public void back(ActionEvent event) {
        redirect("manage_users");
    }

    public void save(ActionEvent event) {
        UserDAO userDao = new UserDAO();

        user.setFname(fname.getText());
        user.setLname(lname.getText());
        user.setEmail(email.getText());
        user.setSchoolId(school.getValue().getSchoolId());
        user.setPassword(password.getText());
        user.setTypeId(utype.getValue().getId());

        userDao.updateUser(user);

        redirect("manage_users");
    }
}
