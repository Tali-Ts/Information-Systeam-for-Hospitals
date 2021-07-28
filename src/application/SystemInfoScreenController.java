package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import Model.Department;
import Model.Disease;
import Model.Doctor;
import Model.Hospital;
import Model.Nurse;
import Model.Patient;
import Model.PatientReport;
import Model.SubDepartment;
import Utils.Condition;
import Utils.ReleaseNote;
import Utils.Specialization;
import Utils.Symptoms;
import Utils.Treatments;
import application.Interfaces.SetPatientTableView;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TabPane;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;

public class SystemInfoScreenController implements Initializable, SetPatientTableView {
	@FXML
    private AnchorPane systemInfoScreen;
	
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Label infoLabel;
	
	/********************patient********************/
    
	@FXML
    private Tab patientsTab;
    
    @FXML
    private AnchorPane patientTVPane;

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
    private ChoiceBox<String> pSearchCB;

    @FXML
    private TextField pSearchField;

    /********************doctor********************/

	@FXML
    private Tab doctorsTab;

    @FXML
    private TableView<Doctor> doctorsTV;

    @FXML
    private TableColumn<Doctor, Integer> docIdColum;

    @FXML
    private TableColumn<Doctor, String> docFnameColum;

    @FXML
    private TableColumn<Doctor, String> docLnameColum;

    @FXML
    private TableColumn<Doctor, Specialization> docSpecColum;

    @FXML
    private TableColumn<Doctor, Integer> docShiftColum;

    @FXML
    private TableColumn<Doctor, Integer> docSbColum;

    @FXML
    private TableColumn<?, ?> docPasswordColumn;
    
    @FXML
    private ChoiceBox<String> docSearchCB;

    @FXML
    private TextField docSearchField;
    
    /********************nurse********************/
    
    @FXML
    private Tab nursesTab;

    @FXML
    private TableView<Nurse> nursesTV;

    @FXML
    private TableColumn<Nurse, Integer> nIdColum;

    @FXML
    private TableColumn<Nurse, String> nFnameColum;

    @FXML
    private TableColumn<Nurse, String> nLnameColum;

    @FXML
    private TableColumn<Nurse, Treatments> nTreatColum;

    @FXML
    private TableColumn<Nurse,Integer> nShiftColum;

    @FXML
    private TableColumn<Nurse, Integer> nSbColum;
    
    @FXML
    private TableColumn<?, ?> nPasswordColumn;

    @FXML
    private ChoiceBox<String> nSearchCB;

    @FXML
    private TextField nSearchField;
    
    /********************patient report********************/
    
    @FXML
    private Tab reportsTab;

    @FXML
    private TableView<PatientReport> reportsTV;

    @FXML
    private TableColumn<PatientReport, Integer> rIdColum;

    @FXML
    private TableColumn<PatientReport, Patient> rPatientColum;

    @FXML
    private TableColumn<PatientReport, Doctor> rDoctorColumn;

    @FXML
    private TableColumn<PatientReport, Date> rDateColumn;

    @FXML
    private TableColumn<PatientReport, Disease> rDiseaseColumn;

    @FXML
    private TableColumn<PatientReport, Integer> rSbColumn;

    @FXML
    private TableColumn<PatientReport, ReleaseNote> rReleaseNoteCollumn;
    
    @FXML
    private ChoiceBox<String> rSearchCB;

    @FXML
    private TextField rSearchField;
    
    /********************disease********************/
	
    @FXML
    private Tab diseasesTab;

    @FXML
    private TableView<Disease> diseasesTV;

    @FXML
    private TableColumn<Disease, Integer> dIdColumn;

    @FXML
    private TableColumn<Disease, String> dNameColumn;

    @FXML
    private TableColumn<Disease, HashSet<Symptoms>> dSymptomsColumn;
    @FXML
    private ChoiceBox<String> dSearchCB;

    @FXML
    private TextField dSearchField;
    
    /********************department********************/
    
    @FXML
    private Tab departmentsTab;

    @FXML
    private TableView<Department> departmentsTV;

    @FXML
    private TableColumn<Department, Integer> depIdColumn;

    @FXML
    private TableColumn<Department, String> depNameColumn;

