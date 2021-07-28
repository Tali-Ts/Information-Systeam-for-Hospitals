package application.Interfaces;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface Message {
	//creates a pop up alert, of the given type
	default void message (AlertType type, String title, String str) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(str);
		alert.showAndWait();
	}

}
