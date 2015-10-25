package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.Reportes;;

public interface ReportesDAO {
	
	public List<Reportes> getReportesEstado();
	
	public List<Reportes> getReportesMayorTiempoAbierto(String proyecto);
	
	public List<Reportes> getReportesFechaDias();
	
	public List<Reportes> getReportesCategoria();
	
	public List<Reportes> getReportesPrioridad();
	
	public List<Reportes> getReportesEstadisticasUsuarios();
	
	public List<Reportes> getReportesEstadisticasReporteros();
	
	public List<Reportes> getReportesReporterosResolucion();
	
	public List<Reportes> getReportesUsuariosResolucion();
	
	public List<Reportes> getReportesMasActivos();
	
}
