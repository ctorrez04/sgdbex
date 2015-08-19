package sgdbex.model.pojos;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Proyectos {

	private Integer proyecto_id;
	
	private String proyecto_nombre;
	
	private String proyecto_descripcion;
	
	private String proyecto_lider;
	
	private char proyecto_activo;
	
	private Date proy_fecha_modificacion;
	
	private String proy_usuario_modificacion;

	public Proyectos(){
//		this.proyecto_nombre = "";
//		this.proyecto_descripcion = "";
//		this.estado = "";
//		BigDecimal lider = new BigDecimal(String.valueOf(0)); 
//		this.lider_proyecto = lider;
//		this.n_empleados = 0;
//		this.proyecto_id = lider;
//		this.activo_proy = 'S';
	}
	
	
	public Proyectos(Integer proyecto_id, String proyecto_nombre,
			String proyecto_descripcion, String proyecto_lider,
			char proyecto_activo, Date proy_fecha_modificacion,
			String proy_usuario_modificacion) {
		this.proyecto_id = proyecto_id;
		this.proyecto_nombre = proyecto_nombre;
		this.proyecto_descripcion = proyecto_descripcion;
		this.proyecto_lider = proyecto_lider;
		this.proyecto_activo = proyecto_activo;
		this.proy_fecha_modificacion = proy_fecha_modificacion;
		this.proy_usuario_modificacion = proy_usuario_modificacion;
	}


	public String getProyecto_nombre() {
		return proyecto_nombre;
	}

	public void setProyecto_nombre(String proyecto_nombre) {
		this.proyecto_nombre = proyecto_nombre;
	}

	public String getProyecto_descripcion() {
		return proyecto_descripcion;
	}

	public void setProyecto_descripcion(String proyecto_descripcion) {
		this.proyecto_descripcion = proyecto_descripcion;
	}
	public Integer getProyecto_id() {
		return proyecto_id;
	}
	public void setProyecto_id(Integer proyecto_id) {
		this.proyecto_id = proyecto_id;
	}
	public String getProyecto_lider() {
		return proyecto_lider;
	}
	public void setProyecto_lider(String proyecto_lider) {
		this.proyecto_lider = proyecto_lider;
	}
	public char getProyecto_activo() {
		return proyecto_activo;
	}
	public void setProyecto_activo(char proyecto_activo) {
		this.proyecto_activo = proyecto_activo;
	}
	public Date getProy_fecha_modificacion() {
		return proy_fecha_modificacion;
	}
	public void setProy_fecha_modificacion(Date proy_fecha_modificacion) {
		this.proy_fecha_modificacion = proy_fecha_modificacion;
	}
	public String getProy_usuario_modificacion() {
		return proy_usuario_modificacion;
	}
	public void setProy_usuario_modificacion(String proy_usuario_modificacion) {
		this.proy_usuario_modificacion = proy_usuario_modificacion;
	}
	@Override
	public String toString() {
		return "Proyectos [proyecto_id=" + proyecto_id + ", proyecto_nombre="
				+ proyecto_nombre + ", proyecto_descripcion="
				+ proyecto_descripcion + ", proyecto_lider=" + proyecto_lider
				+ ", proyecto_activo=" + proyecto_activo
				+ ", proy_fecha_modificacion=" + proy_fecha_modificacion
				+ ", proy_usuario_modificacion=" + proy_usuario_modificacion
				+ "]";
	}

}