package com.workshop.main.controller;


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
import com.workshop.main.services.TsscTopicService;


@Controller
public class TopicController {
	
	private TsscTopicService topicServ;
	private TsscGameService gameServ;

	@Autowired
	public TopicController(TsscTopicService topicServ, TsscGameService gameServ) {
		this.topicServ = topicServ;
		this.gameServ = gameServ;
	}

	@GetMapping("/topic/")
	public String index(Model model) {
		model.addAttribute("topics", topicServ.findAll());
		return "topic/index";
	}

	@GetMapping("/topic/add")
	public String addTopic(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "topic/add-topic";
	}

	@PostMapping("/topic/add")
	public String addTopic(@Validated TsscTopic top, BindingResult br, Model model, @RequestParam(value = "action", required = true) String action) {

		if (br.hasErrors()) {
			
			return "topic/add-topic";

		} else {
			if (!action.equals("Cancelar")) {
				topicServ.addTopic(top);
			}	
			return "redirect:/topic/";
		}
	}
	
	@GetMapping("/topic/edit/{id}")
	public String editTopic(@PathVariable("id") long id, Model model) {
		TsscTopic top = topicServ.findTopic(id);

		if (top == null)
			throw new IllegalArgumentException("Invalid Id:" + id);

		model.addAttribute("tsscTopic", top);


		return "topic/update-topic";
	}
	
	@PostMapping("/topic/edit/{id}")
	public String editTopic(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, TsscTopic tsscTopic, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			return "topic/update-topic";
			
		}else if (action.equals("Cancelar")) {

			return "redirect:/topic/";
			
		}else if (action != null && !action.equals("Cancelar")) {
			
				topicServ.addTopic(tsscTopic);
			
		}

		return "redirect:/topic/";
	}
	

	@GetMapping("/topic/del/{id}")
	public String delete(@PathVariable("id") long id) {
		TsscTopic tsscTopic = topicServ.findTopic(id);
		
		for(TsscGame ga : gameServ.findAll()) {
			
			if(ga.getTsscTopic() != null) {
				
				if(ga.getTsscTopic().equals(tsscTopic)) {
					
					ga.setTsscTopic(null);
					
				}
				
			}
			
		}
		
		topicServ.delete(tsscTopic);
		return "redirect:/topic/";
	}
	
}
