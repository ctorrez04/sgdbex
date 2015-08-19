package sgdbex.model.pojos;

import java.sql.Timestamp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class Prioridades {
	
	private int prioridad_id;
	
	private String prioridad_tipo;
	
	private String prioridad_descripcion;

	private char prioridad_activo;
	
	private Timestamp prior_fecha_modificacion;
	
	private String prior_usuario_modificacion;
	

	public int getPrioridad_id() {
		return prioridad_id;
	}

	public void setPrioridad_id(int prioridad_id) {
		this.prioridad_id = prioridad_id;
	}

	public String getPrioridad_tipo() {
		return prioridad_tipo;
	}

	public void setPrioridad_tipo(String prioridad_tipo) {
		this.prioridad_tipo = prioridad_tipo;
	}

	public String getPrioridad_descripcion() {
		return prioridad_descripcion;
	}

	public void setPrioridad_descripcion(String prioridad_descripcion) {
		this.prioridad_descripcion = prioridad_descripcion;
	}

	public char getPrioridad_activo() {
		return prioridad_activo;
	}

	public void setPrioridad_activo(char prioridad_activo) {
		this.prioridad_activo = prioridad_activo;
	}

	public Timestamp getPrior_fecha_modificacion() {
		return prior_fecha_modificacion;
	}

	public void setPrior_fecha_modificacion(Timestamp prior_fecha_modificacion) {
		this.prior_fecha_modificacion = prior_fecha_modificacion;
	}

	public String getPrior_usuario_modificacion() {
		return prior_usuario_modificacion;
	}

	public void setPrior_usuario_modificacion(String prior_usuario_modificacion) {
		this.prior_usuario_modificacion = prior_usuario_modificacion;
	}

	@Override
	public String toString() {
		return "Prioridades [prioridad_id=" + prioridad_id
				+ ", prioridad_tipo=" + prioridad_tipo
				+ ", prioridad_descripcion=" + prioridad_descripcion
				+ ", prioridad_activo=" + prioridad_activo
				+ ", prior_fecha_modificacion=" + prior_fecha_modificacion
				+ ", prior_usuario_modificacion=" + prior_usuario_modificacion
				+ "]";
	}

	

}