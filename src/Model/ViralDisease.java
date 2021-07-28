package Model;
import java.io.Serializable;
import java.util.HashSet;
import Model.Disease;
import Utils.Symptoms;

//This class describes a viral disease, and it extends the disease class
public class ViralDisease extends Disease implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isContagious;

	
	//Constructors
	public ViralDisease(int id, String name, HashSet<Symptoms> symptoms, boolean isContagious) {
		super(id, name, symptoms);
		this.isContagious = isContagious;
	}


	//Getters and Setters
	public boolean isContagious() {
		return isContagious;
	}

	public void setContagious(boolean isContagious) {
		this.isContagious = isContagious;
	}
	
}
