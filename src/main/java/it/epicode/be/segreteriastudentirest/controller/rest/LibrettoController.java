package it.epicode.be.segreteriastudentirest.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.segreteriastudentirest.model.Libretto;
import it.epicode.be.segreteriastudentirest.service.LibrettoService;

@RestController
@RequestMapping("/api")
public class LibrettoController {
	
	@Autowired
	LibrettoService librettoService;
	
	@GetMapping("/libretto/{codice}")
	public ResponseEntity<Page<Libretto>> findAllByCodice(Pageable pageable,@PathVariable String codice) {
		Page<Libretto> tempLibretto = librettoService.findAllByCodice(pageable, codice);
		return new ResponseEntity<>(tempLibretto,HttpStatus.OK);
 	}

}
