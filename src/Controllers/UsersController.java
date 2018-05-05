package Controllers;

import Application.Main;
import Data.User;
import Data.UserDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class UsersController {
    @FXML TableView<User> tbl_users;
    @FXML TableColumn<User,Integer> col_uId;
    @FXML TableColumn<User,Integer> col_usId;
    @FXML TableColumn<User,String> col_fName;
    @FXML TableColumn<User,String> col_lName;
    @FXML TableColumn<User,String> col_email;
    @FXML TableColumn<User,Integer> col_utId;
    private ObservableList<User> data;
    private UserDAO dao;

    FXMLLoader dashboard_loader = new FXMLLoader(getClass().getResource("/Resources/registrar_dashboard.fxml"));

    @FXML
    public void initialize() {
        assert tbl_users != null : "fx:id=\"tbl_users\" was not injected";

        col_uId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        col_usId.setCellValueFactory(
                new PropertyValueFactory<>("school_id")
        );
        col_fName.setCellValueFactory(
                new PropertyValueFactory<>("fname")
        );
        col_lName.setCellValueFactory(
                new PropertyValueFactory<>("lname")
        );
        col_email.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );
        col_utId.setCellValueFactory(
                new PropertyValueFactory<>("type_id")
        );

        dao = new UserDAO();
        data = dao.getAllUsers();
        tbl_users.setItems(data);
    }

    public void backToRegistrar(ActionEvent event) {
        try {
            Stage stage = Main.getPrimaryStage();
            Parent root = dashboard_loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.err.println("Something went wrong when going back to dashboard");
            System.err.println(e.getMessage());
        }
    }
}
