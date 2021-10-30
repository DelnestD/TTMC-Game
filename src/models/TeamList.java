package models;


import exceptions.DoublonException;
import interfaces.Iterator;
import interfaces.List;

public class TeamList implements List<Team>{
	private int numberTeam = 0;
	private static int numberTeamMax = 2;
	private Team[] teams;
	
	public TeamList(int i) {
		numberTeamMax = i;
		teams = new Team[numberTeamMax];
	}

	public TeamList(Team[] teams) {
		this.teams = teams;
	}
	@Override
	public Iterator<Team> iterator() {
		// TODO Auto-generated method stub
		return new TeamIterator(teams);
	}
	
	public void addItem(Team team) throws DoublonException {
		
		if(numberTeam<numberTeamMax) {
			if (numberTeam>0 && contains(team)) {
				throw new DoublonException(team);
			}else {
			teams[numberTeam] = team;
			numberTeam++;
			}
		}
	}
	
	public Team get(int i) {
		return teams[i];
	}

	public boolean contains(Team team) {
		// TODO Auto-generated method stub
		for(int i=0;i<numberTeam;i++) {
			if(teams[i].equals(team))return true;
		}			
		return false;
	}
	
	public int size() {
		return teams.length;
	}
	
	public Team[] getTeams() {
		return teams;
	}
}
