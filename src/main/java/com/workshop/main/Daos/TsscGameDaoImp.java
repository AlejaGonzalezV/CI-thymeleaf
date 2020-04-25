package com.workshop.main.Daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.workshop.main.model.TsscGame;

@Repository
@Scope("singleton")
public class TsscGameDaoImp implements TsscGameDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscGame save(TsscGame entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public TsscGame merge(TsscGame entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public void delete(TsscGame entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public TsscGame findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscGame.class, id);
	}
	
	@Override
	public List<TsscGame> findAll() {
		String jpql = "SELECT t FROM TsscGame t";
		return entityManager.createQuery(jpql).getResultList();
	}
	

}
