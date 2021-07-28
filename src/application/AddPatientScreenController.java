package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import Model.Disease;
import Model.Doctor;
import Model.Hospital;
import Model.Nurse;
import Model.Patient;
import Model.SubDepartment;
import application.Interfaces.EmptyStringCheck;
import application.Interfaces.Message;
import application.Interfaces.NameValidation;
import application.Interfaces.SetDComboBox;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;

public class AddPatientScreenController extends NameValidation implements Initializable, SetDComboBox, EmptyStringCheck, Message{
	@FXML
    private AnchorPane addPatientScreen;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameFiled;
    
    @FXML
    private Label nameErrorMessage;

    @FXML
    private ComboBox<String> subdepartmentList;

    @FXML
    private ComboBox<String> diseaseList;

    @FXML
    private Button addButton;

    @FXML
    private TextField statusField;
    
    @FXML
    private Label fnameErrorMessage;

    @FXML
    private Label lnameErrorMessage;

    @FXML
    private Label statuseErrorMessage;

    @FXML
    private Label subDepartmentErrorMessage;

    @FXML
    private Label diseaseErrorMessage;
    
    @FXML
    private Label infoLabel;

    @FXML
    private Tooltip infoTooltip;
    
    @FXML
    private Label statusInfoLabel;

    @FXML
    private Tooltip statusInfoTooltip;

    @FXML
    private Label statusRangErrorMessage;
    
    @FXML
    private Label statusCharErrorMessage;
    
    private int doctorSdId;
    
    private int nurseSdId;
    
    /* initializes the combo box that contains the patients list.
     * and checks what type of user logged in (admin/doctor/nurse), and adjusts the fields according to his permissions
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDiseaseComboBox(diseaseList);
		setSubDepartmentComboBox(subdepartmentList);
		
		//a tool tip to inform the user that the status must be between 0 to 100
		statusInfoLabel.setTooltip(statusInfoTooltip);
	}
    
	/* this method gets called when the user clicks on the add button in the screen,
	 * it checks that all the fields are full, and if they are it creates a new patient, using the data the user entered
	 */
	@FXML
    void add (Event event) {
		fnameErrorMessage.setVisible(false);
		lnameErrorMessage.setVisible(false);
		statuseErrorMessage.setVisible(false);
		subDepartmentErrorMessage.setVisible(false);
		diseaseErrorMessage.setVisible(false);
		nameErrorMessage.setVisible(false);
		statusCharErrorMessage.setVisible(false);
    	statusRangErrorMessage.setVisible(false);
		boolean flagFname=true, flagLname=true, flag=true;
		
		//if at least one of the fields is empty, show a  relevant message to the user and change the flag to false
    	String fname = firstNameField.getText();
    	flagFname = isEmptyStringCheck(fname, fnameErrorMessage);
    
    	String lname = lastNameFiled.getText();
    	flagLname = isEmptyStringCheck(lname, lnameErrorMessage);
    	
    	int status = -1;
    	try {
    		status = Integer.parseInt(statusField.getText());
		} catch (Exception e) {
			statuseErrorMessage.setVisible(true);
			flag = false;
		}
    	
    	int sdId = -1;
    	try {
    		sdId = Integer.parseInt(subdepartmentList.getValue());
		} catch (NumberFormatException e) {
			subDepartmentErrorMessage.setVisible(true);
			flag = false;
		}
    	
    	String diseasName = diseaseList.getValue();
    	if (diseasName == null) {
    		diseaseErrorMessage.setVisible(true);
    		flag = false;
    	}
    	
    	//if flag is true it means all the required fields are filled, so it creates a new patient with the data the user entered
    	if (flag && flagFname &&flagLname) {
    		SubDepartment sd = Hospital.getInstance().getRealSubDepartment(sdId);
        	Disease d = Hospital.getInstance().getRealDiseaseByName(diseasName);
        	Patient p = new Patient(fname, lname, status, sd, d);
        	//if the patient was added successfully, show a success message
        	if (Hospital.getInstance().addPatient(p, sd)) {
        		message(AlertType.INFORMATION, "Information Dialog", "The patient " + p.getFname() + " " + p.getLname() 
				+ " was added successfully!");
        	}
        	else {
        		message(AlertType.WARNING, "Warning Dialog", "Couldn't add the patient");
        	}
        	
        	//clear the selection, so it will be ready for the next add
        	firstNameField.clear();
        	lastNameFiled.clear();
        	statusField.clear();
        	diseaseList.getSelectionModel().clearSelection();
        	subdepartmentList.getSelectionModel().clearSelection();
    		setDiseaseComboBox(diseaseList);
    		setSubDepartmentComboBox(subdepartmentList);
        	setDiseaseComboBox(diseaseList);
        	setSubDepartmentComboBox(subdepartmentList);
        	fnameErrorMessage.setVisible(false);
    		lnameErrorMessage.setVisible(false);
    		statuseErrorMessage.setVisible(false);
    		subDepartmentErrorMessage.setVisible(false);
    		diseaseErrorMessage.setVisible(false);
    		nameErrorMessage.setVisible(false);
    		statusCharErrorMessage.setVisible(false);
        	statusRangErrorMessage.setVisible(false);
    	}	
    }
	
