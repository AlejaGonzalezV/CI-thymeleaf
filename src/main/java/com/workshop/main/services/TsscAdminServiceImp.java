package com.workshop.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.Daos.TsscAdminDao;
import com.workshop.main.model.TsscAdmin;
import com.workshop.main.repositories.TsscAdminRepository;

@Service
public class TsscAdminServiceImp implements TsscAdminService{
	
//private TsscAdminRepository adminRepo;
//	
//	@Autowired
//	public TsscAdminServiceImp(TsscAdminRepository adminRepo) {
//		this.adminRepo = adminRepo;
//	}
//
//	@Override
//	public TsscAdmin save(TsscAdmin adm) {
//		return adminRepo.save(adm);
//	}
//
//	@Override
//	public TsscAdmin edit(TsscAdmin adm) {
//		return adminRepo.save(adm);
//	}
//
//	@Override
//	public void delete(TsscAdmin adm) {
//		adminRepo.delete(adm);
//	}
	
	private TsscAdminDao adminDao;
	
	@Autowired
	public TsscAdminServiceImp(TsscAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscAdmin save(TsscAdmin adm) {
		return adminDao.save(adm);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscAdmin edit(TsscAdmin adm) {
		return adminDao.save(adm);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TsscAdmin adm) {
		adminDao.delete(adm);
	}
	

}
