package Controllers;

import Data.School;
import Data.SchoolDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class SchoolsController extends Controller {
    @FXML private TableView<School> tbl_school;
    @FXML private TableColumn<School,Integer> col_sId;
    @FXML private TableColumn<School,String> col_sName;
    @FXML private TableColumn<School,Integer> col_rId;
    private ObservableList<School> data;
    private SchoolDAO dao;

    @FXML
    void initialize() {
        assert tbl_school != null : "fx:id=\"tbl_school\" was not injected";

        col_sId.setCellValueFactory(new PropertyValueFactory<>("schoolId"));
        col_sName.setCellValueFactory(new PropertyValueFactory<>("schoolName"));
        col_rId.setCellValueFactory(new PropertyValueFactory<>("region"));

        dao = new SchoolDAO();

        data = dao.getSchools();
        tbl_school.setItems(data);

        insertEditButton();
        insertDeleteButton();
    }

    public void back(ActionEvent event) {
        redirect("registrar_dashboard");
    }

    private void editSchool(School school) {
        SchoolFormController controller = redirect("school_form").getController();
        controller.setSchool(school);
        controller.fillForm();
    }

    public void newSchool(ActionEvent event) {
        SchoolFormController controller = redirect("school_form").getController();
    }

    private void deleteSchool(School school) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Delete '" + school.getSchoolName() + "'? This will delete all data associated with this school.",
                ButtonType.YES,
                ButtonType.CANCEL
        );

        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            SchoolDAO schoolDao = new SchoolDAO();

            schoolDao.deleteSchool(school);

            redirect("manage_schools");
        }
    }

    private void insertEditButton() {
        TableColumn col_action = new TableColumn<>("");
        col_action.setSortable(false);

        col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<School, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<School, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        col_action.setCellFactory(new Callback<TableColumn<School, Boolean>, TableCell<School, Boolean>>() {
            @Override
            public TableCell<School, Boolean> call(TableColumn<School, Boolean> p) {
                return new EditButtonCell();
            }
        });

        tbl_school.getColumns().add(col_action);
    }

    private void insertDeleteButton() {
        TableColumn col_action = new TableColumn<>("");
        col_action.setSortable(false);

        col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<School, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<School, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        col_action.setCellFactory(new Callback<TableColumn<School, Boolean>, TableCell<School, Boolean>>() {
            @Override
            public TableCell<School, Boolean> call(TableColumn<School, Boolean> p) {
                return new DeleteButtonCell();
            }
        });

        tbl_school.getColumns().add(col_action);
    }

    //Define the button cell
    private class EditButtonCell extends TableCell<School, Boolean> {
        final Button cellButton = new Button("Edit");

        EditButtonCell() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    School school = tbl_school.getItems().get(getTableRow().getIndex());

                    editSchool(school);
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
    private class DeleteButtonCell extends TableCell<School, Boolean> {
        final Button cellButton = new Button("Delete");

        DeleteButtonCell() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    // Get selected item
                    School school = tbl_school.getItems().get(getTableRow().getIndex());

                    deleteSchool(school);
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
