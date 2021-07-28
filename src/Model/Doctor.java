package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import Utils.ReleaseNote;
import Utils.Specialization;
import Utils.Symptoms;

//The class describes all the doctors in the hospital, it extends of hospital user class
public class Doctor extends HospitalUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Specialization spec;
	private int shifts;
	private String password;

	
	//Constructors
	 Doctor(int id) {
		super(id);
	}
	 
	 public Doctor(String fname, String lname, Specialization spec, SubDepartment sd, String password) {
		super(Hospital.getInstance().getDoctorID(), fname, lname, sd);
		this.spec = spec;
		this.shifts = 0;
		this.password = password;
		Hospital.getInstance().setDoctorID(Hospital.getInstance().getDoctorID()+1);
	}

	//Getters and Setters
	public Specialization getSpec() {
		return spec;
	}

	public void setSpec(Specialization spec) {
		this.spec = spec;
	}
	
	public int getShiftCounter() {
		return shifts;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//toStrings
	@Override
	public String toString() {
		return this.getFname() + " " + this.getLname();
	}
	
	public String toStringAnother() {
		return String.format("Doctor name: %s",super.toString());
	}
	
	public String toStringLong() {
		return String.format("%s, Specialization: %s",toString(),getSpec());
	}
	
	
	
	//This method raises the shifts counter in one
	public void updateShiftCounter () {
		this.shifts++;
	}
	
	/* This method creates patient report and adds it to all the data structures, calls the "updateShiftCounter" and
	"hasTreatedPatient" methods */
	public boolean checkPatient (Patient p) {
		if (p == null)
			return false;
		else {
			Date now = new Date();
			ReleaseNote note = p.checkCondition();
			PatientReport pr = new PatientReport(p, this, now, p.getDisease(), p.getSd(), note);
			p.getSd().addPatientReport(pr);
			Hospital.getInstance().addPatientReport(p, this, now, p.getDisease(), note);
			updateShiftCounter();
			hasTreatedPatient(p);
			return true;
		}
	}
	
	//This method adds a doctor to patient-doctor hashMap
	public boolean hasTreatedPatient (Patient p) {
		if (Hospital.getInstance().getDoctorsByPatient().containsKey(p)) {
			Hospital.getInstance().getDoctorsByPatient().get(p).add(this);
			return true;
		}
		else {
			HashSet<Doctor> d = new HashSet<Doctor>();
			d.add(this);
			Hospital.getInstance().getDoctorsByPatient().put(p, d);
			return true;
		}
	}
	
	//This method classifies a patient's disease by its symptoms
	public boolean checkDisease (Patient p) {
		if (p == null)
			return false;
		hasTreatedPatient(p);
		updateShiftCounter();
		if (p.getDisease().getSymptoms().contains(Symptoms.DIFFICULTY_BREATHING) && 
				p.getDisease().getSymptoms().contains(Symptoms.FEVER)) {
			ChronicDisease cd = new ChronicDisease(p.getDisease().getId(), p.getDisease().getName(), p.getDisease().getSymptoms(), true);
			p.setDisease(cd);
		}
		else {
			ViralDisease vd = new ViralDisease(p.getDisease().getId(), p.getDisease().getName(), p.getDisease().getSymptoms(), true);
			p.setDisease(vd);
		}
		return true;
	}
}
