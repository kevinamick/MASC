package Controllers;

import Application.Main;
import Data.Event;
import Data.EventDAO;
import Data.User;
import Data.UserDAO;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController extends Controller {
    @FXML
    private TextField fld_email;
    @FXML
    private PasswordField fld_pass;
    @FXML
    private ChoiceBox<Event> event;

    UserDAO dao = new UserDAO();
    Alert alert;

    @FXML
    public void initialize() {
        // get event list
        EventDAO eventDao = new EventDAO();

        // set event list
        event.getItems().clear();
        event.getItems().addAll(eventDao.getEvents());

        // submit on enter from password
        fld_pass.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                    doLogin();
                }
            }
        });
    }

    public void doLogin() {
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
                        redirect("registrar_dashboard");
                    } else {
                        // ensure event was selected
                        if (this.event.getValue() == null) {
                            showEventAlert();
                        } else {
                            AdvisorDashboardController controller = redirect("advisor_dashboard").getController();
                            controller.setEvent(this.event.getValue());
                            controller.setUser(user);
                            controller.loadData();
                        }
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

    public void login(ActionEvent event) {
        doLogin();
    }

    private void showEventAlert() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Missing Event");
        alert.setHeaderText(null);
        alert.setContentText("Please select an event.");
        alert.showAndWait();
    }

    public void openRegistration(ActionEvent event) {
        try {
            //Parent root = reg_loader.load();
            //Stage stage = Main.getPrimaryStage();
            //stage.setScene(new Scene(root));
            //stage.show();
            Pane pane = FXMLLoader.load(getClass().getResource("/Resources/register.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println("Something went wrong when loading root");
            System.err.println(e.getMessage());
        }
    }

}
