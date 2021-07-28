package application;

import java.net.URL;
import java.util.ResourceBundle;
import Model.Doctor;
import Model.Hospital;
import Model.SubDepartment;
import Utils.Specialization;
import application.Interfaces.EmptyStringCheck;
import application.Interfaces.Message;
import application.Interfaces.NameValidation;
import application.Interfaces.PasswordValidation;
import application.Interfaces.SetSdComboBox;
import application.Interfaces.SetSpecComboBox;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ProgressBar;

public class AddDoctorScreenController extends NameValidation implements Initializable, SetSpecComboBox, SetSdComboBox, 
			EmptyStringCheck, Message, PasswordValidation {
	@FXML
    private AnchorPane addDoctorScreen;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameFiled;
    
    @FXML
    private Label nameErrorMessage;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> subdepartmentList;

    @FXML
    private ComboBox<String> specializationList;

    @FXML
    private PasswordField passwordFiled;
    
    @FXML
    private Label fnameErrorMessage;

    @FXML
    private Label lnameErrorMessage;

    @FXML
    private Label subDepartmentErrorMessage;

    @FXML
    private Label specializationErrorMessage;

    @FXML
    private Label passwordErrorMessage;
    
    @FXML
    private Label infoLabel;

    @FXML
    private Tooltip infoTooltip;
    
    @FXML
    private ProgressBar passwordStrength;

  //initializes the toolTip, and sets the specialization and sub-department lists for the combo box
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		infoLabel.setTooltip(infoTooltip);
		passwordStrength.setId("start");
		setSpecializationComboBox(specializationList);
		setSubDepartmentComboBox(subdepartmentList);
	}
	
	/* this method gets called when the user clicks on the add button in the screen,
	 * it checks that all the fields are full, and if they are it creates a new doctor, using the data the user entered
	 */
	@FXML
    void add (Event event) {
		fnameErrorMessage.setVisible(false);
		lnameErrorMessage.setVisible(false);
		subDepartmentErrorMessage.setVisible(false);
		specializationErrorMessage.setVisible(false);
		passwordErrorMessage.setVisible(false);
		boolean flagFname=true, flagLname=true, flag=true;

		//if at least one of the fields is empty, show a  relevant message to the user and change the flag to false
    	String fname = firstNameField.getText();
    	flagFname = isEmptyStringCheck(fname, fnameErrorMessage);
    	
    	String lname = lastNameFiled.getText();
    	flagLname = isEmptyStringCheck(lname, lnameErrorMessage);
    	
    	int sdId = -1;
    	try {
    		sdId = Integer.parseInt(subdepartmentList.getValue());
		} catch (NumberFormatException e) {
			subDepartmentErrorMessage.setVisible(true);
			flag = false;
		}
    	
    	Specialization spec = null;
    	try {
    		spec = Specialization.valueOf(specializationList.getValue());
		} catch (Exception e) {
			specializationErrorMessage.setVisible(true);
			flag = false;
		}
    	
    	String password = passwordFiled.getText();
    	if (password.length()<6) {
    		passwordErrorMessage.setVisible(true);
    		flag = false;
    	}
    	
    	//if flag is true it means all the required fields are filled, so it creates a new doctor with the data the user entered
    	if (flag && flagFname && flagLname) {
    		SubDepartment sd = Hospital.getInstance().getRealSubDepartment(sdId);
        	Doctor d = new Doctor(fname, lname, spec, sd, password);
        	//if the doctor was added successfully, show a success message
        	if (Hospital.getInstance().addDoctor(d, sd)) {
        		message(AlertType.INFORMATION, "Information Dialog", "The doctor was added successfully!\nThe user name is: d-" 
        				+ d.getId() + "\nThe password is: " + d.getPassword());
        	}
        	else {
        		message(AlertType.WARNING, "Warning Dialog", "Couldn't add the doctor");
        	}
        	
        	//clear the selection, so it will be ready for the next add
        	firstNameField.clear();
        	lastNameFiled.clear();
        	passwordFiled.clear();
        	subdepartmentList.getSelectionModel().clearSelection();
        	specializationList.getSelectionModel().clearSelection();
        	setSpecializationComboBox(specializationList);
    		setSubDepartmentComboBox(subdepartmentList);
        	fnameErrorMessage.setVisible(false);
    		lnameErrorMessage.setVisible(false);
    		subDepartmentErrorMessage.setVisible(false);
    		specializationErrorMessage.setVisible(false);
    		passwordErrorMessage.setVisible(false);
    		passwordStrength.setId("start");
    	}
    }
	
	//this method checks every character the user is typing, and if it isn't valid input(for example: number), it doesn't type it
	@FXML
	void nameValidation (KeyEvent e) {
		nameValidation(e, nameErrorMessage, validChar); 	
	}
	
	// we use this variable to know if the user pressed backspace/tab/minus (if validChar is true), for the nameValidation method
	private boolean validChar;
	
	//this method returns true if the user pressed backspace/tab/minus, false else
	@FXML
	void validCharacters (KeyEvent event) {
		validChar = keyTypedCheck(event);
	}
	
	/* this method checks every character the user enters in the password field and checks if it is a valid password, it also lets
	 * the user know if the password is strong using the progress bar
	 */
	@FXML
	void validatePassword (KeyEvent event) {
		passwordValidation(passwordFiled, passwordStrength);
	}

}
