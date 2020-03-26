package com.workshop.main.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.workshop.main.model.TsscStory;



@Repository
public interface TsscStoryRepository extends CrudRepository<TsscStory, Long> {
	

}
