package exceptions;

// quand le nom d'equipe est incorrecte dans EnterTeamsFP

public class InvalidTeamNameException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidTeamNameException(String message) {
		super("This username is invalide, the username must have between 2 to 15 characters");
	}
}
