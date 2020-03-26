package com.workshop.main.repositories;


import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.workshop.main.model.TsscTopic;


@Repository
public interface TsscTopicRepository extends CrudRepository<TsscTopic, Long> {


	 
}
