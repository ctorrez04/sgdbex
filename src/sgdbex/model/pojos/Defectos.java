package sgdbex.model.pojos;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Defectos {

	private Integer defecto_id;
	
	private String asunto;
	
	private String descripcion;
	
	private Integer prioridad_fk;
	
	private Integer impacto_fk;
	
	private Integer urgencia_fk;
	
	private Integer estado_fk;

	private Integer proyecto_fk;
	
	private Integer categoria_fk;
	
	private String reportero_fk;
	
	private String reportero_nombre;
	
	private Date fecha_creacion;
	
	private Date fecha_estimada;
	
	private String carnet_modificacion;
	
	private String usuario_modificacion;
	
	private Date fecha_modificacion;

	private String comentarios;
	
	private char defecto_activo;
	
	private String prioridad_tipo;
//	private String prioridad_descripcion;
    
	private String impacto_tipo;
//	private String impacto_descripcion;
    
	private String urgencia_tipo;
//	private String urgencia_descripcion;
    
	private String estado_nombre;
	private String estado_descripcion;
    
	private String proyecto_nombre;
	private String proyecto_descripcion;
    
	private String categoria_nombre;
//	private String categoria_descripcion;
	private String direccionIp;
	private String modulo;
	private String accion;
	
	private String responsable_fk;
	private String responsable_nombre;
	private Date fecha_asignacion;
	private Integer motivo_fk;
	private String motivo_nombre;
	private List<ArchivosAdjuntos> adjuntos;
	
	
	public Defectos(){
		
	}
	
	public Integer getDefecto_id() {
		return defecto_id;
	}
	public void setDefecto_id(Integer defecto_id) {
		this.defecto_id = defecto_id;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getPrioridad_fk() {
		return prioridad_fk;
	}
	public void setPrioridad_fk(Integer prioridad_fk) {
		this.prioridad_fk = prioridad_fk;
	}
	public Integer getImpacto_fk() {
		return impacto_fk;
	}
	public void setImpacto_fk(Integer impacto_fk) {
		this.impacto_fk = impacto_fk;
	}
	public Integer getUrgencia_fk() {
		return urgencia_fk;
	}
	public void setUrgencia_fk(Integer urgencia_fk) {
		this.urgencia_fk = urgencia_fk;
	}
	public Integer getEstado_fk() {
		return estado_fk;
	}
	public void setEstado_fk(Integer estado_fk) {
		this.estado_fk = estado_fk;
	}
	public Integer getProyecto_fk() {
		return proyecto_fk;
	}
	public void setProyecto_fk(Integer proyecto_fk) {
		this.proyecto_fk = proyecto_fk;
	}
	public Integer getCategoria_fk() {
		return categoria_fk;
	}
	public void setCategoria_fk(Integer categoria_fk) {
		this.categoria_fk = categoria_fk;
	}
	public String getReportero_fk() {
		return reportero_fk;
	}
	public void setReportero_fk(String reportero_fk) {
		this.reportero_fk = reportero_fk;
	}
	public String getResponsable_fk() {
		return responsable_fk;
	}

	public void setResponsable_fk(String responsable_fk) {
		this.responsable_fk = responsable_fk;
	}

	public String getResponsable_nombre() {
		return responsable_nombre;
	}

	public void setResponsable_nombre(String responsable_nombre) {
		this.responsable_nombre = responsable_nombre;
	}

	public Date getFecha_asignacion() {
		return fecha_asignacion;
	}

	public void setFecha_asignacion(Date fecha_asignacion) {
		this.fecha_asignacion = fecha_asignacion;
	}

	public String getDireccionIp() {
		return direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getFecha_estimada() {
		return fecha_estimada;
	}
	public void setFecha_estimada(Date fecha_estimada) {
		this.fecha_estimada = fecha_estimada;
	}
	public String getUsuario_modificacion() {
		return usuario_modificacion;
	}
	public void setUsuario_modificacion(String usuario_modificacion) {
		this.usuario_modificacion = usuario_modificacion;
	}
	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public char getDefecto_activo() {
		return defecto_activo;
	}
	public void setDefecto_activo(char defecto_activo) {
		this.defecto_activo = defecto_activo;
	}

	public String getPrioridad_tipo() {
		return prioridad_tipo;
	}

	public void setPrioridad_tipo(String prioridad_tipo) {
		this.prioridad_tipo = prioridad_tipo;
	}

	public String getImpacto_tipo() {
		return impacto_tipo;
	}

	public void setImpacto_tipo(String impacto_tipo) {
		this.impacto_tipo = impacto_tipo;
	}
	public String getUrgencia_tipo() {
		return urgencia_tipo;
	}

	public void setUrgencia_tipo(String urgencia_tipo) {
		this.urgencia_tipo = urgencia_tipo;
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

	public String getCategoria_nombre() {
		return categoria_nombre;
	}

	public void setCategoria_nombre(String categoria_nombre) {
		this.categoria_nombre = categoria_nombre;
	}

	public String getReportero_nombre() {
		return reportero_nombre;
	}

	public void setReportero_nombre(String reportero_nombre) {
		this.reportero_nombre = reportero_nombre;
	}

	public String getCarnet_modificacion() {
		return carnet_modificacion;
	}

	public void setCarnet_modificacion(String carnet_modificacion) {
		this.carnet_modificacion = carnet_modificacion;
	}
	public Integer getMotivo_fk() {
		return motivo_fk;
	}

	public void setMotivo_fk(Integer motivo_fk) {
		this.motivo_fk = motivo_fk;
	}
	public String getMotivo_nombre() {
		return motivo_nombre;
	}

	public void setMotivo_nombre(String motivo_nombre) {
		this.motivo_nombre = motivo_nombre;
	}
	
	@Override
	public String toString() {
		return "Defectos [defecto_id=" + defecto_id + ", asunto=" + asunto
				+ ", descripcion=" + descripcion + ", prioridad_fk="
				+ prioridad_fk + ", impacto_fk=" + impacto_fk
				+ ", urgencia_fk=" + urgencia_fk + ", estado_fk=" + estado_fk
				+ ", proyecto_fk=" + proyecto_fk + ", categoria_fk="
				+ categoria_fk + ", reportero_fk=" + reportero_fk
				+ ", reportero_nombre=" + reportero_nombre
				+ ", fecha_creacion=" + fecha_creacion + ", fecha_estimada="
				+ fecha_estimada + ", carnet_modificacion="
				+ carnet_modificacion + ", usuario_modificacion="
				+ usuario_modificacion + ", fecha_modificacion="
				+ fecha_modificacion + ", comentarios=" + comentarios
				+ ", defecto_activo=" + defecto_activo + ", prioridad_tipo="
				+ prioridad_tipo + ", impacto_tipo=" + impacto_tipo
				+ ", urgencia_tipo=" + urgencia_tipo + ", estado_nombre="
				+ estado_nombre + ", estado_descripcion=" + estado_descripcion
				+ ", proyecto_nombre=" + proyecto_nombre
				+ ", proyecto_descripcion=" + proyecto_descripcion
				+ ", categoria_nombre=" + categoria_nombre + ", direccionIp="
				+ direccionIp + ", modulo=" + modulo + ", accion=" + accion
				+ ", responsable_fk=" + responsable_fk
				+ ", responsable_nombre=" + responsable_nombre
				+ ", fecha_asignacion=" + fecha_asignacion + "]";
	}

	public List<ArchivosAdjuntos> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<ArchivosAdjuntos> adjuntos) {
		this.adjuntos = adjuntos;
	}

}
