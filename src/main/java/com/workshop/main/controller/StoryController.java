package com.workshop.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop.main.model.TsscStory;
import com.workshop.main.services.TsscGameServiceImp;
import com.workshop.main.services.TsscStoryServiceImp;

@Controller
public class StoryController {
	
	private TsscStoryServiceImp storyServ;

	private TsscGameServiceImp gameServ;

	@Autowired
	public StoryController(TsscStoryServiceImp storyServ, TsscGameServiceImp gameServ) {
		this.storyServ = storyServ;
		this.gameServ = gameServ;
	}

	@GetMapping("/story/")
	public String indexStory(Model model) {
		model.addAttribute("stories", storyServ.findAll());
		return "story/index";
	}

	@GetMapping("/story/add")
	public String addStory(Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("games", gameServ.findAll());
		return "story/add-story";
	}

	@PostMapping("/story/add")
	public String addStoryPost(@Validated TsscStory tsscStory, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("games", gameServ.findAll());
			return "story/add-story";

		} else {
			if (!action.equals("Cancelar")) {
				
				storyServ.addStory(tsscStory, tsscStory.getTsscGame().getId());
			}	
			return "redirect:/story/";
		}
	}

	@GetMapping("/story/edit/{id}")
	public String updateStory(@PathVariable("id") long id, Model model) {
		TsscStory story = storyServ.findStory(id);

		if (story == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);

		model.addAttribute("tsscStory", story);
		model.addAttribute("games", gameServ.findAll());

		return "story/update-story";
	}

	@PostMapping("story/edit/{id}")
	public String updateStoryPost(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			@Validated TsscStory tsscStory, BindingResult bindingResult, Model model) {

		if (action.equals("Cancelar")) {

			return "redirect:/story/";
		}

		if (bindingResult.hasErrors()) {

			model.addAttribute("games", gameServ.findAll());			
			return "story/update-story";
		}

		if (action != null && !action.equals("Cancelar")) {

			
				storyServ.addStory(tsscStory, tsscStory.getTsscGame().getId());
		}

		return "redirect:/story/";
	}

	@GetMapping("/story/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		TsscStory tsscStory = storyServ.findStory(id);
		storyServ.delete(tsscStory);
		return "redirect:/story/";
	}

	

}
