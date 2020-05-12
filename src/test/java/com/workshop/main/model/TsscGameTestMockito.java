package com.workshop.main.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.repositories.TsscGameRepository;
import com.workshop.main.services.TsscGameServiceImp;
import com.workshop.main.services.TsscTopicServiceImp;

@SpringBootTest
class TsscGameTestMockito {

	@Autowired
	private TsscTopicServiceImp topicServ;
	
	@Mock
	private TsscGameRepository gameRepo;
	
	@Autowired
	@InjectMocks
	private TsscGameServiceImp gameServ;

	@BeforeAll
	public static void beforeTest() {
		System.out.println("-----> SETUP <-----");
	}

	@AfterAll
	public static void afterTest() {
		System.out.println("-----> FINISH <-----");
	}
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGame2GroupMenor1() {

		TsscGame g = new TsscGame();
		g.setNGroups(0);
		g.setNSprints(4);
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(2);
		top.setDefaultSprints(2);
		gameServ.addGame2(g, top.getId());
		Long id = g.getId();
		
		when(gameRepo.existsById(id)).thenReturn(false);

		assertFalse(gameServ.existById(id));

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGame2SprintsMenor1() {

		TsscGame g = new TsscGame();
		g.setNGroups(4);
		g.setNSprints(0);
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(2);
		top.setDefaultSprints(2);
		gameServ.addGame2(g, top.getId());
		Long id = g.getId();
		
		when(gameRepo.existsById(id)).thenReturn(false);

		assertFalse(gameServ.existById(id));

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGame2() {

		TsscGame g = new TsscGame();
		g.setNGroups(2);
		g.setNSprints(2);
		
		List<TsscGame> list = new ArrayList<TsscGame>();
	    TsscTopic top = new TsscTopic();
	    top.setDefaultGroups(3);
	    top.setDefaultSprints(2);
	    top.setTsscGames(list);
	    List<TsscStory> stories = new ArrayList<TsscStory>();
	    List<TsscTimecontrol> times = new ArrayList<TsscTimecontrol>();
	    top.setTsscStories(stories);
	    top.setTsscCronograma(times);
	    topicServ.addTopic(top);

        when(gameRepo.existsById(g.getId())).thenReturn(true);
        gameServ.addGame2(g,top.getId());
        assertNotNull(gameServ.findGame(g.getId()));

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGameGroupMenor1() {

		TsscGame g = new TsscGame();
		g.setNGroups(0);
		g.setNSprints(1);
		gameServ.addGame(g);

		Long id = g.getId();
		
		when(gameRepo.existsById(id)).thenReturn(false);

		assertFalse(gameServ.existById(id));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGameSprintsMenor1() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(0);
		gameServ.addGame(g);

		Long id = g.getId();
		
		when(gameRepo.existsById(id)).thenReturn(false);

		assertFalse(gameServ.existById(id));
		

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGame() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		long id = g.getId();
		
		when(gameRepo.existsById(id)).thenReturn(true);
		gameServ.addGame(g);
		
		assertNotNull(gameServ.existById(id));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGameT() {

		TsscGame g = new TsscGame();
		g.setNGroups(2);
		g.setNSprints(2);

		List<TsscGame> list = new ArrayList<TsscGame>();
	    TsscTopic top = new TsscTopic();
	    top.setDefaultGroups(3);
	    top.setDefaultSprints(2);
	    top.setTsscGames(list);
	    List<TsscStory> stories = new ArrayList<TsscStory>();
	    List<TsscTimecontrol> times = new ArrayList<TsscTimecontrol>();
	    top.setTsscStories(stories);
	    top.setTsscCronograma(times);
	    topicServ.addTopic(top);
			
        when(gameRepo.existsById(g.getId())).thenReturn(true);
        gameServ.addGame2(g,top.getId());
		long id = g.getId();
		assertTrue(gameServ.existById(id));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddGameTFail() {

		TsscGame g = new TsscGame();
		g.setNGroups(2);
		g.setNSprints(2);

		List<TsscGame> list = new ArrayList<TsscGame>();
	    TsscTopic top = new TsscTopic();
	    top.setDefaultGroups(3);
	    top.setDefaultSprints(2);
	    top.setTsscGames(list);
	    List<TsscStory> stories = new ArrayList<TsscStory>();
	    List<TsscTimecontrol> times = new ArrayList<TsscTimecontrol>();
	    top.setTsscStories(stories);
	    top.setTsscCronograma(times);
	    topicServ.addTopic(top);
			
		long id = g.getId();
		
		when(gameRepo.existsById(id)).thenReturn(false);
		gameServ.addGameT(g, top.getId());
		assertFalse(gameServ.existById(id));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSetGameGroupMenor1() {

		TsscGame g = new TsscGame();
		g.setNGroups(6);
		g.setNSprints(1);
		String name = "Nombre1";
		gameServ.addGame(g);
		
		Optional<TsscGame> op = Optional.of(g);

		when(gameRepo.existsById(g.getId())).thenReturn(true);
		when(gameRepo.findById(g.getId())).thenReturn(op);
		gameServ.setGame(g, 0, name);
		
		assertNotEquals(gameServ.findGame(g.getId()).getNGroups(), 0);
		assertNotEquals(gameServ.findGame(g.getId()).getName(), name);
	

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSetGameNoName() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		g.setName("Nombre");
		String name = "";
		gameServ.addGame(g);
		
		Optional<TsscGame> op = Optional.of(g);
		when(gameRepo.existsById(g.getId())).thenReturn(true);
		when(gameRepo.findById(g.getId())).thenReturn(op);
		
		gameServ.setGame(g, 1, name);
		assertNotEquals(gameServ.findGame(g.getId()).getName(), name);
		
	

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSetGameNullName() {

		TsscGame g = new TsscGame();
		g.setNGroups(1);
		g.setNSprints(1);
		g.setName("Nombre");
		gameServ.addGame(g);
		
		Optional<TsscGame> op = Optional.of(g);
		when(gameRepo.existsById(g.getId())).thenReturn(true);
		when(gameRepo.findById(g.getId())).thenReturn(op);
		
		gameServ.setGame(g, 1, null);
		assertNotNull(gameServ.findGame(g.getId()).getName());
	

	}
	

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSetGame() {

		TsscGame g = new TsscGame();
		g.setNGroups(3);
		g.setNSprints(1);
		String name = "Nombre";
		gameServ.addGame(g);
		
		Optional<TsscGame> op = Optional.of(g);
		when(gameRepo.existsById(g.getId())).thenReturn(true);
		when(gameRepo.findById(g.getId())).thenReturn(op);

		gameServ.setGame(g, 2, name);


		assertEquals(gameServ.findGame(g.getId()).getNGroups(), 2);
		assertEquals(gameServ.findGame(g.getId()).getName(), name);
		

	}

}
