package rc.bootsecurity.model;

import java.util.List;

import javax.persistence.Entity;


@Entity
public class Examen {
	
	private Long id;
	
	private List<Pregunta> listaPreguntas;
}
