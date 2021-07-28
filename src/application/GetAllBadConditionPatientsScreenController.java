package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import Model.Disease;
import Model.Doctor;
import Model.Hospital;
import Model.Patient;
import Model.SubDepartment;
import Utils.Condition;
import application.Interfaces.SetDocComboBox;
import application.Interfaces.SetPatientTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GetAllBadConditionPatientsScreenController implements Initializable, SetDocComboBox, SetPatientTableView{
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

    @FXML
    private ComboBox<String> docList;

  //this methods initializes the combo box with the list of all the doctors, for the user to choose
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDoctorComboBox(docList);
	}
	
	/*this method uses the getAllBadConditionPatients to get a list of all the
	 * patients in the hospital that are treated by the doctor the user chose and have a critical or serious condition,
	 * and puts it on a table for the user to see
	 */
	@FXML
	void activateQuery () {
		patientsTV.getItems().clear();
		boolean flag = true;
		
		int docId = -1;
    	try {
    		String[] str = docList.getValue().split(" ");
    		docId = Integer.parseInt(str[0]);
		} catch (Exception e) {
			flag = false;
		}
		
		if (flag) {
			Doctor doc = Hospital.getInstance().getRealDoctor(docId);
			ArrayList<Patient> tmp = Hospital.getInstance().getAllBadConditionPatients(doc);
			patientsTV.setVisible(true);
			//if there are no patients on the list, show the user a message explaining it
			if (tmp.isEmpty()) {
				patientsTV.setPlaceholder(new Label(doc.getFname() + " " + doc.getLname() + " has no bad condition patients"));

			}
			else {
				ObservableList<Patient> patients = FXCollections.observableArrayList(tmp);
				setPatientTableView(patients, idColumn, fnameColumn, lnameColumn, statusColumn, conditionColumn, 
						diseaseColumn, sdColumn, patientsTV);
			}
		}
		setDoctorComboBox(docList);
	}
    
}
