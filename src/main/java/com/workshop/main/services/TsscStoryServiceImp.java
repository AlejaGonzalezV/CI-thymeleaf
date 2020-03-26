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
	public TsscStoryServiceImp(TsscStoryRepository story, TsscGameRepository game) {
		super();
		this.story = story;
		this.game = game;
	}


	@Override
	public TsscStory addStory(TsscStory s, long id) {

		if (s != null && s.getBusinessValue().compareTo(BigDecimal.valueOf(0)) == 1
				&& s.getInitialSprint().compareTo(BigDecimal.valueOf(0)) == 1
				&& s.getPriority().compareTo(BigDecimal.valueOf(0)) == 1 && game.findById(id).isPresent()) {
			TsscGame g = game.findById(id).get();

			g.addTsscStory(s);
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

}
