package Controllers;

import Application.Main;
import Data.CredDAO;
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

    CredDAO dao = new CredDAO();
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

}
