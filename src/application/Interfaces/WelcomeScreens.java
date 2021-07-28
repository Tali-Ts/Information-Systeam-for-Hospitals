package application.Interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;

public abstract class WelcomeScreens {
	/* 
	 * This method uploads the next window
	 */
	public void windowTransition (AnchorPane screen, String str) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
			AnchorPane pane= loader.load();
			screen.getChildren().removeAll(screen.getChildren());
			screen.getChildren().add(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	//the method sets a greeting message for the user based on the time
	public void greeting (Label label, String str) {
		Date date = new Date(); 
		int hour = date.getHours();
		if (hour>=5 && hour<12) {
			label.setText("Good Morning, " + str);
		} 
		else if (hour>=12 && hour<17) {
			label.setText("Good Afternoon, " + str);
		}
		else if (hour>=17 && hour<22) {
			label.setText("Good evening, " + str);
		}
		else {
			label.setText("Good Night, " + str);
		}
	}
	
	/*this method gets called when the user presses on the logout button,
	  * it asks the user if he's sure he wants to logout of the system, and reacts based on the admin's decision(yes/cancel)
	  */
	public void logout (AnchorPane screen) {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Confirmation Dialog");
		alert1.setHeaderText("Are you sure you want to logout?");
		ButtonType yesButton = new ButtonType("Yes");
		ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert1.getButtonTypes().setAll(yesButton, cancelButton);
		Optional<ButtonType> result = alert1.showAndWait();
		if (result.get() == yesButton){
			windowTransition(screen, "LoginScreen.fxml");
		}
	}
	
	

}
