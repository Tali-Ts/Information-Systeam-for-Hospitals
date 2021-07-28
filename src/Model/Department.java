package Model;

import java.io.Serializable;
import java.util.ArrayList;
import Utils.Specialization;

//The class describes all the hospital's departments
public class Department implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String deptName;
	private ArrayList<SubDepartment> sdepts;
	private Specialization spec;
	
	
	//Constructors
	public Department(String deptName, Specialization spec) {
		super();
		this.id = Hospital.getInstance().getDepartmentID();
		Hospital.getInstance().setDepartmentID(id+1);
		this.deptName = deptName;
		this.spec = spec;
		this.sdepts = new ArrayList<SubDepartment>();
		}

	 Department(int id) {
		this.id = id;
	}
	 
	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public String getDeptName() {
		return deptName;
	}

	public ArrayList<SubDepartment> getSdepts() {
		return sdepts;
	}

	public Specialization getSpec() {
		return spec;
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
		Department other = (Department) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	//toString
	@Override
	public String toString() {
		return "Department [deptName=" + deptName + ", spec=" + spec + "]";
	}

	
	//The method adds sub-department to its array-list if it not already exists there
	public boolean addSubDepartment(SubDepartment s) {
		if (getSdepts().contains(s))
			return false;
		else {
			getSdepts().add(s);
			Hospital.getInstance().getSubDepartment().put(s.getId(), s);
		}
		return true;		
	}

	//The method removes sub-department if it exists it its array-list
	public boolean removeSubDepartment(SubDepartment s) {
		if (!getSdepts().contains(s))
			return false;
		else
			getSdepts().remove(s);
		return true;		
	}
	
	//The method prints all the doctors that relate to this department
	public String printAllDoctors()
	{
		String toReturn = "All doctors for "+this +"\n";
		for (SubDepartment s : getSdepts() ) {
			for(Doctor d : s.getDoctors())
				toReturn += d.toStringLong() + "\n";
		}
		return toReturn;
	}
	
	//The method prints all the nurses that relate to this department
	public String printAllNurses()
	{
		String toReturn = "All nurses for "+this +"\n";
		for (SubDepartment s : getSdepts() ) {
			for(Nurse n : s.getNurses())
				toReturn += n.toStringLong() + "\n";
		}
		return toReturn;
	}
	
	//The method prints all the patients that relate to this department
	public String printAllPatients()
	{
		String toReturn = "All patients for "+this +"\n";
		for (SubDepartment s : getSdepts() ) {
			for(Patient p : s.getPatients())
				toReturn += p.toStringLong() + "\n";
		}
		return toReturn;
	}
	
	
	
	//This method adds all the values of the data bases from sub-department A to the data bases in sub-department B
	public void moveSubDepartment(SubDepartment a, SubDepartment b) {
		b.getDoctors().addAll(a.getDoctors());
		b.getNurses().addAll(a.getNurses());
		b.getPatients().addAll(a.getPatients());
		b.getReports().addAll(a.getReports());
		
		for (Doctor d : b.getDoctors())
			d.setSd(b);
		for (Nurse n : b.getNurses())
			n.setSd(b);
		for (Patient p : b.getPatients())
			p.setSd(b);
		for (PatientReport pr : b.getReports())
			pr.setSdept(b);
	}
}