    @FXML
    private TableColumn<Department, ArrayList<SubDepartment>> depSbColumn;

    @FXML
    private TableColumn<Department, Specialization> depSpecColumn;
    
    @FXML
    private ChoiceBox<String> depSearchCB;

    @FXML
    private TextField depSearchField;
    
    /********************sub-department********************/
    
    @FXML
    private Tab subDepsTab;

    @FXML
    private ComboBox<String> subDepList;

    @FXML
    private BarChart<String, Number> subDepChart;

    @FXML
    private Button showButton;

    @FXML
    private Label sdErrorMessage;
    
    /********************More Data********************/
    
    @FXML
    private Tab MoreDataTab;

    @FXML
    private ComboBox<String> patientList;

    @FXML
    private TextArea patientInfo;

    @FXML
    private Button showPInfoButton;
    
    @FXML
    private Label patientErrorMessage;
    
/**************************************** initialize ****************************************/    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	/******************** patient ********************/
		setPatientTableView(getPatients(), pIdColumn, pFnameColumn, pLnameColumn, pStatusColumn, pConditionColumn,
				pDiseaseColumn, pSdColumn, patientsTV);
		setPatientTVSearch();
		
	/******************** doctor ********************/
		setDoctorTableView();
		setDoctorTVSearch();
		
	/******************** nurse ********************/
		setNurseTableView();
		setNurseTVSearch();
		
	/******************** report ********************/
		setReportTableView();
		setPatientReportTVSearch();
		
	/******************** disease ********************/
		setDiseaseTableView();
		setDiseaseTVSearch();
		
	/******************** department ********************/
		setDepartmentTableView();
		setDepartmentTVSearch();
		
