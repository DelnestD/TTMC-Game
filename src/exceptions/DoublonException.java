package exceptions;

import models.Team;

public class DoublonException extends Exception{

	
	private static final long serialVersionUID = 1L;
	

	public DoublonException(Team team) {
		super("La personne suivante est déjà présente dans le répertoire : " + team.getName());
	}
}
