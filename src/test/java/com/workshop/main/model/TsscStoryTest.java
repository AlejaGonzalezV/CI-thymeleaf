package com.workshop.main.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.workshop.main.services.TsscGameServiceImp;
import com.workshop.main.services.TsscStoryServiceImp;

@SpringBootTest
class TsscStoryTest {

	@Autowired
	private TsscStoryServiceImp StoryServiceImp;

	@Autowired
	private TsscGameServiceImp gameImp;

	@Test
	void testSaveStoryAsociadaJuego() {
		
		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);
		StoryServiceImp.addStory(st, g.getId());

		assertNotNull(StoryServiceImp.findStory(st.getId()));
		assertTrue(StoryServiceImp.existById(st.getId()));

	}
	
	@Test
	void testSaveStoryAsociadaJuegoFail() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(0));
		st.setInitialSprint(BigDecimal.valueOf(0));
		st.setPriority(BigDecimal.valueOf(0));

		TsscGame g = new TsscGame();
		g.setNGroups(0);
		g.setNSprints(1);
		gameImp.addGame(g);
		
		StoryServiceImp.addStory(st, g.getId());
		assertFalse(StoryServiceImp.existById(st.getId()));

	}
	
	@Test
	void testSaveStoryAsociadaJuegoFail2() {


		TsscGame g = new TsscGame();
		g.setNGroups(0);
		g.setNSprints(1);
		gameImp.addGame(g);
		
		assertNull(StoryServiceImp.addStory(null, g.getId()));

	}

	
	@Test
	void testSetStoryDescriptionAltDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setAltDescripton("Probar");
		st.setDescription("probar2");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);
		StoryServiceImp.addStory(st, g.getId());
		

		StoryServiceImp.setStory(st, "Primero", "Segundo");
		Long id=st.getId();
		
		assertEquals(StoryServiceImp.findStory(id).getAltDescripton(), "Primero");
		assertEquals(StoryServiceImp.findStory(id).getDescription(), "Segundo");
			

	}
	
	@Test
	void testSetStoryDescriptionAltDescriptionFail() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setAltDescripton("Probar");
		st.setDescription("probar2");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);

		assertNull(StoryServiceImp.setStory(st, "", ""));

	}
}
