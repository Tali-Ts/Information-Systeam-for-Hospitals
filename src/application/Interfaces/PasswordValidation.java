package application.Interfaces;

import java.util.regex.Pattern;

import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;

public interface PasswordValidation {
	/* this method checks every character the user enters in the password field and checks if it is a valid password, it also lets
	 * the user know if the password is strong using the progress bar
	 */
	default void passwordValidation (PasswordField pf, ProgressBar pb) {
		Pattern pBad = Pattern.compile("^(?=.*[0-9]).{1,}");
		Pattern pBetter = Pattern.compile("^(?=.*[a-z])(?=.*[0-9]).{6,}");
		Pattern pExcellent = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{7,}");
		
		if (pExcellent.matcher(pf.getText()).matches()) {
			pb.setId("excellent");
		}
		else if (pBetter.matcher(pf.getText()).matches()) {
			pb.setId("better");
		}
		else if (pBad.matcher(pf.getText()).matches() || pf.getLength()<5) {
			pb.setId("bad");
		}
	}

}
