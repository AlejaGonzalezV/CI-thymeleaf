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
	void AgregarUnTemaGrupoMenora1() {
	
		TsscTopic tsstopic = new TsscTopic();
	
		tsstopic.setDefaultGroups(0);
		tsstopic.setDefaultSprints(4);
		topicServiceImp.addTopic(tsstopic);
		Long id = tsstopic.getId();
		assertNull(topicServiceImp.findTopic(id));

	}
	
	@Test
	void AgregarUnTemaSprintsMenora1() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(0);
		topicServiceImp.addTopic(tsstopic);

		Long id = tsstopic.getId();
		assertNull(topicServiceImp.findTopic(id));

	}
	@Test
	void AgregarUnTemaSprintsyGrupoMayora0() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("HEY BA");
		topicServiceImp.addTopic(tsstopic);

		Long id = tsstopic.getId();
		assertNotNull(topicServiceImp.findTopic(id));

	}
	
	
	@Test
	void editarunTema0() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("El mejor tema");
		topicServiceImp.addTopic(tsstopic);

		Long id = tsstopic.getId();
	    TsscTopic encontrado=topicServiceImp.findTopic(id);
	    topicServiceImp.setTopic(encontrado, "Miguel", "Un tema");
	    
	    assertEquals("Miguel", encontrado.getName());
	    assertEquals("Un tema", encontrado.getDescription());
	  

	}
	
	
	
	@Test
	void editarunTemaNameNull() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("El mejor tema");
		topicServiceImp.addTopic(tsstopic);

		Long id = tsstopic.getId();
	    TsscTopic encontrado=topicServiceImp.findTopic(id);
	   assertNull(topicServiceImp.setTopic(encontrado, null, "Un tema"));
	      

	}
	
	
	@Test
	void editarunTemaNameVacio() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("El mejor tema");
		topicServiceImp.addTopic(tsstopic);

		Long id = tsstopic.getId();
	    TsscTopic encontrado=topicServiceImp.findTopic(id);
	   assertNull(topicServiceImp.setTopic(encontrado, "", "Un tema"));
	      

	}
	
	
	@Test
	void editarunTemaDescripcionVacio() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("El mejor tema");
		topicServiceImp.addTopic(tsstopic);

		Long id = tsstopic.getId();
	    TsscTopic encontrado=topicServiceImp.findTopic(id);
	   assertNull(topicServiceImp.setTopic(encontrado, "Miguel", ""));
	      

	}
	
	@Test
	void editarunTemaDescripcionNull() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("El mejor tema");
		topicServiceImp.addTopic(tsstopic);

		Long id = tsstopic.getId();
	    TsscTopic encontrado=topicServiceImp.findTopic(id);
	   assertNull(topicServiceImp.setTopic(encontrado, "Miguel", null));
	      

	}
}
