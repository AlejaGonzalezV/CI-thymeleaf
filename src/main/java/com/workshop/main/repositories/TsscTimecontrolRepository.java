package com.workshop.main.repositories;


import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.workshop.main.model.TsscTimecontrol;


@Repository
public interface TsscTimecontrolRepository extends CrudRepository<TsscTimecontrol, Long>{

}
