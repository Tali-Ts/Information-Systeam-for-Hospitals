package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.Doctor;
import Model.Hospital;
import application.Interfaces.ConfirmRemove;
import application.Interfaces.Message;
import application.Interfaces.SetDocComboBox;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class RemoveDoctorScreenController implements Initializable, SetDocComboBox, Message, ConfirmRemove{
	@FXML
    private AnchorPane removeDoctorScreen;

    @FXML
    private Button removeButton;

    @FXML
    private ComboBox<String> doctorList;

    @FXML
    private Label errorMessage;

    //initializes the list of the doctors in the combo box
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDoctorComboBox(doctorList);
	}
	
	//this method removes the chosen doctor
	@FXML
	void remove (Event event) {
		errorMessage.setVisible(false);
		boolean flag = true;
		
		int docId = -1;
    	try {
    		/* opens a pop up message that asks the user if he's sure he wants to remove,
    		 * and reacts based on the user's answer
    		 */
    		String[] str = doctorList.getValue().split(" ");
    		docId = Integer.parseInt(str[0]);
		} catch (Exception e) {
			errorMessage.setVisible(true);
			flag = false;
		}
		
		if (flag) {
			Alert alert = confirmRemove("Are you sure you want to remove the doctor?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().getText().equalsIgnoreCase("Yes")){
				Doctor doc = Hospital.getInstance().getRealDoctor(docId);
				if (Hospital.getInstance().removeDoctor(doc)) {
					message(AlertType.INFORMATION, "Information Dialog", "The doctor " + doc.getFname() + " " + doc.getLname() + " was removed succssefully!");
	        	}
				else {
	        		message(AlertType.WARNING, "Warning Dialog", "Couldn't remove the doctor");
	        	}
				doctorList.getSelectionModel().clearSelection();
				setDoctorComboBox(doctorList);
			}
			
		}
	}

}
