package com.workshop.main.Daos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	//Punto 1b)
	

		@Override
		public List<TsscGame> findByNameTopic(String name) {
			
			String q = "SELECT t FROM TsscGame t where t.tsscTopic.name = :name";
			Query query = entityManager.createQuery(q);
			query.setParameter("name", name);
			return query.getResultList();
			
		}

		@Override
		public List<TsscGame> findByDescriptionTopic(String description) {
			
			String q = "SELECT t FROM TsscGame t where t.tsscTopic.description = :description";
			Query query = entityManager.createQuery(q);
			query.setParameter("description", description);
			return query.getResultList();
			
		}

		@Override
		public List<TsscGame> findByIdTopic(long idTopic) {

			String q = "SELECT t FROM TsscGame t where t.tsscTopic.id = :id";
			Query query = entityManager.createQuery(q);
			query.setParameter("id", idTopic);
			return query.getResultList();
		}

		//1c) 
		
		@Override
		public List<TsscGame> findByDates(LocalDate date1, LocalDate date2) {

			String q = "SELECT t FROM TsscGame t where t.scheduledDate between :date1 and :date2";
			Query query = entityManager.createQuery(q);
			query.setParameter("date1", date1);
			query.setParameter("date2", date2);
			return query.getResultList();
		}

		@Override
		public List<TsscGame> findByDateHours(LocalDate date, LocalTime h1, LocalTime h2) {

			String q = "SELECT t FROM TsscGame t where t.scheduledDate = :date and  t.scheduledTime between :h1 and :h2";
			Query query = entityManager.createQuery(q);
			query.setParameter("h1", h1);
			query.setParameter("h2", h2);
			return query.getResultList();
		}
		
		//2b)
		//Mostrar los juegos que están programados para una fecha pero tienen menos de diez
		//historias asociadas para una fecha dada o no tienen al menos un cronómetro
		//especificado

		@Override
		public List<TsscGame> findByDateStoryTime(LocalDate date) {
			
			String q = "SELECT t FROM TsscGame t where t.scheduledDate = :date and (size(t.tsscStories) < 10 or size(t.tsscTimecontrols)>0";
			Query query = entityManager.createQuery(q);
			query.setParameter("date", date);
			return query.getResultList();
		}
	

}
