package com.workshop.main.Daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.workshop.main.model.TsscTimecontrol;
import com.workshop.main.model.TsscTopic;

@Repository
@Scope("singleton")
public class TsscTopicDaoImp implements TsscTopicDao{
	

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscTopic save(TsscTopic entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public TsscTopic merge(TsscTopic entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public void delete(TsscTopic entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public TsscTopic findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscTopic.class, id);
	}
	
	@Override
	public List<TsscTopic> findAll() {
		String jpql = "SELECT t FROM TsscGame t";
		return entityManager.createQuery(jpql).getResultList();
	}

}