	/******************************sub department******************************/
		//set sub-department comboBox in the sub-department's tab
		ArrayList<String> sdTmp = new ArrayList<String>();
		for (SubDepartment sd : Hospital.getInstance().getSubDepartment().values()) {
			String s = Integer.toString(sd.getId());
			sdTmp.add(s);
		}
		String[] sdId = Arrays.copyOf(sdTmp.toArray(), sdTmp.toArray().length, String[].class);
		subDepList.setItems(FXCollections.observableArrayList(sdId));
		AutoComplete.autoCompleteComboBoxPlus(subDepList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
		
		XYChart.Series<String, Number> set1 = new XYChart.Series<>();
		set1.getData().add(new XYChart.Data<String, Number>("Patients", 0));
		set1.getData().add(new XYChart.Data<String, Number>("Doctors", 0));
		set1.getData().add(new XYChart.Data<String, Number>("Nurses", 0));
		set1.getData().add(new XYChart.Data<String, Number>("Reports", 0));
		subDepChart.getData().addAll(set1);
		
	/******************** More Data ********************/
		ArrayList<String> pTmp = new ArrayList<String>();
		for (Patient p : Hospital.getInstance().getPatients().values()) {
			String s = Integer.toString(p.getId()) + " - " + p.getFname() + " " + p.getLname();
			pTmp.add(s);
		}
		String[] pId = Arrays.copyOf(pTmp.toArray(), pTmp.toArray().length, String[].class);
		patientList.setItems(FXCollections.observableArrayList(pId));
		AutoComplete.autoCompleteComboBoxPlus(patientList, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
	
	
	/**************************************** patient ****************************************/	
	public ObservableList<Patient> getPatients() {
		ObservableList<Patient> patients = FXCollections.observableArrayList();
		for (Patient p : Hospital.getInstance().getPatients().values()) {
			patients.add(p);
		}
		return patients;
	}
	
	public void setPatientTVSearch () {
		FilteredList<Patient> filteredPatients = new FilteredList(getPatients(), p -> true);//Pass the data to a filtered list
		patientsTV.setItems(filteredPatients); //Set the table's items using the filtered list
		pSearchCB.getItems().addAll("ID" ,"First Name", "Last Name", "Status", "Condition", "Disease", "Sub-Department");
        pSearchCB.setValue("ID");
        pSearchField.setOnKeyReleased(keyEvent -> {
            switch (pSearchCB.getValue()) { //Switch on choiceBox value
            	case "ID": //filter table by ID
            		filteredPatients.setPredicate(p -> Integer.toString(p.getId()).contains(pSearchField.getText().trim()));
            		break;
            	case "First Name": //filter table by first name
                	filteredPatients.setPredicate(p -> p.getFname().toLowerCase().contains(pSearchField.getText().toLowerCase().trim()));
                    break;
                case "Last Name": //filter table by last name
                	filteredPatients.setPredicate(p -> p.getLname().toLowerCase().contains(pSearchField.getText().toLowerCase().trim()));
                    break;
                case "Status": //filter table by status
                	filteredPatients.setPredicate(p -> Integer.toString(p.getStatus()).contains(pSearchField.getText().trim()));
                    break;
                case "Condition": //filter table by condition
                	filteredPatients.setPredicate(p -> p.getCondition().toString().contains(pSearchField.getText().toUpperCase().trim()));
                	break;
                case "Disease": //filter table by disease name
                	filteredPatients.setPredicate(p -> p.getDisease().getName().toLowerCase().contains(pSearchField.getText().toLowerCase().trim()));
                	break;
                case "Sub-Department": //filter table by id's sub-department
                	filteredPatients.setPredicate(p -> Integer.toString(p.getSd().getId()).contains(pSearchField.getText().trim()));
                	break;
            }
        });
        pSearchCB.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                pSearchField.setText("");
                filteredPatients.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
	}
	
	
	/**************************************** doctor ****************************************/
	public void setDoctorTableView () {
		docPasswordColumn.setVisible(false);
		//set up the columns in the table
		docIdColum.setCellValueFactory(new PropertyValueFactory<>("id"));
		docFnameColum.setCellValueFactory(new PropertyValueFactory<>("fname"));
		docLnameColum.setCellValueFactory(new PropertyValueFactory<>("lname"));
		docSpecColum.setCellValueFactory(new PropertyValueFactory<>("spec"));
		docShiftColum.setCellValueFactory(new PropertyValueFactory<>("shiftCounter"));
		docSbColum.setCellValueFactory(new PropertyValueFactory<>("sd"));
		
		if (LoginScreenController.getType().equals("admin")) {
			docPasswordColumn.setVisible(true);
			docPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		}
		
		doctorsTV.setItems(getDoctors());
	}
	
	public ObservableList<Doctor> getDoctors() {
		ObservableList<Doctor> doctors = FXCollections.observableArrayList();
		for (Doctor doc : Hospital.getInstance().getDoctors().values()) {
			doctors.add(doc);
		}
		return doctors;
	}
	
	public void setDoctorTVSearch () {
		FilteredList<Doctor> filteredDoctors = new FilteredList(getDoctors(), doc -> true);//Pass the data to a filtered list
		doctorsTV.setItems(filteredDoctors); //Set the table's items using the filtered list
		docSearchCB.getItems().addAll("ID" ,"First Name", "Last Name", "Specialization", "Shifts", "Sub-Department");
        docSearchCB.setValue("ID");
        docSearchField.setOnKeyReleased(keyEvent -> {
            switch (docSearchCB.getValue()) { //Switch on choiceBox value
            	case "ID": //filter table by ID
            		filteredDoctors.setPredicate(doc -> Integer.toString(doc.getId()).contains(docSearchField.getText().trim()));
            		break;
            	case "First Name": //filter table by first name
            		filteredDoctors.setPredicate(doc -> doc.getFname().toLowerCase().contains(docSearchField.getText().toLowerCase().trim()));
                    break;
                case "Last Name": //filter table by last name
                	filteredDoctors.setPredicate(doc -> doc.getLname().toLowerCase().contains(docSearchField.getText().toLowerCase().trim()));
                    break;
                case "Specialization": //filter table by specialization
                	filteredDoctors.setPredicate(doc -> doc.getSpec().toString().contains(docSearchField.getText().toUpperCase().trim()));
                    break;
                case "Shifts": //filter table by number of shifts
                	filteredDoctors.setPredicate(doc -> Integer.toString(doc.getShiftCounter()).contains(docSearchField.getText().trim()));
                	break;
                case "Sub-Department": //filter table by id's sub-department
                	filteredDoctors.setPredicate(doc -> Integer.toString(doc.getSd().getId()).contains(docSearchField.getText().trim()));
                	break;
            }
        });
        docSearchCB.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                docSearchField.setText("");
                filteredDoctors.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
	}
	
	
	/**************************************** nurse ****************************************/
	public void setNurseTableView() {
		//set up the columns in the table
		nIdColum.setCellValueFactory(new PropertyValueFactory<>("id"));
		nFnameColum.setCellValueFactory(new PropertyValueFactory<>("fname"));
		nLnameColum.setCellValueFactory(new PropertyValueFactory<>("lname"));
		nTreatColum.setCellValueFactory(new PropertyValueFactory<>("treat"));
		nShiftColum.setCellValueFactory(new PropertyValueFactory<>("shifts"));
		nSbColum.setCellValueFactory(new PropertyValueFactory<>("sd"));
		
		if (LoginScreenController.getType().equals("admin")) {
			nPasswordColumn.setVisible(true);
			nPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		}
		
		nursesTV.setItems(getNurses());
	}
	
	public ObservableList<Nurse> getNurses() {
		ObservableList<Nurse> nurses = FXCollections.observableArrayList();
		for (Nurse nurse : Hospital.getInstance().getNurses().values()) {
			nurses.add(nurse);
		}
		return nurses;
	}
	
	public void setNurseTVSearch () {
		FilteredList<Nurse> filteredNurses = new FilteredList(getNurses(), n -> true);//Pass the data to a filtered list
		nursesTV.setItems(filteredNurses); //Set the table's items using the filtered list
		nSearchCB.getItems().addAll("ID" ,"First Name", "Last Name", "Treatments", "Shifts", "Sub-Department");
		nSearchCB.setValue("ID");
        nSearchField.setOnKeyReleased(keyEvent -> {
            switch (nSearchCB.getValue()) { //Switch on choiceBox value
            	case "ID": //filter table by ID
            		filteredNurses.setPredicate(n -> Integer.toString(n.getId()).contains(nSearchField.getText().trim()));
            		break;
            	case "First Name": //filter table by first name
            		filteredNurses.setPredicate(n -> n.getFname().toLowerCase().contains(nSearchField.getText().toLowerCase().trim()));
                    break;
                case "Last Name": //filter table by last name
                	filteredNurses.setPredicate(n -> n.getLname().toLowerCase().contains(nSearchField.getText().toLowerCase().trim()));
                    break;
                case "Treatments": //filter table by specialization
                	filteredNurses.setPredicate(n -> n.getTreat().toString().contains(nSearchField.getText().toUpperCase().trim()));
                    break;
                case "Shifts": //filter table by number of shifts
                	filteredNurses.setPredicate(n -> Integer.toString(n.getShifts()).contains(nSearchField.getText().trim()));
                	break;
                case "Sub-Department": //filter table by id's sub-department
                	filteredNurses.setPredicate(n -> Integer.toString(n.getSd().getId()).contains(nSearchField.getText().trim()));
                	break;
            }
        });
        nSearchCB.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
            	nSearchField.setText("");
                filteredNurses.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
	}
	
	
	/**************************************** reports ****************************************/
	public void setReportTableView() {
		//set up the columns in the table
		rIdColum.setCellValueFactory(new PropertyValueFactory<>("id"));
		rPatientColum.setCellValueFactory(new PropertyValueFactory<>("patient"));
		rDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));
		rDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		rDiseaseColumn.setCellValueFactory(new PropertyValueFactory<>("disease"));
		rSbColumn.setCellValueFactory(new PropertyValueFactory<>("Sdept"));
		rReleaseNoteCollumn.setCellValueFactory(new PropertyValueFactory<>("releaseNote"));
		
		reportsTV.setItems(getReports());
	}
	
