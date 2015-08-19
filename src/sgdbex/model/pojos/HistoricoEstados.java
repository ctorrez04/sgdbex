package sgdbex.model.pojos;

import java.util.Date;

public class HistoricoEstados {
	private Integer defecto_id;
	private Date fecha_estatus;
	private String duracion;
	private String carnet_estatus_anterior;
	private String usuario_estatus_anterior;
	private String estado_nombre;
	private String comentarios;
	
	public Date getFecha_estatus() {
		return fecha_estatus;
	}
	public void setFecha_estatus(Date fecha_estatus) {
		this.fecha_estatus = fecha_estatus;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getCarnet_estatus_anterior() {
		return carnet_estatus_anterior;
	}
	public void setCarnet_estatus_anterior(String carnet_estatus_anterior) {
		this.carnet_estatus_anterior = carnet_estatus_anterior;
	}
	public String getUsuario_estatus_anterior() {
		return usuario_estatus_anterior;
	}
	public void setUsuario_estatus_anterior(String usuario_estatus_anterior) {
		this.usuario_estatus_anterior = usuario_estatus_anterior;
	}
	public String getEstado_nombre() {
		return estado_nombre;
	}
	public void setEstado_nombre(String estado_nombre) {
		this.estado_nombre = estado_nombre;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	@Override
	public String toString() {
		return "HistoricoEstados [fecha_estatus=" + fecha_estatus
				+ ", duracion=" + duracion + ", carnet_estatus_anterior="
				+ carnet_estatus_anterior + ", usuario_estatus_anterior="
				+ usuario_estatus_anterior + ", estado_nombre=" + estado_nombre
				+ ", comentarios=" + comentarios + "]";
	}
	public Integer getDefecto_id() {
		return defecto_id;
	}
	public void setDefecto_id(Integer defecto_id) {
		this.defecto_id = defecto_id;
	}
	

}
