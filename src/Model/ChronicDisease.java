package Model;

import java.io.Serializable;
import java.util.HashSet;
import Utils.Symptoms;

//This class describes a chronic disease, and it extends the disease class
public class ChronicDisease extends Disease implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isGenetic;

	//Constructor
	public ChronicDisease(int id, String name, HashSet<Symptoms> symptoms, boolean isGenetic) {
		super(id, name, symptoms);
		this.isGenetic = isGenetic;
	}


	//Getters and Setters
	public boolean isGenetic() {
		return isGenetic;
	}

	public void setGenetic(boolean isGenetic) {
		this.isGenetic = isGenetic;
	}
	
}
