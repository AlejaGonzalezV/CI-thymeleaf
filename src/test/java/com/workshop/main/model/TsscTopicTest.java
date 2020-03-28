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
	void testAddTopic() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setDescription("Desc");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
		assertNotNull(topicServiceImp.findTopic(id));

	}
	
	@Test
	void TestEditTopic() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		topicServiceImp.addTopic(top);

		Long id = top.getId();
	    TsscTopic find=topicServiceImp.findTopic(id);
	    topicServiceImp.setTopic(find, "Name", "Desc");
	    
	    assertEquals(find.getName(), "Name");
	    assertEquals(find.getDescription(), "Desc");
	  

	}
	
	@Test
	void TestEditTopicFail() {
	
		TsscTopic top = new TsscTopic();
		top.setDefaultGroups(4);
		top.setDefaultSprints(4);
		top.setName("Name");
		top.setName("Desc");
		topicServiceImp.addTopic(top);

		Long id = top.getId();
	    TsscTopic find=topicServiceImp.findTopic(id);
	    topicServiceImp.setTopic(find, "", "");
	    
	    assertNotEquals(find.getName(), "");
	    assertNotEquals(find.getDescription(), "");
	  

	}

}
