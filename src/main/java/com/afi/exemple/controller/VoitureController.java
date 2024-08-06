package com.afi.exemple.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.afi.exemple.model.Voiture;
import com.afi.exemple.service.VoitureService;

@Controller
public class VoitureController {
	
	@Autowired
	private VoitureService vs;
	
	@GetMapping("/")
	public String index(Model model) {
		Iterable<Voiture> listeVoitures = vs.listerVoitures();
		model.addAttribute("voitures", listeVoitures);
		
		return "index";
	}
	
	@GetMapping("/new")
	public String createVoiture(Model model) {
		Voiture v = new Voiture();
		model.addAttribute("voiture", v);
		return "ajouterVoiture";
	}
	
	@GetMapping("/edit/{id}")
	public String createVoiture(Model model, @PathVariable("id") final Long id) {
		Optional<Voiture> v = vs.rechercherParId(id); 
		
		if (v.isPresent()) {
			model.addAttribute("voiture", v);
			return "modifierVoiture";			
		} else { return "index"; }		
	}

	@PostMapping("/save")
	public ModelAndView saveEmployee(@ModelAttribute Voiture v) {
		System.out.println("Id de la voiture: "+v.getId());
		vs.saveVoiture(v);
		
		return new ModelAndView("redirect:/");	
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView deleteVoiture(@PathVariable("id") final Long id) {
		vs.deleteVoiture(id);
		
		return new ModelAndView("redirect:/");		
	}
	
	// SEARCH RESULTS
	@GetMapping("/search")
	public String searchVoitures(Model model, @RequestParam String termes, @RequestParam String critere, @RequestParam int min, @RequestParam int max) {
	Iterable<Voiture> resultats = null;
	
		switch (critere) {
			case "marque": {
				resultats = vs.rechercherParMarque(termes);
				System.out.println("Resultats: "+ resultats.toString());
				break;
			}
				
			case "provenance": {
				resultats = vs.rechercherParProvenance(termes);
				break;
			}
			
			case "prix": 
			{
				resultats = vs.rechercherParPrix(min, max);
			}
		}
		
		model.addAttribute("resultats", resultats);
		model.addAttribute("termes", termes);
		
		return "searchResults.html";
	}
}
