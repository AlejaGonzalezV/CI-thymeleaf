package com.workshop.main.Daos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

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


	@Override
	public List<TsscTopic> findByName(String name) {
		String q = "Select t from TsscTopic t where t.name = :name";
		Query query = entityManager.createQuery(q);
		query.setParameter("name", name);
		return query.getResultList();
		
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		
		String q = "Select t from TsscTopic t where t.description = :description";
		Query query = entityManager.createQuery(q);
		query.setParameter("description", description);
		return query.getResultList();
		
	}

	@Override
	public List<Object[]> findTopicsByGameDate(LocalDate date) {
			
		List<Pair<TsscTopic, Integer>> list = new ArrayList<>();
			
		String q = "SELECT b.tsscTopic , count(b.tsscTopic) FROM TsscGame b WHERE b.id IN (SELECT a.id from TsscGame a WHERE a.scheduledDate = :date"
				+ "  ORDER BY a.scheduledTime DESC) GROUP BY b.tsscTopic";
		//String q = "Select a from TsscGame a Where (a.scheduledDate =:date AND (((SELECT Count(b) FROM TsscTimecontrol b WHERE b.tsscGame.id = a.id)=0) OR (SELECT Count(c) FROM TsscStory c WHERE c.TsscGame.id = a.id ) < 10))";		
		Query query = entityManager.createQuery(q);
		query.setParameter("date", date);
		return query.getResultList();
		
	}

	@Override
	public boolean existById(long id) {
		
		if(entityManager.find(TsscTopic.class, id) == null) {
			
			return false;
			
		}else {
			
			return true;
			
		}
		
	}

		


}
