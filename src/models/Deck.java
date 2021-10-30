package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	//variables
	private List<BasicCard> cards;
	
	//constructor
	public Deck() {
		cards = new ArrayList<BasicCard>();
	}

	public Deck(List<BasicCard> cards) {
		this.setCards(cards);
	}
	
	//setter getter
	public List<BasicCard> getCards() {
		return cards;
	}
	
	public void setCards(List<BasicCard> cards) {
		this.cards = cards;
	}
	
	public BasicCard getSelectedCard(int id) {
		return cards.get(id);
	}
	
	//function
	public Deck clone() {
		return new Deck(cards);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
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
		Deck other = (Deck) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (BasicCard bc : cards) {
			sb.append("Auteur : ").append(bc.getAuthor()).append(" Theme : ").append(bc.getTheme()).append(" Sujet : ").append(bc.getSubject()).append("\nQuestions :\n").append(bc.toString()).append("\n");
		}
		return sb.toString();
	}
	
	//function for game
	public boolean addCard(BasicCard card) {
		//if card is already in deck we don't add it
		if(!cards.contains(card)) {
			return cards.add(card.clone());
		}
		return false;
	}
	
	// pour verifier s'il reste des cartes
	public boolean deckIsEmpty() {
		return cards.isEmpty();
	}
	
	public void shuffle() {
		//shortcut for shuffle the deck
		Collections.shuffle(cards);
	}
	
	public boolean deleteCard(BasicCard card) {
		return cards.remove(card);
	}
	
	public boolean contains(BasicCard card) {
		return cards.contains(card);
	}
	
	public BasicCard topCard() {
		//draw the first card of the deck
		return cards.get(0).clone();
	}
	
	
	
	
	
	
	
	
}
