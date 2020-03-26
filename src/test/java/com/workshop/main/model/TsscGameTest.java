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
	void testAnadirGame2GrupoMenorAUno() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(0);
		gameOne.setNSprints(1);

		assertNull(storyImp.addGame2(gameOne, 2));

	}
	
	@Test
	void testAnadirGame2SprintsMenorAUno() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(0);

		assertNull(storyImp.addGame2(gameOne, 2));

	}
	
	@Test
	void testAnadirGameConTemaJuego2() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(1);
		tema.setDefaultSprints(1);

		topicServiceImp.addTopic(tema);

		assertNotNull(storyImp.addGame2(gameOne, tema.getId()));

	}
	
	
	@Test
	void testAnadirGameGrupoMenorAUno() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(0);
		gameOne.setNSprints(1);

		assertNull(storyImp.addGameT(gameOne, 2));

	}

	@Test
	void testAnadirGameSprintsMenorAUno() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(0);

		assertNull(storyImp.addGameT(gameOne, 2));

	}

	
	
	
	
	
	
	
	
	
	@Test
	void testAnadirGameConTema() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(1);
		tema.setDefaultSprints(1);

		topicServiceImp.addTopic(tema);

		assertNotNull(storyImp.addGameT(gameOne, tema.getId()));

	}

	@Test
	void testAnadirGameConTemaFalla() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(0);
		tema.setDefaultSprints(1);

		topicServiceImp.addTopic(tema);

		assertNotNull(storyImp.addGameT(gameOne, tema.getId()));

	}

	@Test
	void testAnadirGameSinTema() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		assertNotNull(storyImp.addGame(gameOne));

	}

	
	@Test
	void testActualizarGameGrupoMenorA1() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameOne.setName("Cambiando Nombre");
		storyImp.addGame(gameOne);

		assertNull(storyImp.setGame(gameOne, 0, "Historia"));
	

	}
	
	@Test
	void testActualizarGameNombreVacio() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameOne.setName("Cambiando Nombre");
		storyImp.addGame(gameOne);

		assertNull(storyImp.setGame(gameOne, 2, ""));
	

	}
	
	@Test
	void testActualizarGameNombreNull() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameOne.setName("Cambiando Nombre");
		storyImp.addGame(gameOne);

		assertNull(storyImp.setGame(gameOne, 2, null));
	

	}
	
	
	
	
	@Test
	void testActualizarGame() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameOne.setName("Cambiando Nombre");
		storyImp.addGame(gameOne);

		storyImp.setGame(gameOne, 2, "Historia");

		assertEquals("Historia", storyImp.findGame(gameOne.getId()).getName());
		assertEquals(2, gameOne.getNGroups());

	}
}
