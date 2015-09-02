package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.Defectos;
import sgdbex.model.pojos.HistoricoEstados;

public interface DefectosDAO {

	public String createDefectos(Defectos Defecto);

	public String updateDefectos(Defectos Defecto);

	public String deleteDefectos(Defectos Defecto);

//	public List<Defectos> getAllDefectos();

	public List<Defectos> getDefectosNoAsignados(String usuario, String rol, String proyecto);

	public List<Defectos> getDefectosMonitorizados(String usuario, String rol, String proyecto);

	public List<Defectos> getDefectosAsignados(String usuario, String rol, String proyecto);
	
	public List<Defectos> getDefectosModificar(String usuario, String rol, String proyecto);
	
	public List<Defectos> getDefectosValidar(String usuario, String rol, String proyecto);

	public Defectos getDetalleDefecto(Integer id);
	
	public String cambiarEstadoDefecto(Defectos defecto);
	
	public List<HistoricoEstados> getHistoricoEstados(Integer id);

	public List<Defectos> getDefectosCerrados(String usuario, String rol, String proyecto);

	public List<Defectos> getDefectosModifReciente(String usuario, String rol, String proyecto);

	public List<Defectos> getDefectosReportadosPorMi(String usuario, String rol, String proyecto);

	public List<Defectos> buscarDefectos(String nombre, String proyecto, String categoria, String prioridad);

	
}
