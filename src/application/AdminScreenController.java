package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.Interfaces.WelcomeScreens;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

public class AdminScreenController extends WelcomeScreens implements Initializable{
	 @FXML
	 private AnchorPane adminScreen;
	 
	 @FXML
	 private AnchorPane childScreen;
	 
	 @FXML
	 private MenuItem addPanient;
	 
	 @FXML
	 private MenuItem addDoctor;
	 
	 @FXML
	 private MenuItem addNurse;
	 
	 @FXML
	 private MenuItem addPanientReport;
	 
	 @FXML
	 private MenuItem addDisease;
	 
	 @FXML
	 private MenuItem addDepartment;
	 
	 @FXML
	 private MenuItem addSubDepartment;
	 
	 @FXML
	 private MenuItem removePatient;
	 
	 @FXML
	 private MenuItem removeDoctor;
	 
	 @FXML
	 private MenuItem removeNurse;
	 
	 @FXML
	 private MenuItem removePatientReport;
	 
	 @FXML
	 private MenuItem removeDisease;
	 
	 @FXML
	 private MenuItem removeDepartment;
	 
	 @FXML
	 private MenuItem removeSubDepartment;
	 
	 @FXML
	 private Button treatments;
	 
	 @FXML
	 private Button systemInfo;
	 
	 @FXML
	 private MenuItem getAllBadConditionPatients;
	 
	 @FXML
	 private MenuItem findHardestWorkingNurse;
	 
	 @FXML
	 private MenuItem getCriticalSteroidsNeuPatients;
	 
	 @FXML
	 private MenuItem getBestStatusSubDepartments;
	 
	 @FXML
	 private MenuItem getDoctorBySpec;
	 
	 @FXML
	 private MenuItem getDiseasesByRange;
	 
	 @FXML
	 private MenuItem getAllDifficultBreathingPatients;
	 
	 @FXML
	 private Button logoutButton;
	 
	 @FXML
	 private Label greetingText;
	 
	 //the initialize method sets a greeting message for the admin based on the time
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 greeting(greetingText, "admin");
	}
	 
	 /*this method gets called when the admin presses on the logout button,
	  * it asks the admin if he's sure he wants to logout of the system, and reacts based on the admin's decision(yes/cancel)
	  */
	 @FXML
	 void logout (Event event) {
		logout(adminScreen);
	 }
	 
	 /* all of the next methods are called when the admin presses one of the options on the menu.
	  * each method uploads the window that handles with the chosen action
	  */
	 @FXML
	 void addPatientButton (Event event) {
		 windowTransition(childScreen, "AddPatientScreen.fxml");
	 }
	 
	 @FXML
	 void addDoctorButton (Event event) {
		 windowTransition(childScreen, "AddDoctorScreen.fxml");
	 }
	 
	 @FXML
	 void addNurseButton (Event event) {
		 windowTransition(childScreen, "AddNurseScreen.fxml");
	 }
	 
	 @FXML
	 void addPatientReportButton (Event event) {
		 windowTransition(childScreen, "AddPatientReportScreen.fxml");
	 }
	 
	 @FXML
	 void addDiseaseButton (Event event) {
		 windowTransition(childScreen, "AddDiseaseScreen.fxml");
	 }
	 
	 @FXML
	 void addDepartmentButton (Event event) {
		 windowTransition(childScreen, "AddDepartmentScreen.fxml");
	 }
	 
	 @FXML
	 void addSubDepartmentButton (Event event) {
		 windowTransition(childScreen, "AddSubDepartmentScreen.fxml");
	 }
	 
	 @FXML
	 void removePatient (Event event) {
		 windowTransition(childScreen, "RemovePatientScreen.fxml");
	 }
	 
	 @FXML
	 void removeDoctor (Event event) {
		 windowTransition(childScreen, "RemoveDoctorScreen.fxml");
	 }
	 
	 @FXML
	 void removeNurse (Event event) {
		 windowTransition(childScreen, "RemoveNurseScreen.fxml");
	 }
	 
	 @FXML
	 void removePatientReport (Event event) {
		 windowTransition(childScreen, "RemovePatientReportScreen.fxml");
	 }
	 
	 @FXML
	 void removeDisease (Event event) {
		 windowTransition(childScreen, "RemoveDiseaseScreen.fxml" );
	 }
	 
	 @FXML
	 void removeDepartment (Event event) {
		 windowTransition(childScreen, "RemoveDepartmentScreen.fxml");
	 }
	 
	 @FXML
	 void removeSubDepartment (Event event) {
		 windowTransition(childScreen, "RemoveSubDepartmentScreen.fxml");
	 }
	 
	 @FXML
	 void treatments (Event event) {
		 windowTransition(childScreen, "TreatmentsScreen.fxml");
	 }
	 
	 @FXML
	 void systemInfo (Event event) {
		 windowTransition(childScreen, "SystemInfoScreen.fxml");
	 }
	 
	 @FXML
	 void getAllBadConditionPatients (Event event) {
		 windowTransition(childScreen, "GetAllBadConditionPatientsScreen.fxml");
	 }
	 
	 @FXML
	 void findHardestWorkingNurse (Event event) {
		 windowTransition(childScreen, "FindHardestWorkingNurseScreen.fxml");
	 }
	 
	 @FXML
	 void getCriticalSteroidsNeuPatients (Event event) {
		 windowTransition(childScreen, "GetCriticalSteroidsNeuPatientsScreen.fxml");
	 }
	 
	 @FXML
	 void getBestStatuseSubDepartments (Event event) {
		 windowTransition(childScreen, "GetBestStatuseSubDepartmentsScreen.fxml");
	 }
	 
	 @FXML
	 void getDoctorBySpecScreen (Event event) {
		 windowTransition(childScreen, "GetDoctorBySpecScreen.fxml");
	 }
	 
	 @FXML
	 void getDiseasesByRange (Event event) {
		 windowTransition(childScreen, "getDiseasesByRangeScreenController.fxml");
	 }
	 
	 @FXML
	 void getAllDifficultBreathingPatients (Event event) {
		 windowTransition(childScreen, "GetAllDifficultBreathingPatientsScreen.fxml");
	 }
	 
	 @FXML
	 void treatDisease (Event event) {
		 windowTransition(childScreen, "TreatDiseaseScreen.fxml");
	 }
	 
	 @FXML
	 void treatPatients (Event event) {
		 windowTransition(childScreen, "TreatPatientsScreen.fxml");
	 }

}
