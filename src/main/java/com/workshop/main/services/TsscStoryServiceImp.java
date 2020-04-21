package com.workshop.main.services;


import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.main.model.TsscGame;
import com.workshop.main.model.TsscStory;
import com.workshop.main.repositories.TsscGameRepository;
import com.workshop.main.repositories.TsscStoryRepository;


@Service
public class TsscStoryServiceImp implements TsscStoryService{
	
	@Autowired
	private TsscStoryRepository story;
	@Autowired
	private TsscGameRepository game;
	@Autowired
	private TsscGameService gameServ;

	@Autowired
	public TsscStoryServiceImp(TsscStoryRepository story, TsscGameRepository game, TsscGameService gameServ) {
		super();
		this.story = story;
		this.game = game;
		this.gameServ = gameServ;
	}


	@Override
	public TsscStory addStory(TsscStory s, long id) {

		if (s != null && s.getBusinessValue().compareTo(BigDecimal.valueOf(0)) == 1
				&& s.getInitialSprint().compareTo(BigDecimal.valueOf(0)) == 1
				&& s.getPriority().compareTo(BigDecimal.valueOf(0)) == 1 && gameServ.existById(id)) {

			gameServ.addStory(s, gameServ.findGame(id));
			story.save(s);
			return s;
		}

		return null;
	}

	@Override
	public TsscStory setStory(TsscStory s, String d1, String d) {

		if (s != null && d1 != null && !d1.equals("") && d != null
				&& !d.equals("")) {
			s.setAltDescripton(d1);
			s.setDescription(d);
			story.save(s);
			return s;
		}

		return null;
	}
	
	
	@Override
	public TsscStory findStory(Long id) {
		if (story.findById(id).get() != null){
			 return story.findById(id).get();
		}
		return null;
	}


	@Override
	public boolean existById(long id) {
		
		return story.existsById(id);
	}

	@Override
	public void delete(TsscStory st) {
		TsscGame game = st.getTsscGame();
		game.getTsscStories().remove(st);
		story.delete(st);
		
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return story.findAll();
	}


}
