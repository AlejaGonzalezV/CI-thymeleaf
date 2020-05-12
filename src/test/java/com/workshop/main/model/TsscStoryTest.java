package com.workshop.main.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.services.TsscGameServiceImp;
import com.workshop.main.services.TsscStoryServiceImp;

@SpringBootTest
class TsscStoryTest {

	@Autowired
	private TsscStoryServiceImp StoryServiceImp;

	@Autowired
	private TsscGameServiceImp gameImp;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSaveStoryAsociadaJuego() {
		
		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		
		List<TsscStory> lista = new ArrayList<TsscStory>();

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		g.setTsscStories(lista);
		gameImp.addGame(g);
		StoryServiceImp.addStory(st, g.getId());

		assertNotNull(StoryServiceImp.findStory(st.getId()));
		assertTrue(StoryServiceImp.existById(st.getId()));

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSaveStoryAsociadaJuegoFail2() {


		TsscGame g = new TsscGame();
		g.setNGroups(0);
		g.setNSprints(1);
		gameImp.addGame(g);
		
		assertNull(StoryServiceImp.addStory(null, g.getId()));

	}

	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSetStoryDescriptionAltDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setAltDescripton("Probar");
		st.setDescription("probar2");

		List<TsscStory> lista = new ArrayList<TsscStory>();
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		g.setTsscStories(lista);
		gameImp.addGame(g);
		StoryServiceImp.addStory(st, g.getId());
		

		StoryServiceImp.setStory(st, "Primero", "Segundo");
		Long id=st.getId();
		
		assertEquals(StoryServiceImp.findStory(id).getAltDescripton(), "Primero");
		assertEquals(StoryServiceImp.findStory(id).getDescription(), "Segundo");
			

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
