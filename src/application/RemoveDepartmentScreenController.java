package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.Department;
import Model.Hospital;
import application.Interfaces.ConfirmRemove;
import application.Interfaces.Message;
import application.Interfaces.SetDepComboBox;
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

public class RemoveDepartmentScreenController implements Initializable, SetDepComboBox, Message, ConfirmRemove{
	@FXML
    private AnchorPane removeDepartmentScreen;

    @FXML
    private Button removeButton;

    @FXML
    private ComboBox<String> departmentList;

    @FXML
    private Label errorMessage;

  //initializes the list of the departments in the combo box
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDepartmentComboBox(departmentList);
	}
	
	//this method removes the chosen department
	@FXML
	void remove (Event event) {
		errorMessage.setVisible(false);
		boolean flag = true;
		
		int depId = -1;
		try {
			//splits the text to get the id of the department
			String[] str = departmentList.getValue().split(" ");
			depId = Integer.parseInt(str[0]);
		} catch (NullPointerException e) {
			errorMessage.setVisible(true);
			flag = false;
		}
		
		if (flag) {
			/* opens a pop up message that asks the user if he's sure he wants to remove,
			 * and reacts based on the user's answer
			 */
			Alert alert = confirmRemove("Are you sure you want to remove the department?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().getText().equalsIgnoreCase("Yes")){
				Department d = Hospital.getInstance().getRealDepartment(depId);
				if (Hospital.getInstance().removeDepartment(d)) {
					message(AlertType.INFORMATION, "Information Dialog", "The department " 
							+ d.getDeptName() + " was removed succssefully!");
	        	}
				else {
	        		message(AlertType.WARNING, "Warning Dialog", "Couldn't remove the department");
	        	}
				departmentList.getSelectionModel().clearSelection();
				setDepartmentComboBox(departmentList);
			} 
		}
	}

}
