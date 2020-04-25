package com.workshop.main.Daos;

import java.util.List;

import com.workshop.main.model.TsscGame;

public interface TsscGameDao {
	
	public TsscGame save(TsscGame entity);
	public TsscGame merge(TsscGame entity);
	public void delete(TsscGame entity);
	public TsscGame findById(long id);
	public List<TsscGame> findAll();

}
