package application.Interfaces;

import Model.ChronicDisease;
import Model.Patient;
import Model.ViralDisease;

public interface FindDType {
	//this methods gets a patient and returns a string that says the type of his disease
	default String findDiseaseType (Patient p) {
		String diseaseType = "";
		if (p.getDisease().getClass().equals(ChronicDisease.class)) {
			diseaseType = "Chronic";
		}
		else if (p.getDisease().getClass().equals(ViralDisease.class)) {
			diseaseType = "Viral";
		}
		return diseaseType;
	}

}
