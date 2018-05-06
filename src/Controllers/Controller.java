package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Controller {
    @FXML protected Pane rootPane;

    protected FXMLLoader redirect(String view) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/" + view + ".fxml"));
            Pane pane = loader.load();

            rootPane.getChildren().setAll(pane);

            return loader;
        } catch (Exception e) {
            System.err.println("Failed to load resource: " + view);
            System.err.println(e.getMessage());
        }

        return null;
    }
}
