package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import Model.Disease;
import Model.Doctor;
import Model.Hospital;
import Model.Patient;
import Utils.ReleaseNote;
import application.Interfaces.Message;
import application.Interfaces.SetDComboBox;
import application.Interfaces.SetDocComboBox;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class AddPatientReportScreenController implements Initializable, SetDocComboBox, SetDComboBox, Message {
	@FXML
    private AnchorPane addPatientReportScreen;

    @FXML
    private ComboBox<String> patientList;

    @FXML
    private ComboBox<String> doctorList;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> diseaseList;

    @FXML
    private DatePicker datePicker;
    
    @FXML
    private Label patientErrorMessage;

    @FXML
    private Label doctorErrorMessage;

    @FXML
    private Label diseaseErrorMessage;

    @FXML
    private Label dateErrorMessage;

    /* initializes the combo boxes that contain the patients, doctor and diseases lists.
     * also initializes the date picker so that the default choice will be today, and the user can only change it to past dates (cannot make a report from the future)
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDoctorComboBox(doctorList);
		setPatientsComboBox();
		
		LocalDate now = LocalDate.now();
    	String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	datePicker.setValue(LocalDate.parse(formattedNow));
		datePicker.setDayCellFactory(picker-> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				LocalDate today = LocalDate.now();
				String formattedToday = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				setDisable(empty || formattedDate.compareTo(formattedToday) >= 0 );
				}
			});
	}
	
	/* this method gets called when the user clicks on the add button in the screen,
	 * it checks that all the fields are full, and if they are it creates a new patient report, using the data the user entered
	 */
	@FXML
    void add (Event event) {
		patientErrorMessage.setVisible(false);
		doctorErrorMessage.setVisible(false);
		dateErrorMessage.setVisible(false);
		boolean flag=true;
		
		//if at least one of the fields is empty, show a  relevant message to the user and change the flag to false
		int pId = -1;
		try {
			String[] str = patientList.getValue().split(" ");
			pId = Integer.parseInt(str[0]);
		} catch (Exception e) {
			patientErrorMessage.setVisible(true);
			flag = false;
		}
		
    	int docId = -1;
    	try {
    		String[] str = doctorList.getValue().split(" ");
    		docId = Integer.parseInt(str[0]);
		} catch (Exception e) {
			doctorErrorMessage.setVisible(true);
			flag = false;
		}
    	
    	Date date = null;
    	try {
			date = java.sql.Date.valueOf(datePicker.getValue());
		} catch (Exception e) {
			dateErrorMessage.setVisible(true);
			flag = false;
		}
    	
    	//if flag is true it means all the required fields are filled, so it creates a new report according the data the user entered
    	if (flag) {
    		Patient p = Hospital.getInstance().getRealPatient(pId);
        	Doctor doc = Hospital.getInstance().getRealDoctor(docId);
        	Disease d = p.getDisease();
        	ReleaseNote rn = p.checkCondition();
        	 
        	if (Hospital.getInstance().addPatientReport(p, doc, date, d, rn) != null) {
        		//if the doctor was added successfully, show a success message
        		message(AlertType.INFORMATION, "Information Dialog", "The report was added successfully!\nRelease note: " + rn);
        	}
        	else {
        		message(AlertType.WARNING, "Warning Dialog", "Couldn't add the report");
        	}
        	
        	//clear the selection, so it will be ready for the next add
        	doctorList.getSelectionModel().clearSelection();
        	patientList.getSelectionModel().clearSelection();
    		setDoctorComboBox(doctorList);
    		setPatientsComboBox();
        	
        	//initializes the date picker so that the default choice will be today
        	LocalDate now = LocalDate.now();
        	String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        	datePicker.setValue(LocalDate.parse(formattedNow));
    	}
    }
	
	void setPatientsComboBox() {
		ArrayList<String> pTmp = new ArrayList<String>();
		for (Patient p : Hospital.getInstance().getPatients().values()) {
			String s = Integer.toString(p.getId()) + " - " + p.getFname() + " " + p.getLname();
			pTmp.add(s);
		}
		String[] pId = Arrays.copyOf(pTmp.toArray(), pTmp.toArray().length, String[].class);
		patientList.setItems(FXCollections.observableArrayList(pId));
		AutoComplete.autoCompleteComboBoxPlus(patientList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}

}
