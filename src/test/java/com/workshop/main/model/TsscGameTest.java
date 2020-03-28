package com.workshop.main.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.workshop.main.services.TsscGameServiceImp;
import com.workshop.main.services.TsscTopicServiceImp;

@SpringBootTest
class TsscGameTest {

	@Autowired
	private TsscTopicServiceImp topicServiceImp;
	@Autowired
	private TsscGameServiceImp storyImp;

	@BeforeAll
	public static void beforeTest() {
		System.out.println("-----> SETUP <-----");
	}

	@AfterAll
	public static void afterTest() {
		System.out.println("-----> FINISH <-----");
	}

	@Test
	void testAddGame2GroupMenor1() {

		TsscGame g = new TsscGame();
		g.setNGroups(0);
		g.setNSprints(4);

		assertNull(storyImp.addGame2(g, 2));

	}
	
	@Test
	void testAddGame2SprintsMenor1() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(0);

		assertNull(storyImp.addGame2(g, 2));

	}
	
	@Test
	void testAddGameTopic2() {

		TsscGame g = new TsscGame();
		// duda
		g.setNGroups(1);
		g.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(1);
		tema.setDefaultSprints(1);

		topicServiceImp.addTopic(tema);

		assertNotNull(storyImp.addGame2(g, tema.getId()));

	}
	
	
	@Test
	void testAddGameGroupMenor1() {

		TsscGame g = new TsscGame();
		g.setNGroups(0);
		g.setNSprints(1);

		assertNull(storyImp.addGameT(g, 2));

	}

	@Test
	void testAddGameSprintsMenor1() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(0);

		assertNull(storyImp.addGameT(g, 2));

	}

	@Test
	void testAddGameT() {

		TsscGame g = new TsscGame();
		// duda
		g.setNGroups(1);
		g.setNSprints(1);

		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(1);
		top.setDefaultSprints(1);

		topicServiceImp.addTopic(top);

		assertNotNull(storyImp.addGameT(g, top.getId()));

	}

	@Test
	void testAddGameTFail() {

		TsscGame g = new TsscGame();
		// duda
		g.setNGroups(1);
		g.setNSprints(1);

		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(0);
		top.setDefaultSprints(1);

		topicServiceImp.addTopic(top);

		assertNotNull(storyImp.addGameT(g, top.getId()));

	}

	@Test
	void testAddGame() {

		TsscGame g = new TsscGame();
		// duda
		g.setNGroups(1);
		g.setNSprints(1);

		assertNotNull(storyImp.addGame(g));

	}

	
	@Test
	void testSetGameGroupMenor1() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		g.setName("Cambiando Nombre");
		storyImp.addGame(g);

		assertNull(storyImp.setGame(g, 0, "Historia"));
	

	}
	
	@Test
	void testSetGameNoName() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		g.setName("Cambiando Nombre");
		storyImp.addGame(g);

		assertNull(storyImp.setGame(g, 2, ""));
	

	}
	
	@Test
	void testSetGameNullName() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		g.setName("Cambiando Nombre");
		storyImp.addGame(g);

		assertNull(storyImp.setGame(g, 2, null));
	

	}
	

	@Test
	void testSetGame() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		g.setName("Cambiando Nombre");
		storyImp.addGame(g);

		storyImp.setGame(g, 2, "Historia");

		assertEquals("Historia", storyImp.findGame(g.getId()).getName());
		assertEquals(2, g.getNGroups());

	}
}
