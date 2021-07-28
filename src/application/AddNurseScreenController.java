package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;
import Model.Department;
import Model.Doctor;
import Model.Hospital;
import Model.Nurse;
import Model.SubDepartment;
import Utils.Treatments;
import application.Interfaces.EmptyStringCheck;
import application.Interfaces.Message;
import application.Interfaces.NameValidation;
import application.Interfaces.PasswordValidation;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ProgressBar;

public class AddNurseScreenController extends NameValidation implements Initializable, EmptyStringCheck, Message, PasswordValidation {
    @FXML
    private AnchorPane addNurseScreen;
	
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
    private ComboBox<String> treatmentList;

    @FXML
    private PasswordField passwordFiled;
    
    @FXML
    private Label fnameErrorMessage;
    
    @FXML
    private Label lnameErrorMessage;

    @FXML
    private Label subdepartmentErrorMessage;

    @FXML
    private Label treatmentErrorMessage;

    @FXML
    private Label passwordErrorMessage;

    @FXML
    private Label infoLabel;

    @FXML
    private Tooltip infoTooltip;
    
    @FXML
    private ProgressBar passwordStrength;
    
    @FXML
    private Label sdInfoLabel;

    @FXML
    private Tooltip sdInfoTooltip;
    
    private Department doctorDep;
    
    private int nurseSdId;
    
  //this method checks what type of user logged in (admin/doctor/nurse), and adjusts the fields according to his permissions
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	infoLabel.setTooltip(infoTooltip);
    	sdInfoLabel.setTooltip(sdInfoTooltip);
    	passwordStrength.setId("start");
    	setSubDepartmentComboBox(subdepartmentList);
    	setTreatmentsComboBox();
		
	}
    
    /* this method gets called when the user clicks on the add button in the screen,
     * it checks that all the fields are full, and if they are it creates a new nurse, using the data the user entered
     */
    @FXML
    void add (Event event) {
    	fnameErrorMessage.setVisible(false);
    	lnameErrorMessage.setVisible(false);
    	subdepartmentErrorMessage.setVisible(false);
    	treatmentErrorMessage.setVisible(false);
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
			subdepartmentErrorMessage.setVisible(true);
			flag = false;
		}
    	
    	Treatments treat = null;
    	try {
			treat = Treatments.valueOf(treatmentList.getValue());
		} catch (Exception e) {
			treatmentErrorMessage.setVisible(true);
			flag = false;
		}
    	
    	String password = passwordFiled.getText();
    	if (password.length()<5) {
    		passwordErrorMessage.setVisible(true);
    		flag = false;
    	}
    	
    	//if flag is true it means all the required fields are filled, so it creates a new nurse with the data the user entered
    	if (flag && flagFname && flagLname) {
    		SubDepartment sd = Hospital.getInstance().getRealSubDepartment(sdId);
        	Nurse n = new Nurse(fname, lname, treat, sd, password);
        	if (Hospital.getInstance().addNurse(n, sd)) {
        		//if the nurse was added successfully, show a success message
        		message(AlertType.INFORMATION, "Information Dialog", "The nurse was added successfully!\nThe user name is: n-" 
        				+ n.getId() + "\nThe password is: " + n.getPassword());
        	}
        	else {
        		message(AlertType.WARNING, "Warning Dialog", "Couldn't add the nurse");
        	}
        	
        	//clear the selection, so it will be ready for the next add
        	firstNameField.clear();
        	lastNameFiled.clear();
        	passwordFiled.clear();
        	subdepartmentList.getSelectionModel().clearSelection();
        	treatmentList.getSelectionModel().clearSelection();
        	setSubDepartmentComboBox(subdepartmentList);
        	setTreatmentsComboBox();
        	fnameErrorMessage.setVisible(false);
        	lnameErrorMessage.setVisible(false);
        	subdepartmentErrorMessage.setVisible(false);
        	treatmentErrorMessage.setVisible(false);
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
	
	/* this method checks every character the user enters in the password field and checks if it is a valid password, it also lets the user
	 * know if the password is strong with the progress bar
	 */
	@FXML
	void validatePassword (KeyEvent event) {
		passwordValidation(passwordFiled, passwordStrength);
	}
	
	void setSubDepartmentComboBox(ComboBox<String> cb) {
	ArrayList<String> sdTmp = new ArrayList<String>();
	for (SubDepartment sd : Hospital.getInstance().getSubDepartment().values()) {
		//if the user is a doctor - let him see only options of sub departments that are in his department
		if (LoginScreenController.getType().equals("doctor")) {
			int i = LoginScreenController.getId();
			Doctor doc = Hospital.getInstance().getRealDoctor(i);
			doctorDep = doc.getSd().getDeptment();
			if (sd.getDeptment().equals(doctorDep)) {
				String s = Integer.toString(sd.getId());
				sdTmp.add(s);
			}
			sdInfoLabel.setVisible(true);
		}
		//if the user is a nurse - he can only add to his own sub department so the selection for him is disabled
		else if (LoginScreenController.getType().equals("nurse")) {
			int i = LoginScreenController.getId();
			Nurse n = Hospital.getInstance().getRealNurse(i);
			nurseSdId = n.getSd().getId();
			cb.getSelectionModel().select(Integer.toString(nurseSdId));
			cb.setDisable(true);
			AutoComplete.autoCompleteComboBoxPlus(cb, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
			sdInfoLabel.setVisible(true);
		}
		//admin can add to all the sub departments in the hospital
		else {
			String s = Integer.toString(sd.getId());
			sdTmp.add(s);
		}
	}
	String[] sdId = Arrays.copyOf(sdTmp.toArray(), sdTmp.toArray().length, String[].class);
	cb.setItems(FXCollections.observableArrayList(sdId));
	AutoComplete.autoCompleteComboBoxPlus(cb, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
	
	void setTreatmentsComboBox() {
	String[] treatList = Arrays.stream(Treatments.values()) // create stream of enum values
	        .map(e -> e.toString())  // convert enum stream to String stream
	        .toArray(String[]::new); // convert stream to an array
	treatmentList.setItems(FXCollections.observableArrayList(treatList));	
	AutoComplete.autoCompleteComboBoxPlus(treatmentList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
}
