package application.Interfaces;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Department;
import Model.Hospital;
import application.AutoComplete;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public interface SetDepComboBox {
	default void setDepartmentComboBox (ComboBox<String> cb) {
		//adds to the combo box a list of departments
		ArrayList<String> depTmp = new ArrayList<String>();
		for (Department dep : Hospital.getInstance().getDepartments().values()) {
			String s = Integer.toString(dep.getId()) + " - " + dep.getDeptName();
			depTmp.add(s);
		}
		String[] depId = Arrays.copyOf(depTmp.toArray(), depTmp.toArray().length, String[].class);
		cb.setItems(FXCollections.observableArrayList(depId));
		AutoComplete.autoCompleteComboBoxPlus(cb, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
}
