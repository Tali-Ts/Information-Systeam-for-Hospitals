package Utils;

import java.io.Serializable;

public enum Condition implements Serializable{
	
	GOOD(80),FAIR(60),SERIOUS(40),CRITICAL(10);




	private final int value;
	
private Condition (int value) {
	 this.value = value;	
}

/**
 * @return the value
 */
public int getValue() {
	return value;
}
	
}