package com.workshop.main.Daos;

import java.util.List;

import com.workshop.main.model.TsscTopic;

public interface TsscTopicDao {
	
	public TsscTopic save(TsscTopic entity);
	public TsscTopic merge(TsscTopic entity);
	public void delete(TsscTopic entity);
	public TsscTopic findById(long id);
	public List<TsscTopic> findAll();

}
