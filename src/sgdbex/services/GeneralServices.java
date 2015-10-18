package sgdbex.services;

import java.util.List;

import sgdbex.model.pojos.ArchivosAdjuntos;
import sgdbex.model.pojos.Auditoria;
import sgdbex.model.pojos.Categorias;
import sgdbex.model.pojos.Defectos;
import sgdbex.model.pojos.Estados;
import sgdbex.model.pojos.HistoricoEstados;
import sgdbex.model.pojos.MotivoRechazo;
import sgdbex.model.pojos.Prioridades;
import sgdbex.model.pojos.Proyectos;
import sgdbex.model.pojos.RolUsuarios;
import sgdbex.model.pojos.Usuarios;
import sgdbex.model.pojos.Urgencias;
import sgdbex.model.pojos.Impactos;
import sgdbex.model.pojos.Reportes;
import sgdbex.model.pojos.Correo;

public interface GeneralServices {

	public void eliminarArchivos_Adjuntos(List<ArchivosAdjuntos> Lista_Archivos_Adjuntos);
	//no utilizados
	public List<ArchivosAdjuntos> obtenerArchivos_Adjuntos();
	//no utilizados	
	public List<Estados> obtenerEstados();
	
	public List<RolUsuarios> obtenerRolesUsuarios();
	
	public String createProyectos(Proyectos Proyecto);

	public String updateProyectos(Proyectos Proyecto);

	public String deleteProyectos(Proyectos Proyecto);

	public List<Proyectos> getProyectos();
	
	public List<Proyectos> getProyectosPorUsuario(String rol, String carnet);
	
	public List<Prioridades> obtenerPrioridades();
	
	public List<Urgencias> obtenerUrgencias();
	
	public List<Impactos> obtenerImpactos();
	
	public String createUsuarios(Usuarios usuario);

	public String updateUsuarios(Usuarios usuario);

	public String deleteUsuarios(Usuarios usuario);
	
	public List<Usuarios> getUsuarios();
	
	public List<Usuarios> getUsuariosPorProyecto(String proyecto);
	
	public List<Usuarios> getUsuariosLider();
	
	public List<Usuarios> getUsuariosAnalistas();
	
	public void insertarAuditoria(Auditoria auditoria);
		
	public String createCategorias(Categorias Categoria);

	public String updateCategorias(Categorias Categoria);

	public String deleteCategorias(Categorias Categoria);

	public List<Categorias> getCategorias();
//	-----------------------------------------------------	
	public String createDefectos(Defectos Defecto);

	public String updateDefectos(Defectos Defecto);

	public String deleteDefectos(Defectos Defecto);

	public List<Defectos> getDefectosNoAsignados(String usuario, String rol, String proyecto);

	public List<Defectos> getDefectosMonitorizados(String usuario, String rol, String proyecto);	

	public List<Defectos> getDefectosAsignados(String usuario, String rol, String proyecto);
	
	public List<Defectos> getDefectosModificar(String usuario, String rol, String proyecto);
	
	public List<Defectos> getDefectosValidar(String usuario, String rol, String proyecto);
	
	public List<Defectos> getDefectosCerrados(String usuario, String rol, String proyecto);

	public List<Defectos> getDefectosModifReciente(String usuario, String rol, String proyecto);

	public List<Defectos> getDefectosReportadosPorMi(String usuario, String rol, String proyecto);
	
	public List<Defectos> buscarDefectos(String nombre, String proyecto, String categoria, String prioridad);
	
	public List<HistoricoEstados> getHistorico(Integer id);

//	public void createPrioridad(Prioridades prioridad);
//
//	public void updatePrioridad(Prioridades prioridad);
//
//	public void deletePrioridad(List<Prioridades> prioridades);
//			
	public Defectos getDetalleDefecto(Integer id);
	
	public String cambiarEstadoDefectos(Defectos defecto);
	
	public String createMotivos(MotivoRechazo motivo);

	public String updateMotivos(MotivoRechazo motivo);

	public String deleteMotivos(MotivoRechazo motivo);
	
	public List<MotivoRechazo> getMotivos();
	
	public List<Reportes> getReportesEstado();
	
	public List<Reportes> getReportesMayorTiempoAbierto();
	
	public List<Reportes> getReportesFechaDias();
	
	public List<Reportes> getReportesCategoria();
	
	public List<Reportes> getReportesPrioridad();
	
	public List<Reportes> getReportesEstadisticasUsuarios();
	
	public List<Reportes> getReportesEstadisticasReporteros();
	
	public List<Reportes> getReportesReporterosResolucion();
	
	public List<Reportes> getReportesUsuariosResolucion();
	
	public List<Reportes> getReportesMasActivos();
	
	public Correo enviarNotificacionDefectoCreado(Integer defecto);
	public Correo enviarNotificacionDefectoActualizado(Integer defecto);
	
}
