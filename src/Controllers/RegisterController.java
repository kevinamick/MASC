package Controllers;

import Application.Main;
import Data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterController extends Controller {
    @FXML
    private TextField fld_f_name;
    @FXML
    private TextField fld_l_name;
    @FXML
    private ChoiceBox<School> school;
    @FXML
    private TextField fld_email_reg;
    @FXML
    private TextField fld_type_id;
    @FXML
    private PasswordField fld_pass_reg;
    @FXML
    private PasswordField fld_pass2_reg;

    UserDAO dao = new UserDAO();
    FXMLLoader reg_loader = new FXMLLoader(getClass().getResource("/Resources/register.fxml"));
    //FXMLLoader login_loader = new FXMLLoader(getClass().getResource("/Resources/login.fxml"));
    Alert alert;

    @FXML
    public void initialize() {
        // get event list
        SchoolDAO schoolDao = new SchoolDAO();

        // set event list
        school.getItems().clear();
        school.getItems().addAll(schoolDao.getSchools());
    }

    public void backToLogin(ActionEvent event) {
        try {
            //Parent root = login_loader.load();
            //Stage stage = Main.getPrimaryStage();
            //stage.setScene(new Scene(root));
            //stage.show();
            Pane pane = FXMLLoader.load(getClass().getResource("/Resources/login.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println("Something went wrong when going back to login");
            System.err.println(e.getMessage());
        }
    }

    public void register(ActionEvent event) {
        if (hasBadInput()) {
            showBadInputAlert();
        } else if (!passwordsMatch()) {
            showPasswordMismatchAlert();
        } else {
            try {
                // Insert new user
                insertNewUser();

                // Redirect to login
                redirect("login");
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Insertion Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private void insertNewUser() {
        UserTypeDAO userTypeDao = new UserTypeDAO();
        UserType userType = userTypeDao.getUserTypeByName("Advisor");
        dao.insertUser(new User(
                0,
                fld_f_name.getText().trim(),
                fld_l_name.getText().trim(),
                school.getValue().getSchoolId(),
                fld_email_reg.getText().trim(),
                fld_pass_reg.getText().trim(),
                userType.id
        ));
    }

    private boolean hasBadInput() {
        return fld_email_reg.getText().trim().isEmpty()
                || fld_pass_reg.getText().trim().isEmpty()
                || fld_pass2_reg.getText().trim().isEmpty()
                || fld_f_name.getText().trim().isEmpty()
                || fld_l_name.getText().trim().isEmpty()
                || school.getValue() == null;
    }

    private void showPasswordMismatchAlert() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Password Mismatch");
        alert.setHeaderText(null);
        alert.setContentText("These Passwords are not the Same");
        alert.showAndWait();
    }

    private void showBadInputAlert() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bad Input");
        alert.setHeaderText(null);
        alert.setContentText("Please complete all fields.");
        alert.showAndWait();
    }

    private boolean passwordsMatch() {
        return fld_pass_reg.getText().equals(fld_pass2_reg.getText());
    }

    private boolean validPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}");
    }

    private boolean validEmail(String email) {
        return email.matches("[a-zA-Z0-9]+[._a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]*[a-zA-Z]*@[a-zA-Z0-9]{2,8}.[a-zA-Z.]{2,6}");
    }
}
