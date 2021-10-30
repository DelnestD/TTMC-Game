package exceptions;

public class InvalidPointsToWinException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidPointsToWinException(int message) {
		super("cannot have a negative score");
	}
}
