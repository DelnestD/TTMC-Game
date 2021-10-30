package models;

import java.util.List;

import serialisation.Serialisation;

public class Admin {
	private List <Question> listQuestion;
	private BasicCard bc;
	private Deck deck;
	private boolean addCard;
	public Admin() {
		initDeck();
		addCard=true;
	}
	private Admin(List<Question> listQuestions,BasicCard cardInUse,Deck deck,boolean addCard) {
		this.setListQuestion(listQuestions);
		this.setBasicCard(cardInUse);
		this.setAddCard(addCard);
		this.setDeck(deck);
	}
	
	public Deck initDeck() {
		deck = new Deck(Serialisation.read());	
		return deck;
	}
	
	public List<Question> getListQuestion() {
		return listQuestion;
	}
	public void setListQuestion(List<Question> listQuestion) {
		this.listQuestion = listQuestion;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public List<BasicCard> getCards(){
		return deck.getCards();
	}
	
	public void setBasicCard(BasicCard bc) {
		this.bc=bc;
	}
	
	public BasicCard getBasicCard() {
		return bc;
	}

	public boolean isAddCard() {
		return addCard;
	}

	public void setAddCard(boolean addCard) {
		this.addCard = addCard;
	}

	public BasicCard getSelectedCard(int id) {
		return deck.getSelectedCard(id);
	}
	
	//Add card to Deck and json file
	public boolean addCard(BasicCard bcAdd) {
		//if we can add to deck so we can add to the end of file 
		if(deck.addCard(bcAdd)) {
			//add to file with serialization
			Serialisation.writeNew(bcAdd);
			return true;
		}
		return false;
	}
	//remove card to Deck and json file
	public boolean removeCard() {
		//if we can remove from deck so we can remove from the file 
		if(deck.deleteCard(bc.clone())) {
			Serialisation.writeAll(deck.getCards());
			return true;
		}
		return false;
	}
	
	public boolean modifyCard(BasicCard bcNew) {
		deck.deleteCard(bc);
		deck.addCard(bcNew);
		Serialisation.writeAll(deck.getCards());
		return true;
	}
	
	
	
	public Admin clone() {
		return new Admin(listQuestion,bc,deck,addCard);
	}
}
