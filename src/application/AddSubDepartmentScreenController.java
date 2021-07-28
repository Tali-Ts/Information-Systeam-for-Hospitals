package application;

import java.net.URL;
import java.util.ResourceBundle;
import Model.Department;
import Model.Hospital;
import Model.SubDepartment;
import application.Interfaces.Message;
import application.Interfaces.SetDepComboBox;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

public class AddSubDepartmentScreenController implements Initializable, SetDepComboBox, Message{
	@FXML
    private AnchorPane addSubDepartmentScreen;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> departmentList;
    
    @FXML
    private Label departmentErrorMessage;

    //initializes the combo box that contains the sub-departments list.
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDepartmentComboBox(departmentList);
	}
    
	/* this method gets called when the user clicks on the add button in the screen,
	 * it checks that all the fields are full, and if they are it creates a new patient, using the data the user entered
	 */
	@FXML
    void add (Event event) {
		boolean flag = true;
		
		//if the user didn't choose any department, show a relevant message to the user and change the flag to false
		int depId = -1;
		try {
			String[] str = departmentList.getValue().split(" ");
			depId = Integer.parseInt(str[0]);
		} catch (NullPointerException e) {
			departmentErrorMessage.setVisible(true);
			flag = false;
		}
    	
		//if flag is true it means the user chose a department, so it creates a new sub-department in that department
		if (flag) {
			Department d = Hospital.getInstance().getRealDepartment(depId); 
	    	SubDepartment sd = new SubDepartment(d);
	    	//if the sub-department was added successfully, show a success message
	    	if (Hospital.getInstance().addSubDepartment(d, sd)) {
	    		message(AlertType.INFORMATION, "Information Dialog", "The sub-department was added successfully!");
	    	}
	    	else {
	    		message(AlertType.WARNING, "Warning Dialog", "Couldn't add the sub-department");
        	}
	    	
	    	//clear the check box for the next add
	    	departmentList.getSelectionModel().clearSelection();
	    	setDepartmentComboBox(departmentList);
		}
    }

}
