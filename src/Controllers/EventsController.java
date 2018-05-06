package Controllers;
import Data.Event;
import Data.EventDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class EventsController extends Controller {
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

        insertEditButton();
        insertDeleteButton();
    }

    public void back(ActionEvent event) {
        redirect("registrar_dashboard");
    }

    public void newEvent(ActionEvent event) {
        EventFormController controller = redirect("event_form").getController();
    }

    private void editEvent(Event event) {
        EventFormController controller = redirect("event_form").getController();
        controller.setEvent(event);
        controller.fillForm();
    }

    private void deleteEvent(Event event) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Delete '" + event.getEventName() + "'? This will delete all data associated with this event.",
                ButtonType.YES,
                ButtonType.CANCEL
        );

        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            EventDAO eventDao = new EventDAO();

            eventDao.deleteEvent(event);

            redirect("manage_events");
        }
    }

    private void insertEditButton() {
        TableColumn col_action = new TableColumn<>("");
        col_action.setSortable(false);

        col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Event, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        col_action.setCellFactory(new Callback<TableColumn<Event, Boolean>, TableCell<Event, Boolean>>() {
            @Override
            public TableCell<Event, Boolean> call(TableColumn<Event, Boolean> p) {
                return new EditButtonCell();
            }
        });

        tbl_events.getColumns().add(col_action);
    }

    private void insertDeleteButton() {
        TableColumn col_action = new TableColumn<>("");
        col_action.setSortable(false);

        col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Event, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        col_action.setCellFactory(new Callback<TableColumn<Event, Boolean>, TableCell<Event, Boolean>>() {
            @Override
            public TableCell<Event, Boolean> call(TableColumn<Event, Boolean> p) {
                return new DeleteButtonCell();
            }
        });

        tbl_events.getColumns().add(col_action);
    }

    //Define the button cell
    private class EditButtonCell extends TableCell<Event, Boolean> {
        final Button cellButton = new Button("Edit");

        EditButtonCell() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    Event event = tbl_events.getItems().get(getTableRow().getIndex());

                    editEvent(event);
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
    private class DeleteButtonCell extends TableCell<Event, Boolean> {
        final Button cellButton = new Button("Delete");

        DeleteButtonCell() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    // Get selected item
                    Event event = tbl_events.getItems().get(getTableRow().getIndex());

                    deleteEvent(event);
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
