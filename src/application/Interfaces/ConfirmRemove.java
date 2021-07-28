package application.Interfaces;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

public interface ConfirmRemove {
	//creates a pop up confirmation dialog, of the given type
	default Alert confirmRemove (String str) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(str);

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(yesButton, cancelButton);
        
        return alert;
	}

}
