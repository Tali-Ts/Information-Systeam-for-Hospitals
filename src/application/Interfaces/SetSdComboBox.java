package application.Interfaces;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Hospital;
import Model.SubDepartment;
import application.AutoComplete;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public interface SetSdComboBox {
	//adds to the combo box a list of sub-departments
	default void setSubDepartmentComboBox (ComboBox<String> cb) {
		ArrayList<String> sdTmp = new ArrayList<String>();
		for (SubDepartment sd : Hospital.getInstance().getSubDepartment().values()) {
			String s = Integer.toString(sd.getId());
			sdTmp.add(s);
		}
		String[] sdId = Arrays.copyOf(sdTmp.toArray(), sdTmp.toArray().length, String[].class);
		cb.setItems(FXCollections.observableArrayList(sdId));
		AutoComplete.autoCompleteComboBoxPlus(cb, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains((typedText.toLowerCase())));
	}
}
