package sgdbex.model.pojos;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Usuarios {
	private Integer usuario_id;
	
	private String nombre_completo;
	
	private String carnet;
	
	private String correo_electronico;
	
	private String departamento;
	
	private String telefono;
	
	private Integer usuario_proyecto_fk;
	
	private int usuario_rol_fk;
	
	private char usuario_activo;
	
	public Usuarios(){
		this.usuario_id = 0;
		this.nombre_completo = "";
		this.carnet = "";
		this.correo_electronico = "";
		this.departamento = "";
		this.telefono = "";
		this.usuario_proyecto_fk = 0;
		this.usuario_rol_fk = 0;
		this.usuario_activo = 'N';
	}

	public Usuarios(Integer usuario_id, String nombre_completo, String carnet,
			String correo_electronico, String departamento, String telefono,
			Integer usuario_proyecto_fk, int usuario_rol_fk, char usuario_activo) {
		this.usuario_id = usuario_id;
		this.nombre_completo = nombre_completo;
		this.carnet = carnet;
		this.correo_electronico = correo_electronico;
		this.departamento = departamento;
		this.telefono = telefono;
		this.usuario_proyecto_fk = usuario_proyecto_fk;
		this.usuario_rol_fk = usuario_rol_fk;
		this.usuario_activo = usuario_activo;
	}

	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public String getCorreo_electronico() {
		return correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getUsuario_proyecto_fk() {
		return usuario_proyecto_fk;
	}

	public void setUsuario_proyecto_fk(Integer usuario_proyecto_fk) {
		this.usuario_proyecto_fk = usuario_proyecto_fk;
	}

	public int getUsuario_rol_fk() {
		return usuario_rol_fk;
	}

	public void setUsuario_rol_fk(int usuario_rol_fk) {
		this.usuario_rol_fk = usuario_rol_fk;
	}

	public char getUsuario_activo() {
		return usuario_activo;
	}

	public void setUsuario_activo(char usuario_activo) {
		this.usuario_activo = usuario_activo;
	}

	@Override
	public String toString() {
		return "Usuarios [usuario_id=" + usuario_id + ", nombre_completo="
				+ nombre_completo + ", carnet=" + carnet
				+ ", correo_electronico=" + correo_electronico
				+ ", departamento=" + departamento + ", telefono=" + telefono
				+ ", usuario_proyecto_fk=" + usuario_proyecto_fk
				+ ", usuario_rol_fk=" + usuario_rol_fk + ", usuario_activo="
				+ usuario_activo + "]";
	}

	
}
