package Application;

import Controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    FXMLLoader login_loader;
    private static Stage window;

    private void setPrimaryStage(Stage stage) {
        Main.window = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    static public Stage getPrimaryStage() {
        return Main.window;
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Load window
        //window = primaryStage;
        //setPrimaryStage(window);
        stage.setTitle("Maryland Association of Student Councils");

        // Load initial login view
        login_loader = new FXMLLoader(getClass().getResource("/Resources/login.fxml"));
        Parent root = login_loader.load();

        // Set the scene, aaaaand showtime.
        stage.setScene(new Scene(root));
        stage.show();
    }
}
