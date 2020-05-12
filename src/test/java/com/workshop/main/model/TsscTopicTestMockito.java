package com.workshop.main.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.Daos.TsscTopicDao;
import com.workshop.main.repositories.TsscTopicRepository;
import com.workshop.main.services.TsscTopicServiceImp;

@SpringBootTest
class TsscTopicTestMockito {

	@Autowired
	@InjectMocks
	private TsscTopicServiceImp topicServiceImp;
	
	@Mock
	private TsscTopicDao topicRepo;
	
	
	@BeforeAll
	public static void beforeTest() {
		System.out.println("-----> SETUP <-----");
	}

	@AfterAll
	public static void afterTest() {
		System.out.println("-----> FINISH <-----");
	}

	
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddTopicGroupMenor1() {
	
		TsscTopic top = new TsscTopic();
	
		top.setDefaultGroups(0);
		top.setDefaultSprints(4);
		topicServiceImp.addTopic(top);
		Long id = top.getId();
		
		when(topicRepo.existById(id)).thenReturn(false);
		assertNull(topicServiceImp.findTopic(id));

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddTopicSprintsMenor1() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(0);
		topicServiceImp.addTopic(top);
		Long id = top.getId();
		
		when(topicRepo.existById(id)).thenReturn(false);
		assertNull(topicServiceImp.findTopic(id));

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testAddTopic() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setDescription("Description");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
		when(topicRepo.existById(id)).thenReturn(false);
		assertNotNull(topicServiceImp.findTopic(id));

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testEditTopic() {
	
		TsscTopic top = new TsscTopic();
		
		String name = "HSM";
		String description = "Desc";
		top.setDefaultGroups(5);
		top.setDefaultSprints(5);
		topicServiceImp.addTopic(top);

		TsscTopic t = topicServiceImp.setTopic(top, name, description);
		Long id = top.getId();
		when(topicRepo.findById(id)).thenReturn(t);

		assertEquals(topicServiceImp.findTopic(top.getId()).getName(), top.getName());
	    assertEquals(topicServiceImp.findTopic(top.getId()).getDescription(), top.getDescription());

	}
	
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testEditTopicNullName() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setName(null);
		top.setDescription("Desc");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
		when(topicRepo.existById(id)).thenReturn(true);
		when(topicRepo.findById(id)).thenReturn(top);
		
		assertNull(topicServiceImp.findTopic(top.getId()).getName());

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testEditTopicNoName() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		String description = "Desc";
		topicServiceImp.addTopic(top);
		topicServiceImp.setTopic(top, "", description);

		Long id = top.getId();
		when(topicRepo.findById(id)).thenReturn(top);
		
		assertNull(topicServiceImp.findTopic(top.getId()).getName());      

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testEditTopicNoDesc() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		String name = "HSM";
		topicServiceImp.addTopic(top);
		topicServiceImp.setTopic(top, name, "");

		Long id = top.getId();
		when(topicRepo.findById(id)).thenReturn(top);
		
		assertNull(topicServiceImp.findTopic(top.getId()).getName());   

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testEditTopicNullDesc() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		
		String name = "HSM";
		topicServiceImp.addTopic(top);
		topicServiceImp.setTopic(top, name, null);

		Long id = top.getId();
		when(topicRepo.findById(id)).thenReturn(top);
		
		assertNull(topicServiceImp.findTopic(top.getId()).getName());
	      

	}

}
