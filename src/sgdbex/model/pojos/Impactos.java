package sgdbex.model.pojos;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Impactos {
	private int impacto_id;
	private String impacto_tipo;
	private String impacto_descripcion;
	private char impacto_activo;
	private Date impacto_fecha_modificacion;
	private String impacto_usuario_modificacion;
	public Impactos(){}
	public Impactos(int impacto_id, String impacto_tipo,
			String impacto_descripcion, char impacto_activo,
			Date impacto_fecha_modificacion, String impacto_usuario_modificacion) {
		this.impacto_id = impacto_id;
		this.impacto_tipo = impacto_tipo;
		this.impacto_descripcion = impacto_descripcion;
		this.impacto_activo = impacto_activo;
		this.impacto_fecha_modificacion = impacto_fecha_modificacion;
		this.impacto_usuario_modificacion = impacto_usuario_modificacion;
	}
	public int getImpacto_id() {
		return impacto_id;
	}
	public void setImpacto_id(int impacto_id) {
		this.impacto_id = impacto_id;
	}
	public String getImpacto_tipo() {
		return impacto_tipo;
	}
	public void setImpacto_tipo(String impacto_tipo) {
		this.impacto_tipo = impacto_tipo;
	}
	public String getImpacto_descripcion() {
		return impacto_descripcion;
	}
	public void setImpacto_descripcion(String impacto_descripcion) {
		this.impacto_descripcion = impacto_descripcion;
	}
	public char getImpacto_activo() {
		return impacto_activo;
	}
	public void setImpacto_activo(char impacto_activo) {
		this.impacto_activo = impacto_activo;
	}
	public Date getImpacto_fecha_modificacion() {
		return impacto_fecha_modificacion;
	}
	public void setImpacto_fecha_modificacion(Date impacto_fecha_modificacion) {
		this.impacto_fecha_modificacion = impacto_fecha_modificacion;
	}
	public String getImpacto_usuario_modificacion() {
		return impacto_usuario_modificacion;
	}
	public void setImpacto_usuario_modificacion(String impacto_usuario_modificacion) {
		this.impacto_usuario_modificacion = impacto_usuario_modificacion;
	}
	@Override
	public String toString() {
		return "Impacto [impacto_id=" + impacto_id + ", impacto_tipo="
				+ impacto_tipo + ", impacto_descripcion=" + impacto_descripcion
				+ ", impacto_activo=" + impacto_activo
				+ ", impacto_fecha_modificacion=" + impacto_fecha_modificacion
				+ ", impacto_usuario_modificacion="
				+ impacto_usuario_modificacion + "]";
	}
	
}
