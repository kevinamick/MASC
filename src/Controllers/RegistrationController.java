package Controllers;

import javafx.event.ActionEvent;

public class RegistrationController extends Controller {
    public void back(ActionEvent event) {
        redirect("advisor_dashboard");
    }
}
