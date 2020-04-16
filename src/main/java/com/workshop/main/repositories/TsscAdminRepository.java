package com.workshop.main.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workshop.main.model.TsscAdmin;

@Repository
public interface TsscAdminRepository extends CrudRepository<TsscAdmin, Long>{

	TsscAdmin findByUsername(String user);
	
}
