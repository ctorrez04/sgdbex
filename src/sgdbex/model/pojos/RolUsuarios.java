package sgdbex.model.pojos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROL_USUARIO")
@ManagedBean
@ViewScoped
public class RolUsuarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rol_id;
	
	@Column(name = "rol_nombre")
	private String rol_nombre;
	
	@Column(name = "rol_descripcion")
	private String descripcion;
	
	@Column(name = "rol_privilegios")
	private String privilegios;

	@Column(name = "rol_activo")
	private String activo;

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}


	public String getRol_nombre() {
		return rol_nombre;
	}

	public void setRol_nombre(String rol_nombre) {
		this.rol_nombre = rol_nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getRol_id() {
		return rol_id;
	}

	public void setRol_id(int rol_id) {
		this.rol_id = rol_id;
	}

	public String getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(String privilegios) {
		this.privilegios = privilegios;
	}
	
	@Override
	public String toString() {
		return "Rol_Usuarios [rol_id=" + rol_id + ", rol_nombre=" + rol_nombre
				+ ", descripcion=" + descripcion + ", privilegios=" + privilegios + "]"; 
	}
}
