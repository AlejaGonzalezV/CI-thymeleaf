package com.workshop.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.main.model.TsscAdmin;
import com.workshop.main.repositories.TsscAdminRepository;

@Service
public class TsscAdminServiceImp implements TsscAdminService{
	
private TsscAdminRepository adminRepo;
	
	@Autowired
	public TsscAdminServiceImp(TsscAdminRepository adminRepo) {
		this.adminRepo = adminRepo;
	}

	@Override
	public TsscAdmin save(TsscAdmin adm) {
		return adminRepo.save(adm);
	}

	@Override
	public TsscAdmin edit(TsscAdmin adm) {
		return adminRepo.save(adm);
	}

	@Override
	public void delete(TsscAdmin adm) {
		adminRepo.delete(adm);
	}
	

}
