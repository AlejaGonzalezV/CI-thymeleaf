package com.workshop.main.Daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import com.workshop.main.model.TsscStory;

@Repository
@Scope("singleton")
public class TsscStoryDaoImp implements TsscStoryDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscStory save(TsscStory entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public TsscStory merge(TsscStory entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public void delete(TsscStory entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public TsscStory findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscStory.class, id);
	}
	
	@Override
	public List<TsscStory> findAll() {
		String jpql = "SELECT t FROM TsscGame t";
		return entityManager.createQuery(jpql).getResultList();
	}

}
