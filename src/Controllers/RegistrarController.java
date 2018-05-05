package Controllers;

import Application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class RegistrarController {

    @FXML
    Button btn_manage_schools;

    FXMLLoader schools_loader = new FXMLLoader(getClass().getResource("/Resources/manage_schools.fxml"));
    FXMLLoader events_loader = new FXMLLoader(getClass().getResource("/Resources/manage_events.fxml"));
    FXMLLoader users_loader = new FXMLLoader(getClass().getResource("/Resources/manage_users.fxml"));

    public void schools(ActionEvent event) {
        try {
            Parent root = schools_loader.load();
            Stage stage = Main.getPrimaryStage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.err.println("Something went wrong loading the school manager");
            System.err.println(e.getMessage());
        }
    }

    public void events(ActionEvent event) {
        try {
            Parent root = events_loader.load();
            Stage stage = Main.getPrimaryStage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.err.println("Something went wrong loading the events manager");
            System.err.println(e.getMessage());
        }
    }

    public void users(ActionEvent event) {
        try {
            Parent root = users_loader.load();
            Stage stage = Main.getPrimaryStage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.err.println("Something went wrong loading the user manager");
            System.err.println(e.getMessage());
        }
    }
}
