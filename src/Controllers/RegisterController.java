package Controllers;

import Application.Main;
import Data.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    private TextField fld_email_reg;
    @FXML
    private PasswordField fld_pass_reg;
    @FXML
    private PasswordField fld_pass2_reg;

    UserDAO dao = new UserDAO();
    FXMLLoader reg_loader = new FXMLLoader(getClass().getResource("/Resources/register.fxml"));
    FXMLLoader login_loader = new FXMLLoader(getClass().getResource("/Resources/login.fxml"));
    Alert alert;

    public void backToLogin(ActionEvent event) {
        try {
            Parent root = login_loader.load();
            Stage stage = Main.getPrimaryStage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.err.println("Something went wrong when going back to login");
            System.err.println(e.getMessage());
        }
    }

    public void register(ActionEvent event) {
        if (fld_email_reg.getText().trim().isEmpty() || fld_pass_reg.getText().isEmpty()
                || fld_pass2_reg.getText().trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Bad Input");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter an Email, Password, and Reenter the Password");
            alert.showAndWait();
        } else if (!fld_pass_reg.getText().equals(fld_pass2_reg.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Mismatch");
            alert.setHeaderText(null);
            alert.setContentText("These Passwords are not the Same");
            alert.showAndWait();
        } else {
            try {
                //TODO: Create input for missing fields
                dao.insertUser("Temp","Temp",1234,fld_email_reg.getText().trim(), fld_pass_reg.getText().trim());
                Parent root = login_loader.load();
                Stage stage = Main.getPrimaryStage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Insertion Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private boolean validPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}");
    }

    private boolean validEmail(String email) {
        return email.matches("[a-zA-Z0-9]+[._a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]*[a-zA-Z]*@[a-zA-Z0-9]{2,8}.[a-zA-Z.]{2,6}");
    }
}
