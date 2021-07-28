package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;
import Model.Doctor;
import Model.Hospital;
import Utils.Specialization;
import application.Interfaces.SetSpecComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GetDoctorBySpecScreenController implements Initializable, SetSpecComboBox{
	@FXML
    private ComboBox<String> specList;

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

    /*This method initializes the combo box with a list of all the specializations*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setSpecializationComboBox(specList);
	}
	
	/*This method uses the hospital query that returns a treeSet of all the doctors that have the chosen
	 * specialization and have issued a report
	 */
	@FXML
	void activateQuery (Event event) {
		doctorsTV.getItems().clear();
		boolean flag = true;
		
		Specialization spec = null;
    	try {
    		spec = Specialization.valueOf(specList.getValue());
		} catch (Exception e) {
			flag=false;
		}
    	
    	if (flag) { //if flag is true it means the user chose a specialization
    		TreeSet<Doctor> tmp = Hospital.getInstance().getDoctorBySpec(spec);
    		doctorsTV.setVisible(true);
    		if (tmp.isEmpty()) {
    			doctorsTV.setPlaceholder(new Label("There are no doctors who have this specialization and provided reports"));
    		}
    		else {
    			ObservableList<Doctor> doctors = FXCollections.observableArrayList(tmp);
				setDoctorTableView(doctors);
    		}
    	}
    	setSpecializationComboBox(specList);
	}
	
	public void setDoctorTableView (ObservableList<Doctor> list) {
		//set up the columns in the table
		docIdColum.setCellValueFactory(new PropertyValueFactory<>("id"));
		docFnameColum.setCellValueFactory(new PropertyValueFactory<>("fname"));
		docLnameColum.setCellValueFactory(new PropertyValueFactory<>("lname"));
		docSpecColum.setCellValueFactory(new PropertyValueFactory<>("spec"));
		docShiftColum.setCellValueFactory(new PropertyValueFactory<>("shiftCounter"));
		docSbColum.setCellValueFactory(new PropertyValueFactory<>("sd"));
		
		doctorsTV.setItems(list);
	}

}