	//this method checks every character the user is typing, and if it isn't valid input(for example: number), it doesn't type it
	@FXML
	void nameValidation (KeyEvent e) {
		nameValidation(e, nameErrorMessage, validChar);
	}
	
	// we use this variable to know if the user pressed backspace/tab/minus (if validChar is true), for the nameValidation method
	private boolean validChar;
	
	//this method changes the value of res to true if the user pressed backspace/tab/minus, false else
	@FXML
	void validCharacters (KeyEvent event) {
		validChar = keyTypedCheck(event);
	}
	
	//this method lets the user type only numbers that are in the range (0-100)
	@FXML
	void statusValidation (KeyEvent e) {
		statusCharErrorMessage.setVisible(false);
    	statusRangErrorMessage.setVisible(false);
		String str = e.getCharacter();
        int len = str.length();
        for(int i = 0; i < len; i++) {
            Character c = str.charAt(i);
            if ((!validChar || str.contains("-")) && !Character.isDigit(c)) {
            	statusRangErrorMessage.setVisible(false);
            	statusCharErrorMessage.setVisible(true);
            	e.consume();
            }
            if (!validChar && !statusField.getText().isEmpty() && statusField.getLength()>1) {
            	statusField.setText("100");
            	statusCharErrorMessage.setVisible(false);
            	statusRangErrorMessage.setVisible(true);
            	e.consume();
            }
        } 	
	}
	
	public void setSubDepartmentComboBox (ComboBox<String> cb) {
	ArrayList<String> sdTmp = new ArrayList<String>();
	for (SubDepartment sd : Hospital.getInstance().getSubDepartment().values()) {
		String s = Integer.toString(sd.getId());
		sdTmp.add(s);
	}
	String[] sdId = Arrays.copyOf(sdTmp.toArray(), sdTmp.toArray().length, String[].class);
	subdepartmentList.setItems(FXCollections.observableArrayList(sdId));
	AutoComplete.autoCompleteComboBoxPlus(subdepartmentList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	//if the user is a doctor - let him see only options of sub departments that are in his department
	if (LoginScreenController.getType().equals("doctor")) {
		int i = LoginScreenController.getId();
		Doctor doc = Hospital.getInstance().getRealDoctor(i);
		doctorSdId = doc.getSd().getId();
		subdepartmentList.getSelectionModel().select(Integer.toString(doctorSdId));
		subdepartmentList.setDisable(true);
		infoLabel.setVisible(true);
		infoLabel.setTooltip(infoTooltip);
		AutoComplete.autoCompleteComboBoxPlus(subdepartmentList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
	//if the user is a nurse - he can only add to his own sub department so the selection for him is disabled
	if (LoginScreenController.getType().equals("nurse")) {
		int i = LoginScreenController.getId();
		Nurse n = Hospital.getInstance().getRealNurse(i);
		nurseSdId = n.getSd().getId();
		subdepartmentList.getSelectionModel().select(Integer.toString(nurseSdId));
		subdepartmentList.setDisable(true);
		infoLabel.setVisible(true);
		infoLabel.setTooltip(infoTooltip);
	}
	}
	
}
