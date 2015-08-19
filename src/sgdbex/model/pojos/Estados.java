package sgdbex.model.pojos;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class Estados {

	private int estado_id;
	private String estado_nombre;
	private String estado_descripcion;
	private char estado_activo;
	private String edo_usuario_modificacion;
	private Date edo_fecha_modificacion;

	public Estados(){		
	}

	public Estados(int estado_id, String estado_nombre,
			String estado_descripcion, char estado_activo,
			String edo_usuario_modificacion, Date edo_fecha_modificacion) {
		this.estado_id = estado_id;
		this.estado_nombre = estado_nombre;
		this.estado_descripcion = estado_descripcion;
		this.estado_activo = estado_activo;
		this.edo_usuario_modificacion = edo_usuario_modificacion;
		this.edo_fecha_modificacion = edo_fecha_modificacion;
	}

	public int getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(int estado_id) {
		this.estado_id = estado_id;
	}

	public String getEstado_nombre() {
		return estado_nombre;
	}

	public void setEstado_nombre(String estado_nombre) {
		this.estado_nombre = estado_nombre;
	}

	public String getEstado_descripcion() {
		return estado_descripcion;
	}

	public void setEstado_descripcion(String estado_descripcion) {
		this.estado_descripcion = estado_descripcion;
	}

	public char getEstado_activo() {
		return estado_activo;
	}

	public void setEstado_activo(char estado_activo) {
		this.estado_activo = estado_activo;
	}

	public String getEdo_usuario_modificacion() {
		return edo_usuario_modificacion;
	}

	public void setEdo_usuario_modificacion(String edo_usuario_modificacion) {
		this.edo_usuario_modificacion = edo_usuario_modificacion;
	}

	public Date getEdo_fecha_modificacion() {
		return edo_fecha_modificacion;
	}

	public void setEdo_fecha_modificacion(Date edo_fecha_modificacion) {
		this.edo_fecha_modificacion = edo_fecha_modificacion;
	}

	@Override
	public String toString() {
		return "Estados [estado_id=" + estado_id + ", estado_nombre="
				+ estado_nombre + ", estado_descripcion=" + estado_descripcion
				+ ", estado_activo=" + estado_activo
				+ ", edo_usuario_modificacion=" + edo_usuario_modificacion
				+ ", edo_fecha_modificacion=" + edo_fecha_modificacion + "]";
	}
	
}
