package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.Hospital;
import Model.PatientReport;
import application.Interfaces.ConfirmRemove;
import application.Interfaces.Message;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class RemovePatientReportScreenController implements Initializable, Message, ConfirmRemove{
	@FXML
    private AnchorPane removePatientReportScreen;

    @FXML
    private Button removeButton;

    @FXML
    private ComboBox<String> reportList;

    @FXML
    private Label errorMessage;

    @FXML
    private TextArea reportInfo;

    //initializes the list of the reports in the combo box, for every report it shows its id and the name of the patient
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setReportsSubDepartment();
	}
	
	//if the user chose a report, it shows him the details of that report
	@FXML
	void prInfo (Event event) {
		reportInfo.clear();
		boolean flag = true;
		
		int prId = -1;
		try {
			String[] tmp = reportList.getValue().split(" ");
			prId = Integer.parseInt(tmp[0]);
		} catch (Exception e) {
			flag = false;
		}
		
		if (flag) {
			PatientReport pr = Hospital.getInstance().getRealPatientReport(prId);
			reportInfo.setText(pr.toStringLong());
		}
	}
	
	//this method removes the chosen report
	@FXML
	void remove (Event event) {
		errorMessage.setVisible(false);
		boolean flag = true;
		
		int prId = -1;
		try {
			String[] tmp = reportList.getValue().split(" ");
			prId = Integer.parseInt(tmp[0]);
		} catch (Exception e) {
			errorMessage.setVisible(true);
			flag = false;
		}
		
		if (flag) {
			/*opens a pop up message that asks the user if he's sure he wants to remove,
			 * and reacts based on the user's answer
			 */
			Alert alert = confirmRemove("Are you sure you want to remove the report?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().getText().equalsIgnoreCase("Yes")){
				PatientReport pr = Hospital.getInstance().getRealPatientReport(prId);
				if (Hospital.getInstance().removePatientReport(pr)) {
					message(AlertType.INFORMATION, "Information Dialog", "The report " + pr.getId() + " was removed succssefully!");
	        	}
				else {
	        		message(AlertType.WARNING, "Warning Dialog", "Couldn't remove the report");
	        	}
				reportList.getSelectionModel().clearSelection();
				setReportsSubDepartment();
				
			}
			
		}
	}
	
	void setReportsSubDepartment() {
		ArrayList<String> prTmp = new ArrayList<String>();
		for (PatientReport pr : Hospital.getInstance().getReports().values()) {
			prTmp.add(pr.getId() + " - " + pr.getPatient().getFname() + " " + pr.getPatient().getLname());
		}
		String[] prDetails = Arrays.copyOf(prTmp.toArray(), prTmp.toArray().length, String[].class);
		reportList.setItems(FXCollections.observableArrayList(prDetails));
		AutoComplete.autoCompleteComboBoxPlus(reportList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
}
