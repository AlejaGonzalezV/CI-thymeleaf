package com.workshop.main.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.workshop.main.repositories.TsscStoryRepository;
import com.workshop.main.services.TsscGameServiceImp;
import com.workshop.main.services.TsscStoryServiceImp;

@SpringBootTest
class TsscStoryTestMockito {

	@InjectMocks
	private TsscStoryServiceImp storyServ;

	@Mock
	private TsscGameServiceImp gameServ;
	
	@Mock
	private TsscStoryRepository storyRepo;
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSaveStoryVNMenor0() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(0));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		
		when(gameServ.existById(g.getId())).thenReturn(true);
		when(gameServ.findGame(g.getId())).thenReturn(g);
		when(storyRepo.existsById(st.getId())).thenReturn(false);
		
		storyServ.addStory(st, g.getId());
		
		assertFalse(storyServ.existById(st.getId()));

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
		
		when(gameServ.existById(g.getId())).thenReturn(true);
		when(gameServ.findGame(g.getId())).thenReturn(g);
		when(storyRepo.existsById(st.getId())).thenReturn(false);
		
		storyServ.addStory(st, g.getId());
		
		assertFalse(storyServ.existById(st.getId()));

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
		
		when(gameServ.existById(g.getId())).thenReturn(true);
		when(gameServ.findGame(g.getId())).thenReturn(g);
		when(storyRepo.existsById(st.getId())).thenReturn(false);
		
		storyServ.addStory(st, g.getId());
		
		assertFalse(storyServ.existById(st.getId()));

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
		
		when(gameServ.existById(g.getId())).thenReturn(true);
		when(gameServ.findGame(g.getId())).thenReturn(g);
		when(storyRepo.existsById(st.getId())).thenReturn(true);
		when(gameServ.addStory(st, g)).thenReturn(st);
		
		storyServ.addStory(st, g.getId());
		
		assertTrue(storyServ.existById(st.getId()));

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
		
		when(gameServ.existById(g.getId())).thenReturn(false);
		when(storyRepo.existsById(st.getId())).thenReturn(false);

		storyServ.addStory(st, g.getId());
		
		assertFalse(storyServ.existById(st.getId()));

	}
	
	
	@Test
	void TestSetStoryAltDescriptionNull() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setAltDescripton("AltDesc");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		
		Optional<TsscStory> op = Optional.of(st);
		when(storyRepo.existsById(g.getId())).thenReturn(true);
		when(storyRepo.findById(g.getId())).thenReturn(op);
		
		storyServ.setStory(st, null, "segunda");
		
		assertNotNull(storyServ.findStory(st.getId()).getAltDescripton());
		assertNotEquals(storyServ.findStory(st.getId()).getDescription(), "segunda");
		
			

	}
	
	@Test
	void TestSetStoryAltDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setDescription("Descrip");
		st.setAltDescripton("AltDesc");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		
		Optional<TsscStory> op = Optional.of(st);
		when(storyRepo.existsById(g.getId())).thenReturn(true);
		when(storyRepo.findById(g.getId())).thenReturn(op);
		
		storyServ.setStory(st, "", "segunda");
		
		assertNotEquals(storyServ.findStory(st.getId()).getAltDescripton(), "");
		assertNotEquals(storyServ.findStory(st.getId()).getDescription(), "segunda");
			

	}
	
	@Test
	void TestSetStoryNoDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setDescription("Descrip");
		st.setAltDescripton("AltDesc");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		
		Optional<TsscStory> op = Optional.of(st);
		when(storyRepo.existsById(g.getId())).thenReturn(true);
		when(storyRepo.findById(g.getId())).thenReturn(op);
		
		storyServ.setStory(st, "segunda", null);
		
		assertNotNull(storyServ.findStory(st.getId()).getDescription());
		assertNotEquals(storyServ.findStory(st.getId()).getAltDescripton(), "segunda");
			

	}
	
	@Test
	void testSetStoryDescription() {

		TsscStory st = new TsscStory();
		st.setBusinessValue(BigDecimal.valueOf(1));
		st.setInitialSprint(BigDecimal.valueOf(1));
		st.setPriority(BigDecimal.valueOf(1));
		st.setDescription("Descrip");
		st.setAltDescripton("AltDesc");
		
		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		
		Optional<TsscStory> op = Optional.of(st);
		when(storyRepo.existsById(g.getId())).thenReturn(true);
		when(storyRepo.findById(g.getId())).thenReturn(op);
		
		storyServ.setStory(st, "segunda", "");
		
		assertNotEquals(storyServ.findStory(st.getId()).getAltDescripton(), "segunda");
		assertNotEquals(storyServ.findStory(st.getId()).getDescription(), "");
			

	}
	
	@Test
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
		
		Optional<TsscStory> op = Optional.of(st);
		when(storyRepo.existsById(g.getId())).thenReturn(true);
		when(storyRepo.findById(g.getId())).thenReturn(op);
		
		storyServ.setStory(st, "Primero", "Segundo");
		
		assertEquals(storyServ.findStory(st.getId()).getAltDescripton(), "Primero");
		assertEquals(storyServ.findStory(st.getId()).getDescription(), "Segundo");
			

	}

}
