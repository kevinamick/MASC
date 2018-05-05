package Controllers;
import Data.Event;
import Data.EventDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EventsController {
    @FXML private TableView<Event> tbl_events;
    @FXML private  TableColumn<Event,Integer> col_eId;
    @FXML private TableColumn<Event,String> col_eName;
    private ObservableList<Event> data;
    private EventDAO dao;

    @FXML
    void initialize() {
        assert tbl_events != null : "fx:id=\"tbl_events\" was not injected";
        col_eName.setCellValueFactory(
                new PropertyValueFactory<>("eventName")
        );

        dao = new EventDAO();

        data = dao.getEvents();
        tbl_events.setItems(data);
    }
}
