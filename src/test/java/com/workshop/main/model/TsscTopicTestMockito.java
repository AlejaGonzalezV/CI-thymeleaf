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

import com.workshop.main.repositories.TsscTopicRepository;
import com.workshop.main.services.TsscTopicServiceImp;

@SpringBootTest
class TsscTopicTestMockito {

	@Autowired
	@InjectMocks
	private TsscTopicServiceImp topicServiceImp;
	
	@Mock
	private TsscTopicRepository topicRepo;
	
	
	@BeforeAll
	public static void beforeTest() {
		System.out.println("-----> SETUP <-----");
	}

	@AfterAll
	public static void afterTest() {
		System.out.println("-----> FINISH <-----");
	}

	
	
	
	@Test
	void TestAddTopicGroupMenor1() {
	
		TsscTopic top = new TsscTopic();
	
		top.setDefaultGroups(0);
		top.setDefaultSprints(4);
		topicServiceImp.addTopic(top);
		Long id = top.getId();
		
		when(topicRepo.existsById(id)).thenReturn(false);
		assertNull(topicServiceImp.findTopic(id));

	}
	
	@Test
	void TestAddTopicSprintsMenor1() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(0);
		topicServiceImp.addTopic(top);
		Long id = top.getId();
		
		when(topicRepo.existsById(id)).thenReturn(false);
		assertNull(topicServiceImp.findTopic(id));

	}
	
	@Test
	void TestAddTopicSprintsGroupMayor0() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setDescription("Description");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
		when(topicRepo.existsById(id)).thenReturn(false);
		assertNotNull(topicServiceImp.findTopic(id));

	}
	
	
	@Test
	void TestEditTopic0() {
	
		TsscTopic top = new TsscTopic();
		
		String name = "HSM";
		String description = "Desc";
		top.setDefaultGroups(5);
		top.setDefaultSprints(5);
		topicServiceImp.addTopic(top);

		TsscTopic t = topicServiceImp.setTopic(top, name, description);
		Optional<TsscTopic> op = Optional.of(t);
		Long id = top.getId();
		when(topicRepo.findById(id)).thenReturn(op);

		assertEquals(topicServiceImp.findTopic(top.getId()).getName(), top.getName());
	    assertEquals(topicServiceImp.findTopic(top.getId()).getDescription(), top.getDescription());

	}
	
	
	
	@Test
	void TestEditTopicNullName() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		String description = "Desc";
		topicServiceImp.addTopic(top);
		topicServiceImp.setTopic(top, null, description);

		Long id = top.getId();
		Optional<TsscTopic> op = Optional.of(top);
		when(topicRepo.findById(id)).thenReturn(op);
		
		assertNull(topicServiceImp.findTopic(top.getId()).getName());

	}
	
	
	@Test
	void TestEditTopicNoName() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		String description = "Desc";
		topicServiceImp.addTopic(top);
		topicServiceImp.setTopic(top, "", description);

		Long id = top.getId();
		Optional<TsscTopic> op = Optional.of(top);
		when(topicRepo.findById(id)).thenReturn(op);
		
		assertNull(topicServiceImp.findTopic(top.getId()).getName());      

	}
	
	
	@Test
	void TestEditTopicNoDesc() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		String name = "High school musical";
		topicServiceImp.addTopic(top);
		topicServiceImp.setTopic(top, name, "");

		Long id = top.getId();
		Optional<TsscTopic> op = Optional.of(top);
		when(topicRepo.findById(id)).thenReturn(op);
		
		assertNull(topicServiceImp.findTopic(top.getId()).getName());   

	}
	
	@Test
	void TestEditTopicNullDesc() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		
		String name = "High school musical";
		topicServiceImp.addTopic(top);
		topicServiceImp.setTopic(top, name, null);

		Long id = top.getId();
		Optional<TsscTopic> op = Optional.of(top);
		when(topicRepo.findById(id)).thenReturn(op);
		
		assertNull(topicServiceImp.findTopic(top.getId()).getName());
	      

	}

}
