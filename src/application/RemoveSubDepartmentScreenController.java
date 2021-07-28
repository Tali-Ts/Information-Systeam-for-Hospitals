package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Department;
import Model.Hospital;
import Model.SubDepartment;
import application.Interfaces.ConfirmRemove;
import application.Interfaces.Message;
import application.Interfaces.SetSdComboBox;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class RemoveSubDepartmentScreenController implements Initializable, SetSdComboBox, Message, ConfirmRemove {
	
    @FXML
    private AnchorPane removeSubDepartmentScreen;

    @FXML
    private Button removeButton;

    @FXML
    private ComboBox<String> subDepartmentList;

    @FXML
    private Label errorMessage;

  //initializes the list of the sub-departments in the combo box
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setSubDepartmentComboBox(subDepartmentList);
	}
	
	//this method removes the chosen sub-department
	@FXML
	void remove (Event event) {
		errorMessage.setVisible(false);
		boolean flag1=true;
		
		int toDeleteId = -1;
		try {
			toDeleteId = Integer.parseInt(subDepartmentList.getValue());
		} catch (Exception e) {
			errorMessage.setVisible(true);
			flag1 = false;
		}
		
		if (flag1) {
			/* opens a pop up message that asks the user if he's sure he wants to remove,
			 * and reacts based on the user's answer
			 */
			SubDepartment toDelete = Hospital.getInstance().getRealSubDepartment(toDeleteId);
			Department dep = toDelete.getDeptment();
			/* if one of the data structures in the chosen sub-department is not empty, make a least with all the sub-departments in the
			 * same department except the chosen one
			 */
			if (!toDelete.getDoctors().isEmpty() || !toDelete.getNurses().isEmpty() || !toDelete.getPatients().isEmpty()
					|| !toDelete.getReports().isEmpty()) {
				ArrayList<String> sdTmp = new ArrayList<String>();
				for (SubDepartment sd : dep.getSdepts()) {
					if (sd != toDelete) {
						String s = Integer.toString(sd.getId());
						sdTmp.add(s);
					}
				}
				/*show the user a pop-up dialog box where he needs to choose a sub department from the list to move the data there*/
				List<String> choices = sdTmp;
				ChoiceDialog<String> dialog = new ChoiceDialog<>(null, choices);
				dialog.setTitle("Choice Dialog");
				dialog.setHeaderText("Look, the sub-department you're trying to remove contains data");
				dialog.setContentText("Choose a sub-department that will contain the data instead:");
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    boolean flag2=true;
					int toMoveToId = -1;
					try {
						toMoveToId = Integer.parseInt(result.get());
					} catch (Exception e) {
						flag2 = false;
					}
					if (flag2) {
						SubDepartment toMoveTo = Hospital.getInstance().getRealSubDepartment(toMoveToId);
						dep.moveSubDepartment(toDelete, toMoveTo); //move all the data from the sub department the user chose to delete, to the one he chose to move to
						//show a success message
						if (dep.removeSubDepartment(toDelete) && Hospital.getInstance().removeSubDepartment(toDelete)) {
							message(AlertType.INFORMATION, "Information Dialog", "The sub-department " + toDelete.getId() 
							+ " was removed succssefully!");
						}
						else {
							message(AlertType.WARNING, "Warning Dialog", "Couldn't remove the sub-department");
						}
					}	
				}
			}
			/* opens a pop up message that asks the user if he's sure he wants to remove,
			 * and reacts based on the user's answer
			 */
			else {
				Alert alert = confirmRemove("Are you sure you want to remove the sub-department?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get().getText().equalsIgnoreCase("Yes")){
					if (dep.removeSubDepartment(toDelete) && Hospital.getInstance().removeSubDepartment(toDelete)) {
						message(AlertType.INFORMATION, "Information Dialog", "The sub-department " + toDelete.getId() 
								+ " was removed succssefully!");
					}
					else {
		        		message(AlertType.WARNING, "Warning Dialog", "Couldn't remove the sub-department");
		        	}
				}
			}
			subDepartmentList.getSelectionModel().clearSelection();
			setSubDepartmentComboBox(subDepartmentList);
		}
	}
	
}
