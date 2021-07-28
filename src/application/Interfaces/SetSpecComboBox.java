package application.Interfaces;

import java.util.Arrays;

import Utils.Specialization;
import application.AutoComplete;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public interface SetSpecComboBox {
	default void setSpecializationComboBox (ComboBox<String> cb) {
		String[] specList = Arrays.stream(Specialization.values()) // create stream of enum values
		        .map(e -> e.toString())  // convert enum stream to String stream
		        .toArray(String[]::new); // convert stream to an array
		cb.setItems(FXCollections.observableArrayList(specList));
		AutoComplete.autoCompleteComboBoxPlus(cb, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}

}
