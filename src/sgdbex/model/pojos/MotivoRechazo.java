package sgdbex.model.pojos;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MotivoRechazo {
	Integer motivo_id;
	String motivo_nombre;
	String motivo_descripcion;
	char motivo_activo;
	String motivo_usuario_modificacion;
	Date motivo_fecha_modificacion;
	public MotivoRechazo(){
		
	}
	public MotivoRechazo(Integer motivo_id, String motivo_nombre,
			String motivo_descripcion, char motivo_activo,
			String motivo_usuario_modificacion, Date motivo_fecha_modificacion) {
		this.motivo_id = motivo_id;
		this.motivo_nombre = motivo_nombre;
		this.motivo_descripcion = motivo_descripcion;
		this.motivo_activo = motivo_activo;
		this.motivo_usuario_modificacion = motivo_usuario_modificacion;
		this.motivo_fecha_modificacion = motivo_fecha_modificacion;
	}

	public Integer getMotivo_id() {
		return motivo_id;
	}

	public void setMotivo_id(Integer motivo_id) {
		this.motivo_id = motivo_id;
	}

	public String getMotivo_nombre() {
		return motivo_nombre;
	}

	public void setMotivo_nombre(String motivo_nombre) {
		this.motivo_nombre = motivo_nombre;
	}

	public String getMotivo_descripcion() {
		return motivo_descripcion;
	}

	public void setMotivo_descripcion(String motivo_descripcion) {
		this.motivo_descripcion = motivo_descripcion;
	}

	public char getMotivo_activo() {
		return motivo_activo;
	}

	public void setMotivo_activo(char motivo_activo) {
		this.motivo_activo = motivo_activo;
	}

	public String getMotivo_usuario_modificacion() {
		return motivo_usuario_modificacion;
	}

	public void setMotivo_usuario_modificacion(String motivo_usuario_modificacion) {
		this.motivo_usuario_modificacion = motivo_usuario_modificacion;
	}

	public Date getMotivo_fecha_modificacion() {
		return motivo_fecha_modificacion;
	}

	public void setMotivo_fecha_modificacion(Date motivo_fecha_modificacion) {
		this.motivo_fecha_modificacion = motivo_fecha_modificacion;
	}

	@Override
	public String toString() {
		return "MotivoRechazo [motivo_id=" + motivo_id + ", motivo_nombre="
				+ motivo_nombre + ", motivo_descripcion=" + motivo_descripcion
				+ ", motivo_activo=" + motivo_activo
				+ ", motivo_usuario_modificacion="
				+ motivo_usuario_modificacion + ", motivo_fecha_modificacion="
				+ motivo_fecha_modificacion + "]";
	}
	
}
