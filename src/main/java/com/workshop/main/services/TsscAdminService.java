package com.workshop.main.services;

import com.workshop.main.model.TsscAdmin;

public interface TsscAdminService {
	
	public TsscAdmin save(TsscAdmin adm);
	public TsscAdmin edit(TsscAdmin adm);
	public void delete(TsscAdmin adm);

}
