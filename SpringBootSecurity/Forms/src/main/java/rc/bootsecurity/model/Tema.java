package rc.bootsecurity.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Modulo modulo;
	
	@Column(nullable=false, length=100)
	private String nombreTema;
	
	@Column(nullable=false, length=200)
	private String descTema;
	
	@OneToMany
	private List<Pregunta> listaPreguntas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public String getNombreTema() {
		return nombreTema;
	}

	public void setNombreTema(String nombreTema) {
		this.nombreTema = nombreTema;
	}

	public String getDescTema() {
		return descTema;
	}

	public void setDescTema(String descTema) {
		this.descTema = descTema;
	}

	public List<Pregunta> getListaPreguntas() {
		return listaPreguntas;
	}

	public void setListaPreguntas(List<Pregunta> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}
}
