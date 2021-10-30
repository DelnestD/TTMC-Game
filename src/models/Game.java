package models;

import java.util.List;
import exceptions.DoublonException;
import exceptions.InvalidTeamNameException;
import serialisation.Serialisation;

public class Game {
	
	//variable
	private TeamList listTeams;
	private interfaces.Iterator<Team> iteratorTeams ;
	private static List<Question> listQuestions;
	private int level;
	private BasicCard cardInUse;
	private Question questionInUse;
	private static Deck deck;
	private static int counterTour = 0;
	
	//constructor
	public Game(int nbTeams) {
		listTeams = new TeamList(nbTeams);
		iteratorTeams = listTeams.iterator();
		level = 1;
		initDeck();
	}
	
	public Game(Team[] teams,List<Question> listQuestions,BasicCard cardInUse,Question questionInUse,Deck deck) {
		listTeams = new TeamList(teams);
		iteratorTeams = listTeams.iterator();
		this.setListQuestions(listQuestions);
		this.setCardInUse(cardInUse);
		this.setQuestionInUse(questionInUse);
		this.setDeck(deck);
	}
	
	public TeamList getListTeams() {
		return listTeams;
	}

	public interfaces.Iterator<Team> getIteratorTeams() {
		return iteratorTeams;
	}

	public void addTeam(Team team) throws DoublonException, InvalidTeamNameException {
			listTeams.addItem(team);
	}
	
	//get the number of team
	public int getNbrTeams() { 
		return listTeams.size();
	}
	
	// get the name of the team with the position in the list
	public String getTeamName(int posTeam) {
		if (listTeams.size()==0){
			return "";
		}
		return listTeams.get(posTeam).getName();
	}	
	
	//get the poistion of the team playing
	public int getTeamPlaying() {
		
		if (counterTour <= listTeams.size()-1) {
			return counterTour;
		}else if (listTeams.size() == 4) { 
			return counterTour%4;
		}else if (listTeams.size() == 3) {
			return counterTour%3;
		}else if (listTeams.size() == 2) {
			return counterTour%2;
		}
		
		return -1;
	}
	
	//increase the score of a team
	public void increaseTeamScore(int posTeam,int Score) {
		listTeams.get(posTeam).addPoints(Score);
	}
	
	public int getScoreTeam(int posTeam) {
		if (listTeams.size()==0){
			return 0;
		}		
		return (listTeams.get(posTeam).getScore());
	}
	
	// retourne le score d'une equipe sous forme de String
		public String getScoreTeamByPos(int posTeam) {
			if (listTeams.size()==0){
				return null;
			}		
			return String.valueOf(listTeams.get(posTeam).getScore());
		}
	
	//Sort the list of all the team playing by their scores
	public String sortTeamsByScore() {
		StringBuilder sb = new StringBuilder();
		while(iteratorTeams.hasNext()) {
            Team currentTeam= iteratorTeams.next();
            sb.append(currentTeam.getName()).append(" has ").append(currentTeam.getScore()).append(" points.\n");
        }
		return sb.toString();
	}
	
	public int getTour() {
		return counterTour;
	}
	
	public void nextTour() {
		counterTour++;
	}	
	
	public int getLevel() { 
		return level + 1;
	}	
	
	public Game clone() {
		return new Game(listTeams.getTeams(),listQuestions,cardInUse,questionInUse,deck);
	}

	public void shuffleDeck() {
		deck.deleteCard(deck.topCard());
		if (deck.deckIsEmpty()) {
			initDeck();
		}
	}
	
	public String getTheme() {
		//draw the first card of the deck
		cardInUse = deck.topCard();
		//return theme
		return cardInUse.getTheme().toString();
	}
	
	public String getSubject() {
		//return subject
		return cardInUse.getSubject().toString();
	}
	
	public String getQuestion() {
		//get the question choose by user with de level
		questionInUse = cardInUse.getQuestion(level);
		//return the question
		return questionInUse.getChallenge();
	}
	
	//test if the answer is correct
	public String getReponse() {
		return questionInUse.getAnswer();
	}
	
	//ignore special character
	public boolean testAnswer(String answerUser) {
	
		String answerUserSimplified = answerUser.replaceAll("[ ,'-./]", "");
		String answerCorrectSimplified = questionInUse.getAnswer().replaceAll("[ ,'-./]", "");
				
		return answerUserSimplified.equalsIgnoreCase(answerCorrectSimplified);
	}
	
	
	public static Deck initDeck() {
		deck = new Deck(Serialisation.read());
		deck.shuffle();
		return deck;
	}	
	

	//setter getter

	public List<Question> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(List<Question> listQuestions) {
		Game.listQuestions = listQuestions;
	}
	
	public BasicCard getCardInUse() {
		return cardInUse;
	}
	public void setCardInUse(BasicCard cardInUse) {
		this.cardInUse = cardInUse;
	}
	public Question getQuestionInUse() {
		return questionInUse;
	}
	public void setQuestionInUse(Question questionInUse) {
		this.questionInUse = questionInUse;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		Game.deck = deck;
	}

	public void setLevel(int level) {
		this.level = level;
	}


}
