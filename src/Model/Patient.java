package Model;

import java.io.Serializable;

import Exceptions.StatusOutOfRangeException;
import Utils.Condition;
import Utils.ReleaseNote;

//The class describes all the patients in the hospital, it extends of hospital user class
public class Patient extends HospitalUser implements Comparable<Patient>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int status;
	private Condition condition; 
	private Disease disease;
	
	
	//Constructors
	public Patient(int id) {
		super(id);
	}

	public Patient(String fname, String lname, int status, SubDepartment sd, Disease disease) {
		super(Hospital.getInstance().getPatientID(), fname, lname, sd);
		this.disease = disease;
		setStatus(status);
		Hospital.getInstance().setPatientID(Hospital.getInstance().getPatientID()+1);
	}

	
	//Getters and Setters
	public int getStatus() {
		return status;
	}

	/*If the status that sent is under 0 or over 100 the method throws exception and handles it, 
	 * else the method sets patient's condition according the terms and sets patient's status*/
	public void setStatus(int status) {
		try {
			if (status<0 || status>100)
				throw new StatusOutOfRangeException(status);
			else {
				if(status >= 0 && status < 40) {
					condition = Condition.CRITICAL;
				}
				else if(status >= 40 && status < 60) {
					condition = Condition.SERIOUS;
				}
				else if(status >= 60 && status < 80) {
					condition = Condition.FAIR;
				}
				else {
					condition = Condition.GOOD;
				}
				this.status = status;
			}
			
		} catch (StatusOutOfRangeException e) {
			if (status>100) {
				this.status = 100;
				condition = Condition.GOOD;
			}
			if (status<0) {
				this.status = 0;
				condition = Condition.CRITICAL;
			}
		}
	}
	
	public Condition getCondition() {
		return condition;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	
	//toStrings
	@Override
	public String toString() {
		return this.getFname() + " " + this.getLname();
	}
	
	public String toStringAnother() {
		return String.format("Patient name: %s",super.toString());
	}
	
	public String toStringLong() {
		return String.format("%s, Status is: %s, Condition %s",toString(),
				getStatus(),condition);
	}

	
	/*CompareTo between two patients by their last name, if last names are the same the method compares by the first name, 
	 * and returns the results in descending order */
	@Override
	public int compareTo(Patient other) {
		if (this.getLname().compareTo(other.getLname()) == 0)
			return -1*(this.getFname().compareTo(other.getFname()));
		else
			return -1*(this.getLname().compareTo(other.getLname()));
	}
	
	
	//The method checking the patien's condition and return the right note to the condition
	public ReleaseNote checkCondition() {
		if (this.condition==Condition.CRITICAL || this.condition==Condition.SERIOUS) 
			return ReleaseNote.STANDBY;
		if (this.condition == Condition.FAIR)
			return ReleaseNote.MOVE_TO_HOTEL;
		//if (this.condition == Condition.GOOD)
		return ReleaseNote.CAN_GO_HOME;
	}
	
}
