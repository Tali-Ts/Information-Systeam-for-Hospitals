package application;

import java.util.HashSet;
import java.util.TreeSet;
import Model.Disease;
import Model.Hospital;
import Utils.Symptoms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GetDiseasesByRangeScreenController {

    @FXML
    private Label from;

    @FXML
    private Label to;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private TableView<Disease> diseasesTV;

    @FXML
    private TableColumn<Disease, Integer> dIdColumn;

    @FXML
    private TableColumn<Disease, String> dNameColumn;

    @FXML
    private TableColumn<Disease, HashSet<Symptoms>> dSymptomsColumn;
    
    @FXML
    private Label nameErrorMessage;

    @FXML
    private Button searchButton;
    
    @FXML
    private Label alphabeticErrorMessage;
	
    /* This method validates every character the user types to the fields, 
     * it makes sure he only puts one character, that it is a letter and the order is alphabetic
     */
	@FXML
	void charValidation (KeyEvent e) {
    	nameErrorMessage.setVisible(false);
    	alphabeticErrorMessage.setVisible(false);
		String str = e.getCharacter();
        int len = str.length();
        for(int i = 0; i < len; i++) {
            Character c = str.charAt(i);
            //if the user tries to put invalid input, it doesn't type it
            if (!validChar && (!Character.isLetter(c) || (e.getSource().equals(fromTextField)&&fromTextField.getLength()>0)
            		|| (e.getSource().equals(toTextField)&&toTextField.getLength()>0)) ) {
            	alphabeticErrorMessage.setVisible(false);
            	nameErrorMessage.setVisible(true);
            	e.consume();
            }
            if (!validChar && ((e.getSource().equals(toTextField)&&(str.compareTo(fromTextField.getText())<0))
            		|| (e.getSource().equals(fromTextField)&&((toTextField.getText()).compareTo(str)<0
            				&&!toTextField.getText().isEmpty()))) ) {
            	nameErrorMessage.setVisible(false);
            	alphabeticErrorMessage.setVisible(true);
            	e.consume();
            }
        } 	
	}
	
	// we use this variable to know if the user pressed backspace (if validChar is true), for the nameValidation method
	private boolean validChar;
	
	//this method returns true if the user pressed backspace, false else
	@FXML
	void backSpaceCheck (KeyEvent event) {
		 if (event.getEventType() == KeyEvent.KEY_PRESSED && event.getCode() == KeyCode.BACK_SPACE) {
			 validChar = true;
		 }
		 else
			 validChar = false;
	}
	
	/*This method checks that both of the text fields are filled, and if they are it uses the getDiseaseByRange query
	 * from hospital to return a list with all the diseases that start with a letter in the given range
	 */
	@FXML
	void activateQuery() {
		diseasesTV.getItems().clear();
		boolean flagFrom = true;
		boolean flagTo = true;
		
		if(fromTextField.getText().isEmpty()) {
			nameErrorMessage.setVisible(true);
			flagFrom = false;
		}
		
		if(toTextField.getText().isEmpty()) {
			nameErrorMessage.setVisible(true);
			flagTo = false;
		}	
		
		if (flagFrom && flagTo) {
			Character from = fromTextField.getText().charAt(0);
			Character to = toTextField.getText().charAt(0);
			TreeSet<Disease> tmp = Hospital.getInstance().getDiseasesByRange(from, to);
			diseasesTV.setVisible(true);
			if (tmp.isEmpty()) { //in case there are no diseases in the range
				diseasesTV.setPlaceholder(new Label("There are no diseases in this range"));

			}
			else {
				ObservableList<Disease> diseases = FXCollections.observableArrayList(tmp);
				setDiseaseTableView(diseases);
			}
		}
		
	}
	
	public void setDiseaseTableView(ObservableList<Disease> list) {
		//set up the columns in the table
		dIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		dNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		dSymptomsColumn.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
		diseasesTV.setItems(list);
	}
	
	
	
	
    
    

}
