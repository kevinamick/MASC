package Controllers;

import Data.Event;
import Data.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Controller {
    @FXML protected Pane rootPane;

    public User user;
    public Event event;

    public void setUser(User user) {
        this.user = user;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    protected FXMLLoader redirect(String view) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/" + view + ".fxml"));
            Pane pane = loader.load();

            rootPane.getChildren().setAll(pane);

            return loader;
        } catch (Exception e) {
            System.err.println("Failed to load resource: " + view);
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
