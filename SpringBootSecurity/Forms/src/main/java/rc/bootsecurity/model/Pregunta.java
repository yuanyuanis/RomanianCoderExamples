package rc.bootsecurity.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pregunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="tema_id", nullable= false, updatable=false)
	private Tema tema;
	
	@Column(nullable=false, length=600)
	private String pregunta;
	
	private Blob imagen;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta")
	private List<Respuesta> answers;
	
	@Column(nullable=false, length=800)
	private String textoRespuestaCorrecta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Blob getImagen() {
		return imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}

	public List<Respuesta> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Respuesta> answers) {
		this.answers = answers;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	
	
	

}
