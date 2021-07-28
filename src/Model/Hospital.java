package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;
import Exceptions.CannotReleasePatientException;
import Utils.Condition;
import Utils.ReleaseNote;
import Utils.Specialization;
import Utils.Symptoms;
import Utils.Treatments;

//The class represents a hospital
public class Hospital implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Hospital theHospital = null;

	private HashMap<Integer, Patient> patients;
	private HashMap<Integer, Doctor> doctors;
	private HashMap<Integer, Nurse> nurses;
	private HashMap<Integer, PatientReport> reports;
	private HashMap<Integer, Patient> hotelPatients;
	private HashMap<Integer, Disease> diseases;
	private HashMap<Integer, Department> departments;
	private HashMap<Integer, SubDepartment> subDepartment;
	private HashMap<Patient, HashSet<Doctor>> doctorsByPatient;
	private HashMap<Patient, HashSet<Nurse>> nursesByPatient;
	
	private int patientID;
	private int doctorID;
	private int nurseID;
	private int departmentID;
	private int subDepartmentID;
	private int diseaseID;
	private int patientReportID;
	

	//Singleton method
	public static Hospital getInstance() {
		if(theHospital==null)
			theHospital = new Hospital();
		return theHospital;
		
	}
	
	private Hospital() {
		patients = new HashMap<Integer, Patient>();
		doctors = new HashMap<Integer, Doctor>();
		nurses = new HashMap<Integer, Nurse>();
		reports =  new HashMap<Integer, PatientReport>();
		hotelPatients = new HashMap<Integer, Patient>();
		diseases = new HashMap<Integer, Disease>();
		departments = new HashMap<Integer, Department>();
		subDepartment = new HashMap<Integer, SubDepartment>();
		doctorsByPatient = new HashMap<>();
		nursesByPatient = new HashMap<>();
		patientID = 1;
		doctorID = 1;
		nurseID = 1;
		departmentID = 1;
		subDepartmentID = 1;
		diseaseID = 1;
		patientReportID = 1;
	}

	
	//Getters and Setters
	public void setPatients(HashMap<Integer, Patient> patients) {
		this.patients = patients;
	}

	public void setDoctors(HashMap<Integer, Doctor> doctors) {
		this.doctors = doctors;
	}
	
	public HashMap<Integer, Doctor> getDoctors() {
		return doctors;
	}

	public HashMap<Integer, Patient> getPatients() {
		return patients;
	}
	
	public HashMap<Integer, Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(HashMap<Integer, Nurse> nurses) {
		this.nurses = nurses;
	}

	public HashMap<Integer, PatientReport> getReports() {
		return reports;
	}

	public void setReports(HashMap<Integer, PatientReport> reports) {
		this.reports = reports;
	}

	public HashMap<Integer, Patient> getHotelPatients() {
		return hotelPatients;
	}

	public void setHotelPatients(HashMap<Integer, Patient> hotelPatients) {
		this.hotelPatients = hotelPatients;
	}

	public HashMap<Integer, Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(HashMap<Integer, Disease> diseases) {
		this.diseases = diseases;
	}
	
	public HashMap<Integer, Department> getDepartments() {
		return departments;
	}

	public void setDepartments(HashMap<Integer, Department> departments) {
		this.departments = departments;
	}

	public HashMap<Integer, SubDepartment> getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(HashMap<Integer, SubDepartment> subDepartment) {
		this.subDepartment = subDepartment;
	}

	public HashMap<Patient, HashSet<Doctor>> getDoctorsByPatient() {
		return doctorsByPatient;
	}

	public void setDoctorsByPatient(HashMap<Patient, HashSet<Doctor>> doctorsByPatient) {
		this.doctorsByPatient = doctorsByPatient;
	}

	public HashMap<Patient, HashSet<Nurse>> getNursesByPatient() {
		return nursesByPatient;
	}

	public void setNursesByPatient(HashMap<Patient, HashSet<Nurse>> nursesByPatient) {
		this.nursesByPatient = nursesByPatient;
	}
	
	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	
	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public int getNurseID() {
		return nurseID;
	}

	public void setNurseID(int nurseID) {
		this.nurseID = nurseID;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public int getSubDepartmentID() {
		return subDepartmentID;
	}

	public void setSubDepartmentID(int subDepartmentID) {
		this.subDepartmentID = subDepartmentID;
	}

	public int getDiseaseID() {
		return diseaseID;
	}

	public void setDiseaseID(int diseaseID) {
		this.diseaseID = diseaseID;
	}

	public int getPatientReportID() {
		return patientReportID;
	}

	public void setPatientReportID(int patientReportID) {
		this.patientReportID = patientReportID;
	}

	//ADD
	//The method adds a new patient to the structures in the hospital and in a specific sub-department
	public boolean addPatient(Patient patient, SubDepartment s) {
		if(patient==null || s==null)
			return false;
		
		if(!getPatients().containsKey(patient.getId()))
			getPatients().put(patient.getId(), patient);
		else
			return false;
		s.addPatient(patient);
		return true;
	}
	
	//The method adds a new doctor to the structures in the hospital and in a specific sub-department
	public boolean addDoctor(Doctor doc, SubDepartment s) {
		if(doc==null || s==null) 
			return false;
		if(!getDoctors().containsKey(doc.getId()))
			getDoctors().put(doc.getId(), doc);
		else
			return false;
		s.addDcotor(doc);
		return true;
	}
	
	//The method adds a new nurse to the structures in the hospital and in a specific sub-department
	public boolean addNurse(Nurse nurse, SubDepartment s) {
		if(nurse==null || s==null) {
			return false;
		}
		
		if(!getNurses().containsKey(nurse.getId()))
			getNurses().put(nurse.getId(), nurse);
		else
			return false;
		s.addNurse(nurse);
		return true;
	}
	
	//The method adds a new patient report to the structures in the hospital and in a specific sub-department
	public PatientReport addPatientReport(Patient pat, Doctor doc, Date date, Disease des, ReleaseNote note) {
		if (doc==null || pat==null || des==null || note==null) {
			return null;
		}
		SubDepartment sd = pat.getSd();
		if(sd == null)
			return null;
		PatientReport pr = new PatientReport(pat,doc,date,des,sd,note);
		if (!getReports().containsKey(pr.getId()))
			getReports().put(pr.getId(), pr);
		else
			return null;
		sd.addPatientReport(pr);
		return pr;
	}

	//The method adds a new disease to the structures in the hospital
	public boolean addDisease(Disease disease) {
		if (disease == null)
			return false;
		if(!getDiseases().containsKey(disease.getId())) {
			getDiseases().put(disease.getId(), disease);
			return true;
		}
		else
			return false;
	}
	
	//The method adds a new department to the structures in the hospital
	public boolean addDepartment(Department d) {
		if (d == null)
			return false;
		if(!getDepartments().containsKey(d.getId())) {
			getDepartments().put(d.getId(), d);
			return true;
		}
		else
			return false;
	}
	
	//The method adds a new sub-department to the structures in the hospital and in a specific department
	public boolean addSubDepartment(Department d, SubDepartment s) {
		if (d==null || s==null)
			return false;
		if (!getSubDepartment().containsKey(s.getId()))
			getSubDepartment().put(s.getId(), s);
		else
			return false;
		d.getSdepts().add(s);
		return true;
	}
	
	
	//REMOVE
	//The method removes a patient from the structures in the hospital and in a specific sub-department
	public boolean removePatient(Patient patient) {
		if (patient == null)
			return false;
		SubDepartment s = patient.getSd();
		if (s == null)
			return false;
		s.removePatient(patient);
		getPatients().remove(patient.getId());
		doctorsByPatient.remove(patient);
		nursesByPatient.remove(patient);
		return true;
	}
	
	//The method removes a doctor from the structures in the hospital and in a specific sub-department
	public boolean removeDoctor(Doctor doc) {
		if(doc == null)
			return false;
		SubDepartment s = doc.getSd();
		if (s == null)
			return false;
		s.removeDoctor(doc);
		getDoctors().remove(doc.getId());
		return true;
		
	}
	
	//The method removes a nurse from the structures in the hospital and in a specific sub-department
	public boolean removeNurse(Nurse nurse) {
		SubDepartment s = nurse.getSd();
		if(s==null || nurse==null)
			return false;
		s.removeNurse(nurse);
		getNurses().remove(nurse.getId());
		return true;
		
	}
	
	//The method removes a patient report from the structures in the hospital and in a specific sub-department
	public boolean removePatientReport(PatientReport pr) {
		if(pr == null)
			return false;
		pr.getSdept().removePatientReport(pr);
		getReports().remove(pr.getId());
		return true;
	}
	
	//The method removes a disease from the structures in the hospital and in a specific sub-department
	public boolean removeDisease(Disease disease) {
		if(disease == null)
			return false;
		getDiseases().remove(disease.getId());
		return true;
	}
	
	//The method removes a department from the structures in the hospital
	public boolean removeDepartment(Department d) {
		if (d == null)
			return false;
		getDepartments().remove(d.getId());
		return true;
	}
	
	//The method removes a sub-department from the structures in the hospital and in a specific department
	public boolean removeSubDepartment (SubDepartment s) {
		if (s == null)
			return false;
		Department d = s.getDeptment();
		if (d == null)
			return false;
		d.getSdepts().remove(s);
		getSubDepartment().remove(s.getId());
		return true;
	}
	
	/*The method removes a patient using the remove patient method, if he has "can go home" release note, 
	 * if he doesn't has this release note it will throw an exception*/
	public String removeRecoverPatient(Patient patient) {
		try {
			if (patient.checkCondition().equals(ReleaseNote.CAN_GO_HOME)) {
				if (removePatient(patient))
					return "Success";
				return "Fail";
			}
			else 
				throw new CannotReleasePatientException(patient.checkCondition(), ReleaseNote.CAN_GO_HOME);
		} catch (CannotReleasePatientException e) {
			return e.getMessage();
		}
	}
	
	/*The method removes a patient using the remove patient method and adding it to the hotel structure, 
	 * if he has "move to hotel" release note, if he doesn't has this release note it will throw an exception*/
	public String removeToHotelPatient(Patient patient) {
		try {
			if (patient.checkCondition().equals(ReleaseNote.MOVE_TO_HOTEL)) {
				if (removePatient(patient)) {
					hotelPatients.put(patient.getId(), patient);
					return "Success";
				}
				return "Fail";
			}
			else 
				throw new CannotReleasePatientException(patient.checkCondition(), ReleaseNote.MOVE_TO_HOTEL);
		} catch (CannotReleasePatientException e) {
			return e.getMessage();
		}
	}
	
	
	//GET REAL
	//The method return patient that matches the ID sent to the method 
	public Patient getRealPatient(int pid) {
		Integer id = pid;
		if (patients.containsKey(id))
			return patients.get(id);
		return null;		
	}
	
	//The method return doctor that matches the ID sent to the method
	public Doctor getRealDoctor(int did) {
		Integer id = did;
		if (doctors.containsKey(id))
			return doctors.get(id);
		return null;
	}
	
	//The method return nurse that matches the ID sent to the method
	public Nurse getRealNurse(int nid) {
		Integer id = nid;
		if (nurses.containsKey(id))
			return nurses.get(id);
		return null;
	}
	
	//The method return patient report that matches the ID sent to the method
	public PatientReport getRealPatientReport(int prid) {
		Integer id = prid;
		if (reports.containsKey(id))
			return reports.get(id);
		return null;
	}
	
	//The method return disease that matches the ID sent to the method
	public Disease getRealDisease(int did) {
		Integer id = did;
		if (diseases.containsKey(id))
			return diseases.get(id);
		return null;
	}
	
	public Disease getRealDiseaseByName (String name) {
		for (Disease d : diseases.values()) {
			if (d.getName().equals(name)) {
				return d;
			}
		}
		return null;
	}
	
	//The method return department that matches the ID sent to the method
	public Department getRealDepartment(int did) {
		Integer id = did;
		if (departments.containsKey(id))
			return departments.get(id);
		return null;
	}
	
	//The method return sub-department that matches the ID sent to the method
	public SubDepartment getRealSubDepartment(int sid) {
		Integer id = sid;
		if (subDepartment.containsKey(id))
			return subDepartment.get(id);
		return null;
	}
	
	
	//PRINT ALL
	public boolean printAllDoctors(Department dep) {
		if(dep == null)
			return false;
		dep.printAllDoctors();
		return true;
		
	}
	public boolean printAllNurses(Department d) {
		if(d == null)
			return false;
		
		d.printAllNurses();
		return true;
		
	}
	public boolean printAllPatients(Department dep) {
		if(dep == null)
			return false;
		dep.printAllPatients();
		return true;
		
	}
	
	
	
	//This method returns array list of all the doctor's patient with serious or critical condition
	public ArrayList<Patient> getAllBadConditionPatients(Doctor d) {
		ArrayList <Patient> toReturn = new ArrayList<Patient>();
		
		for (Patient p : doctorsByPatient.keySet()) {
			if (doctorsByPatient.get(p).contains(d)) {
				if (p.getCondition().equals(Condition.CRITICAL) || p.getCondition().equals(Condition.SERIOUS))
					toReturn.add(p);
			}
		}
		
		toReturn.sort(new Comparator<Patient>() {
			public int compare (Patient o1, Patient o2) {
				Integer o1Status = o1.getStatus();
				Integer o2Status = o2.getStatus();
				return -1*(o1Status.compareTo(o2Status));
			}
		});
		
		return toReturn;
	}
	
	
	//This method returns the hardest working nurse, which appears in most patients
	public Nurse findHardestWorkingNurse() {
		HashMap <Nurse, Integer> tmp = new HashMap<>();
		
		for (Patient p : nursesByPatient.keySet()) {
			for (Nurse n : nursesByPatient.get(p)) {
				if (!tmp.containsKey(n))
					tmp.put(n, 1);
				else
					tmp.put(n, tmp.get(n)+1);
			}
		}
		
		int max = 0;
		Nurse hardestWorkingNurse = null;
		
		for (Nurse n : tmp.keySet()) {
			if (tmp.get(n)>max) {
				max = tmp.get(n);
				hardestWorkingNurse = n;
			}
		}
		
		return hardestWorkingNurse;
	}
	
	
	/*This method returns all the patient that are critical and treated by nurses specialized in STEROIDS 
	and doctors specialized in NEUROLOGY*/
	public TreeSet<Patient> getCriticalSteroidsNeuPatients() {
		TreeSet<Patient> toReturn = new TreeSet<Patient>(new Comparator<Patient>() {
			@Override
			public int compare (Patient o1, Patient o2) {
				return -1*(o1.compareTo(o2));
			}
		});
		
		for (Patient p : nursesByPatient.keySet()) {
			if (p.getCondition().equals(Condition.CRITICAL)) {
				for (Nurse n : nursesByPatient.get(p)) {
					if (n.getTreat().equals(Treatments.STEROIDS)) {
						if (doctorsByPatient.containsKey(p)) {
							for (Doctor d : doctorsByPatient.get(p)) {
								if (d.getSpec().equals(Specialization.NEUROLOGY))
									toReturn.add(p);
							}
						}
					}
				}
			}
		}
		return toReturn;
	}
	
	
	//This method returns two sub-department which have the biggest number of patients in good condition
	public TreeSet<SubDepartment> getBestStatusSubDepartments() {
		TreeSet<SubDepartment> subs = new TreeSet<>(new Comparator<SubDepartment>() {

			@Override
			public int compare(SubDepartment o1, SubDepartment o2) {
				Integer o1Size = o1.getPatients().size();
				Integer o2Size = o2.getPatients().size();
				return o2Size.compareTo(o1Size);
			}
		});
		HashMap<SubDepartment, Integer> subCountGoodPatients = new HashMap<>();
		for(SubDepartment s : getSubDepartment().values()) {
			int counter = 0;
			for(Patient p : s.getPatients()) {
				if(p.getCondition().equals(Condition.GOOD))
					counter++;
			}
			subCountGoodPatients.put(s, counter);
		}
		int max = 0;
		int secondMax = 0;
		SubDepartment bestSub = null;
		SubDepartment secondBestSub = null;
		for(SubDepartment s : subCountGoodPatients.keySet()) {
			if(subCountGoodPatients.get(s) > max) {
				secondBestSub = bestSub;
				secondMax = max;
				max = subCountGoodPatients.get(s);
				bestSub = s;
				continue;
			}
			else if(subCountGoodPatients.get(s) > secondMax) {
				secondBestSub = s;
				secondMax = subCountGoodPatients.get(s);
				continue;
			}
		}
		subs.add(bestSub);
		subs.add(secondBestSub);
		return subs;
	}
	
	
	/*This method returns all the doctor who wrote reports and have the accepted specialization 
	 * in sorted treeSet by the number of shifts*/
	public TreeSet<Doctor> getDoctorBySpec(Specialization s) {
		TreeSet<Doctor> doctorsBySpec = new TreeSet<Doctor>(new Comparator<Doctor>() {
			@Override
			public int compare (Doctor o1, Doctor o2) {
				Integer o1NumShifts = o1.getShiftCounter();
				Integer o2NumShifts = o2.getShiftCounter();
				return -1*(o1NumShifts.compareTo(o2NumShifts));
			}
		});
		for (PatientReport pr : reports.values()) {
			if (pr.getDoctor().getSpec().equals(s))
				doctorsBySpec.add(pr.getDoctor());
		}

		return doctorsBySpec;
	}
	
	
	//This method returns all the disease that starts at the letter range that sent to the method
	public TreeSet<Disease> getDiseasesByRange(char start, char end) {
		TreeSet<Disease> diseaseByRange = new TreeSet<Disease>(new Comparator<Disease>() {
			@Override
			public int compare (Disease o1, Disease o2) {
				return -1*(o1.getName().compareTo(o2.getName()));
			}
		});
		
		for (Disease d : diseases.values()) {
			if (d.getName().charAt(0)>=Character.toUpperCase(start)  &&  d.getName().charAt(0)<=Character.toUpperCase(end))
				diseaseByRange.add(d);
		}
		
		return diseaseByRange;
	}
	
	
	//This method returns all the department's patients that have difficulty breathing and treated by breathing support nurse
	public LinkedList<Patient> getAllDifficultBreathingPatients(Department d) {
		LinkedList<Patient> patients = new LinkedList<>();
		for(SubDepartment s : d.getSdepts()) {
			for(Patient p : s.getPatients()) {
				if(p.getDisease().getSymptoms().contains(Symptoms.DIFFICULTY_BREATHING) 
						&& getNursesByPatient().containsKey(p))
					for(Nurse n : getNursesByPatient().get(p)) {
						if(n.getTreat().equals(Treatments.BREATHING_SUPPORT)) {
							patients.add(p);
							break;
						}
					}
			}
		}
		patients.sort(new Comparator<Patient>() {

			@Override
			public int compare(Patient o1, Patient o2) {
				if(o1.getSd().getId() != o2.getSd().getId()) {
					Integer o1SubID = o1.getSd().getId();
					Integer o2SubID = o2.getSd().getId();
					return o2SubID.compareTo(o1SubID);
				}
				else
					return o2.getLname().compareTo(o1.getLname());
			}
		});
		return patients;
	}
	
	
	/*The method returns sorted TreeSet of patient that have a popular disease after activate the check disease method 
	 * by all the doctors that in the department on all the patient in the department*/
	public TreeSet<Patient> treatDiseases(Department d) {
		TreeSet<Patient> toReturn = new TreeSet<Patient>(new Comparator<Patient>() {
			@Override
			public int compare (Patient o1, Patient o2) {
				return o1.compareTo(o2);
			}
		});
		
		int viralCounter=0, chronicCounter=0;
		for (SubDepartment sd : d.getSdepts()) {
			Iterator<Patient> pIterator = sd.getPatients().iterator();
			Iterator<Doctor> dIterator = sd.getDoctors().iterator();
			while (pIterator.hasNext()) {
				if (!dIterator.hasNext())
					dIterator = sd.getDoctors().iterator();
				Doctor doc = dIterator.next();
				Patient p = pIterator.next();
				doc.checkDisease(p);
				
				if (p.getDisease().getClass().equals(ViralDisease.class))
					viralCounter++;
				if (p.getDisease().getClass().equals(ChronicDisease.class))
					chronicCounter++;
			}
		}
		
		if (viralCounter > chronicCounter) {
			for (SubDepartment sd : d.getSdepts()) {
				for (Patient p : sd.getPatients()) {
					if (p.getDisease().getClass().equals(ViralDisease.class))
						toReturn.add(p);
				}
			}
		}
		else {
			for (SubDepartment sd : d.getSdepts()) {
				for (Patient p : sd.getPatients()) {
					if (p.getDisease().getClass().equals(ChronicDisease.class))
						toReturn.add(p);
				}
			}
		}
		
		return toReturn;
	}
	
	
	/*The method returns sorted TreeSet of patient that their condition changed due to activation of the check patient method by
	 * all the nurses in the department on all the patient that in the department*/ 
	public TreeSet<Patient> treatPatients(Department d) {
		TreeSet<Patient> toReturn = new TreeSet<Patient>(new Comparator<Patient>() {
			@Override
			public int compare (Patient o1, Patient o2) {
				Integer o1Id = o1.getId();
				Integer o2Id = o2.getId();
				return o1Id.compareTo(o2Id);
			}
		});
		
		for (SubDepartment sd: d.getSdepts()) {
			Iterator<Patient> pIterator = sd.getPatients().iterator();
			Iterator<Nurse> nIterator = sd.getNurses().iterator();
			while (pIterator.hasNext()) {
				if (nIterator.hasNext())
					nIterator = sd.getNurses().iterator();
				Patient p = pIterator.next();
				if(nIterator.hasNext()) {//Agat added 
					Nurse n = nIterator.next();
					Condition before = p.getCondition();
					n.checkPatient(p);
					Condition after = p.getCondition();
					if (before != after)
						toReturn.add(p);
				}
			}
		}
		return toReturn;
	}
	
	
	//deserialize
	public static void readFile() {
		try {
			FileInputStream file = new FileInputStream("Hospital.ser");
			ObjectInputStream in = new ObjectInputStream(file);
			theHospital = (Hospital) in.readObject();
			in.close();
			file.close();
			System.out.println("file found");
		} catch (FileNotFoundException e1) {
			System.out.println("file not found");
			theHospital = new Hospital();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		}
	}
	
}
