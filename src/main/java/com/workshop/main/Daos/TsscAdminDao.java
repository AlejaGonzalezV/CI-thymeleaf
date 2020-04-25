package com.workshop.main.Daos;

import com.workshop.main.model.TsscAdmin;

public interface TsscAdminDao {
	
	public TsscAdmin save(TsscAdmin entity);
	public TsscAdmin merge(TsscAdmin entity);
	public void delete(TsscAdmin entity);
	public TsscAdmin findById(long id);

}
