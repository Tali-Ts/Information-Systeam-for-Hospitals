package application.Interfaces;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class NameValidation {
	//this method checks every character the user is typing, and if it isn't valid input(for example: number), it doesn't type it
	public void nameValidation (KeyEvent e, Label label, boolean res) {
		String str = e.getCharacter();
		boolean isApostrophe=false;
		if (str.contains("\'")) {
			isApostrophe=true;
		}
        int len = str.length();
        for(int i = 0; i < len; i++) {
        	label.setVisible(false);
            Character c = str.charAt(i);
            if (!res && !Character.isLetter(c) && !isApostrophe) {
                e.consume();
                label.setVisible(true);
            }
        } 	
	}
	
	//this method returns true if the user pressed backspace/tab/minus, false else
	public boolean keyTypedCheck (KeyEvent event) {
		if (event.getEventType() == KeyEvent.KEY_PRESSED && event.getCode() == KeyCode.BACK_SPACE) {
			 return true;
		 }
		 else if (event.getEventType()==KeyEvent.KEY_PRESSED && event.getCode()==KeyCode.TAB) {
			 return true;
		 }
		 else if (event.getEventType()==KeyEvent.KEY_PRESSED && event.getCode()==KeyCode.MINUS) {
			 return true;
		 }
		 else
			 return false;
	}

}
