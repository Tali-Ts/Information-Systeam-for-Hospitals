package application;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import Model.Disease;
import Model.Hospital;
import Utils.Symptoms;
import application.Interfaces.EmptyStringCheck;
import application.Interfaces.Message;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;

public class AddDiseaseScreenController implements Initializable, EmptyStringCheck, Message {
    @FXML
    private AnchorPane addDiseasScreen;
	
	@FXML
    private TextField nameField;

    @FXML
    private Button addButton;
    
    @FXML
    private Label smptErrorMessage;

    @FXML
    private Label nameErrorMessage;
    
    @FXML
    private MenuButton symptomList;
 
    @FXML
    private CustomMenuItem coughItem;

    @FXML
    private CheckBox cough;

    @FXML
    private CustomMenuItem feverItem;

    @FXML
    private CheckBox fever;

    @FXML
    private CustomMenuItem tirednessItem;

    @FXML
    private CheckBox tiredness;

    @FXML
    private CustomMenuItem difficultyBreathingItem;

    @FXML
    private CheckBox difficultyBreathing;

    @FXML
    private CustomMenuItem backAchesItem;

    @FXML
    private CheckBox backAches;

    @FXML
    private CustomMenuItem faintingItem;

    @FXML
    private CheckBox fainting;
    
  //here we make sure that when the user checks one of the symptoms, the menu stays open so he can choose more symptoms
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		coughItem.setHideOnClick(false);
		feverItem.setHideOnClick(false);
		tirednessItem.setHideOnClick(false);
		difficultyBreathingItem.setHideOnClick(false);
		backAchesItem.setHideOnClick(false);
		faintingItem.setHideOnClick(false);
	}
	
	/* this method gets called when the user clicks on the add button in the screen,
	 * it checks that all the fields are full, and if they are it creates a new disease, using the data the user entered
	 */
	@FXML
    void add (Event event) {
		//the error messages aren't visible until someone presses the add button without filling all the fields first
		nameErrorMessage.setVisible(false);
		smptErrorMessage.setVisible(false);
		Boolean flag=true;
		
		//if at least one of the fields is empty, show a message to the user and change the flag to false
    	String dname = nameField.getText();
    	flag = isEmptyStringCheck(dname, nameErrorMessage);
    	
    	//making a hashSet of all the symptoms the user chose, to send it to the constructor
    	ArrayList<CheckBox> checkBox = new ArrayList<CheckBox>();
    	checkBox.add(fainting);
    	checkBox.add(cough);
    	checkBox.add(tiredness);
    	checkBox.add(fever);
    	checkBox.add(difficultyBreathing);
    	checkBox.add(backAches);
    	HashSet<Symptoms> smpts = new HashSet<Symptoms>();
    	for (CheckBox cb : checkBox) {
    		if (cb.isSelected()) {
    			smpts.add(Symptoms.valueOf(cb.getText().toUpperCase()));
    		}
    	}
    	if (smpts.isEmpty()) {
    		smptErrorMessage.setVisible(true);
    		flag = false;
    	}
    	
    	//if all the fields are full, build a new disease
    	if (flag) { 	
    		Disease d = new Disease(dname, smpts);
    		//if the disease was added successfully, show a success message
        	if (Hospital.getInstance().addDisease(d)) {
        		message(AlertType.INFORMATION, "Information Dialog", "The disease " + d.getName() 
        				+ " was added successfully!");
        	}
        	else {
        		message(AlertType.WARNING, "Warning Dialog", "Couldn't add the disease");
        	}
        	
        	//clear the selection, so it will be ready for the next add
        	nameField.clear();
        	for (CheckBox cb : checkBox) {
        		cb.setSelected(false);
        	}
    	}
    }

}
