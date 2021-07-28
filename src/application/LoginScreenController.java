package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Hospital;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class LoginScreenController implements Initializable{
    @FXML
    private AnchorPane loginScreen;
	
	@FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private CheckBox showPassword;
    
    @FXML
    private Tooltip passwordTooltip;
    
    @FXML
    private MediaView video;  

    @FXML
    private Label passwordToShow;
    
    public TextField getUsernameField() {
		return usernameField;
	}
    
    private static String type;
    
	public static String getType() {
		return type;
	}
	
	private static int id;
	
	public static int getId() {
		return id;
	}
	/*
	 * This method sets a video on the login screen
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String path = "video.mp4";
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		video.setMediaPlayer(mediaPlayer);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);	
	}
	
	/*
	 * This method pops up an alert on the window
	 */
	private void errorMessage (String str) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setContentText(str);
		alert.showAndWait();
	}
	
	/* 
	 * This method uploads the next window when the user clicks on the login button
	 */
	private void windowTransition (String str) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
			AnchorPane pane= loader.load();
			loginScreen.getChildren().removeAll(loginScreen.getChildren());
			loginScreen.getChildren().add(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This methods gets called when the user clicks on the login button, 
	 * it checks that the user name and password are filled,
	 * it checks the user name to know which type of user tried to login (admin\doctor\nurse) and checks his password
	 */
	@FXML
    void login (Event event) {
    	String userName = usernameField.getText();
    	String password = passwordField.getText();
    	String s = "";
    	
		if (userName.isEmpty() || password.isEmpty()) {
			errorMessage("You must fill all the fields");
		}
		else {
			if (userName.contains("-")) {
				String[] str = userName.split("-");//if the user name contains "-" it means he's a doctor\nurse,
				//so we split it to get the first letter (d if it's a doctor, n if it's a nurse)
				s = str[0];
				id = Integer.parseInt(str[1]);
			}
			
			if (userName.equals("admin")) {
	    		if (password.equals("admin")) {
	    			type = "admin";
	    			windowTransition("AdminScreen.fxml");//if the user name and password are admin it opens the admin screen
	    		}
	    		else {//if the user name is admin but the password is not correct shows an error message
	    			errorMessage("The password is not correct!");
	    		}
	    	}		
	    	else if (s.equals("d") || s.equals("n")) {
	    		//for nurses and doctors, checks if the password fits the user name, based on their id
	    		if (Hospital.getInstance().getRealDoctor(id)!= null && s.equals("d")) {
	    			if (Hospital.getInstance().getRealDoctor(id).getPassword().equals(password)) {
	    				type = "doctor";
	    				windowTransition("DoctorScreen.fxml");//if the user name and password are correct it opens the doctor screen
	    			}
	    			else {//if the user name belongs to a doctor but the password is not correct shows an error message
	    				errorMessage("The password is not correct!");
	    			}
	    		}
	    		else if (Hospital.getInstance().getRealNurse(id)!= null && s.equals("n")) {
	    			if (Hospital.getInstance().getRealNurse(id).getPassword().equals(password)) {
	    				type = "nurse";
	    				windowTransition("NurseScreen.fxml");//if the user name and password are correct it opens the nurse screen
	    			}
	    			else {//if the user name belongs to a nurse but the password is not correct shows an error message
	    				errorMessage("The password is not correct!");
	    			}
	    		}
	    		else {//if the user name does not exist, show an error message
		    		errorMessage("The user does not exist!");
	    		}
	    	}
	    	else {//if the user name does not exist, show an error message
	    		errorMessage("The user does not exist!");
			}	
	    }
	}
	
	/*
	 * This method gets called when the users checks the "show password" check box, 
	 * it sets the string he typed in the field
	 */
	@FXML
	void getPassword (KeyEvent event) {
		passwordToShow.setText(passwordField.getText());
	}
	
	/*
	 * This method indicates whether the user checked the "show password" check box or not
	 */
	@FXML
	void showPassword(MouseEvent event) {
		if (showPassword.isSelected()) {
			passwordToShow.setVisible(true);
		}
		else if (!showPassword.isSelected()) {
			passwordToShow.setVisible(false);
		}	
	}

}
