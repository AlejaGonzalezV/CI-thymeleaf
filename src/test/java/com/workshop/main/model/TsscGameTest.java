package com.workshop.main.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.services.TsscGameServiceImp;
import com.workshop.main.services.TsscTopicServiceImp;

@SpringBootTest
class TsscGameTest {

	@Autowired
	private TsscTopicServiceImp topicServiceImp;
	@Autowired
	private TsscGameServiceImp gameServ;

	@BeforeAll
	public static void beforeTest() {
		System.out.println("-----> SETUP <-----");
	}

	@AfterAll
	public static void afterTest() {
		System.out.println("-----> FINISH <-----");
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGame2() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(1);
		tema.setDefaultSprints(1);

		topicServiceImp.addTopic(tema);
		gameServ.addGame2(g, tema.getId());

		assertTrue(gameServ.existById(g.getId()));

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGame2Fail() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(0);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(1);
		tema.setDefaultSprints(1);

		topicServiceImp.addTopic(tema);
		
		assertNull(gameServ.addGame2(g, tema.getId()));
		assertFalse(gameServ.existById(g.getId()));

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGame2Fail2() {

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(1);
		tema.setDefaultSprints(1);

		topicServiceImp.addTopic(tema);
		
		assertNull(gameServ.addGame2(null, tema.getId()));

	}


	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGameT() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);

		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(1);
		top.setDefaultSprints(1);

		topicServiceImp.addTopic(top);

		assertNotNull(gameServ.addGameT(g, top.getId()));

	}


	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGameTFail() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);

		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(0);
		top.setDefaultSprints(1);

		topicServiceImp.addTopic(top);

		assertNull(gameServ.addGameT(g, top.getId()));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGame() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);

		assertNotNull(gameServ.addGame(g));

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGameFail() {

		assertNull(gameServ.addGame(null));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSetGameFail() {

		TsscGame g = new TsscGame();
		String name = "Name";
		g.setNGroups(0);
		g.setNSprints(1);
		

		assertNull(gameServ.setGame(g, 2, name));


	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSetGame() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);

		gameServ.addGame(g);
		gameServ.setGame(g, 2, "Historia");

		assertEquals(gameServ.findGame(g.getId()).getName(), "Historia");
		assertEquals(g.getNGroups(), 2);

	}
}
