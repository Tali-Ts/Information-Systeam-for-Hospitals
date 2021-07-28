package application.Interfaces;

import Model.Disease;
import Model.Patient;
import Model.SubDepartment;
import Utils.Condition;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public interface SetPatientTableView {
	default void setPatientTableView (ObservableList<Patient> list, TableColumn<Patient, Integer> idColumn,
			TableColumn<Patient, String> fnameColumn, TableColumn<Patient, String> lnameColumn, TableColumn<Patient, Integer> statusColumn,
			TableColumn<Patient, Condition> conditionColumn, TableColumn<Patient, Disease> diseaseColumn, TableColumn<Patient, SubDepartment> sdColumn,
			TableView<Patient> patientsTV) {
		//set up the columns in the table
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
		lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
		diseaseColumn.setCellValueFactory(new PropertyValueFactory<>("disease"));
		sdColumn.setCellValueFactory(new PropertyValueFactory<>("sd"));
		
		patientsTV.setItems(list);
	}

}
