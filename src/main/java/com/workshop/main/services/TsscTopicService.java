package com.workshop.main.services;

import java.util.Optional;

import com.workshop.main.model.TsscTopic;

public interface TsscTopicService {
	
	public TsscTopic addTopic(TsscTopic topic);
	public TsscTopic setTopic(TsscTopic topic, String name, String description);
	public TsscTopic findTopic(Long id); 
	public boolean existById(long id);
	public Iterable<TsscTopic> findAll();
	public void delete(TsscTopic topic);
	

}
