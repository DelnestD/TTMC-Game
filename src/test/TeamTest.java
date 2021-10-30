package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidPointsToWinException;
import exceptions.InvalidTeamNameException;
import models.Team;

class TeamTest {
	
	@SuppressWarnings("unused")
	private Team tz;
	
	@BeforeEach
	public void setUp() throws Exception {
	tz = new Team("ses");
	}
	
	@AfterEach
	public void tearDown() throws Exception {
	tz = null;
	}

	@Test
	void testTeamString() throws InvalidTeamNameException, InvalidPointsToWinException {
		Team ag = new Team("name");
		assertNotNull(ag);
	}
	
	@Test
	void testGetName() throws InvalidTeamNameException, InvalidPointsToWinException {
		Team tz = new Team("ses");
		assertEquals("ses", tz.getName());
		Team te = new Team("sse");
		Assertions.assertThrows(InvalidTeamNameException.class, () -> te.setName("se"));
	}

	@Test
	void testGetScore() throws InvalidTeamNameException, InvalidPointsToWinException {
		Team tz = new Team("ses");
		tz.setScore(0);
		assertEquals(0, tz.getScore());
		Assertions.assertThrows(InvalidPointsToWinException.class, () -> tz.setScore(-1));
	}

}
