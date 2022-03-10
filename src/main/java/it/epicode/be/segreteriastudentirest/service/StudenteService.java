package it.epicode.be.segreteriastudentirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.segreteriastudentirest.model.Studente;
import it.epicode.be.segreteriastudentirest.repository.StudenteRepository;

@Service
public class StudenteService {

	@Autowired
	StudenteRepository studenteRepository;

	public Page<Studente> findAll(Pageable pageable) {
		return studenteRepository.findAll(pageable);

	}
	
	public Page<Studente> findByCorsoDiLaureaId(Long id,Pageable pageable){
		return studenteRepository.findByCorsoDiLaureaId(id, pageable);
		
	}
	
	public Optional<Studente> findByLibrettoId(Long id){
		return studenteRepository.findByLibrettonuovoId(id);
		
	}
	
	public List<Studente> findAll() {
		return studenteRepository.findAll();

	}

	public Optional<Studente> findById(Long id) {
		return studenteRepository.findById(id);
	}

	public Studente save(Studente studente) {
		return studenteRepository.save(studente);
	}

	public Studente update(Studente studente, Long id) {
		Optional<Studente> studenteResult = studenteRepository.findById(id);
		if (studenteResult.isPresent()) {
			Studente studenteUpdate = studenteResult.get();
			studenteUpdate.setNome(studente.getNome());
			studenteUpdate.setCognome(studente.getCognome());
			studenteUpdate.setEmail(studente.getEmail());
			studenteUpdate.setCorsoDiLaurea(studente.getCorsoDiLaurea());
			studenteUpdate.setCitta(studente.getCitta());
			studenteUpdate.setIndirizzo(studente.getIndirizzo());
			return studenteRepository.save(studenteUpdate);

		} else {
			return null; // TODO implementare expection
		}
	}

	public void delete(Long id) {
		studenteRepository.deleteById(id);
	}

}