	public ObservableList<PatientReport> getReports() {
		ObservableList<PatientReport> reports = FXCollections.observableArrayList();
		for (PatientReport pr : Hospital.getInstance().getReports().values()) {
			reports.add(pr);
		}
		return reports;
	}
	
	public void setPatientReportTVSearch () {
		FilteredList<PatientReport> filteredReports = new FilteredList(getReports(), r -> true);//Pass the data to a filtered list
		reportsTV.setItems(filteredReports); //Set the table's items using the filtered list
		rSearchCB.getItems().addAll("ID" ,"Patient", "Doctor", "Date", "Disease", "Release Note", "Sub-Department");
		rSearchCB.setValue("ID");
        rSearchField.setOnKeyReleased(keyEvent -> {
            switch (rSearchCB.getValue()) { //Switch on choiceBox value
            	case "ID": //filter table by ID
            		filteredReports.setPredicate(r -> Integer.toString(r.getId()).contains(rSearchField.getText().trim()));
            		break;
            	case "Patient": //filter table by first name
            		filteredReports.setPredicate(r -> r.getPatient().toString().toLowerCase().contains(rSearchField.getText().toLowerCase().trim()));
                    break;
                case "Doctor": //filter table by last name
                	filteredReports.setPredicate(r -> r.getDoctor().toString().toLowerCase().contains(rSearchField.getText().toLowerCase().trim()));
                    break;
                case "Date": //filter table by status
                	filteredReports.setPredicate(r -> r.getDate().toString().contains(rSearchField.getText().trim()));
                    break;
                case "Disease": //filter table by disease name
                	filteredReports.setPredicate(r -> r.getDisease().getName().toLowerCase().contains(rSearchField.getText().toLowerCase().trim()));
                	break;
                case "Release Note": //filter table by condition
                	filteredReports.setPredicate(r -> r.getReleaseNote().toString().contains(rSearchField.getText().toUpperCase().trim()));
                	break;
                case "Sub-Department": //filter table by id's sub-department
                	filteredReports.setPredicate(r -> Integer.toString(r.getSdept().getId()).contains(rSearchField.getText().trim()));
                	break;
            }
        });
        rSearchCB.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
            	rSearchField.setText("");
                filteredReports.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
	}
	
	
	/**************************************** disease ****************************************/
	public void setDiseaseTableView() {
		//set up the columns in the table
		dIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		dNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		dSymptomsColumn.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
		diseasesTV.setItems(getDiseases());
	}
	
