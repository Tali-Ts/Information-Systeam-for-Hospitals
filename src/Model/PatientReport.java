package Model;

import java.io.Serializable;
import java.util.Date;
import Utils.ReleaseNote;

//This class describes a patient report
public class PatientReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Patient patient;
	private Doctor doctor;
	private Date date;
	private Disease disease;
	private SubDepartment sdept;
	private ReleaseNote releaseNote;
	
	
	//Constructors
	public PatientReport(Patient patient, Doctor doctor, Date date,
			Disease disease, SubDepartment sdept, ReleaseNote note) {
		super();
		this.id = Hospital.getInstance().getPatientReportID();
		Hospital.getInstance().setPatientReportID(id+1);
		this.patient = patient;
		this.doctor = doctor;
		this.date =date;
		this.disease = disease;
		this.sdept = sdept;
		this.releaseNote = note;
	}
	
	 PatientReport(int id) {
		this.id = id;
	}
	
	 
	//Getters and Setters
	 public int getId() {
			return id;
		}

	public void setId(int id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public SubDepartment getSdept() {
		return sdept;
	}

	public void setSdept(SubDepartment sdept) {
		this.sdept = sdept;
	}

	public ReleaseNote getReleaseNote() {
		return releaseNote;
	}

	public void setReleaseNote(ReleaseNote releaseNote) {
		this.releaseNote = releaseNote;
	}

	
	//hashCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientReport other = (PatientReport) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

	//toString
	@Override
	public String toString() {
		return "PatientReport [patient=" + patient + ", doctor=" + doctor + ", disease=" + disease + "]";
	}
	
	public String toStringLong() {
		return "doctor: " + doctor + "\ndate: " + date + "\ndisease: " + disease + "\nsub-department: " + sdept + "\nrelease note: "
				+ releaseNote;
	}
	
}
