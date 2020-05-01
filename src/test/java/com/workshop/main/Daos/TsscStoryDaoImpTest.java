package com.workshop.main.Daos;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.model.TsscGame;
import com.workshop.main.model.TsscStory;

@SpringBootTest
class TsscStoryDaoImpTest {
	
	@Autowired
	private TsscStoryDao storyDao;
	@Autowired
	private TsscGameDao gameDao;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTest() {
		
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		gameDao.save(game);
		
		TsscStory story = new TsscStory();
		story.setBusinessValue(BigDecimal.valueOf(1));
		story.setInitialSprint(BigDecimal.valueOf(1));
		story.setPriority(BigDecimal.valueOf(1));
		story.setTsscGame(game);
		storyDao.save(story);

		assertNotNull(storyDao.findById(story.getId()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testMerge() {
		
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		gameDao.save(game);
		
		TsscStory story = new TsscStory();
		story.setBusinessValue(BigDecimal.valueOf(1));
		story.setInitialSprint(BigDecimal.valueOf(1));
		story.setPriority(BigDecimal.valueOf(1));
		story.setTsscGame(game);
		storyDao.save(story);
		story.setBusinessValue(BigDecimal.valueOf(2));
		storyDao.merge(story);
		
		assertEquals(storyDao.findById(story.getId()).getBusinessValue(), story.getBusinessValue());
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testDelete() {
		
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		gameDao.save(game);
		
		TsscStory story = new TsscStory();
		story.setBusinessValue(BigDecimal.valueOf(1));
		story.setInitialSprint(BigDecimal.valueOf(1));
		story.setPriority(BigDecimal.valueOf(1));
		story.setTsscGame(game);
		storyDao.save(story);
		assertNotNull(storyDao.findById(story.getId()));
		storyDao.delete(story);
		
		assertNull(storyDao.findById(story.getId()));
		
	}

}
