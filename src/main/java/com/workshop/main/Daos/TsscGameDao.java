package com.workshop.main.Daos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.workshop.main.model.TsscGame;

public interface TsscGameDao {
	
	public TsscGame save(TsscGame entity);
	public TsscGame merge(TsscGame entity);
	public void delete(TsscGame entity);
	public TsscGame findById(long id);
	public List<TsscGame> findAll();
	
	public List<TsscGame> findByNameTopic(String name);
	public List<TsscGame> findByDescriptionTopic(String description);
	public List<TsscGame> findByIdTopic(long idTopic);
	public List<TsscGame> findByDates(LocalDate date1, LocalDate date2);
	public List<TsscGame> findByDateHours(LocalDate date, LocalTime h1, LocalTime h2);
	public List<TsscGame> findByDateStoryTime(LocalDate date);
		
		

}
