package sgdbex.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sgdbex.model.daos.*;
import sgdbex.model.pojos.*;

@Service
@Transactional
public class GeneralServicesImpl implements GeneralServices{

	@Autowired
	private PrioridadesDAO priorityDAO;
	
	@Autowired
	private CorreoDAO correoDAO;
	
	@Autowired
	private ArchivosAdjuntosDAO archivos_AdjuntosDAO;
	
	@Autowired
	private AuditoriasDAO auditoriaDAO;
	
	@Autowired
	private CategoriasDAO categoriasDAO;
	
	@Autowired
	private EstadosDAO estadosDAO;
	
	@Autowired
	private ProyectosDAO proyectosDAO;
	
	@Autowired
	private RolUsuariosDAO rolesDAO;

	@Autowired
	private DefectosDAO defectosDAO;
	
	@Autowired
	private UsuariosDAO usuariosDAO;
	
	@Autowired
	private UrgenciasDAO urgenciasDAO;
	
	@Autowired
	private ImpactosDAO impactosDAO;

	@Autowired
	private ReportesDAO reportesDAO;

	@Autowired
	private MotivoResolucionDAO motivosDAO;
	
	@Override
	public void eliminarArchivos_Adjuntos(List<ArchivosAdjuntos> Archivos_Adjuntos) {
		archivos_AdjuntosDAO.borrarArchivos_Adjuntos(Archivos_Adjuntos);
	}

	@Override
	public List<ArchivosAdjuntos> obtenerArchivos_Adjuntos() {
		return archivos_AdjuntosDAO.obtenerArchivos_Adjuntos();
	}
	
	@Override
	public String createCategorias(Categorias Categoria) {
		String mensaje = categoriasDAO.createCategorias(Categoria);
		return mensaje;
	}

	@Override
	public String updateCategorias(Categorias Categoria) {
		String mensaje = categoriasDAO.updateCategorias(Categoria);
		return mensaje;
	}

	@Override
	public String deleteCategorias(Categorias Categoria) {
		String mensaje = categoriasDAO.deleteCategorias(Categoria);
		return mensaje;
	}

	@Override
	public List<Categorias> getCategorias() {
		return categoriasDAO.getCategorias();
	}

	@Override
	public String createDefectos(Defectos Defecto) {
		String mensaje = defectosDAO.createDefectos(Defecto);
		return mensaje;
	}

	@Override
	public String updateDefectos(Defectos Defecto) {
		String mensaje = defectosDAO.updateDefectos(Defecto);
		return mensaje;
	}

	@Override
	public String deleteDefectos(Defectos Defecto) {
		String mensaje = defectosDAO.deleteDefectos(Defecto);
		return mensaje;
	}

//	@Override
//	public List<Defectos> getAllDefectos() {
//		return defectosDAO.getAllDefectos();
//	}

	@Override
	public List<Estados> obtenerEstados() {
		return estadosDAO.obtenerEstados();
	}

	@Override
	public List<Prioridades> obtenerPrioridades() {
		return priorityDAO.obtenerPrioridades();
	}

	@Override
	public String createProyectos(Proyectos Proyecto) {
		String mensaje = proyectosDAO.createProyectos(Proyecto);
		System.out.println("sali service create");
		return mensaje;
	}

	@Override
	public String updateProyectos(Proyectos Proyecto) {
		String mensaje = proyectosDAO.updateProyectos(Proyecto);
		return mensaje;
	}

	@Override
	public String deleteProyectos(Proyectos Proyecto) {
		System.out.println("entre service delete");
		String mensaje = proyectosDAO.deleteProyectos(Proyecto);
		return mensaje;	
	}

	@Override
	public List<Proyectos> getProyectos() {
		return proyectosDAO.getProyectos();
	}

	@Override
	public List<RolUsuarios> obtenerRolesUsuarios() {
		return rolesDAO.obtenerRolesUsuarios();
	}

	@Override
	public String createUsuarios(Usuarios usuario) {
		String mensaje = usuariosDAO.createUsuarios(usuario);
		return mensaje;
		
	}

	@Override
	public String updateUsuarios(Usuarios usuario) {
		String mensaje = usuariosDAO.updateUsuarios(usuario);
		return mensaje;
	}

	@Override
	public String deleteUsuarios(Usuarios usuario) {
		String mensaje = usuariosDAO.deleteUsuarios(usuario);
		return mensaje;
		
	}

	@Override
	public List<Usuarios> getUsuarios() {
		return usuariosDAO.getUsuarios();
	}
	
	@Override
	public List<Usuarios> getUsuariosPorProyecto(String proyecto) {
		return usuariosDAO.getUsuariosPorProyecto(proyecto);
	}
	
	@Override
	public List<Usuarios> getUsuariosLider() {
		return usuariosDAO.getUsuariosLider();
	}
	
	@Override
	public List<Usuarios> getUsuariosAnalistas() {
		return usuariosDAO.getUsuariosAnalistas();
	}

	@Override
	public List<Urgencias> obtenerUrgencias() {
		return urgenciasDAO.obtenerUrgencias();
	}

