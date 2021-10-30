package models;

import interfaces.Iterator;

public class TeamIterator implements Iterator<Team>{

	private Team[] teams;
	private int position;
	
	public TeamIterator(Team[] teams) {
		this.teams = teams;
		position = 0;
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		position = 0;
	}

	@Override
	public Team next() {
		// TODO Auto-generated method stub
		return teams[position++];
	}

	@Override
	public Team currentItem() {
		// TODO Auto-generated method stub
		return teams[position];
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(position >= teams.length) {
			return false;
		}
		return true;
	}

}
