package application.Interfaces;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Doctor;
import Model.Hospital;
import application.AutoComplete;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public interface SetDocComboBox {
	default void setDoctorComboBox (ComboBox<String> cb) {
		//adds to the combo box a list of doctors
		ArrayList<String> docTmp = new ArrayList<String>();
		for (Doctor d : Hospital.getInstance().getDoctors().values()) {
			String s = Integer.toString(d.getId()) + " - " + d.getFname() + " " + d.getLname();
			docTmp.add(s);
		}
		String[] docId = Arrays.copyOf(docTmp.toArray(), docTmp.toArray().length, String[].class);
		cb.setItems(FXCollections.observableArrayList(docId));
		AutoComplete.autoCompleteComboBoxPlus(cb, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
}
