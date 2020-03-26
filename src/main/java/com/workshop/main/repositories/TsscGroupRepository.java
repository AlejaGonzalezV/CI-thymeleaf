package com.workshop.main.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.workshop.main.model.TsscGroup;


@Repository
public interface TsscGroupRepository extends CrudRepository<TsscGroup, Long>{

}
