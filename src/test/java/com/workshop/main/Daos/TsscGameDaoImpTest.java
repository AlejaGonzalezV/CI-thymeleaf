package com.workshop.main.Daos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.model.TsscGame;
import com.workshop.main.model.TsscStory;
import com.workshop.main.model.TsscTopic;

@SpringBootTest
class TsscGameDaoImpTest {
	
	@Autowired
	private TsscGameDao gameDao;
	@Autowired
	private TsscTopicDao topicDao;
	@Autowired
	private TsscStoryDao storyDao;
	

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTest() {
		
		TsscGame g = new TsscGame();
		
		g.setNGroups(2);
		g.setNSprints(2);
		
		gameDao.save(g);
		
		assertNotNull(gameDao.findById(g.getId()));
		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testMerge() {
		
		TsscGame g = new TsscGame();
		
		g.setNGroups(2);
		g.setNSprints(2);
		
		gameDao.save(g);
		
		g.setNSprints(7);
		gameDao.merge(g);
		
		assertEquals(gameDao.findById(g.getId()).getNSprints(), g.getNSprints());
		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testDelete() {
		
		TsscGame g = new TsscGame();
		g.setNGroups(2);
		g.setNSprints(2);
		gameDao.save(g);
		assertNotNull(gameDao.findById(g.getId()));
		gameDao.delete(g);
		assertNull(gameDao.findById(g.getId()));
		
	}
	

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByNameTopic() {
		
		TsscGame g = new TsscGame();
		g.setNGroups(4);
		g.setNSprints(4);
	
		g.setName("Juego");
		g.setAdminPassword("pass");
		g.setScheduledDate(LocalDate.of(2020, 01, 01));
		g.setStartTime(LocalTime.MIDNIGHT);
		g.setUserPassword("pass");
		g.setGuestPassword("pass");
		
		TsscTopic t = new TsscTopic();
		t.setDefaultGroups(2);
		t.setDefaultSprints(3);
		t.setName("Nombre");
		t.setDescription("Description");
		t.setGroupPrefix("Grupo");
		topicDao.save(t);
		g.setTsscTopic(t);
		
		gameDao.save(g);
		
		assertTrue(gameDao.findByNameTopic(t.getName()).contains(g));
			
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByDescriptionTopic() {
		
		TsscGame g = new TsscGame();
		g.setNGroups(4);
		g.setNSprints(4);
	
		g.setName("Juego");
		g.setAdminPassword("pass");
		g.setScheduledDate(LocalDate.of(2020, 01, 01));
		g.setStartTime(LocalTime.MIDNIGHT);
		g.setUserPassword("pass");
		g.setGuestPassword("pass");
		
		TsscTopic t = new TsscTopic();
		t.setDefaultGroups(2);
		t.setDefaultSprints(3);
		t.setName("Nombre");
		t.setDescription("Description");
		t.setGroupPrefix("Grupo");
		topicDao.save(t);
		g.setTsscTopic(t);
		
		gameDao.save(g);
		assertTrue(gameDao.findByDescriptionTopic(t.getDescription()).contains(g));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByIdTopic() {
		
		TsscGame g = new TsscGame();
		g.setNGroups(4);
		g.setNSprints(4);
	
		g.setName("Juego");
		g.setAdminPassword("pass");
		g.setScheduledDate(LocalDate.of(2020, 01, 01));
		g.setStartTime(LocalTime.MIDNIGHT);
		g.setUserPassword("pass");
		g.setGuestPassword("pass");
		
		TsscTopic t = new TsscTopic();
		t.setDefaultGroups(2);
		t.setDefaultSprints(3);
		t.setName("Nombre");
		t.setDescription("Description");
		t.setGroupPrefix("Grupo");
		topicDao.save(t);
		g.setTsscTopic(t);
		
		gameDao.save(g);
		assertTrue(gameDao.findByIdTopic(t.getId()).contains(g));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByDates() {
			
//		TsscGame game = new TsscGame();
//		game.setNGroups(3);
//		game.setNSprints(3);
//		game.setName("gameA");
//		game.setAdminPassword("1234");
//		game.setGuestPassword("1234");
//		game.setScheduledDate(LocalDate.of(2020, 3, 4));
//		game.setScheduledTime(LocalTime.of(13, 20));
//		game.setUserPassword("1234");
//		gameDao.save(game);	
//		
//		TsscGame game2 = new TsscGame();
//		game2.setNGroups(3);
//		game2.setNSprints(3);
//		game2.setName("gameB");
//		game2.setAdminPassword("1234");
//		game2.setGuestPassword("1234");
//		game2.setScheduledDate(LocalDate.of(2020, 3, 4));
//		game2.setScheduledTime(LocalTime.of(14, 1));
//		game2.setUserPassword("1234");
//		gameDao.save(game2);	
//		
//		TsscGame game3 = new TsscGame();
//		game3.setNGroups(3);
//		game3.setNSprints(3);
//		game3.setName("gameB");
//		game3.setAdminPassword("1234");
//		game3.setGuestPassword("1234");
//		game3.setScheduledDate(LocalDate.of(2020, 3, 4));
//		game3.setScheduledTime(LocalTime.of(16, 34));
//		game3.setUserPassword("1234");
//		gameDao.save(game3);	
//		
//		LocalDate date = LocalDate.of(2020,3,6);
		
		
		
		
		TsscGame g = new TsscGame();
		g.setNGroups(4);
		g.setNSprints(4);
	
		g.setName("Juego");
		g.setAdminPassword("pass");
		g.setScheduledDate(LocalDate.of(2020, 2, 2));
		g.setStartTime(LocalTime.MIDNIGHT);
		g.setUserPassword("pass");
		g.setGuestPassword("pass");
		gameDao.save(g);
		
		LocalDate date1 = LocalDate.of(2020, 1, 1);
		LocalDate date2 = LocalDate.of(2020, 3, 3); 
		
		assertTrue(gameDao.findByDates(date1, date2).contains(g));
		
		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void FindByDateHours() {
		
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.of(2020, 3, 4));
		game.setScheduledTime(LocalTime.of(13, 20));
		game.setUserPassword("1234");
		gameDao.save(game);	
		
		TsscGame game2 = new TsscGame();
		game2.setNGroups(3);
		game2.setNSprints(3);
		game2.setName("gameB");
		game2.setAdminPassword("1234");
		game2.setGuestPassword("1234");
		game2.setScheduledDate(LocalDate.of(2020, 3, 4));
		game2.setScheduledTime(LocalTime.of(14, 1));
		game2.setUserPassword("1234");
		gameDao.save(game2);	
		
		TsscGame game3 = new TsscGame();
		game3.setNGroups(3);
		game3.setNSprints(3);
		game3.setName("gameB");
		game3.setAdminPassword("1234");
		game3.setGuestPassword("1234");
		game3.setScheduledDate(LocalDate.of(2020, 3, 4));
		game3.setScheduledTime(LocalTime.of(16, 34));
		game3.setUserPassword("1234");
		gameDao.save(game3);	
		
		LocalDate date = LocalDate.of(2020, 3, 4);
		LocalTime h1 = LocalTime.of(11, 30);
		LocalTime h2 = LocalTime.of(15, 4);
		
		assertTrue(gameDao.findByDateHours(date, h1, h2).size() == 2);
		
//		TsscGame g = new TsscGame();
//		g.setNGroups(4);
//		g.setNSprints(4);
//	
//		g.setName("Juego");
//		g.setAdminPassword("pass");
//		g.setScheduledDate(LocalDate.of(2020, 2, 2));
//		g.setStartTime(LocalTime.of(3, 30));
//		g.setUserPassword("pass");
//		g.setGuestPassword("pass");
//		gameDao.save(g);
//		
//		LocalTime time1 = LocalTime.of(2, 30);
//		LocalTime time2 = LocalTime.of(4, 30);
//		
//		assertTrue(gameDao.findByDateHours(g.getScheduledDate(), time1, time2).contains(g));

		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void FindByDateStoryTime() {
		
		TsscGame g = new TsscGame();
		g.setNGroups(4);
		g.setNSprints(4);
	
		g.setName("Juego");
		g.setAdminPassword("pass");
		g.setScheduledDate(LocalDate.of(2020, 2, 2));
		g.setStartTime(LocalTime.MIDNIGHT);
		g.setUserPassword("pass");
		g.setGuestPassword("pass");
		
		TsscStory s = new TsscStory();
		
		
		
		gameDao.save(g);
		
	}

}
