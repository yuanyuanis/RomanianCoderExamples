package rc.bootsecurity.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PASSWORD_RESET_TOKEN")
public class PasswordResetToken {
	
	private static final int EXPIRATION =60*24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String token;
	
	@OneToOne()
	@JoinColumn(nullable=false, name ="user_id")
	private User user;
	
	@Column(nullable=false)
	private Date expirityDate;
	
	public PasswordResetToken() {
		Calendar ahora = Calendar.getInstance();
		ahora.add(Calendar.MINUTE, EXPIRATION);
		this.expirityDate = ahora.getTime();
		System.out.println("Borrar Mandanga, la fecha actual es: "+ this.expirityDate);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpirityDate() {
		return expirityDate;
	}

	

	public static int getExpiration() {
		return EXPIRATION;
	}

	public boolean isExpired() {
		return this.expirityDate.before(new Date());
	}
}
