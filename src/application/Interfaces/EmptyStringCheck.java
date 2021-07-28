package application.Interfaces;

import javafx.scene.control.Label;

public interface EmptyStringCheck {
	//checks if the string is empty, if it's true sets the label visible
	default boolean isEmptyStringCheck (String str, Label label) {
		if (str.isEmpty()) {
    		label.setVisible(true);
    		return false;
    	}
		else {
			return true;
		}
	}

}
