package com.workshop.main.Daos;

import java.util.List;

import com.workshop.main.model.TsscTimecontrol;

public interface TsscTimecontrolDao {
	
	public TsscTimecontrol save(TsscTimecontrol entity);
	public TsscTimecontrol merge(TsscTimecontrol entity);
	public void delete(TsscTimecontrol entity);
	public TsscTimecontrol findById(long id);
	public List<TsscTimecontrol> findAll();

}
