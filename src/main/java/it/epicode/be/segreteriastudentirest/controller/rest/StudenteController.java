package it.epicode.be.segreteriastudentirest.controller.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.segreteriastudentirest.model.Studente;
import it.epicode.be.segreteriastudentirest.service.StudenteService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class StudenteController {

	@Autowired
	StudenteService studenteService;

	@GetMapping("/studente")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Page<Studente>> findAll(Pageable pageable) {
		log.info("*** getAllStudenti ***");
		Page<Studente> findAll = studenteService.findAll(pageable);
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			log.info("*** Studenti non trovati ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/studente/{id}")
	public ResponseEntity<Studente> findById(@PathVariable Long id) {
		log.info("*** getById ***");
		Optional<Studente> findById = studenteService.findById(id);
		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		} else {
			log.info("*** Corso non trovato ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/studente")
	public ResponseEntity<Studente> save(@RequestBody Studente studente) {
		log.info("*** Inizio salvataggio studente ***");
		Studente s = studenteService.save(studente);
		log.info("*** Fine salvataggio studente ***");
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}

	@DeleteMapping("/studente/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("*** Inizio cancella studente ***");
		studenteService.delete(id);
		log.info("*** Fine cancella studente ***");
		return new ResponseEntity<String>("Studente eliminato", HttpStatus.OK);
	}

	@PutMapping("/studente/{id}")
	public ResponseEntity<Studente> update(@RequestBody Studente studente, @PathVariable Long id) {
		log.info("*** Inizio modifica studente ***");
		Studente s = studenteService.update(studente, id);
		log.info("*** Fine modifica studente ***");
		return new ResponseEntity<>(s, HttpStatus.OK);

	}
	
	@GetMapping("/studentibycorso/{id}")
	public ResponseEntity<Page<Studente>> findStudentiByCorso(@PathVariable Long id,Pageable pageable){
		Page<Studente> pagina=studenteService.findByCorsoDiLaureaId(id, pageable);
		if(pagina.hasContent()) {
			return new ResponseEntity<>(pagina,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/studentibylibretto/{id}")
	public ResponseEntity<Studente> findStudentiByLibretto(@PathVariable Long id){
		Optional<Studente> pagina=studenteService.findByLibrettoId(id);
		if(pagina.isPresent()) {
			return new ResponseEntity<>(pagina.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
}
