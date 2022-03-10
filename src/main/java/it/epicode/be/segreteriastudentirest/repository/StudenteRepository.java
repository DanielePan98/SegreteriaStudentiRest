package it.epicode.be.segreteriastudentirest.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.segreteriastudentirest.model.Studente;

public interface StudenteRepository extends JpaRepository<Studente, Long>{
	
	Page<Studente> findByCorsoDiLaureaId(Long id,Pageable pageable);
	Optional<Studente> findByLibrettonuovoId(Long id);

}
