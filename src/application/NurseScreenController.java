package application;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Hospital;
import Model.Nurse;
import application.Interfaces.WelcomeScreens;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class NurseScreenController extends WelcomeScreens implements Initializable{
	 @FXML
	 private AnchorPane nurseScreen;
	 
	 @FXML
	 private MenuItem addPanient;
	 
	 @FXML
	 private MenuItem addNurse;
	 
	 @FXML
	 private MenuItem removeNurse;
	 
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
	 private MenuItem treatDiseases;
	 
	 @FXML
	 private MenuItem treatPatients;
	 
	 @FXML
	 private Button logoutButton;
	 
	 @FXML
	 private Label greetingText;
	 
	 @FXML
	 private AnchorPane childScreen;
	 
	 private Nurse nurse;
	 
	 //the initialize method sets a greeting message based on the name of nurse that logged in, and the time
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int i = LoginScreenController.getId();
		nurse = Hospital.getInstance().getRealNurse(i);
		String fullName = nurse.getFname() + " " + nurse.getLname();
		greeting(greetingText, fullName);
	}
	
	/* this method gets called when the nurse presses on the logout button,
	 * it asks the doctor if he's sure he wants to logout of the system, and reacts based on the doctor's decision(yes/cancel)
	 */
	@FXML
	 void logout (Event event) {
		logout(nurseScreen);
	 }
	
	/* all of the next methods are called when the nurse presses one of the options on the menu.
	 * each method uploads the window that handles with the chosen action
	 */
	@FXML
	 void addPatientButton (Event event) {
		windowTransition(childScreen, "AddPatientScreen.fxml");
	 }
	
	@FXML
	 void addNurseButton (Event event) {
		windowTransition(childScreen,"AddNurseScreen.fxml");
	 }
	
	@FXML
	 void removeNurse (Event event) {
		windowTransition(childScreen, "RemoveNurseScreen.fxml");

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


