package com.workshop.main.Daos;

import java.util.List;

import com.workshop.main.model.TsscStory;

public interface TsscStoryDao {
	
	public TsscStory save(TsscStory entity);
	public TsscStory merge(TsscStory entity);
	public void delete(TsscStory entity);
	public TsscStory findById(long id);
	public List<TsscStory> findAll();
	public boolean existById(long id);


}
