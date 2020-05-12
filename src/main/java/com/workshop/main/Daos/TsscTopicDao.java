package com.workshop.main.Daos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.util.Pair;

import com.workshop.main.model.TsscTopic;

public interface TsscTopicDao {
	
	public TsscTopic save(TsscTopic entity);
	public TsscTopic merge(TsscTopic entity);
	public void delete(TsscTopic entity);
	public TsscTopic findById(long id);
	public List<TsscTopic> findAll();
	
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String description);
	public List<Object[]> findTopicsByGameDate(LocalDate date);
	
	public boolean existById(long id);
	
		

}
