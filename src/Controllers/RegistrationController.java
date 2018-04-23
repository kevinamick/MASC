package Controllers;

import Application.Main;
import Data.CredDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {
    @FXML
    private TextField fld_email_reg;
    @FXML
    private PasswordField fld_pass_reg;
    @FXML
    private PasswordField fld_pass2_reg;

    CredDAO dao = new CredDAO();
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
        try {
            Parent root = login_loader.load();
            if(fld_email_reg.getText().trim().isEmpty() || fld_pass_reg.getText().isEmpty()
                    || fld_pass2_reg.getText().trim().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Bad Input");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter an Email, Password, and Reenter the Password");
                alert.showAndWait();
            } else if(!fld_pass_reg.getText().equals(fld_pass2_reg.getText())){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Mismatch");
                alert.setHeaderText(null);
                alert.setContentText("These Passwords are not the Same");
                alert.showAndWait();
            } else {
                dao.insertUser(fld_email_reg.getText().trim(), fld_pass_reg.getText().trim());
                Stage stage = Main.getPrimaryStage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insertion Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.err.println("Something went wrong when inserting user");
            System.err.println(e.getMessage());
        }
    }
}
