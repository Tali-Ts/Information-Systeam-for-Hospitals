package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.Disease;
import Model.Hospital;
import application.Interfaces.ConfirmRemove;
import application.Interfaces.Message;
import application.Interfaces.SetDComboBox;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class RemoveDiseaseScreenController implements Initializable, SetDComboBox, Message, ConfirmRemove{
	@FXML
    private AnchorPane removeDiseaseScreen;

    @FXML
    private Button removeButton;

    @FXML
    private ComboBox<String> diseaseList;

    @FXML
    private Label errorMessage;

  //initializes the list of the diseases in the combo box
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDiseaseComboBox(diseaseList);
	}
	
	//this method removes the chosen disease
	@FXML
	void remove (Event event) {
		errorMessage.setVisible(false);
		boolean flag = true;
		
		String dName = diseaseList.getValue();
    	if (dName == null) {
    		errorMessage.setVisible(true);
    		flag = false;
    	}
		
		if (flag) {
			/* opens a pop up message that asks the user if he's sure he wants to remove,
			 * and reacts based on the user's answer
			 */
			Alert alert = confirmRemove("Are you sure you want to remove the disease?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().getText().equalsIgnoreCase("Yes")) {
				Disease d = Hospital.getInstance().getRealDiseaseByName(dName);
				if (Hospital.getInstance().removeDisease(d)) {
					message(AlertType.INFORMATION, "Information Dialog", "The disease " + d.getName() + " was removed succssefully!");
	        	}
				else {
	        		message(AlertType.WARNING, "Warning Dialog", "Couldn't remove the disease");
	        	}
				diseaseList.getSelectionModel().clearSelection();
				setDiseaseComboBox(diseaseList);
			}
			
		}
	}

}
