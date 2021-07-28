package Exceptions;

//The exception happen when trying set status to patient with negative or over 100 status number
public class StatusOutOfRangeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatusOutOfRangeException(int status) {
		super("The Status " + status + " is Not In Range Of 0 To 100");
	}
	
}
