package Controllers;

import Application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class RegistrarDashboardController extends Controller {
    public void back(ActionEvent event) {
        redirect("login");
    }

    public void schools(ActionEvent event) {
        redirect("manage_schools");
    }

    public void events(ActionEvent event) {
        redirect("manage_events");
    }

    public void users(ActionEvent event) {
        redirect("manage_users");
    }
}
