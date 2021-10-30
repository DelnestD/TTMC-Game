package models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import enums.Theme;

public class BasicCard {
	
	//variables
	private String author;
	private Theme theme;
	private String subject;
	private List<Question> questions;
	
	//constructor
	public BasicCard(String author,Theme theme,String subject) {
		this.setAuthor(author);
		this.setTheme(theme);
		this.setSubject(subject);
		questions = new ArrayList<Question>();
	}
	
	public BasicCard(String author,Theme theme,String subject,List<Question>listQuestions) {
		this.setAuthor(author);
		this.setTheme(theme);
		this.setSubject(subject);
		this.setQuestions(listQuestions);
	}
	
	//setter getter
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author.toUpperCase();
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		if(!questions.isEmpty()) {
			this.questions = questions;
		}
	}
	
	//function for game
	public void addQuestion(Question q) {
		//if question exist
		if(q != null) {
			//if question have same theme, subject, author as the card
			if(sameTypeQuestion(q)) {
				//if card didn't have any question we add it
				if(questions.isEmpty()) {
					questions.add(q.clone());
				//else if the question isn't already on the card, add it
				}else if(!questions.contains(q)) {
					questions.add(q.clone());
				}
			}
		}
	}
	public void removeQuestion(Question q) {
		questions.remove(q);
	}
	public boolean sameTypeQuestion(Question q) {
		//if author of question is the same as author of card
		if(author.equals(q.getAuthor())) {
			//if subject of question is the same as subject of card
			if(subject.equals(q.getSubject())) {
				//if theme of question is the same as theme of card
				if(theme.equals(q.getTheme())) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	

	public Question getQuestion(int level) {
		//return question of the card by level
		return questions.get(level).clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
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
		BasicCard other = (BasicCard) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (theme != other.theme)
			return false;
		return true;
	}

	public BasicCard clone(){
		return new BasicCard(author,theme,subject,questions);
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(Question q : questions) {
			sb.append(q.toString()).append("\n");
		}
		return sb.toString();
	}
	//function linked to serialisation
	public String toJson() {
		return new Gson().toJson(this);
	}
		
	public static BasicCard fromJson(String json) {
		return new Gson().fromJson(json, BasicCard.class);
	}
}
