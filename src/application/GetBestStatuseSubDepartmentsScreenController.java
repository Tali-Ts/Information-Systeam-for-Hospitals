package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeSet;
import Model.Doctor;
import Model.Hospital;
import Model.Nurse;
import Model.Patient;
import Model.PatientReport;
import Model.SubDepartment;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class GetBestStatuseSubDepartmentsScreenController implements Initializable{
	@FXML
    private ListView<String> listView;

    @FXML
    private TextArea infoTextArea;
    
    private SubDepartment sdA;
    private SubDepartment sdB;

    //This method shows the user a list of the two sub departments that have the most good condition patients
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TreeSet<SubDepartment> tmp = Hospital.getInstance().getBestStatusSubDepartments();
		ArrayList<String> toListView = new ArrayList<String>();
		for (SubDepartment sd : tmp) {
			toListView.add(sd.toStringLong());
		}
		listView.setItems(FXCollections.observableArrayList(toListView));
		sdA = tmp.first();
		sdB = tmp.last();
	}
	
	//This method lets the user see the details about the sub-department he chose, when he clicks on it
	@FXML
	void moreAboutInfo (Event event) {
		infoTextArea.clear();
		if (listView.getSelectionModel().getSelectedIndex() == 0) {
			infoTextArea.setText(printSdData(sdA));
		}
		else if (listView.getSelectionModel().getSelectedIndex() == 1) {
			infoTextArea.setText(printSdData(sdB));
		}
	}
	
	//This method makes the string that contains the information about the sub-departments, that will be shown once the user clicks on one
	public String printSdData (SubDepartment sd) {
		String pStr = "Patients:\n";
		for (Patient p : sd.getPatients()) {
			pStr += p.toStringLong() + "\n";
		}
		
		String docStr = "Doctors:\n";
		for (Doctor doc : sd.getDoctors()) {
			docStr += doc.toStringLong() + "\n";
		}
		
		String nStr = "Nurses:\n";
		for (Nurse n : sd.getNurses()) {
			nStr += n.toStringLong() + "\n";
		}
		
		String rStr = "Reports:\n";
		for (PatientReport pr : sd.getReports()) {
			rStr += pr.toStringLong() + "\n";
			rStr += "\n";
		}
		
		String str = pStr + "\n" + docStr + "\n" + nStr + "\n" + rStr;
		return str;
	}
	

}
