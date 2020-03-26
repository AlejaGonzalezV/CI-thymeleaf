package com.workshop.main.services;

import com.workshop.main.model.TsscTopic;

public interface TsscTopicService {
	
	public TsscTopic addTopic(TsscTopic topic);
	public TsscTopic setTopic(TsscTopic topic, String name, String description);
	public TsscTopic findTopic(Long id); 

}
