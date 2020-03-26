package com.workshop.main.repositories;


import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.workshop.main.model.TsscGame;


@Repository
public interface TsscGameRepository extends CrudRepository<TsscGame, Long> {
	
	
	
}
