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
	void TestSaveStoryVNMenor0() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(0));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);

		assertNull(StoryServiceImp.addStory(st, g.getId()));

	}

	@Test
	void TestSaveStorySprintInicialMenor0() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(0));
		st.setPriority(BigDecimal.valueOf(1));

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);

		assertNull(StoryServiceImp.addStory(st, g.getId()));

	}

	@Test
	void TestSaveStoryPriorityMenor0() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(0));

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);

		assertNull(StoryServiceImp.addStory(st, g.getId()));

	}

	@Test
	void TestSaveStoryAsociadaJuego() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);

		assertNotNull(StoryServiceImp.addStory(st, g.getId()));

	}
	

	
	
	@Test
	void TestSaveStoryNoAsociadaJuego() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));

		TsscGame g = new TsscGame();
		g.setNGroups(0);
		g.setNSprints(1);
		gameImp.addGame(g);
		

		assertNull(StoryServiceImp.addStory(st, g.getId()));

	}
	
	
	@Test
	void TestSetStoryAltDescriptionNull() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);
		StoryServiceImp.addStory(st, g.getId());

		assertNull(StoryServiceImp.setStory(st, null, "SegundaPri"));
			

	}
	
	@Test
	void TestSetStoryAltDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);
		StoryServiceImp.addStory(st, g.getId());
		
		assertNull(StoryServiceImp.setStory(st, "", "SegundaPri"));
			

	}
	
	@Test
	void TestSetStoryNoDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);
		StoryServiceImp.addStory(st, g.getId());
		

		assertNull(StoryServiceImp.setStory(st, "Primero", null));
			

	}
	
	@Test
	void actualizarHistoriaDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameImp.addGame(g);
		StoryServiceImp.addStory(st, g.getId());
		

		assertNull(StoryServiceImp.setStory(st, "Primero", ""));
			

	}
	
	@Test
	void TestSetStoryDescriptionAltDescription() {

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
		
		assertEquals("Primero", StoryServiceImp.findStory(id).getAltDescripton());
		assertEquals("Segundo", StoryServiceImp.findStory(id).getDescription());
			

	}
}
