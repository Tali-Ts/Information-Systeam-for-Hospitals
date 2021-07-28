package Model;

import java.io.Serializable;
import java.util.HashSet;

import Utils.Treatments;

//The class describes all the patients in the hospital, it extends of hospital user class
public class Nurse extends HospitalUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Treatments treat;
	private int shifts;
	private String password;

	
	//Constructors
	 Nurse(int id) {
		super(id);
	}
	
	public Nurse(String fname, String lname, Treatments treat, SubDepartment sd, String password) {
		super(Hospital.getInstance().getNurseID(), fname, lname, sd);
		this.treat = treat;
		this.shifts = 0;
		this.password = password;
		Hospital.getInstance().setNurseID(Hospital.getInstance().getNurseID()+1);
	}

	
	//Getters and Setters
	public Treatments getTreat() {
		return treat;
	}

	public void setTreat(Treatments treat) {
		this.treat = treat;
	}

	public int getShifts() {
		return shifts;
	}

	public void setShifts(int shifts) {
		this.shifts = shifts;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//toStrings
	public String toString() {
		return this.getFname() + " " + this.getLname();
	}
	
	public String toStringAnother() {
		return String.format("Nurse name: %s",super.toString());
	}
	
	public String toStringLong() {
		return String.format("%s, Treatments: %s",toString(),getTreat());
	}
	
	
	
	//This method raises the shifts counter in one
	public void updateShiftCounter () {
		this.shifts++;
	}
	
	//This method raises the patient's condition in one, raises shift counter in one and activates the "hasTreatedPatient" method
	public boolean checkPatient (Patient p) {
		if (p == null)
			return false;
		else {
			int status = p.getStatus();
			p.setStatus(++status);
			updateShiftCounter();
			if (!hasTreatedPatient(p))
				return false;
			return true;
		}
	}
	
	//This method decreases the patient's condition in one, raises shift counter in one and activates the "hasTreatedPatient" method
	public boolean checkDisease (Patient p) {
		if (p == null)
			return false;
		else {
			int status = p.getStatus();
			p.setStatus(--status);
			updateShiftCounter();
			if (!hasTreatedPatient(p))
				return false;
			return true;
		}
	}
	
	//This method adds a nurse to patient-doctor hashMap
	public boolean hasTreatedPatient (Patient p) {
		if (Hospital.getInstance().getNursesByPatient().containsKey(p)) {
			Hospital.getInstance().getNursesByPatient().get(p).add(this);
			return true;
		}
		else {
			HashSet<Nurse> n = new HashSet<Nurse>();
			n.add(this);
			Hospital.getInstance().getNursesByPatient().put(p, n);
			return true;
		}
	}
	
}
