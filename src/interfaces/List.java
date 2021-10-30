package interfaces;

import models.Team;

public interface List<E> {
	Iterator<E> iterator();

	boolean contains(Team team);
}
