package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import Model.Doctor;
import Model.Hospital;
import Model.Nurse;
import Model.Patient;
import application.Interfaces.FindDType;
import application.Interfaces.Message;
import application.Interfaces.SetDocComboBox;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class TreatmentsScreenController implements Initializable, SetDocComboBox, Message, FindDType {
    @FXML
    private AnchorPane treatmentsScreen;

    @FXML
    private Button treatDiseaseDoc;

    @FXML
    private ComboBox<String> nurseList;

    @FXML
    private Label patientErrorMessage;

    @FXML
    private Button treatDiseaseNurse;

    @FXML
    private Button treatPatientDoc;

    @FXML
    private Button treatPatientNurse;

    @FXML
    private ComboBox<String> doctorList;

    @FXML
    private ComboBox<String> patientList;

    @FXML
    private Label doctorErrorMessage;

    @FXML
    private Label nurseErrorMessage;
    
    @FXML
    private Label doctorLabel;

    @FXML
    private Label nursLabel;
    
    private Doctor doctor;
    
    private Nurse nurse;

    /*
     *This method initializes the screen, according to the type of user that logged in 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDoctorComboBox(doctorList); //initializes the combo box of the doctors
		setNursesComboBox(); //initializes the combo box of the nurses
		setPatientsComboBox(); //initializes the combo box of the patients
	}
	
	/*
	 * this method gets called when the user clicks on the "treat patient" button
	 * first it checks that all the fields the user needs to complete are not empty (based on the type of user that logged in) 
	 * and then calls the method that does the treatment with the selected patient
	 */
	@FXML
	void treatPatientDoc (Event event) {
		if (LoginScreenController.getType().equals("admin")) {
			doctorErrorMessage.setVisible(false);
			patientErrorMessage.setVisible(false);
			boolean flag1=true, flag2=true;
			
			int docId = -1;
	    	try {
	    		//split to only get the doctors's id from the string
	    		String[] str = doctorList.getValue().split(" ");
				docId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				doctorErrorMessage.setVisible(true);
				flag1 = false;
			}
	    	
	    	int pId = -1;
			try {
				//split to only get the patient's id from the string
				String[] str = patientList.getValue().split(" ");
				pId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				patientErrorMessage.setVisible(true);
				flag2 = false;
			}
	    	
	    	if (flag1 && flag2) {
	    		Doctor doc = Hospital.getInstance().getRealDoctor(docId);
	    		Patient p = Hospital.getInstance().getRealPatient(pId);
	    		doc.checkPatient(p);
	    		message(AlertType.INFORMATION, "Information Dialog", "You've treated " + p.getFname() + " " + p.getLname() 
	    				+ " successfully!\n" + "Patient's current status: " + p.getStatus() + "\nDoctor's shifts: " 
	    				+ doc.getShiftCounter());
	    	}
	    	patientList.getSelectionModel().clearSelection();
	    	doctorList.getSelectionModel().clearSelection();
	    	nurseList.getSelectionModel().clearSelection();
			setDoctorComboBox(doctorList); //initializes the combo box of the doctors
			setNursesComboBox(); //initializes the combo box of the nurses
			setPatientsComboBox(); //initializes the combo box of the patients
		}
		
		if (LoginScreenController.getType().equals("doctor")) {
			patientErrorMessage.setVisible(false);
			boolean flag=true;
			
			int pId = -1;
			try {
				String[] str = patientList.getValue().split(" ");
				pId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				patientErrorMessage.setVisible(true);
				flag = false;
			}
			
			if (flag) {
				Patient p = Hospital.getInstance().getRealPatient(pId);
				doctor.checkPatient(p);
				message(AlertType.INFORMATION, "Information Dialog", "You've treated " + p.getFname() + " " 
						+ p.getLname() + " successfully!\n" + "Patient's current status: " + p.getStatus() 
						+ "\nDoctor's shifts: " + doctor.getShiftCounter());
			}
			//clear selection
			patientList.getSelectionModel().clearSelection();
			setPatientsComboBox();
		}
	}
	
	/*
	 * this method gets called when the user clicks on the "treat disease" button
	 * first it checks that all the fields the user needs to complete are not empty (based on the type of user that logged in) 
	 * and then calls the method that does the treatment with the selected patient
	 */
	@FXML
	void treatDiseaseDoc (Event event) {
		if (LoginScreenController.getType().equals("admin")) {
			doctorErrorMessage.setVisible(false);
			nurseErrorMessage.setVisible(false);
			patientErrorMessage.setVisible(false);
			boolean flag1=true, flag2=true;
			
			int docId = -1;
	    	try {
	    		//split to only get the doctor's id from the string
	    		String[] str = doctorList.getValue().split(" ");
	    		docId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				doctorErrorMessage.setVisible(true);
				flag1 = false;
			}
	    	
	    	int pId = -1;
			try {
				//split to only get the patient's id from the string
				String[] str = patientList.getValue().split(" ");
				pId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				patientErrorMessage.setVisible(true);
				flag2 = false;
			}
	    	
	    	if (flag1 && flag2) {
	    		Doctor doc = Hospital.getInstance().getRealDoctor(docId);
	    		Patient p = Hospital.getInstance().getRealPatient(pId);
	    		doc.checkDisease(p);
	    		message(AlertType.INFORMATION, "Information Dialog", "You've treated " + p.getFname() + " " 
	    				+ p.getLname() + " successfully!\n" + "Patient's disease: " + findDiseaseType(p) + " " 
	    				+ p.getDisease().getName() + "\nDoctor's shifts: " + doc.getShiftCounter());
	    	}
	    	//clear selections
	    	patientList.getSelectionModel().clearSelection();
	    	doctorList.getSelectionModel().clearSelection();
	    	nurseList.getSelectionModel().clearSelection();
			setDoctorComboBox(doctorList); //initializes the combo box of the doctors
			setNursesComboBox(); //initializes the combo box of the nurses
			setPatientsComboBox(); //initializes the combo box of the patients
		}
		
		if (LoginScreenController.getType().equals("doctor")) {
			doctorErrorMessage.setVisible(false);
			nurseErrorMessage.setVisible(false);
			patientErrorMessage.setVisible(false);
			boolean flag=true;
			
			int pId = -1;
			try {
				//split to only get the patient's id from the string
				String[] str = patientList.getValue().split(" ");
				pId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				patientErrorMessage.setVisible(true);
				flag = false;
			}
			
			if (flag) {
				Patient p = Hospital.getInstance().getRealPatient(pId);
				doctor.checkDisease(p);
				message(AlertType.INFORMATION, "Information Dialog", "You've treated " + p.getFname() + " " 
						+ p.getLname() + " successfully!\n" + "Patient's disease: " + findDiseaseType(p) + " " 
						+ p.getDisease().getName() + "\nDoctor's shifts: " + doctor.getShiftCounter());
			}
			patientList.getSelectionModel().clearSelection();
			setPatientsComboBox();
		}
	}
	
	/*
	 * this method gets called when the user clicks on the "treat patient" button
	 * first it checks that all the fields the user needs to complete are not empty (based on the type of user that logged in) 
	 * and then calls the method that does the treatment with the selected patient
	 */
	@FXML
	void treatPatientNurse (Event event) {
		if (LoginScreenController.getType().equals("admin")) {
			doctorErrorMessage.setVisible(false);
			nurseErrorMessage.setVisible(false);
			patientErrorMessage.setVisible(false);
			boolean flag1=true, flag2=true;
			
			int nId = -1;
			try {
				//split to only get the nurse's id from the string
				String[] str = nurseList.getValue().split(" ");
				nId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				nurseErrorMessage.setVisible(true);
				flag1 = false;
			}
	    	
	    	int pId = -1;
			try {
				//split to only get the patient's id from the string
				String[] str = patientList.getValue().split(" ");
				pId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				patientErrorMessage.setVisible(true);
				flag2 = false;
			}
	    	
	    	if (flag1 && flag2) {
	    		Nurse n = Hospital.getInstance().getRealNurse(nId);
	    		Patient p = Hospital.getInstance().getRealPatient(pId);
	    		n.checkPatient(p);
	    		message(AlertType.INFORMATION, "Information Dialog", "You've treated " + p.getFname() + " " 
	    				+ p.getLname() + " successfully!\n" + "Patient's current status: " + p.getStatus() + 
	    				"\nNurse's shifts: " + n.getShifts());
	    	}
	    	//clear selections
	    	patientList.getSelectionModel().clearSelection();
	    	doctorList.getSelectionModel().clearSelection();
	    	nurseList.getSelectionModel().clearSelection();
			setDoctorComboBox(doctorList); //initializes the combo box of the doctors
			setNursesComboBox(); //initializes the combo box of the nurses
			setPatientsComboBox(); //initializes the combo box of the patients
		}
		
		if (LoginScreenController.getType().equals("nurse")) {
			doctorErrorMessage.setVisible(false);
			nurseErrorMessage.setVisible(false);
			patientErrorMessage.setVisible(false);
			boolean flag=true;
			
			int pId = -1;
			try {
				//split to only get the patient's id from the string
				String[] str = patientList.getValue().split(" ");
				pId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				patientErrorMessage.setVisible(true);
				flag = false;
			}
			
			if (flag) {
				Patient p = Hospital.getInstance().getRealPatient(pId);
				nurse.checkPatient(p);
				message(AlertType.INFORMATION, "Information Dialog", "You've treated " + p.getFname() + " " 
						+ p.getLname() + " successfully!\n" + "Patient's current status: " + p.getStatus() 
						+ "\nNurse's shifts: " + nurse.getShifts());
			}
			//clear selection
	    	patientList.getSelectionModel().clearSelection();
	    	setPatientsComboBox();
		}
	}
	
	/*
	 * this method gets called when the user clicks on the "treat disease" button
	 * first it checks that all the fields the user needs to complete are not empty (based on the type of user that logged in) 
	 * and then calls the method that does the treatment with the selected patient
	 */
	@FXML
	void treatDiseaseNurse (Event event) {
		if (LoginScreenController.getType().equals("admin")) {
			doctorErrorMessage.setVisible(false);
			nurseErrorMessage.setVisible(false);
			patientErrorMessage.setVisible(false);
			boolean flag1=true, flag2=true;
			
			int nId = -1;
			try {
				//split to only get the nurse's id from the string
				String[] str = nurseList.getValue().split(" ");
				nId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				nurseErrorMessage.setVisible(true);
				flag1 = false;
			}
	    	
	    	int pId = -1;
			try {
				//split to only get the patient's id from the string
				String[] str = patientList.getValue().split(" ");
				pId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				patientErrorMessage.setVisible(true);
				flag2 = false;
			}
	    	
	    	if (flag1 && flag2) {
	    		Nurse n = Hospital.getInstance().getRealNurse(nId);
	    		Patient p = Hospital.getInstance().getRealPatient(pId);
	    		n.checkDisease(p);
	    		message(AlertType.INFORMATION, "Information Dialog", "You've treated " + p.getFname() + " " 
	    				+ p.getLname() + " successfully!\n" + "Patient's current status: " + p.getStatus() 
	    				+ "\nNurse's shifts: " + n.getShifts());
	    	}
	    	//clear selections
	    	patientList.getSelectionModel().clearSelection();
	    	doctorList.getSelectionModel().clearSelection();
	    	nurseList.getSelectionModel().clearSelection();
			setDoctorComboBox(doctorList); //initializes the combo box of the doctors
			setNursesComboBox(); //initializes the combo box of the nurses
			setPatientsComboBox(); //initializes the combo box of the patients
		}
		
		if (LoginScreenController.getType().equals("nurse")) {
			doctorErrorMessage.setVisible(false);
			nurseErrorMessage.setVisible(false);
			patientErrorMessage.setVisible(false);
			boolean flag=true;
			
			int pId = -1;
			try {
				//split to only get the patient's id from the string
				String[] str = patientList.getValue().split(" ");
				pId = Integer.parseInt(str[0]);
			} catch (Exception e) {
				patientErrorMessage.setVisible(true);
				flag = false;
			}
			
			if (flag) {
				Patient p = Hospital.getInstance().getRealPatient(pId);
				nurse.checkDisease(p);
				message(AlertType.INFORMATION, "Information Dialog", "You've treated " + p.getFname() 
						+ " " + p.getLname() + " successfully!\n" + "Patient's current status: " + p.getStatus() 
						+ "\nNurse's shifts: " + nurse.getShifts());
			}
	    	patientList.getSelectionModel().clearSelection();
	    	setPatientsComboBox();
		}
	}
	
	void setNursesComboBox() {
		ArrayList<String> nTmp = new ArrayList<String>();
		for (Nurse n : Hospital.getInstance().getNurses().values()) {
			nTmp.add(Integer.toString(n.getId()) + " - " + n.getFname() + " " + n.getLname());
		}
		String[] nId = Arrays.copyOf(nTmp.toArray(), nTmp.toArray().length, String[].class);
		nurseList.setItems(FXCollections.observableArrayList(nId));
		AutoComplete.autoCompleteComboBoxPlus(nurseList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
	
	void setPatientsComboBox() {
		ArrayList<String> pTmp = new ArrayList<String>();
		switch (LoginScreenController.getType()) {
		case "admin":
			/*
			 * if an admin logged in, he has the permissions to treat all the patients as a doctor and as a nurse
			 * so in that case, the lists of doctors and nurses are visible to him, and the list of patients
			 * so he can choose who he wants to treat
			 */
			for (Patient p : Hospital.getInstance().getPatients().values()) {
				pTmp.add(Integer.toString(p.getId()) + " - " + p.getFname() + " " + p.getLname());
			}
			String[] pIdAdmin = Arrays.copyOf(pTmp.toArray(), pTmp.toArray().length, String[].class);
			patientList.setItems(FXCollections.observableArrayList(pIdAdmin));
			AutoComplete.autoCompleteComboBoxPlus(patientList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
			treatPatientDoc.setVisible(true);
			treatDiseaseDoc.setVisible(true);
			treatPatientNurse.setVisible(true);
			treatDiseaseNurse.setVisible(true);
			doctorLabel.setVisible(true);
			nursLabel.setVisible(true);
			doctorList.setVisible(true);
			nurseList.setVisible(true);
			break;
		case "doctor":
			/*
			 * if a doctor logged in, he can only treat the patients in his department as himself and only doctor treatments,
			 * so in that case only the patients list will be visible and only the buttons with the treatments he can give
			 */
			int i = LoginScreenController.getId();
			doctor = Hospital.getInstance().getRealDoctor(i);
			for (Patient p : Hospital.getInstance().getPatients().values()) {
				if (p.getSd().getDeptment().equals(doctor.getSd().getDeptment())) {
					//add to the patients list only the patients that are in the same department as the doctor
					pTmp.add(Integer.toString(p.getId()) + " - " + p.getFname() + " " + p.getLname());//make a string of their names to show
				}
			}
			String[] pIdDoctor = Arrays.copyOf(pTmp.toArray(), pTmp.toArray().length, String[].class);
			patientList.setItems(FXCollections.observableArrayList(pIdDoctor));
			AutoComplete.autoCompleteComboBoxPlus(patientList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
			treatPatientDoc.setVisible(true);
			treatDiseaseDoc.setVisible(true);
			break;
		case "nurse":
			/*
			 * if a nurse logged in, he can only treat the patients in his sub-department as himself and only nurse treatments,
			 * so in that case only the patients list will be visible and only the buttons with the treatments he can give
			 */
			int j = LoginScreenController.getId();
			nurse = Hospital.getInstance().getRealNurse(j);
			for (Patient p : Hospital.getInstance().getPatients().values()) {
				if (p.getSd().equals(nurse.getSd())) {
					pTmp.add(Integer.toString(p.getId()) + " - " + p.getFname() + " " + p.getLname());
				}
			}
			String[] pIdNurse = Arrays.copyOf(pTmp.toArray(), pTmp.toArray().length, String[].class);
			patientList.setItems(FXCollections.observableArrayList(pIdNurse));
			AutoComplete.autoCompleteComboBoxPlus(patientList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
			treatPatientNurse.setVisible(true);
			treatDiseaseNurse.setVisible(true);
			break;
		}
	}
	
}
