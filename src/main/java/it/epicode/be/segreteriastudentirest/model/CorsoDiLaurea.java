package it.epicode.be.segreteriastudentirest.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
public class CorsoDiLaurea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codice;
	private Integer numeroEsami;
	private String nome;
	private String indirizzo;

	@OneToMany(mappedBy = "corsoDiLaurea")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<Studente> studenti;

	@ManyToMany
	@JoinTable(name = "docenti_corsi", joinColumns = @JoinColumn(name = "corso_id"), inverseJoinColumns = @JoinColumn(name = "docente_id"))
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<Docente> docenti;

	@Override
	public String toString() {
		return "CorsoDiLaurea [id=" + id + ", codice=" + codice + ", numeroEsami=" + numeroEsami + ", nome=" + nome
				+ ", indirizzo=" + indirizzo + "]";
	}

}
