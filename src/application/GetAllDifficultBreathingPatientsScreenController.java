package application;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import Model.Department;
import Model.Disease;
import Model.Hospital;
import Model.Patient;
import Model.SubDepartment;
import Utils.Condition;
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

public class GetAllDifficultBreathingPatientsScreenController implements Initializable, SetDepComboBox, SetPatientTableView{
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

  //this methods initializes the combo box with the list of all the departments, for the user to choose
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDepartmentComboBox(depList);
	}
	
	/* this method uses the getAllDifficultBreathingPatients from hospital to return all the
	 * required patients from the chosen department
	 */
	@FXML
	void activateQuery() {
		patientsTV.getItems().clear();
		boolean flag = true;
		
		int depId = -1;
		try {
			//split the text because we only need the id of the department to know which one it is, and use it later when calling the query
			String[] str = depList.getValue().split(" ");
			depId = Integer.parseInt(str[0]);
		} catch (NullPointerException e) { //if the user didn't choose a department it shows him a message and the flag gets a false value
			depList.setVisible(true);
			flag = false;
		}
    	
		//if the flag is true, it means the required field is full and it calls the query
		if (flag) {
			Department d = Hospital.getInstance().getRealDepartment(depId);
			LinkedList<Patient> tmp = Hospital.getInstance().getAllDifficultBreathingPatients(d);
			patientsTV.setVisible(true);
			//if the list the query returns is empty, let the user know
			if (tmp.isEmpty()) {
				patientsTV.setPlaceholder(new Label("There are no difficult breathing patients in this department"));

			}
			else {
				ObservableList<Patient> patients = FXCollections.observableArrayList(tmp);
				setPatientTableView(patients, pIdColumn, pFnameColumn, pLnameColumn, pStatusColumn, pConditionColumn, 
						pDiseaseColumn, pSdColumn, patientsTV);
			}
		}
		setDepartmentComboBox(depList);
	}

}
