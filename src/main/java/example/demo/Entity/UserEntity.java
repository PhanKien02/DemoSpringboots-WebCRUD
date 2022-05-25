package example.demo.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int user_id;
	@Column(name = "fullName")
//	@NotEmpty(message =  "full name không được để trống")
//	@NotNull(message = "full name không được null")
	private String full_Name;
	@Column(name = "Email")
	private String Email;
//	@NotEmpty(message = "username không được để trống")
//	@NotNull(message ="username không được null" )
	@Column(name = "username")
	private String userName;
	@NotNull(message ="password không được null" )
	@NotEmpty(message = "password không được để trống")
	@Column(name = "passwords")
	private String password;
	@Column(name = "Address")
	private String Address;
	@Column(name = "AboutMe")
	private String AboutMe;
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;

	
}
