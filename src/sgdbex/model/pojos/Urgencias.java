package sgdbex.model.pojos;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Urgencias {
	private int urgencia_id;
	private String urgencia_tipo;
	private String urgencia_descripcion;
	private char urgencia_activo;
	private Date urgencia_fecha_modificacion;
	private String urgencia_usuario_modificacion;
	public Urgencias(){
	}
	public Urgencias(int urgencia_id, String urgencia_tipo,
			String urgencia_descripcion, char urgencia_activo,
			Date urgencia_fecha_modificacion,
			String urgencia_usuario_modificacion) {
		this.urgencia_id = urgencia_id;
		this.urgencia_tipo = urgencia_tipo;
		this.urgencia_descripcion = urgencia_descripcion;
		this.urgencia_activo = urgencia_activo;
		this.urgencia_fecha_modificacion = urgencia_fecha_modificacion;
		this.urgencia_usuario_modificacion = urgencia_usuario_modificacion;
	}
	public int getUrgencia_id() {
		return urgencia_id;
	}
	public void setUrgencia_id(int urgencia_id) {
		this.urgencia_id = urgencia_id;
	}
	public String getUrgencia_tipo() {
		return urgencia_tipo;
	}
	public void setUrgencia_tipo(String urgencia_tipo) {
		this.urgencia_tipo = urgencia_tipo;
	}
	public String getUrgencia_descripcion() {
		return urgencia_descripcion;
	}
	public void setUrgencia_descripcion(String urgencia_descripcion) {
		this.urgencia_descripcion = urgencia_descripcion;
	}
	public char getUrgencia_activo() {
		return urgencia_activo;
	}
	public void setUrgencia_activo(char urgencia_activo) {
		this.urgencia_activo = urgencia_activo;
	}
	public Date getUrgencia_fecha_modificacion() {
		return urgencia_fecha_modificacion;
	}
	public void setUrgencia_fecha_modificacion(Date urgencia_fecha_modificacion) {
		this.urgencia_fecha_modificacion = urgencia_fecha_modificacion;
	}
	public String getUrgencia_usuario_modificacion() {
		return urgencia_usuario_modificacion;
	}
	public void setUrgencia_usuario_modificacion(
			String urgencia_usuario_modificacion) {
		this.urgencia_usuario_modificacion = urgencia_usuario_modificacion;
	}
	@Override
	public String toString() {
		return "Urgencia [urgencia_id=" + urgencia_id + ", urgencia_tipo="
				+ urgencia_tipo + ", urgencia_descripcion="
				+ urgencia_descripcion + ", urgencia_activo=" + urgencia_activo
				+ ", urgencia_fecha_modificacion="
				+ urgencia_fecha_modificacion
				+ ", urgencia_usuario_modificacion="
				+ urgencia_usuario_modificacion + "]";
	}

}
