package application.Interfaces;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Disease;
import Model.Hospital;
import application.AutoComplete;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public interface SetDComboBox {
	//adds to the combo box a list of diseases
	default void setDiseaseComboBox (ComboBox<String> cb) {
		ArrayList<String> dTmp = new ArrayList<String>();
		for (Disease d : Hospital.getInstance().getDiseases().values()) {
			dTmp.add(d.getName());
		}
		String[] dName = Arrays.copyOf(dTmp.toArray(), dTmp.toArray().length, String[].class);
		cb.setItems(FXCollections.observableArrayList(dName));
		AutoComplete.autoCompleteComboBoxPlus(cb, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
}
