package it.epicode.be.segreteriastudentirest.controller.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.segreteriastudentirest.model.CorsoDiLaurea;
import it.epicode.be.segreteriastudentirest.model.Studente;
import it.epicode.be.segreteriastudentirest.service.CorsoDiLaureaService;
import it.epicode.be.segreteriastudentirest.service.StudenteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/web")
public class StudenteControllerWeb {

	@Autowired
	StudenteService studenteService;
	
	@Autowired
	CorsoDiLaureaService corsoDiLaureaService;
	
	@GetMapping("/mostraelenco")
	public ModelAndView findAllStudenti() {
		log.info("*** findAllStudenti ***");
		ModelAndView view = new ModelAndView("visualizzaStudenti"); // all'interno delle parentesi facciamo riferimento al file html dedicato
		view.addObject("pippo", studenteService.findAll());
		return view;
	}
	
	@GetMapping("/mostraformaggiungi")
	public String aggiungiStudente(Studente studente, Model model) {
		log.info("*** aggiungiStudente ***");
		model.addAttribute("corsi", corsoDiLaureaService.findAll());
		return "aggiungiStudente";
	}
	
	@PostMapping("/aggiungistudente")
	// valid significa fa un controllo sul back end per verificare che il campo non sia vuoto
	public String aggiungiStudente(@Valid Studente studente, BindingResult bindingResult, Model model) {
		log.info("*** aggiunta in corso ***");
		if(bindingResult.hasErrors()) {
			model.addAttribute("corsi", corsoDiLaureaService.findAll());
			return "aggiungiStudente";
		}
		studenteService.save(studente);
		log.info("*** studente salvato ***");
		return "redirect:/web/mostraelenco";	
	}
	
	@GetMapping("/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable("id") Long id, Model model) {
		log.info("*** formAggiornaStudente ***");
		Optional<Studente> tempStudente = studenteService.findById(id);
		
		if(tempStudente.isPresent()) {
			ModelAndView view = new ModelAndView("aggiornaStudente");
			view.addObject("studente", tempStudente.get());
			view.addObject("corsi", corsoDiLaureaService.findAll());
			return view;
		}
		else {
			return new ModelAndView("error").addObject("message", "Id non trovato");
		}
	}
	
	@PutMapping("/aggiornastudente/{id}")
	// valid significa fa un controllo sul back end per verificare che il campo non sia vuoto
	public String aggiornaStudente(@Valid Studente studente,@PathVariable Long id, BindingResult bindingResult, Model model) {
		log.info("*** aggiornamento studente in corso ***");
		if(bindingResult.hasErrors()) {
			model.addAttribute("corsi", corsoDiLaureaService.findAll());
			return "aggiornaStudente";
		}
		studenteService.update(studente,id);
		log.info("*** studente aggiornato ***");
		return "redirect:/web/mostraelenco";	
	}
	/*
	 * TODO implementare controllo su libretto esistente
	 */
	@GetMapping("/eliminastudente/{id}")
	// valid significa fa un controllo sul back end per verificare che il campo non sia vuoto
	public String eliminaStudente(@PathVariable Long id, Model model) {
		log.info("*** cancellazione studente in corso ***");
		studenteService.delete(id);
		log.info("*** studente cancellato ***");
		return "visualizzaStudenti";	
	}
	
	
	
}
