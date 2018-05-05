package Controllers;

import Application.Main;
import Data.Event;
import Data.EventDAO;
import Data.User;
import Data.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


public class LoginController {

    @FXML
    private TextField fld_email;
    @FXML
    private PasswordField fld_pass;
    @FXML
    private ChoiceBox event;

    UserDAO dao = new UserDAO();
    FXMLLoader dashboard_loader = new FXMLLoader(getClass().getResource("/Resources/registrar_dashboard.fxml"));
    FXMLLoader advisor_dashboard_loader = new FXMLLoader(getClass().getResource("/Resources/advisor_dashboard.fxml"));
    FXMLLoader reg_loader = new FXMLLoader(getClass().getResource("/Resources/register.fxml"));
    Alert alert;

    public void setData() {
        // get event list
        EventDAO eventDao = new EventDAO();

        // set event list
        event.getItems().clear();
        event.getItems().addAll(eventDao.getEvents());
    }

    public void login(ActionEvent event) {
        if (fld_email.getText().trim().isEmpty() || fld_pass.getText().trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Bad Input");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter an Email and Password");
            alert.showAndWait();
        } else {
            // Validate the user
            User user = dao.validateUser(fld_email.getText().trim(), fld_pass.getText().trim());

            if (user != null) {
                try {
                    // direct to correct dashboard
                    if (user.getUserType().access > 0) {
                        Stage stage = Main.getPrimaryStage();
                        Parent root = dashboard_loader.load();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } else {
                        Stage stage = Main.getPrimaryStage();
                        Parent root = advisor_dashboard_loader.load();
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception e) {
                    System.err.println("Something went wrong when logging in");
                    System.err.println(e.getMessage());
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Credentials");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email or password");
                alert.showAndWait();
            }
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
