package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import Model.Department;
import Model.Hospital;
import Utils.Specialization;
import application.Interfaces.EmptyStringCheck;
import application.Interfaces.Message;
import application.Interfaces.SetSpecComboBox;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class AddDepartmentScreenController implements Initializable, SetSpecComboBox, EmptyStringCheck, Message {
    @FXML
    private AnchorPane addDepartmentScreen;
	
	@FXML
    private TextField nameField;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> specializationList;
    
    @FXML
    private Label nameErrorMessage;

    @FXML
    private Label SpecErrorMessage;

    //initialize the specialization list on the comboBox  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setSpecializationComboBox(specializationList);
	}
	
	/* this method gets called when the user clicks on the add button in the screen,
	 * it checks that all the fields are full, and if they are it creates a new department, using the data the user entered 
	 */
	@FXML
    void add (Event event) {
		//the error messages aren't visible until someone presses the add button without filling all the fields first
		nameErrorMessage.setVisible(false);
    	SpecErrorMessage.setVisible(false);
		Boolean flag=true;
		
		//if at least one of the fields is empty, show a relevant message to the user and change the flag to false
    	String name = nameField.getText();
    	flag = isEmptyStringCheck(name, nameErrorMessage);
    	
    	Specialization spec = null;
    	try {
    		spec = Specialization.valueOf(specializationList.getValue());
		} catch (Exception e) {
			SpecErrorMessage.setVisible(true);
			flag = false;
		}
    	
    	/* if the user filled all the required fields, build a new department using the name and specialization he entered,
    	 * then show a success pop-up message 
    	 */
    	if (flag) {
    		Department d = new Department(name, spec);
        	if (Hospital.getInstance().addDepartment(d)) {
        		message(AlertType.INFORMATION, "Information Dialog", "The department " + d.getDeptName() 
        				+ " was added successfully!");
        	}
        	else {
        		message(AlertType.WARNING, "Warning Dialog", "Couldn't add the department");
        	}
        	
        	//clear the selection, so it will be ready for the next add
        	nameField.clear();
        	specializationList.getSelectionModel().clearSelection();
        	setSpecializationComboBox(specializationList);
    	}	
	}

}
