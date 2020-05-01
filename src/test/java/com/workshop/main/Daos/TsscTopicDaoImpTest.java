package com.workshop.main.Daos;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.model.TsscTopic;

@SpringBootTest
class TsscTopicDaoImpTest {

	@Autowired
	private TsscTopicDao topicDao;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTest() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("Nombre");
		topic.setDescription("Description");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
		topicDao.save(topic);
		assertNotNull(topicDao.findById(topic.getId()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testMerge() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("Nombre");
		topic.setDescription("Description");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topicDao.save(topic);
		topic.setName("Nombre2");
		topicDao.merge(topic);
		
		assertEquals(topicDao.findById(topic.getId()).getName(), topic.getName());
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testDelete() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("Nombre");
		topic.setDescription("Description");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topicDao.save(topic);
		assertNotNull(topicDao.findById(topic.getId()));
		topicDao.delete(topic);
		assertNull(topicDao.findById(topic.getId()));
		
	}

	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByName() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("Nombre");
		topic.setDescription("Description");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topic.setGroupPrefix("Grupo");
		topicDao.save(topic);
		
		assertNotNull(topicDao.findByName(topic.getName()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByDescription() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("Nombre");
		topic.setDescription("Description");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topic.setGroupPrefix("Grupo");
		topicDao.save(topic);
		
		assertNotNull(topicDao.findByDescription(topic.getDescription()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindTopicsByGameDate() {
		
		//Pendiente
		
		
	}

}
