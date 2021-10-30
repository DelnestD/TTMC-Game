package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exceptions.DoublonException;
import exceptions.InvalidPointsToWinException;
import exceptions.InvalidTeamNameException;
import models.BasicCard;
import models.Deck;
import models.Game;
import models.Question;
import models.Team;
import models.TeamList;


class GameTest {
	
	private Game game;
	private TeamList liste;
	private List<Question> listQ;
	private Question question;
	BasicCard cardInUse;
	Deck deck;
	
	@BeforeEach
	public void setUp() throws Exception {
	game = new Game(4);
	liste = (TeamList)Explorateur.getField(game, "listTeams");
	}

	@AfterEach
	public void tearDown() throws Exception {
	game = null;
	liste = null;
	listQ = null;
	}
	
	@Test
	@DisplayName("Vérifie que le constructeur fonctionne correctement")
	void testGame() {
		Game ag = new Game(4);
		assertNotNull(ag);
		TeamList liste = (TeamList)Explorateur.getField(ag, "listTeams");
		assertNotNull(liste);
	}

	@Test
	void testAddTeam() throws InvalidTeamNameException, InvalidPointsToWinException {
		Team p = new Team("Gof");
		Assertions.assertDoesNotThrow(() -> game.addTeam(p),"pas d'exception");
		assertTrue(liste.contains(p),"ajout normal");
		assertEquals(4, liste.size());
	}
	
	@Test
	public void testAjouterDoublon() throws DoublonException, InvalidTeamNameException, InvalidPointsToWinException {
	Team p = new Team("Gos");
	liste.addItem(p);
	Team p1 = new Team("Gos");
	Assertions.assertThrows(DoublonException.class, () -> game.addTeam(p1));
	}
	
	@Test
	void testGetNbrTeams() throws InvalidTeamNameException, InvalidPointsToWinException, DoublonException {
		Team p = new Team("Gof");
		liste.addItem(p);
		assertEquals(4,game.getNbrTeams());

	}

	@Test
	void testGetTeamPlaying() {
		assertEquals(0, game.getTeamPlaying());
	}

	@Test
	void testGetTour() {
		assertEquals(0, game.getTour());
	}

	@Test
	void testNextTour() {
		assertEquals(0, game.getTour());
		game.nextTour();
		assertEquals(1, game.getTour());
	}

	@Test
	void testGetLevel() {
		game.setLevel(1);
		assertEquals(2, game.getLevel() );
	}

	@Test
	void testGetTheme() {
		String the = game.getTheme();
		assertEquals(the, game.getTheme());
	}
	
	@Test
	void testGetDeck() {
		Deck de = game.getDeck();
		assertEquals(de, game.getDeck());
	}
	
	@Test
	void testGetCardInUse() {
		BasicCard car = game.getCardInUse();
		assertEquals(car, game.getCardInUse());
	}

	@Test
	void testGetQuestionInUse() {
		Question que = game.getQuestionInUse();
		assertEquals(que, game.getQuestionInUse());
	}
	
	@Test
	void testGetListTeams() {
		TeamList lis = game.getListTeams();
		assertEquals(lis, game.getListTeams());
	}
	
	@Test
	void testGetListQuestions() {
		List<Question> ques = game.getListQuestions();
		assertEquals(ques, game.getQuestionInUse());
	}
	


}
