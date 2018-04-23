package Controllers;

import Application.Main;
import Data.LoginDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


public class LoginController {

    @FXML
    private TextField fld_email;
    @FXML
    private PasswordField fld_pass;
    @FXML
    private TextField fld_email_reg;
    @FXML
    private PasswordField fld_pass_reg;
    @FXML
    private PasswordField fld_pass2_reg;

    LoginDAO dao = new LoginDAO();
    FXMLLoader dashboard_loader = new FXMLLoader(getClass().getResource("/Resources/dashboard.fxml"));
    FXMLLoader reg_loader = new FXMLLoader(getClass().getResource("/Resources/register.fxml"));
    Alert alert;

    public void login(ActionEvent event) {
        try {
            Parent root = dashboard_loader.load();
            if (fld_email.getText().trim().isEmpty() || fld_pass.getText().trim().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Bad Input");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter an Email and Password");
                alert.showAndWait();
            } else {
                boolean validated = dao.validateUser(fld_email.getText().trim(), fld_pass.getText().trim());
                if (validated) {
                    Stage stage = Main.getPrimaryStage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Credentials");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid email or password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            System.err.println("Something went wrong when logging in");
            System.err.println(e.getMessage());
        }
    }

    public void openRegistration(ActionEvent event) {
        try {
            Parent root = reg_loader.load();
            Stage stage = Main.getPrimaryStage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.err.println("Something went wrong when loading root");
            System.err.println(e.getMessage());
        }
    }

    public void register(ActionEvent event) {
        try {
            Parent root = reg_loader.load();
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
            System.err.println("Something went wrong when inserting user");
            System.err.println(e.getMessage());
        }
    }

}
