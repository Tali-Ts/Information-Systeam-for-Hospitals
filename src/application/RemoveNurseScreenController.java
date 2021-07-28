package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.Department;
import Model.Doctor;
import Model.Hospital;
import Model.Nurse;
import Model.SubDepartment;
import application.Interfaces.ConfirmRemove;
import application.Interfaces.Message;
import javafx.collections.FXCollections;
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

public class RemoveNurseScreenController implements Initializable, Message, ConfirmRemove{
	@FXML
    private AnchorPane removeNurseScreen;

    @FXML
    private Button removeButton;

    @FXML
    private ComboBox<String> nurseList;

    @FXML
    private Label errorMessage;
    
    private Department doctorDep;
    
    private SubDepartment nurseSd;

  //initializes the list of the nurses in the combo box
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setNurseComboBox();
	}
	
	//this method checks what type of user logged in (admin/doctor/nurse), and adjusts the combo box according to his permissions
	public void setNurseComboBox () {
		ArrayList<String> nTmp = new ArrayList<String>();
		for (Nurse n : Hospital.getInstance().getNurses().values()) {
			//a doctor can remove all the nurses from his department
			if (LoginScreenController.getType().equals("doctor")) {
				int i = LoginScreenController.getId();
				Doctor doc = Hospital.getInstance().getRealDoctor(i);
				doctorDep = doc.getSd().getDeptment();
				if (n.getSd().getDeptment().equals(doctorDep)) {
					nTmp.add(Integer.toString(n.getId()) + " - " + n.getFname() + " " + n.getLname());
				}
			}
			//a nurse can remove all the nurses from his sub-department
			else if (LoginScreenController.getType().equals("nurse")) {
				int i = LoginScreenController.getId();
				Nurse nurse = Hospital.getInstance().getRealNurse(i);
				nurseSd = nurse.getSd();
				//makes sure a nurse can't remove herself
				if (n.getSd().equals(nurseSd) && n.getId()!=i) {
					nTmp.add(Integer.toString(n.getId()) + " - " + n.getFname() + " " + n.getLname());
				}
			}
			//admin can remove all the nurses in the hospital
			else {
				nTmp.add(Integer.toString(n.getId()) + " - " + n.getFname() + " " + n.getLname());
			}
		}
		String[] nId = Arrays.copyOf(nTmp.toArray(), nTmp.toArray().length, String[].class);
		nurseList.setItems(FXCollections.observableArrayList(nId));
		AutoComplete.autoCompleteComboBoxPlus(nurseList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
	
	//this method removes the chosen nurse
	@FXML
	void remove (Event event) {
		errorMessage.setVisible(false);
		boolean flag = true;
		
		int nId = -1;
		try {
			//splits the text because we only use the id, to get the nurse
			String[] str = nurseList.getValue().split(" ");
			nId = Integer.parseInt(str[0]);
		} catch (Exception e) {
			errorMessage.setVisible(true);
			flag = false;
		}
		
		if (flag) {
			/* opens a pop up message that asks the user if he's sure he wants to remove,
			 * and reacts based on the user's answer
			 */
			Alert alert = confirmRemove("Are you sure you want to remove the nurse?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().getText().equalsIgnoreCase("Yes")){
				Nurse n = Hospital.getInstance().getRealNurse(nId);
				if (Hospital.getInstance().removeNurse(n)) {
					message(AlertType.INFORMATION, "Information Dialog", "The nurse " + n.getFname() + " " + n.getLname() + " was removed succssefully!");
	        	}
				else {
	        		message(AlertType.WARNING, "Warning Dialog", "Couldn't remove the nurse");
	        	}
				nurseList.getSelectionModel().clearSelection();
				setNurseComboBox();
			}
			
		}
	}


}
