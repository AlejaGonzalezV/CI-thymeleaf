package com.workshop.main.repositories;


import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.workshop.main.model.TsscSprint;


@Repository
public interface TsscSprintRepository extends CrudRepository<TsscSprint, Long>{

}
