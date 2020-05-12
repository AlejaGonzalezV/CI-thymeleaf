package com.workshop.main.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.workshop.main.Daos.TsscStoryDao;
import com.workshop.main.services.TsscGameServiceImp;
import com.workshop.main.services.TsscStoryServiceImp;

@SpringBootTest
class TsscStoryTestMockito {

	@InjectMocks
	@Autowired
	private TsscStoryServiceImp storyServ;

	@Mock
	private TsscStoryDao storyRepo;
	
	@Autowired
	private TsscGameServiceImp gameServ;
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSaveStoryVNMenor0() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(0));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameServ.addGame(g);

		when(storyRepo.existById(st.getId())).thenReturn(false);
		
		storyServ.addStory(st, g.getId());
		
		assertFalse(storyServ.existById(st.getId()));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void TestSaveStorySprintInicialMenor0() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(0));
		st.setPriority(BigDecimal.valueOf(1));

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameServ.addGame(g);
		
		when(storyRepo.existById(st.getId())).thenReturn(false);
		
		storyServ.addStory(st, g.getId());
		
		assertFalse(storyServ.existById(st.getId()));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void TestSaveStoryPriorityMenor0() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(0));

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		gameServ.addGame(g);

		when(storyRepo.existById(st.getId())).thenReturn(false);
		
		storyServ.addStory(st, g.getId());
		
		assertFalse(storyServ.existById(st.getId()));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void TestSaveStoryAsociadaJuego() {
		
		
		TsscGame g = new TsscGame();
		g.setNGroups(3);
		g.setNSprints(3);
		List<TsscStory> stList = new ArrayList<TsscStory>();
		g.setTsscStories(stList);
		gameServ.addGame(g);
		
		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(2));
		st.setInitialSprint(BigDecimal.valueOf(2));
		st.setPriority(BigDecimal.valueOf(2));
		

		when(storyRepo.existById(st.getId())).thenReturn(true);			
		storyServ.addStory(st, g.getId());
		
		assertTrue(storyServ.existById(st.getId()));

	}	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void TestSaveStoryNoAsociadaJuego() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		
		TsscGame g = new TsscGame();
		g.setNGroups(0);
		g.setNSprints(1);
		List<TsscStory> stList = new ArrayList<TsscStory>();
		g.setTsscStories(stList);
		gameServ.addGame(g);

		when(storyRepo.existById(st.getId())).thenReturn(false);

		storyServ.addStory(st, g.getId());
		
		assertFalse(storyServ.existById(st.getId()));

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void TestSetStoryAltDescriptionNull() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setAltDescripton("AltDesc");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		List<TsscStory> stList = new ArrayList<TsscStory>();
		g.setTsscStories(stList);
		gameServ.addGame(g);
		
		storyServ.addStory(st, g.getId());
		
		when(storyRepo.existById(g.getId())).thenReturn(true);
		when(storyRepo.findById(g.getId())).thenReturn(st);
		
		storyServ.setStory(st, null, "segunda");
		
		assertNotNull(storyServ.findStory(st.getId()).getAltDescripton());
		assertNotEquals(storyServ.findStory(st.getId()).getDescription(), "segunda");
		
			

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void TestSetStoryAltDescription() {
		
		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setAltDescripton("AltDesc");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		List<TsscStory> stList = new ArrayList<TsscStory>();
		g.setTsscStories(stList);
		gameServ.addGame(g);
		
		storyServ.addStory(st, g.getId());
		
		when(storyRepo.existById(g.getId())).thenReturn(true);
		when(storyRepo.findById(g.getId())).thenReturn(st);
		
		storyServ.setStory(st, "", "segunda");
		
		assertNotEquals(storyServ.findStory(st.getId()).getAltDescripton(), "");
		assertNotEquals(storyServ.findStory(st.getId()).getDescription(), "segunda");		

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void TestSetStoryNoDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setAltDescripton("AltDesc");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		List<TsscStory> stList = new ArrayList<TsscStory>();
		g.setTsscStories(stList);
		gameServ.addGame(g);
		
		storyServ.addStory(st, g.getId());
		
		when(storyRepo.existById(g.getId())).thenReturn(true);
		when(storyRepo.findById(g.getId())).thenReturn(st);
		
		storyServ.setStory(st, "segunda", null);
		
		assertNotNull(storyServ.findStory(st.getId()).getAltDescripton());
		assertNotEquals(storyServ.findStory(st.getId()).getDescription(), "segunda");

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSetStoryDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setAltDescripton("AltDesc");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		List<TsscStory> stList = new ArrayList<TsscStory>();
		g.setTsscStories(stList);
		gameServ.addGame(g);
		
		storyServ.addStory(st, g.getId());
		
		when(storyRepo.existById(g.getId())).thenReturn(true);
		when(storyRepo.findById(g.getId())).thenReturn(st);
		
		storyServ.setStory(st, "segunda", "");
		
		assertNotEquals(storyServ.findStory(st.getId()).getAltDescripton(), "segunda");
		assertNotEquals(storyServ.findStory(st.getId()).getDescription(), "");
			

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void TestSetStoryDescriptionAltDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setDescription("Descrip");
		st.setAltDescripton("AltDesc");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);

		when(storyRepo.existById(g.getId())).thenReturn(true);
		when(storyRepo.findById(g.getId())).thenReturn(st);
		
		storyServ.setStory(st, "Primero", "Segundo");
		
		assertEquals(storyServ.findStory(st.getId()).getAltDescripton(), "Primero");
		assertEquals(storyServ.findStory(st.getId()).getDescription(), "Segundo");
			

	}

}
