package com.workshop.main.Daos;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.model.TsscTimecontrol;

@SpringBootTest
class TsscTimecontrolDaoImpTest {
	
	@Autowired
	private TsscTimecontrolDao tcDao;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTest() {
		
		TsscTimecontrol tc = new TsscTimecontrol();
		tc.setAutostart("Auto");
		tc.setIntervalRunning(BigDecimal.valueOf(20));
		tc.setLastPlayTime(LocalTime.now());
		tc.setName("Nombre");
		tc.setOrder(BigDecimal.valueOf(20));
		tc.setState("State");
		tc.setTimeInterval(BigDecimal.valueOf(20));
		tc.setType("Type");
		tcDao.save(tc);
		
		assertNotNull(tcDao.findById(tc.getId()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testMerge() {
		
		TsscTimecontrol tc = new TsscTimecontrol();
		tc.setAutostart("Auto");
		tc.setIntervalRunning(BigDecimal.valueOf(20));
		tc.setLastPlayTime(LocalTime.now());
		tc.setName("Nombre");
		tc.setOrder(BigDecimal.valueOf(20));
		tc.setState("State");
		tc.setTimeInterval(BigDecimal.valueOf(20));
		tc.setType("Type");
		tcDao.save(tc);
		tc.setName("Nombre2");
		tcDao.merge(tc);
		
		assertEquals(tcDao.findById(tc.getId()).getName(), tc.getName());
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testDelete() {
		
		TsscTimecontrol tc = new TsscTimecontrol();
		tc.setAutostart("Auto");
		tc.setIntervalRunning(BigDecimal.valueOf(20));
		tc.setLastPlayTime(LocalTime.now());
		tc.setName("Nombre");
		tc.setOrder(BigDecimal.valueOf(20));
		tc.setState("State");
		tc.setTimeInterval(BigDecimal.valueOf(20));
		tc.setType("Type");
		tcDao.save(tc);
		assertNotNull(tcDao.findById(tc.getId()));
		tcDao.delete(tc);
		assertNull(tcDao.findById(tc.getId()));
		
		
	}
	

}
