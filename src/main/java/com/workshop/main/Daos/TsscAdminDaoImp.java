package com.workshop.main.Daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.workshop.main.model.TsscAdmin;

@Repository
@Scope("singleton")
public class TsscAdminDaoImp implements TsscAdminDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscAdmin save(TsscAdmin entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public TsscAdmin merge(TsscAdmin entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public void delete(TsscAdmin entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public TsscAdmin findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscAdmin.class, id);
	}

}
