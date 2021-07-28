package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Model.Disease;
import Model.Hospital;
import Model.Patient;
import Model.SubDepartment;
import Utils.Condition;
import application.Interfaces.SetPatientTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GetCriticalSteroidsNeuPatientsScreenController implements Initializable, SetPatientTableView{
	@FXML
    private TableView<Patient> patientsTV;

    @FXML
    private TableColumn<Patient, Integer> idColumn;

    @FXML
    private TableColumn<Patient, String> fnameColumn;

    @FXML
    private TableColumn<Patient, String> lnameColumn;

    @FXML
    private TableColumn<Patient, Integer> statusColumn;

    @FXML
    private TableColumn<Patient, Condition> conditionColumn;

    @FXML
    private TableColumn<Patient, Disease> diseaseColumn;

    @FXML
    private TableColumn<Patient, SubDepartment> sdColumn;

    /* This method shows the user a table with all the patients that are treated with steroids
     * and have a doctor that specializes in neurology
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TreeSet<Patient> tmp = Hospital.getInstance().getCriticalSteroidsNeuPatients();
		if (tmp.isEmpty()) {
			patientsTV.setPlaceholder(new Label("There are no patients who answer the requirements"));

		}
		else {
			ObservableList<Patient> patients = FXCollections.observableArrayList(tmp);
			setPatientTableView(patients, idColumn, fnameColumn, lnameColumn, statusColumn, conditionColumn,
					diseaseColumn, sdColumn, patientsTV);
		}	
	}
	
}
