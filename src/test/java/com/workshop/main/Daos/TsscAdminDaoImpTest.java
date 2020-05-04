package com.workshop.main.Daos;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.model.TsscAdmin;

@SpringBootTest
class TsscAdminDaoImpTest {
	
	@Autowired
	private TsscAdminDao adminDao;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTest() {
		
		TsscAdmin adm = new TsscAdmin();
		
		adm.setUsername("User1");
		adm.setSuperAdmin("super");
		adm.setPassword("pass");
		
		adminDao.save(adm);
		assertNotNull(adminDao.findById(adm.getId()));
		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testMerge() {
		
		TsscAdmin adm = new TsscAdmin();
		adm.setUsername("User1");
		adm.setSuperAdmin("super");
		adm.setPassword("pass");
		
		adminDao.save(adm);
		adm.setUsername("User2");
		adminDao.merge(adm);
		
		assertEquals(adminDao.findById(adm.getId()).getUsername(), adm.getUsername());
	
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testDelete() {
		
		TsscAdmin adm = new TsscAdmin();
		
		adm.setUsername("User1");
		adm.setSuperAdmin("super");
		adm.setPassword("pass");
		
		adminDao.save(adm);
		assertNotNull(adminDao.findById(adm.getId()));
		adminDao.delete(adm);
		assertNull(adminDao.findById(adm.getId()));
		
		
		
	}



}
