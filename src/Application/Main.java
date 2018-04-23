package Application;

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
    public void start(Stage primaryStage) throws Exception {
        login_loader = new FXMLLoader(getClass().getResource("/Resources/login.fxml"));
        Parent root = login_loader.load();

        window = primaryStage;
        setPrimaryStage(window);
        window.setTitle("Maryland Association of Student Councils");

        window.setScene(new Scene(root));
        window.show();
    }
}
