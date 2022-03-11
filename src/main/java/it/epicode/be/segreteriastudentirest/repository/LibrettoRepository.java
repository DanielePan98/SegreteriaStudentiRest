package it.epicode.be.segreteriastudentirest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.epicode.be.segreteriastudentirest.model.Libretto;

public interface LibrettoRepository extends JpaRepository<Libretto, Long> {
	@Query("SELECT l FROM Libretto l WHERE l.codiceLibretto=:codice")
	Page<Libretto> findAllByCodice(Pageable pageable,@Param("codice")String codice);

}
