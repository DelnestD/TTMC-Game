package models;

import enums.Theme;

public class Question {
	
	//variable
	/**
	 * 
	 */
	private String author;
	private Theme theme;
	private String subject, challenge, answer;
	
	//contructor
	public Question(String author,Theme theme,String subject,String challenge,String answer) {
		this.setAuthor(author);
		this.setTheme(theme);
		this.setSubject(subject);
		this.setChallenge(challenge);
		this.setAnswer(answer);
	}

	//getter setter
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

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((challenge == null) ? 0 : challenge.hashCode());
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
		Question other = (Question) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (challenge == null) {
			if (other.challenge != null)
				return false;
		} else if (!challenge.equals(other.challenge))
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

	public Question clone() {
		return new Question(author,theme,subject,challenge,answer);
	}
	
	public String toString() {
		return challenge+" "+answer;
	}
}
