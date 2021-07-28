package Exceptions;

import Utils.ReleaseNote;

//The exception happen when trying remove patient from the hospital or the hotel without the correct release note 
public class CannotReleasePatientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CannotReleasePatientException(ReleaseNote note, ReleaseNote proper) {
		super("Cannot Release This Patient Because  He Have The Note: " + note + " And Not The Proper Release Note: " + proper);
	}

}
