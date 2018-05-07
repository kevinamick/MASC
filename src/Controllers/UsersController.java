package Controllers;

import Application.Main;
import Data.User;
import Data.UserDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UsersController extends Controller {
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

        col_uId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_usId.setCellValueFactory(new PropertyValueFactory<>("school_id"));
        col_fName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        col_lName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        //col_utId.setCellValueFactory(new PropertyValueFactory<>("type_id"));

        dao = new UserDAO();
        data = dao.getAllUsers();
        tbl_users.setItems(data);

        insertEditButton();
        insertDeleteButton();
    }

    public void back(ActionEvent event) {
        redirect("registrar_dashboard");
    }

    private void editUser(User user) {
        UserFormController controller = redirect("user_form").getController();
        controller.setUser(user);
        controller.fillForm();
    }

    private void deleteUser(User user) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Delete '" + user.getFullName() + "'? This will delete all data associated with this user.",
                ButtonType.YES,
                ButtonType.CANCEL
        );

        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            UserDAO userDao = new UserDAO();

            userDao.deleteUser(user);

            redirect("manage_users");
        }
    }

    private void insertEditButton() {
        TableColumn col_action = new TableColumn<>("");
        col_action.setSortable(false);

        col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<User, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        col_action.setCellFactory(new Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>>() {
            @Override
            public TableCell<User, Boolean> call(TableColumn<User, Boolean> p) {
                return new EditButtonCell();
            }
        });

        tbl_users.getColumns().add(col_action);
    }

    private void insertDeleteButton() {
        TableColumn col_action = new TableColumn<>("");
        col_action.setSortable(false);

        col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<User, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        col_action.setCellFactory(new Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>>() {
            @Override
            public TableCell<User, Boolean> call(TableColumn<User, Boolean> p) {
                return new DeleteButtonCell();
            }
        });

        tbl_users.getColumns().add(col_action);
    }

    //Define the button cell
    private class EditButtonCell extends TableCell<User, Boolean> {
        final Button cellButton = new Button("Edit");

        EditButtonCell() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    UserDAO userDao = new UserDAO();

                    User user = userDao.getUser(tbl_users.getItems().get(getTableRow().getIndex()).getUserId());

                    editUser(user);
                }
            });
        }

        // Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) { setGraphic(cellButton); }
        }
    }

    //Define the button cell
    private class DeleteButtonCell extends TableCell<User, Boolean> {
        final Button cellButton = new Button("Delete");

        DeleteButtonCell() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    // Get selected item
                    User user = tbl_users.getItems().get(getTableRow().getIndex());

                    deleteUser(user);
                }
            });
        }

        // Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) { setGraphic(cellButton); }
        }
    }
}
