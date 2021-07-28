package Exceptions;

//The exception happen when trying to create new disease without symptoms
public class DiseaseWithNoSymptomsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DiseaseWithNoSymptomsException() {
		super("This Disease Has No Symptoms");
	}

}
