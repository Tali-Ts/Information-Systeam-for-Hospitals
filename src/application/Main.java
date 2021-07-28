package application;
	
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;
import Model.Hospital;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Hospital.readFile(); //deserialize method
			Parent root = FXMLLoader.load(getClass().getResource("/application/LoginScreen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		/*dialog box which pop out if the user clicks on the close button and asking him if he wants to save the changes
		 * before exiting the program
		 */
		primaryStage.setOnCloseRequest(event -> {
            Alert alert = getAlert();
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().getText().equalsIgnoreCase("Yes")){ //the changes will be saved by the serialize method and then the program will be closed 
            	//Serialize
				try {
					FileOutputStream fileOut = new FileOutputStream("Hospital.ser");//name of the folder we create
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(Hospital.getInstance());
					out.close();
					fileOut.close();
				} catch (IOException i) {
					i.printStackTrace();
				}
				System.out.println("Application Closed by click to Close Button(X)");
            }
            else if (result.get().getText().equalsIgnoreCase("No")) { //the changes won't be saved and the program will be closed
            }
            else if(result.get().getText().equalsIgnoreCase("Cancel")){ //the dialog box will be closed and the user will continue working on the program 
                event.consume();
            }
        });
	}
	
	//this method is called when the user asks to exit the program, it returns the alert with the buttons "Yes", "No", "Cancel"
	private Alert getAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exist Confirmation Dialog");
        alert.setHeaderText("Do you want to save your changes?");
        alert.setContentText("Choose your option.");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesButton, noButton, cancelButton);

        return alert;
    }

}