	public ObservableList<Disease> getDiseases() {
		ObservableList<Disease> diseases = FXCollections.observableArrayList();
		for (Disease disease : Hospital.getInstance().getDiseases().values()) {
			diseases.add(disease);
		}
		return diseases;
	}
	
	public void setDiseaseTVSearch () {
		FilteredList<Disease> filteredDisease = new FilteredList(getDiseases(), d -> true);//Pass the data to a filtered list
		diseasesTV.setItems(filteredDisease); //Set the table's items using the filtered list
		dSearchCB.getItems().addAll("ID" ,"Name", "Symptoms");
		dSearchCB.setValue("ID");
        dSearchField.setOnKeyReleased(keyEvent -> {
            switch (dSearchCB.getValue()) { //Switch on choiceBox value
            	case "ID": //filter table by ID
            		filteredDisease.setPredicate(d -> Integer.toString(d.getId()).contains(dSearchField.getText().trim()));
            		break;
            	case "Name": //filter table by first name
            		filteredDisease.setPredicate(d -> d.getName().toLowerCase().contains(dSearchField.getText().toLowerCase().trim()));
                    break;
                case "Symptoms": //filter table by last name
                	filteredDisease.setPredicate(d -> {
                		for (Symptoms s : d.getSymptoms()) {
                			if (s.toString().toLowerCase().contains(dSearchField.getText().toLowerCase().trim())) {
                				return true;
                			}
                		}
                		return false;
                	});
                    break;
            }
        });
        dSearchCB.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
            	dSearchField.setText("");
                filteredDisease.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
	}
	
	
	/**************************************** department ****************************************/
	public void setDepartmentTableView() {
		//set up the columns in the table
		depIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		depNameColumn.setCellValueFactory(new PropertyValueFactory<>("deptName"));
		depSbColumn.setCellValueFactory(new PropertyValueFactory<>("sdepts"));
		depSpecColumn.setCellValueFactory(new PropertyValueFactory<>("Spec"));
		departmentsTV.setItems(getDepartments());
	}
	
	public ObservableList<Department> getDepartments() {
		ObservableList<Department> departments = FXCollections.observableArrayList();
		for (Department dep : Hospital.getInstance().getDepartments().values()) {
			departments.add(dep);
		}
		return departments;
	}
	
