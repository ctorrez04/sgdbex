package sgdbex.model.pojos;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ArchivosAdjuntos {

	private Integer archivo_id;
	
	private String archivo_nombre;
	
	private String archivo_formato;
	
	private String archivo_ubicacion;

	private String archivo_comentarios;
	
	private char archivo_activo;
	
	private char archivo_tipo;
	
	private String archivo_tamano;
	
	private String archivo_usuario_creacion;
	
	private Date archivo_fecha_creacion;
	
	private int archivo_defecto_fk;
	
	public ArchivosAdjuntos() {
//		this.archivo_id = 0;
//		this.archivo_nombre = null;
//		this.archivo_formato = null;
//		this.archivo_ubicacion = null;
//		this.archivo_comentarios = null;
//		this.archivo_activo = '0';
//		this.archivo_tipo = '0';
//		this.archivo_tamano = null;
//		this.archivo_usuario_creacion = null;
//		this.archivo_fecha_creacion = null;
//		this.archivo_defecto_fk = 0;
	}

	public Integer getArchivo_id() {
		return archivo_id;
	}

	public void setArchivo_id(Integer archivo_id) {
		this.archivo_id = archivo_id;
	}

	public String getArchivo_nombre() {
		return archivo_nombre;
	}

	public void setArchivo_nombre(String archivo_nombre) {
		this.archivo_nombre = archivo_nombre;
	}

	public String getArchivo_formato() {
		return archivo_formato;
	}

	public void setArchivo_formato(String archivo_formato) {
		this.archivo_formato = archivo_formato;
	}

	public String getArchivo_ubicacion() {
		return archivo_ubicacion;
	}

	public void setArchivo_ubicacion(String archivo_ubicacion) {
		this.archivo_ubicacion = archivo_ubicacion;
	}

	public String getArchivo_comentarios() {
		return archivo_comentarios;
	}

	public void setArchivo_comentarios(String archivo_comentarios) {
		this.archivo_comentarios = archivo_comentarios;
	}

	public char getArchivo_activo() {
		return archivo_activo;
	}

	public void setArchivo_activo(char archivo_activo) {
		this.archivo_activo = archivo_activo;
	}

	public char getArchivo_tipo() {
		return archivo_tipo;
	}

	public void setArchivo_tipo(char archivo_tipo) {
		this.archivo_tipo = archivo_tipo;
	}

	public String getArchivo_tamano() {
		return archivo_tamano;
	}

	public void setArchivo_tamano(String archivo_tamano) {
		this.archivo_tamano = archivo_tamano;
	}

	public String getArchivo_usuario_creacion() {
		return archivo_usuario_creacion;
	}

	public void setArchivo_usuario_creacion(String archivo_usuario_creacion) {
		this.archivo_usuario_creacion = archivo_usuario_creacion;
	}

	public Date getArchivo_fecha_creacion() {
		return archivo_fecha_creacion;
	}

	public void setArchivo_fecha_creacion(Date archivo_fecha_creacion) {
		this.archivo_fecha_creacion = archivo_fecha_creacion;
	}

	public int getArchivo_defecto_fk() {
		return archivo_defecto_fk;
	}

	public void setArchivo_defecto_fk(int archivo_defecto_fk) {
		this.archivo_defecto_fk = archivo_defecto_fk;
	}

	@Override
	public String toString() {
		return "Archivos_Adjuntos [archivo_id=" + archivo_id
				+ ", archivo_nombre=" + archivo_nombre + ", archivo_formato="
				+ archivo_formato + ", archivo_ubicacion=" + archivo_ubicacion
				+ ", archivo_comentarios=" + archivo_comentarios
				+ ", archivo_activo=" + archivo_activo + ", archivo_tipo="
				+ archivo_tipo + ", archivo_tamano=" + archivo_tamano
				+ ", archivo_usuario_creacion=" + archivo_usuario_creacion
				+ ", archivo_fecha_creacion=" + archivo_fecha_creacion
				+ ", archivo_defecto_fk=" + archivo_defecto_fk + "]";
	}
//	clasico311.png
//	image/png
//	1040651
}