	@Override
	public List<Impactos> obtenerImpactos() {
		return impactosDAO.obtenerImpactos();
	}

	@Override
	public List<Defectos> getDefectosNoAsignados(String usuario, String rol, String proyecto) {
		return defectosDAO.getDefectosNoAsignados(usuario,rol,proyecto);
	}

	@Override
	public List<Defectos> getDefectosMonitorizados(String usuario, String rol, String proyecto) {
		return defectosDAO.getDefectosMonitorizados(usuario,rol,proyecto);
	}
	
	@Override
	public List<Defectos> getDefectosCerrados(String usuario, String rol,
			String proyecto) {
		// TODO Auto-generated method stub
		return defectosDAO.getDefectosCerrados(usuario,rol,proyecto);
	}

	@Override
	public List<Defectos> getDefectosModifReciente(String usuario, String rol,
			String proyecto) {
		return defectosDAO.getDefectosModifReciente(usuario,rol,proyecto);
	}

	@Override
	public List<Defectos> getDefectosReportadosPorMi(String usuario,
			String rol, String proyecto) {
		return defectosDAO.getDefectosReportadosPorMi(usuario,rol,proyecto);
	}
	
	@Override
	public List<Defectos> getDefectosAsignados(String usuario, String rol, String proyecto) {
		return defectosDAO.getDefectosAsignados(usuario,rol,proyecto);
	}
	@Override
	public Defectos getDetalleDefecto(Integer id){
		return defectosDAO.getDetalleDefecto(id);
	}

	@Override
	public String cambiarEstadoDefectos(Defectos defecto) {
		return defectosDAO.cambiarEstadoDefecto(defecto);
	}

	@Override
	public List<Defectos> getDefectosModificar(String usuario, String rol, String proyecto) {
//		System.out.println("Servicio Usuario " +usuario + " perfil "+rol+" proyecto "+proyecto);
		return defectosDAO.getDefectosModificar(usuario, rol, proyecto);
	}

	@Override
	public List<Defectos> getDefectosValidar(String usuario, String rol, String proyecto) {
		return defectosDAO.getDefectosValidar(usuario, rol, proyecto);
	}

	@Override
	public List<Proyectos> getProyectosPorUsuario(String rol, String carnet) {
		return proyectosDAO.getProyectosPorUsuario(rol, carnet);
	}
	
	@Override
	public String createMotivos(MotivoResolucion motivo) {
		return motivosDAO.createMotivos(motivo);
	}

	@Override
	public String updateMotivos(MotivoResolucion motivo) {
		return motivosDAO.updateMotivos(motivo);
	}

	@Override
	public String deleteMotivos(MotivoResolucion motivo) {
		return motivosDAO.deleteMotivos(motivo);
	}
	
	@Override
	public List<MotivoResolucion> getMotivos() {
		return motivosDAO.getMotivos();
	}

	@Override
	public List<HistoricoEstados> getHistorico(Integer id) {
		return defectosDAO.getHistoricoEstados(id);
	}

	@Override
	public void insertarAuditoria(Auditoria auditoria) {
		auditoriaDAO.insertarAuditoria(auditoria);
	}
	
	@Override
	public List<Reportes> getReportesEstado() {
		return reportesDAO.getReportesEstado();
	}

	@Override
	public List<Reportes> getReportesMayorTiempoAbierto() {
		return reportesDAO.getReportesMayorTiempoAbierto();
	}

	@Override
	public List<Reportes> getReportesFechaDias() {
		return reportesDAO.getReportesFechaDias();
	}

	@Override
	public List<Reportes> getReportesCategoria() {
		return reportesDAO.getReportesCategoria();
	}

	@Override
	public List<Reportes> getReportesPrioridad() {
		return reportesDAO.getReportesPrioridad();
	}

	@Override
	public List<Reportes> getReportesEstadisticasUsuarios() {
		return reportesDAO.getReportesEstadisticasUsuarios();
	}

	@Override
	public List<Reportes> getReportesEstadisticasReporteros() {
		return reportesDAO.getReportesEstadisticasReporteros();
	}

	@Override
	public List<Reportes> getReportesReporterosResolucion() {
		return reportesDAO.getReportesReporterosResolucion();
	}

	@Override
	public List<Reportes> getReportesUsuariosResolucion() {
		return reportesDAO.getReportesUsuariosResolucion();
	}

	@Override
	public List<Reportes> getReportesMasActivos() {
		return reportesDAO.getReportesMasActivos();
	}

	@Override
	public List<Defectos> buscarDefectos(String nombre, String proyecto, String categoria, String prioridad) {
		return defectosDAO.buscarDefectos(nombre,proyecto,categoria,prioridad);
	}

	@Override
	public Correo enviarNotificacionDefectoCreado(Integer defecto) {
		try {
			return correoDAO.enviarNotificacionCreacion(defecto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Correo enviarNotificacionDefectoActualizado(Integer defecto) {
		try {
			return correoDAO.enviarNotificacionActualizacion(defecto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

