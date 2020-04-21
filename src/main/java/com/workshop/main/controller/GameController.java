package com.workshop.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop.main.model.TsscGame;
import com.workshop.main.model.TsscTopic;
import com.workshop.main.services.TsscGameService;
import com.workshop.main.services.TsscGameServiceImp;
import com.workshop.main.services.TsscStoryServiceImp;
import com.workshop.main.services.TsscTopicService;
import com.workshop.main.services.TsscTopicServiceImp;

@Controller
public class GameController {
	
	private TsscGameServiceImp gameServ;

	private TsscTopicServiceImp topicServ;
	
	private TsscStoryServiceImp storyServ;
	
	
	@Autowired
	public GameController(TsscGameServiceImp gameServ, TsscTopicServiceImp topicServ, TsscStoryServiceImp storyServ) {
		this.gameServ = gameServ;
		this.topicServ = topicServ;
		this.storyServ = storyServ;
		
	}

	@GetMapping("/game/")
	public String index(Model model) {
		model.addAttribute("games", gameServ.findAll());
		return "game/index";
	}


	@GetMapping("/game/add")
	public String addGame(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		model.addAttribute("topics", topicServ.findAll());
		return "game/add-game";
	}

	@PostMapping("/game/add")
	public String addGame(@Validated TsscGame tsscGame, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {

		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {

				return "game/add-game";
				
			} else {

					if (tsscGame.getTsscTopic() == null) {

						gameServ.addGame(tsscGame);

					} else {

						gameServ.addGameT(tsscGame, tsscGame.getTsscTopic().getId());
					}

				return "redirect:/game/";
			}
			
		} else {

			model.addAttribute("games", gameServ.findAll());
			return "game/index";
		}

	}


	@GetMapping("/game/edit/{id}")
	public String setGame(@PathVariable("id") long id, Model model) {
		TsscGame game = gameServ.findGame(id);

		if (game == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);

		model.addAttribute("tsscGame", game);


		return "game/update-game";
	}

	@PostMapping("/game/edit/{id}")
	public String updateGame(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			@Validated TsscGame tsscGame, BindingResult bindingResult, Model model) {

		if (action.equals("Cancelar")) {

			return "redirect:/game/";
			
		} else if (bindingResult.hasErrors()) {

			return "game/update-game";
			
		}else if (action != null && !action.equals("Cancelar")) {

				if (tsscGame.getTsscTopic() == null) {

					gameServ.addGame(tsscGame);

				} else {

					gameServ.addGameT(tsscGame, tsscGame.getTsscTopic().getId());
				}
			 
		}

		return "redirect:/game/";
	}

	@GetMapping("/game/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		TsscGame tsscGame = gameServ.findGame(id);
		
		if(tsscGame.getTsscStories() != null) {
			for(int i = 0; i < tsscGame.getTsscStories().size(); i++) {
				storyServ.delete(tsscGame.getTsscStories().get(i));
			}
		}
		
		gameServ.delete(tsscGame);
		return "redirect:/game/";
	}
	
	@GetMapping("/game/topic/{id}")
	public String indexTopic(Model model, @PathVariable("id") long id) {
		List<TsscTopic> list = new ArrayList<TsscTopic>();
		TsscGame tsscGame = gameServ.findGame(id);
		List<TsscGame> list2 = new ArrayList<TsscGame>();
		list2.add(tsscGame);
		if(tsscGame.getTsscTopic()!=null) {
			list.add(tsscGame.getTsscTopic());
		}
		
		model.addAttribute("game", list2);
		model.addAttribute("topics", list);
		
		return "game/index-topic";
	}
	
	@GetMapping("/game/add-topic/{id}")
	public String ChangeTopic(Model model, @PathVariable("id") long id) {
		
		TsscGame game = gameServ.findGame(id);
		model.addAttribute("tsscGame", game);
		model.addAttribute("topics", topicServ.findAll());
		return "game/add-topic";
	}
	
	@PostMapping("/game/add-topic/{id}")
	public String ChangeTopicPost(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			TsscGame tsscGame, Model model) {
		
		
		if (action.equals("Cancelar")) {

			return "redirect:/game/";
		}

		if (action != null && !action.equals("Cancelar")) {

			System.out.println(id);
			TsscGame game = gameServ.findGame(id);
			//CAMBIO
			if(tsscGame.getTsscTopic() == null) {
				game.setTsscTopic(null);
					
				gameServ.addGame(game);
			}else {
				
				gameServ.addGameT(game, tsscGame.getTsscTopic().getId());
			}
		}

		return "redirect:/game/";

	}
	
	@GetMapping("/game/story/{id}")
	public String indexStory(Model model, @PathVariable("id") long id) {
	
		TsscGame tsscGame = gameServ.findGame(id);
		model.addAttribute("stories", tsscGame.getTsscStories());
		
		return "game/index-stories";
	}
	
	
	
}