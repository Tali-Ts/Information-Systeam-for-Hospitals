package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.Department;
import Model.Doctor;
import Model.Hospital;
import Model.Patient;
import application.Interfaces.ConfirmRemove;
import application.Interfaces.Message;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

public class RemovePatientScreenController implements Initializable, Message, ConfirmRemove{
	@FXML
    private AnchorPane removePatientScreen;

    @FXML
    private Button recoveredPatientButton;
    
    @FXML
    private Button deadPatientButton;

    @FXML
    private Button movedToHotelButton;

    @FXML
    private ComboBox<String> patientList;

    @FXML
    private Label errorMessage;
    
    private Department doctorDep;

  //initializes the list of the patients in the combo box
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setPatientComboBox();
	}
	
	public void setPatientComboBox () {
		ArrayList<String> pTmp = new ArrayList<String>();
		for (Patient p : Hospital.getInstance().getPatients().values()) {
			if (LoginScreenController.getType().equals("doctor")) {
				int i = LoginScreenController.getId();
				Doctor doc = Hospital.getInstance().getRealDoctor(i);
				doctorDep = doc.getSd().getDeptment();
				if (p.getSd().getDeptment().equals(doctorDep)) {
					pTmp.add(Integer.toString(p.getId()) + " - " + p.getFname() + " " + p.getLname());
				}
			}
			else {
				pTmp.add(Integer.toString(p.getId()) + " - " + p.getFname() + " " + p.getLname());
			}
		}
		String[] pId = Arrays.copyOf(pTmp.toArray(), pTmp.toArray().length, String[].class);
		patientList.setItems(FXCollections.observableArrayList(pId));
		AutoComplete.autoCompleteComboBoxPlus(patientList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
	
	//this method removes the chosen patient
	@FXML
	void removeDeadPatient (Event event) {
		errorMessage.setVisible(false);
		boolean flag = true;
		
		int pId = -1;
		try {
			String[] str = patientList.getValue().split(" ");
			pId = Integer.parseInt(str[0]);
		} catch (Exception e) {
			errorMessage.setVisible(true);
			flag = false;
		}
		
		if (flag) {
			/* opens a pop up message that asks the user if he's sure he wants to remove,
			 * and reacts based on the user's answer
			 */
			Alert alert = confirmRemove("Are you sure you want to remove the patient?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().getText().equalsIgnoreCase("Yes")){
				Patient p = Hospital.getInstance().getRealPatient(pId);
				if (Hospital.getInstance().removePatient(p)) {
					message(AlertType.INFORMATION, "Information Dialog", "The patient " + p.getFname() + " " + p.getLname() + " was removed succssefully!");
	        	}
				else {
	        		message(AlertType.WARNING, "Warning Dialog", "Couldn't remove the patient");
	        	}
				patientList.getSelectionModel().clearSelection();
				setPatientComboBox();
			}
			
		}
	}
	
	//this method removes the chosen patient to the hotel, if his release note is proper
	@FXML
    void removeMovedToHotel(ActionEvent event) {
		errorMessage.setVisible(false);
		boolean flag = true;
		
		int pId = -1;
		try {
			String[] str = patientList.getValue().split(" ");
			pId = Integer.parseInt(str[0]);
		} catch (Exception e) {
			errorMessage.setVisible(true);
			flag = false;
		}
		
		if (flag) {
			/* opens a pop up message that asks the user if he's sure he wants to remove,
			 * and reacts based on the user's answer
			 */
			Alert alert = confirmRemove("Are you sure you want to remove the patient to hotel?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().getText().equalsIgnoreCase("Yes")){
				Patient p = Hospital.getInstance().getRealPatient(pId);
				if (Hospital.getInstance().removeToHotelPatient(p).equals("Success")) {
					message(AlertType.INFORMATION, "Information Dialog", "Removed patient to hotel succssefully!");
	        	}
				else {
					message(AlertType.ERROR, "Error Dialog", Hospital.getInstance().removeToHotelPatient(p));
				}
				patientList.getSelectionModel().clearSelection();
				setPatientComboBox();
			}
		}
	}
	
	//this method removes the chosen patient, if his release note is proper
	@FXML
    void removeRecoveredPatient(ActionEvent event) {
		errorMessage.setVisible(false);
		boolean flag = true;
		
		int pId = -1;
		try {
			String[] str = patientList.getValue().split(" ");
			pId = Integer.parseInt(str[0]);
		} catch (Exception e) {
			errorMessage.setVisible(true);
			flag = false;
		}
		
		if (flag) {
			/* opens a pop up message that asks the user if he's sure he wants to remove,
			 * and reacts based on the user's answer
			 */
			Alert alert = confirmRemove("Are you sure you want to remove the recovered patient?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().getText().equalsIgnoreCase("Yes")) {
				Patient p = Hospital.getInstance().getRealPatient(pId);
				if (Hospital.getInstance().removeRecoverPatient(p).equals("Success")) {
					message(AlertType.INFORMATION, "Information Dialog", "Recovered patient was removed succssefully!");
	        	}
				else {
					message(AlertType.ERROR, "Error Dialog" , Hospital.getInstance().removeRecoverPatient(p));
				}
				patientList.getSelectionModel().clearSelection();
				setPatientComboBox();
			}
		}
	}

}