	public void setDepartmentTVSearch () {
		FilteredList<Department> filteredDepartments = new FilteredList(getDepartments(), dep -> true);//Pass the data to a filtered list
		departmentsTV.setItems(filteredDepartments); //Set the table's items using the filtered list
		depSearchCB.getItems().addAll("ID" ,"Name", "Specialization", "Sub-Department");
		depSearchCB.setValue("ID");
        depSearchField.setOnKeyReleased(keyEvent -> {
            switch (depSearchCB.getValue()) { //Switch on choiceBox value
            	case "ID": //filter table by ID
            		filteredDepartments.setPredicate(dep -> Integer.toString(dep.getId()).contains(depSearchField.getText().trim()));
            		break;
            	case "Name": //filter table by name
            		filteredDepartments.setPredicate(dep -> dep.getDeptName().toLowerCase().contains(depSearchField.getText().toLowerCase().trim()));
                    break;
                case "Specialization": //filter table by last name
                	filteredDepartments.setPredicate(dep -> dep.getSpec().toString().toLowerCase().contains(depSearchField.getText().toLowerCase().trim()));
                    break;
                case "Sub-Department": //filter table by id's sub-department
                	filteredDepartments.setPredicate(dep -> {
                		for (SubDepartment sd : dep.getSdepts()) {
                			if (Integer.toString(sd.getId()).contains(depSearchField.getText().trim())) {
                				return true;
                			}
                		}
                		return false;
                	});
                	break;
            }
        });
        depSearchCB.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
            	depSearchField.setText("");
                filteredDepartments.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
	}
	
	/**************************************** sub department ****************************************/
	@FXML
	void setSubDepBarChart(Event event) {
		subDepChart.getData().clear();
		sdErrorMessage.setVisible(false);
		boolean flag = true;
		
		int sdId = -1;
    	try {
    		sdId = Integer.parseInt(subDepList.getValue());
		} catch (NumberFormatException e) {
			sdErrorMessage.setVisible(true);
			flag=false;
		}
    	
    	if (flag) {
    		SubDepartment sd = Hospital.getInstance().getRealSubDepartment(sdId);
    		//setting sub-department bar chart
			XYChart.Series<String, Number> set1 = new XYChart.Series<>();
			set1.getData().add(new XYChart.Data<String, Number>("Patients", sd.getPatients().size()));
			set1.getData().add(new XYChart.Data<String, Number>("Doctors", sd.getDoctors().size()));
			set1.getData().add(new XYChart.Data<String, Number>("Nurses", sd.getNurses().size()));
			set1.getData().add(new XYChart.Data<String, Number>("Reports", sd.getReports().size()));
			
			infoLabel.setVisible(true);
			infoLabel.setText("Belongs to department " + sd.getDeptment().getDeptName()
					+ " ID: " + sd.getDeptment().getId());
			
			subDepChart.getData().addAll(set1);
    	}
	}
	
	/**************************************** More Data ****************************************/	
	@FXML 
	void showMorePData (Event event) {
		patientErrorMessage.setVisible(false);
		boolean flag=true;
		
		//if the field is empty, show a relevant message to the user and change the flag to false
		int pId = -1;
		try {
			String[] str = patientList.getValue().split(" ");
			pId = Integer.parseInt(str[0]);
		} catch (Exception e) {
			patientErrorMessage.setVisible(true);
			flag = false;
		}
		
		//if flag is true it means all the required fields are filled, so it creates a new report according the data the user entered
    	if (flag) {
    		Patient p = Hospital.getInstance().getRealPatient(pId);
    		String tmpDoc = "";
    		for (Doctor doc : Hospital.getInstance().getDoctorsByPatient().get(p)) {
    			tmpDoc += doc + "\n";
    		}
    		String strDoc = "The doctors that treated " + p.getFname() + " " + p.getLname() + " are:\n" + tmpDoc;
    		
    		String tmpN = "";
    		for (Nurse n : Hospital.getInstance().getNursesByPatient().get(p)) {
    			tmpN += n + "\n";
    		}
    		String strN = "The nurses that treated " + p.getFname() + " " + p.getLname() + " are:\n" + tmpN;
    		patientInfo.setText(strDoc + "\n\n" + strN);
    	}
	}
	

}






