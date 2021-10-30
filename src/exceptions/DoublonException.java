package exceptions;

import models.Team;

public class DoublonException extends Exception{

	
	private static final long serialVersionUID = 1L;
	

	public DoublonException(Team team) {
		super("La personne suivante est d�j� pr�sente dans le r�pertoire : " + team.getName());
	}
}
