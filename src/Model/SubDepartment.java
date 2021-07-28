package Model;

import java.io.Serializable;
import java.util.HashSet;

//The class describes sub-departments in the hospital
public class SubDepartment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private HashSet<Patient> patients;
	private HashSet<Doctor> doctors;
	private HashSet<Nurse> nurses;
	private HashSet<PatientReport> reports;
	private Department deptment;
	
	
	//Constructors
	public SubDepartment(int id) {
		this.id = id;
	}
	
	public SubDepartment(Department deptment) {
		super();
		id = Hospital.getInstance().getSubDepartmentID();
		Hospital.getInstance().setSubDepartmentID(id+1);
		this.deptment = deptment;
		this.patients = new HashSet<Patient>();
		this.doctors = new HashSet<Doctor>();
		this.nurses = new HashSet<Nurse>();
		this.reports =  new HashSet<PatientReport>();
	}

	
	//Getters and Setters
	public int getId() {
		return id;
	}
		
	public HashSet<Patient> getPatients() {
		return patients;
	}

	public void setPatients(HashSet<Patient> patients) {
		this.patients = patients;
	}

	public HashSet<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(HashSet<Doctor> doctors) {
		this.doctors = doctors;
	}

	public HashSet<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(HashSet<Nurse> nurses) {
		this.nurses = nurses;
	}

	public HashSet<PatientReport> getReports() {
		return reports;
	}

	public void setReports(HashSet<PatientReport> reports) {
		this.reports = reports;
	}

	public Department getDeptment() {
		return deptment;
	}

	public void setDeptment(Department deptment) {
		this.deptment = deptment;
	}

	
	//toString
	@Override
	public String toString() {
		return Integer.toString(id);
	}
	
	public String toStringLong() {
		return "SubDepartment [id=" + id + ", department=" + deptment + "], Number Of Patients is: " + getPatients().size();
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
		SubDepartment other = (SubDepartment) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	//The method returns TRUE if the doctor exists in the HashSet, and FALSE if he doesn't
	public boolean findDoctor(Doctor doc){
		return getDoctors().contains(doc);
	}
	
	//The method returns TRUE if the nurse exists in the HashSet, and FALSE if she doesn't
	public boolean findNurse(Nurse nurse){
		return getNurses().contains(nurse);
	}
	
	//The method returns TRUE if the patient exists in the HashSet, and FALSE if he doesn't
	public boolean findPatient(Patient patient){
		return getPatients().contains(patient);
	}
	
	//The method returns TRUE if the patient report exists in the HashSet, and FALSE if it doesn't
	public boolean findPatientReport(PatientReport patientReport){
		return getReports().contains(patientReport);
	}
	
	//The method adds a doctor if he doesn't exists in the HashSet, and if he does exists it prints an error
	public void addDcotor(Doctor doc) {
		if(findDoctor(doc))
			System.err.printf("%s should not be in Sdept %d\n" , doc,getId());
		getDoctors().add(doc);
		
	}
	
	//The method adds a nurse if doesn't exists in the HashSet, and if he does exists it prints an error
	public void addNurse(Nurse nurse) {
		if(findNurse(nurse))
			System.err.printf("%s should not be in Sdept %d\n" , nurse,getId());
		getNurses().add(nurse);
		
	}
	
	//The method adds a patient if he doesn't exists in the HashSet, and if he does exists it prints an error
	public void addPatient(Patient patient) {
		if(findPatient(patient))
			System.err.printf("%s should not be in Sdept %d\n" , patient,getId());
		getPatients().add(patient);
	}
	
	//The method adds a patient report if it doesn't exists in the HashSet, and if it does exists it prints an error
	public void addPatientReport(PatientReport patientReport) {
		if(findPatientReport(patientReport))
			System.err.printf("%s should not be in Sdept %d\n" , patientReport,getId());
		getReports().add(patientReport);
		
	}
	
	//This method removes a doctor from the HashSet
	public boolean removeDoctor(Doctor doc) {
		return getDoctors().remove(doc);
		
	}
	
	//This method removes a nurse from the HashSet
	public boolean removeNurse(Nurse nurse) {
		return getNurses().remove(nurse);
		
	}
	
	//This method removes a patient from the HashSet
	public boolean removePatient(Patient patient) {
		return getPatients().remove(patient);	
	}
	
	//This method removes a patient reports from the HashSet
	public boolean removePatientReport(PatientReport patientReport) {
		return getReports().remove(patientReport);
	}

}
