	package com.workshop.main.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.main.Daos.TsscGameDao;
import com.workshop.main.model.TsscGame;
import com.workshop.main.model.TsscStory;
import com.workshop.main.model.TsscTopic;
import com.workshop.main.repositories.TsscGameRepository;


@Service
public class TsscGameServiceImp implements TsscGameService {
	
	@Autowired
	private TsscGameDao game;
	@Autowired
	private TsscTopicServiceImp repo;

	@Autowired
	public TsscGameServiceImp(TsscGameDao game, TsscTopicServiceImp repo) {
		super();
		this.game = game;
		this.repo = repo;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscGame addGameT(TsscGame g, long id) {
		if (g != null && g.getNGroups() > 0 && g.getNSprints() > 0) {
			TsscTopic find = repo.findTopic(id);
			if (find != null) {

				g.setTsscTopic(find);
				game.save(g);
				List<TsscGame> lista = new ArrayList<TsscGame>();
				find.setTsscGames(lista);
				find.addTsscGame(g);
				repo.update(find);
				return g;
			}

			else {

				return null;
				
			}

		} else {
			
			return null;
			
		}

		
	}

	
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscGame addGame(TsscGame g) {
		if (g !=null && g.getNGroups() > 0 && g.getNSprints() > 0) {

			game.save(g);
			return g;
		}

		return null;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscGame setGame(TsscGame g, int groups, String name) {

		if (g != null && groups > 0 && name != null&&!name.equals("") && game.existById(g.getId())) {
			g.setName(name);
			g.setNGroups(groups);
			game.save(g);
			return g;
		}

		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscGame addGame2(TsscGame g, long id) {

		if (g != null && g.getNGroups() > 0 && g.getNSprints() > 0) {
			TsscTopic find = repo.findTopic(id);
		
			if (find != null) {

				g.setTsscStories(find.getTsscStories());
				g.setTsscTimecontrol(find.getTsscCronograma());			
				g.setTsscTopic(find);
				game.save(g);
				List<TsscGame> lista = new ArrayList<TsscGame>();
				find.setTsscGames(lista);
				find.addTsscGame(g);
				repo.update(find);
				return g;

			}

		}
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscGame findGame(long id) {
		if(game.existById(id)) {
			
			return game.findById(id);
			
		} else {
			
			return null;
			
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean existById(long id) {
		
		return game.existById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscStory addStory(TsscStory st, TsscGame g) {
		
		g.addTsscStory(st);
		game.merge(g);
		return st;
		
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Iterable<TsscGame> findAll() {
		return game.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TsscGame g) {
		
		game.delete(g);
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(TsscGame g) {
		game.merge(g);
		
	}
	
//	@Autowired
//	private TsscGameRepository game;
//	@Autowired
//	private TsscTopicServiceImp repo;
//
//	@Autowired
//	public TsscGameServiceImp(TsscGameRepository game, TsscTopicServiceImp repo) {
//		super();
//		this.game = game;
//		this.repo = repo;
//	}
//
//	@Override
//	public TsscGame addGameT(TsscGame g, long id) {
//		if (g != null && g.getNGroups() > 0 && g.getNSprints() > 0) {
//			TsscTopic find = repo.findTopic(id);
//			if (find != null) {
//
//				g.setTsscTopic(find);
//				game.save(g);
//				return g;
//			}
//
//			else {
//
//				return null;
//				
//			}
//
//		} else {
//			
//			return null;
//			
//		}
//
//		
//	}
//
//	
//	
//	
//	@Override
//	public TsscGame addGame(TsscGame g) {
//		if (g !=null && g.getNGroups() > 0 && g.getNSprints() > 0) {
//
//			game.save(g);
//			return g;
//		}
//
//		return null;
//
//	}
//
//	@Override
//	public TsscGame setGame(TsscGame g, int groups, String name) {
//
//		if (g != null && groups > 0 && name != null&&!name.equals("") && game.existsById(g.getId())) {
//			g.setName(name);
//			g.setNGroups(groups);
//			game.save(g);
//			return g;
//		}
//
//		return null;
//	}
//
//	@Override
//	public TsscGame addGame2(TsscGame g, long id) {
//
//		if (g != null && g.getNGroups() > 0 && g.getNSprints() > 0) {
//			TsscTopic find = repo.findTopic(id);
//		
//			if (find != null) {
//
//				g.setTsscStories(find.getTsscStories());
//				g.setTsscTimecontrol(find.getTsscCronograma());
//				g.setTsscTopic(find);
//				game.save(g);
//				return g;
//
//			}
//
//		}
//		return null;
//	}
//
//	@Override
//	public TsscGame findGame(long id) {
//		if(game.existsById(id)) {
//			
//			return game.findById(id).get();
//			
//		} else {
//			
//			return null;
//			
//		}
//	}
//
//	@Override
//	public boolean existById(long id) {
//		
//		return game.existsById(id);
//	}
//
//	@Override
//	public TsscStory addStory(TsscStory st, TsscGame g) {
//		
//		return g.addTsscStory(st);
//	}
//	
//	@Override
//	public Iterable<TsscGame> findAll() {
//		return game.findAll();
//	}
//
//	@Override
//	public void delete(TsscGame g) {
//		
//		game.delete(g);
//		
//	}
	
	
}
