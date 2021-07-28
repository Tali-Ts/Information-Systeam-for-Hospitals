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
import application.Interfaces.FindDType;
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

public class TreatDiseaseScreenController implements Initializable, SetDepComboBox, FindDType, Message, SetPatientTableView{
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

    /*This method initializes the combo box with a list of departments*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDepartmentComboBox(depList);
	}
	
	/*
	 * This method uses the treatDiseases query from hospital that returns all the patients with the most common diseases type, 
	 * from the selected department
	 */
	@FXML
	void activateQuery () {
		String diseaseType = "";
		patientsTV.getItems().clear();
		boolean flag = true;
		
		int depId = -1;
		try {
			//split to only get the department's id
			String[] str = depList.getValue().split(" ");
			depId = Integer.parseInt(str[0]);
		} catch (NullPointerException e) {
			depList.setVisible(true);
			flag = false;
		}
    	
		if (flag) {
			Department d = Hospital.getInstance().getRealDepartment(depId);
			TreeSet<Patient> tmp = Hospital.getInstance().treatDiseases(d);
			if (tmp.isEmpty()) {
				patientsTV.setPlaceholder(new Label("There are no patients to show"));

			}
			else {
				//Pop-up message that informs the user the most common type
				diseaseType = findDiseaseType(tmp.first());
				message(AlertType.INFORMATION, "Information Dialog", "The most common disease type is " +  diseaseType);
				tableSubject.setVisible(true);
				tableSubject.setText("Patients with " + diseaseType + " diseases:");
				patientsTV.setVisible(true);
				ObservableList<Patient> patients = FXCollections.observableArrayList(tmp);
				setPatientTableView(patients, pIdColumn, pFnameColumn, pLnameColumn, pStatusColumn, pConditionColumn, 
						pDiseaseColumn, pSdColumn, patientsTV);
			}
		}
		setDepartmentComboBox(depList);
	}
	
}
