package application;

import Model.Hospital;
import Model.Nurse;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FindHardestWorkingNurseScreenController {
    @FXML
    private Button findbutton;

    @FXML
    private Label message;
    
    //this method uses the query from hospital, that returns the nurse that treated the most patients
    @FXML
    void activateQuery() {
    	Nurse n = Hospital.getInstance().findHardestWorkingNurse();
    	//after finding her, shows the user her name and amount of shifts
    	message.setVisible(true);
    	message.setText("ID: " + n.getId() + ", Name: " + n.getFname() + " " + n.getLname() + ", Shifts: " + n.getShifts());
    }

}
