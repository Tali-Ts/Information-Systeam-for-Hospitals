package Model;

import java.io.Serializable;
import java.util.HashSet;

import Exceptions.DiseaseWithNoSymptomsException;
import Utils.Symptoms;

//The class describes a disease
public class Disease implements Comparable<Disease>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private HashSet<Symptoms> symptoms;

	
	//Constructors
	public Disease(String name, HashSet<Symptoms> symptoms) {
		super();
		this.id = Hospital.getInstance().getDiseaseID();
		Hospital.getInstance().setDiseaseID(id+1);
		this.name = name;
		this.symptoms = symptoms;
	}

	public Disease(int id) {
		this.id = id;
	}

	public Disease(int id, String name, HashSet<Symptoms> symptoms) {
		super();
		this.id = id;
		this.name = name;
		this.symptoms = symptoms;
	}

	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public HashSet<Symptoms> getSymptoms() {
		return symptoms;
	}

	/*The method checks if the HashSet that sent is empty, if it is the method throw an exception and returns a massage,
	 * else the method sets it to disease's symptoms HashSet*/
	public String setSymptoms(HashSet<Symptoms> symptoms) {
		try {
			if (symptoms.isEmpty()) 
				throw new DiseaseWithNoSymptomsException();
			else {
				this.symptoms = symptoms;
				return "Success";
			}
		} 
		
		catch (DiseaseWithNoSymptomsException e) {
			return e.toString();
		}
	}

	
	//toString
	@Override
	public String toString() {
	return name; 
	}
	
	public String toStringLong() {
		return "Disease [name=" + name + ", symptoms=" + symptoms + "]";
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
		Disease other = (Disease) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	//CompareTo between two diseases by their names and return the results in descending order
	@Override
	public int compareTo(Disease other) {
		return -1*(this.name.compareTo(other.name));
	}
	
}
