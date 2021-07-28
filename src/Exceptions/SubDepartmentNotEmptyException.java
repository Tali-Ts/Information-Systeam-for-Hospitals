package Exceptions;

//The exception happen when trying to delete sub-department which doesn't empty
public class SubDepartmentNotEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubDepartmentNotEmptyException(int id) {
		super("Cannot Delete SubDepartment With ID: " + id + " Beacuse Its Not Empty");
	}

}
