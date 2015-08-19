package sgdbex.model.pojos;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Categorias {


	private Integer categoria_id;
	private String categoria_nombre;
	private String categoria_descripcion;
	private char categoria_activo;
	private Date categ_fecha_modificacion;
	private String categ_usuario_modificacion;

	public Categorias() {
		
	}
	public Categorias(Integer categoria_id, String categoria_nombre,
			String categoria_descripcion, Date categ_fecha_modificacion,
			String categ_usuario_modificacion, char activo_categ) {
		this.categoria_id = categoria_id;
		this.categoria_nombre = categoria_nombre;
		this.categoria_descripcion = categoria_descripcion;
		this.categ_fecha_modificacion = categ_fecha_modificacion;
		this.categ_usuario_modificacion = categ_usuario_modificacion;
		this.categoria_activo = activo_categ;
	}
	
	
	public Integer getCategoria_id() {
		return categoria_id;
	}
	public void setCategoria_id(Integer categoria_id) {
		this.categoria_id = categoria_id;
	}
	public String getCategoria_nombre() {
		return categoria_nombre;
	}
	public void setCategoria_nombre(String categoria_nombre) {
		this.categoria_nombre = categoria_nombre;
	}
	public String getCategoria_descripcion() {
		return categoria_descripcion;
	}
	public void setCategoria_descripcion(String categoria_descripcion) {
		this.categoria_descripcion = categoria_descripcion;
	}
	public char getCategoria_activo() {
		return categoria_activo;
	}
	public void setCategoria_activo(char categoria_activo) {
		this.categoria_activo = categoria_activo;
	}
	public Date getCateg_fecha_modificacion() {
		return categ_fecha_modificacion;
	}
	public void setCateg_fecha_modificacion(Date categ_fecha_modificacion) {
		this.categ_fecha_modificacion = categ_fecha_modificacion;
	}
	public String getCateg_usuario_modificacion() {
		return categ_usuario_modificacion;
	}
	public void setCateg_usuario_modificacion(String categ_usuario_modificacion) {
		this.categ_usuario_modificacion = categ_usuario_modificacion;
	}
	@Override
	public String toString() {
		return "Categorias [categoria_id=" + categoria_id
				+ ", categoria_nombre=" + categoria_nombre
				+ ", categoria_descripcion=" + categoria_descripcion
				+ ", categoria_activo=" + categoria_activo
				+ ", categ_fecha_modificacion=" + categ_fecha_modificacion
				+ ", categ_usuario_modificacion=" + categ_usuario_modificacion
				+ "]";
	}


}
