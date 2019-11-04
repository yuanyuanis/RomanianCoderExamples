package rc.bootsecurity.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UserPregunta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id")
	private Pregunta pregunta;

	@ManyToOne
	@JoinColumn(name = "id")
	private User user;

	@OneToMany
	private List<Respuesta> listRespuestasUsuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Respuesta> getListRespuestasUsuario() {
		return listRespuestasUsuario;
	}

	public void setListRespuestasUsuario(List<Respuesta> listRespuestasUsuario) {
		this.listRespuestasUsuario = listRespuestasUsuario;
	}
}
