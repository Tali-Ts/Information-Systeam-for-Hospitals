package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;
import Model.Department;
import Model.Disease;
import Model.Hospital;
import Model.Patient;
import Model.SubDepartment;
import Utils.Condition;
import application.Interfaces.Message;
import application.Interfaces.SetDepComboBox;
import application.Interfaces.SetPatientTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class TreatPatientsScreenController implements Initializable, Message, SetDepComboBox, SetPatientTableView {
	
    @FXML
    private ComboBox<String> depList;

    @FXML
    private TableView<Patient> patientsTV;

    @FXML
    private TableColumn<Patient, Integer> pIdColumn;

    @FXML
    private TableColumn<Patient, String> pFnameColumn;

    @FXML
    private TableColumn<Patient, String> pLnameColumn;

    @FXML
    private TableColumn<Patient, Integer> pStatusColumn;

    @FXML
    private TableColumn<Patient, Condition> pConditionColumn;

    @FXML
    private TableColumn<Patient, Disease> pDiseaseColumn;

    @FXML
    private TableColumn<Patient, SubDepartment> pSdColumn;
    
    @FXML
    private Label tableSubject;

    /*This method initializes the combo box with a list of all the departments in the hospital*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDepartmentComboBox(depList);
	}
	
	/*This method uses the query to get all the patients that their statuses have changed due to the treatment*/
	@FXML
	void activateQuery () {
		
		patientsTV.getItems().clear();
		boolean flag = true;
		
		int depId = -1;
		try {
			String[] str = depList.getValue().split(" ");
			depId = Integer.parseInt(str[0]); //take only the id number
			
		} catch (NullPointerException e) {
			depList.setVisible(true);
			flag = false;
		}
    	
		Department d = Hospital.getInstance().getRealDepartment(depId); //use the id to get the department
		TreeSet<Patient> tmp = Hospital.getInstance().treatPatients(d); //send the department to the query
		if (flag) {
			//if no one's status has changed - show a message to the user
			if (tmp.isEmpty()) {
				patientsTV.setVisible(true);
				patientsTV.setPlaceholder(new Label("There are no patients to show"));

			}
			//Show the user a message with the amount of patients that are in the list
			else {
				message(AlertType.INFORMATION, "Information Dialog", "The treatment has changed the condition of " +  tmp.size() + " patients" );
				tableSubject.setVisible(true);
				tableSubject.setText("Patients whose condition has changed: ");
				patientsTV.setVisible(true);
				ObservableList<Patient> patients = FXCollections.observableArrayList(tmp);
				setPatientTableView(patients, pIdColumn, pFnameColumn, pLnameColumn, pStatusColumn, pConditionColumn, 
						pDiseaseColumn, pSdColumn, patientsTV);
			}
		}
		setDepartmentComboBox(depList);
	}

}
