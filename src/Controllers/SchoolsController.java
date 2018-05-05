package Controllers;

import Data.School;
import Data.SchoolDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SchoolsController {
    @FXML private TableView<School> tbl_school;
    @FXML private TableColumn<School,Integer> col_sId;
    @FXML private TableColumn<School,String> col_sName;
    @FXML private TableColumn<School,Integer> col_rId;
    private ObservableList<School> data;
    private SchoolDAO dao;

    @FXML
    void initialize() {
        assert tbl_school != null : "fx:id=\"tbl_school\" was not injected";
        col_sId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        col_sName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        col_rId.setCellValueFactory(
                new PropertyValueFactory<>("region_id")
        );

        dao = new SchoolDAO();

        data = dao.getSchools();
        tbl_school.setItems(data);
    }


}
