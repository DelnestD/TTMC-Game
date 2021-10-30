package models;

import exceptions.InvalidPointsToWinException;
import exceptions.InvalidTeamNameException;

public class Team {
	
	//variables
	private String name;
	private int score;
	
	//constructor
	public Team(String name) throws InvalidTeamNameException, InvalidPointsToWinException{
		this.setName(name);
		this.setScore(0);
	}

	//setter getter
	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidTeamNameException{
		if (name.length()<= 2 || name.length()>=16) {
			throw new InvalidTeamNameException(name);
		}
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) throws InvalidPointsToWinException {
		if(score<0) {
			throw new InvalidPointsToWinException(score);
		}
		this.score = score;
	}
	
	//function

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void addPoints(int Points) {
		int sc = this.score + Points;
		try {
			setScore(sc);
		} catch (InvalidPointsToWinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
