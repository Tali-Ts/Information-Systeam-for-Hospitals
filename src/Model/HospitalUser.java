package Model;

import java.io.Serializable;

//The class describes all the people in the hospital
public abstract class HospitalUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String fname;
	private String lname;
	private SubDepartment sd;
	
	
	//Constructors
	public HospitalUser(int idCount, String fname, String lname, SubDepartment sd) {
		super();
		this.id = idCount;
		this.fname = fname;
		this.lname = lname;
		this.sd = sd;
	}

	public HospitalUser(int idCount) {
		super();
		this.id = idCount;
	}
	

	//Getters and Setters
	public int getId() {
		return id;
	}
	
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public SubDepartment getSd() {
		return sd;
	}
	
	public void setSd(SubDepartment sd) {
		this.sd = sd;
	}
	
	
	//toString
	public String toString()
	{
		return String.format("%s %s", getFname(),getLname());
	}
	
	
	//hashCose and equals
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
		HospitalUser other = (HospitalUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
