package com.workshop.main.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.workshop.main.model.TsscTopic;
import com.workshop.main.services.TsscTopicServiceImp;

@SpringBootTest
class TsscTopicTest {

	@Autowired
	private TsscTopicServiceImp topicServiceImp;
	
	@Test
	void TestAddTopicGroupMenor1() {
	
		TsscTopic top = new TsscTopic();
	
		top.setDefaultGroups(0);
		top.setDefaultSprints(4);
		topicServiceImp.addTopic(top);
		Long id = top.getId();
		assertNull(topicServiceImp.findTopic(id));

	}
	
	@Test
	void TestAddTopicSprintsMenor1() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(0);
		topicServiceImp.addTopic(top);

		Long id = top.getId();
		assertNull(topicServiceImp.findTopic(id));

	}
	@Test
	void TestAddTopicSprintsGroupMayor0() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setDescription("HEY BA");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
		assertNotNull(topicServiceImp.findTopic(id));

	}
	
	
	@Test
	void TestEditTopic0() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setDescription("El mejor tema");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
	    TsscTopic find=topicServiceImp.findTopic(id);
	    topicServiceImp.setTopic(find, "Miguel", "Un tema");
	    
	    assertEquals("Miguel", find.getName());
	    assertEquals("Un tema", find.getDescription());
	  

	}
	
	
	
	@Test
	void TestEditTopicNullName() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setDescription("El mejor tema");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
	    TsscTopic find=topicServiceImp.findTopic(id);
	   assertNull(topicServiceImp.setTopic(find, null, "Un tema"));
	      

	}
	
	
	@Test
	void TestEditTopicNoName() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setDescription("El mejor tema");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
	    TsscTopic find=topicServiceImp.findTopic(id);
	   assertNull(topicServiceImp.setTopic(find, "", "Un tema"));
	      

	}
	
	
	@Test
	void TestEditTopicNoDesc() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setDescription("El mejor tema");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
	    TsscTopic find=topicServiceImp.findTopic(id);
	   assertNull(topicServiceImp.setTopic(find, "Miguel", ""));
	      

	}
	
	@Test
	void TestEditTopicNullDesc() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setDescription("El mejor tema");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
	    TsscTopic find=topicServiceImp.findTopic(id);
	   assertNull(topicServiceImp.setTopic(find, "Miguel", null));
	      

	}
}
