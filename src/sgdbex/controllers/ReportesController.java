package sgdbex.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgdbex.model.pojos.Reportes;
import sgdbex.services.GeneralServices;

@ManagedBean
@ViewScoped
@Controller
public class ReportesController {
	
	@PostConstruct
	public void init(){
		inicializarDropdownReportes();
	}
	
	@Autowired
	private GeneralServices gs;
	
	private String reporteSeleccionado;
	
	//Cada reporte va enumerado para identificar que instrucciones realizar segun el que se pase 
	//por parametro al metodo initBarModel()
	private List<Reportes> reportesEstado;                        //Nº 1
	private List<Reportes> reportesTiempoAbierto;                 //Nº 2
	private List<Reportes> reportesFechaDias;                     //Nº 3
	private List<Reportes> reportesCategoria;                     //Nº 4
	private List<Reportes> reportesPrioridad;                     //Nº 5
	private List<Reportes> reportesEstadisticasUsuarios;          //Nº 6
	private List<Reportes> reportesEstadisticasReporteros;        //Nº 7
	private List<Reportes> reportesReporterosResolucion;          //Nº 8
	private List<Reportes> reportesUsuariosResolucion;            //Nº 9
	private List<Reportes> reportesMasActivos;                    //Nº 10
	
	private List<Reportes> reportesfiltrados;                            //Nº 1
	private List<Reportes> reportesfiltradosTiempoAbierto;               //Nº 2
	private List<Reportes> reportesfiltradosFechaDias;                   //Nº 3
	private List<Reportes> reportesfiltradosCategoria;                   //Nº 4
	private List<Reportes> reportesfiltradosPrioridad;                   //Nº 5
	private List<Reportes> reportesfiltradosEstadisticasUsuarios;        //Nº 6
	private List<Reportes> reportesfiltradosEstadisticasReporteros;      //Nº 7
	private List<Reportes> reportesfiltradosReporterosResolucion;        //Nº 8
	private List<Reportes> reportesfiltradosUsuariosResolucion;          //Nº 9
	private List<Reportes> reportesfiltradosMasActivos;                  //Nº 10
	
	private BarChartModel barModel;                                      //Nº 1
	private BarChartModel barModelTiempoAbierto;                         //Nº 2
	private BarChartModel barModelFechaDias;                             //Nº 3
	private BarChartModel barModelCategoria;                             //Nº 4
	private BarChartModel barModelPrioridad;                             //Nº 5
	private BarChartModel barModelEstadisticasUsuarios;                  //Nº 6
	private BarChartModel barModelEstadisticasReporteros;                //Nº 7
	private BarChartModel barModelReporterosResolucion;                  //Nº 8
	private BarChartModel barModelUsuariosResolucion;                    //Nº 9
	private BarChartModel barModelActivos;                               //Nº 10
	
	private boolean reportesEstadoRender=false;                        //Nº 1
	private boolean reportesTiempoAbiertoRender=false;                 //Nº 2
	private boolean reportesFechaDiasRender=false;                     //Nº 3
	private boolean reportesCategoriaRender=false;                     //Nº 4
	private boolean reportesPrioridadRender=false;                     //Nº 5
	private boolean reportesEstadisticasUsuariosRender=false;          //Nº 6
	private boolean reportesEstadisticasReporterosRender=false;        //Nº 7
	private boolean reportesReporterosResolucionRender=false;          //Nº 8
	private boolean reportesUsuariosResolucionRender=false;            //Nº 9
	private boolean reportesMasActivosRender=false;                    //Nº 10
	
	private boolean graficoEstadoRender=false;                        //Nº 1
	private boolean graficoTiempoAbiertoRender=false;                 //Nº 2
	private boolean graficoFechaDiasRender=false;                     //Nº 3
	private boolean graficoCategoriaRender=false;                     //Nº 4
	private boolean graficoPrioridadRender=false;                     //Nº 5
	private boolean graficoEstadisticasUsuariosRender=false;          //Nº 6
	private boolean graficoEstadisticasReporterosRender=false;        //Nº 7
	private boolean graficoReporterosResolucionRender=false;          //Nº 8
	private boolean graficoUsuariosResolucionRender=false;            //Nº 9
	private boolean graficoMasActivosRender=false;                    //Nº 10
	
	private List<Reportes> opcionesReportes;     
	
	public List<Reportes> getOpcionesReportes() {
		return opcionesReportes;
	}

	public void setOpcionesReportes(List<Reportes> opcionesReportes) {
		this.opcionesReportes = opcionesReportes;
	}

	public boolean isGraficoEstadoRender() {
		return graficoEstadoRender;
	}

	public void setGraficoEstadoRender(boolean graficoEstadoRender) {
		this.graficoEstadoRender = graficoEstadoRender;
	}

	public boolean isGraficoTiempoAbiertoRender() {
		return graficoTiempoAbiertoRender;
	}

	public void setGraficoTiempoAbiertoRender(boolean graficoTiempoAbiertoRender) {
		this.graficoTiempoAbiertoRender = graficoTiempoAbiertoRender;
	}

	public boolean isGraficoFechaDiasRender() {
		return graficoFechaDiasRender;
	}

	public void setGraficoFechaDiasRender(boolean graficoFechaDiasRender) {
		this.graficoFechaDiasRender = graficoFechaDiasRender;
	}

	public boolean isGraficoCategoriaRender() {
		return graficoCategoriaRender;
	}

	public void setGraficoCategoriaRender(boolean graficoCategoriaRender) {
		this.graficoCategoriaRender = graficoCategoriaRender;
	}

	public boolean isGraficoPrioridadRender() {
		return graficoPrioridadRender;
	}

	public void setGraficoPrioridadRender(boolean graficoPrioridadRender) {
		this.graficoPrioridadRender = graficoPrioridadRender;
	}

	public boolean isGraficoEstadisticasUsuariosRender() {
		return graficoEstadisticasUsuariosRender;
	}

	public void setGraficoEstadisticasUsuariosRender(
			boolean graficoEstadisticasUsuariosRender) {
		this.graficoEstadisticasUsuariosRender = graficoEstadisticasUsuariosRender;
	}

	public boolean isGraficoEstadisticasReporterosRender() {
		return graficoEstadisticasReporterosRender;
	}

	public void setGraficoEstadisticasReporterosRender(
			boolean graficoEstadisticasReporterosRender) {
		this.graficoEstadisticasReporterosRender = graficoEstadisticasReporterosRender;
	}

	public boolean isGraficoReporterosResolucionRender() {
		return graficoReporterosResolucionRender;
	}

	public void setGraficoReporterosResolucionRender(
			boolean graficoReporterosResolucionRender) {
		this.graficoReporterosResolucionRender = graficoReporterosResolucionRender;
	}

	public boolean isGraficoUsuariosResolucionRender() {
		return graficoUsuariosResolucionRender;
	}

	public void setGraficoUsuariosResolucionRender(
			boolean graficoUsuariosResolucionRender) {
		this.graficoUsuariosResolucionRender = graficoUsuariosResolucionRender;
	}

	public boolean isGraficoMasActivosRender() {
		return graficoMasActivosRender;
	}

	public void setGraficoMasActivosRender(boolean graficoMasActivosRender) {
		this.graficoMasActivosRender = graficoMasActivosRender;
	}

	public String getReporteSeleccionado() {
		return reporteSeleccionado;
	}

	public void setReporteSeleccionado(String reporteSeleccionado) {
		this.reporteSeleccionado = reporteSeleccionado;
	}

	public List<Reportes> getReportesTiempoAbierto() {
		return reportesTiempoAbierto;
	}
	
	public boolean getReportesEstadoRender() {
		return reportesEstadoRender;
	}

	public void setReportesEstadoRender(boolean reportesEstadoRender) {
		this.reportesEstadoRender = reportesEstadoRender;
	}

	public boolean getReportesTiempoAbiertoRender() {
		return reportesTiempoAbiertoRender;
	}

	public void setReportesTiempoAbiertoRender(
			boolean reportesTiempoAbiertoRender) {
		this.reportesTiempoAbiertoRender = reportesTiempoAbiertoRender;
	}

	public boolean getReportesFechaDiasRender() {
		return reportesFechaDiasRender;
	}

	public void setReportesFechaDiasRender(boolean reportesFechaDiasRender) {
		this.reportesFechaDiasRender = reportesFechaDiasRender;
	}

	public boolean getReportesCategoriaRender() {
		return reportesCategoriaRender;
	}

	public void setReportesCategoriaRender(boolean reportesCategoriaRender) {
		this.reportesCategoriaRender = reportesCategoriaRender;
	}

	public boolean getReportesPrioridadRender() {
		return reportesPrioridadRender;
	}

	public void setReportesPrioridadRender(boolean reportesPrioridadRender) {
		this.reportesPrioridadRender = reportesPrioridadRender;
	}

	public boolean getReportesEstadisticasUsuariosRender() {
		return reportesEstadisticasUsuariosRender;
	}

	public void setReportesEstadisticasUsuariosRender(
			boolean reportesEstadisticasUsuariosRender) {
		this.reportesEstadisticasUsuariosRender = reportesEstadisticasUsuariosRender;
	}

	public boolean getReportesEstadisticasReporterosRender() {
		return reportesEstadisticasReporterosRender;
	}

	public void setReportesEstadisticasReporterosRender(
			boolean reportesEstadisticasReporterosRender) {
		this.reportesEstadisticasReporterosRender = reportesEstadisticasReporterosRender;
	}

	public boolean getReportesReporterosResolucionRender() {
		return reportesReporterosResolucionRender;
	}

	public void setReportesReporterosResolucionRender(
			boolean reportesReporterosResolucionRender) {
		this.reportesReporterosResolucionRender = reportesReporterosResolucionRender;
	}

	public boolean getReportesUsuariosResolucionRender() {
		return reportesUsuariosResolucionRender;
	}

	public void setReportesUsuariosResolucionRender(
			boolean reportesUsuariosResolucionRender) {
		this.reportesUsuariosResolucionRender = reportesUsuariosResolucionRender;
	}

	public boolean getReportesMasActivosRender() {
		return reportesMasActivosRender;
	}

	public void setReportesMasActivosRender(boolean reportesMasActivosRender) {
		this.reportesMasActivosRender = reportesMasActivosRender;
	}

	public List<Reportes> getReportesMasActivos() {
		return reportesMasActivos;
	}

	public void setReportesMasActivos(List<Reportes> reportesMasActivos) {
		this.reportesMasActivos = reportesMasActivos;
	}

	public List<Reportes> getReportesfiltradosMasActivos() {
		return reportesfiltradosMasActivos;
	}

	public void setReportesfiltradosMasActivos(
			List<Reportes> reportesfiltradosMasActivos) {
		this.reportesfiltradosMasActivos = reportesfiltradosMasActivos;
	}

	public List<Reportes> getReportesFechaDias() {
		return reportesFechaDias;
	}

	public void setReportesFechaDias(List<Reportes> reportesFechaDias) {
		this.reportesFechaDias = reportesFechaDias;
	}

	public List<Reportes> getReportesCategoria() {
		return reportesCategoria;
	}

	public void setReportesCategoria(List<Reportes> reportesCategoria) {
		this.reportesCategoria = reportesCategoria;
	}

	public List<Reportes> getReportesPrioridad() {
		return reportesPrioridad;
	}

	public void setReportesPrioridad(List<Reportes> reportesPrioridad) {
		this.reportesPrioridad = reportesPrioridad;
	}

	public List<Reportes> getReportesEstadisticasUsuarios() {
		return reportesEstadisticasUsuarios;
	}

	public void setReportesEstadisticasUsuarios(
			List<Reportes> reportesEstadisticasUsuarios) {
		this.reportesEstadisticasUsuarios = reportesEstadisticasUsuarios;
	}

	public List<Reportes> getReportesEstadisticasReporteros() {
		return reportesEstadisticasReporteros;
	}

	public void setReportesEstadisticasReporteros(
			List<Reportes> reportesEstadisticasReporteros) {
		this.reportesEstadisticasReporteros = reportesEstadisticasReporteros;
	}

	public List<Reportes> getReportesReporterosResolucion() {
		return reportesReporterosResolucion;
	}

	public void setReportesReporterosResolucion(
			List<Reportes> reportesReporterosResolucion) {
		this.reportesReporterosResolucion = reportesReporterosResolucion;
	}

	public List<Reportes> getReportesUsuariosResolucion() {
		return reportesUsuariosResolucion;
	}

	public void setReportesUsuariosResolucion(
			List<Reportes> reportesUsuariosResolucion) {
		this.reportesUsuariosResolucion = reportesUsuariosResolucion;
	}

	public List<Reportes> getReportesfiltradosFechaDias() {
		return reportesfiltradosFechaDias;
	}

	public void setReportesfiltradosFechaDias(
			List<Reportes> reportesfiltradosFechaDias) {
		this.reportesfiltradosFechaDias = reportesfiltradosFechaDias;
	}

	public List<Reportes> getReportesfiltradosCategoria() {
		return reportesfiltradosCategoria;
	}

	public void setReportesfiltradosCategoria(
			List<Reportes> reportesfiltradosCategoria) {
		this.reportesfiltradosCategoria = reportesfiltradosCategoria;
	}

	public List<Reportes> getReportesfiltradosPrioridad() {
		return reportesfiltradosPrioridad;
	}

	public void setReportesfiltradosPrioridad(
			List<Reportes> reportesfiltradosPrioridad) {
		this.reportesfiltradosPrioridad = reportesfiltradosPrioridad;
	}

	public List<Reportes> getReportesfiltradosEstadisticasUsuarios() {
		return reportesfiltradosEstadisticasUsuarios;
	}

	public void setReportesfiltradosEstadisticasUsuarios(
			List<Reportes> reportesfiltradosEstadisticasUsuarios) {
		this.reportesfiltradosEstadisticasUsuarios = reportesfiltradosEstadisticasUsuarios;
	}

	public List<Reportes> getReportesfiltradosEstadisticasReporteros() {
		return reportesfiltradosEstadisticasReporteros;
	}

	public void setReportesfiltradosEstadisticasReporteros(
			List<Reportes> reportesfiltradosEstadisticasReporteros) {
		this.reportesfiltradosEstadisticasReporteros = reportesfiltradosEstadisticasReporteros;
	}

	public List<Reportes> getReportesfiltradosReporterosResolucion() {
		return reportesfiltradosReporterosResolucion;
	}

	public void setReportesfiltradosReporterosResolucion(
			List<Reportes> reportesfiltradosReporterosResolucion) {
		this.reportesfiltradosReporterosResolucion = reportesfiltradosReporterosResolucion;
	}

	public List<Reportes> getReportesfiltradosUsuariosResolucion() {
		return reportesfiltradosUsuariosResolucion;
	}

	public void setReportesfiltradosUsuariosResolucion(
			List<Reportes> reportesfiltradosUsuariosResolucion) {
		this.reportesfiltradosUsuariosResolucion = reportesfiltradosUsuariosResolucion;
	}

	public void setReportesTiempoAbierto(List<Reportes> reportesTiempoAbierto) {
		this.reportesTiempoAbierto = reportesTiempoAbierto;
	}

	public List<Reportes> getReportesfiltradosTiempoAbierto() {
		return reportesfiltradosTiempoAbierto;
	}

	public void setReportesfiltradosTiempoAbierto(
			List<Reportes> reportesfiltradosTiempoAbierto) {
		this.reportesfiltradosTiempoAbierto = reportesfiltradosTiempoAbierto;
	}

	public BarChartModel getBarModelTiempoAbierto() {
		return barModelTiempoAbierto;
	}

	public void setBarModelTiempoAbierto(BarChartModel barModelTiempoAbierto) {
		this.barModelTiempoAbierto = barModelTiempoAbierto;
	}

	public void setReportesEstado(List<Reportes> reportesEstado) {
		this.reportesEstado = reportesEstado;
	}

	public GeneralServices getGs() {
		return gs;
	}
	
	public BarChartModel getBarModelFechaDias() {
		return barModelFechaDias;
	}

	public void setBarModelFechaDias(BarChartModel barModelFechaDias) {
		this.barModelFechaDias = barModelFechaDias;
	}

	public BarChartModel getBarModelCategoria() {
		return barModelCategoria;
	}

	public void setBarModelCategoria(BarChartModel barModelCategoria) {
		this.barModelCategoria = barModelCategoria;
	}

	public BarChartModel getBarModelPrioridad() {
		return barModelPrioridad;
	}

	public void setBarModelPrioridad(BarChartModel barModelPrioridad) {
		this.barModelPrioridad = barModelPrioridad;
	}

	public BarChartModel getBarModelEstadisticasUsuarios() {
		return barModelEstadisticasUsuarios;
	}

	public void setBarModelEstadisticasUsuarios(
			BarChartModel barModelEstadisticasUsuarios) {
		this.barModelEstadisticasUsuarios = barModelEstadisticasUsuarios;
	}

	public BarChartModel getBarModelEstadisticasReporteros() {
		return barModelEstadisticasReporteros;
	}

	public void setBarModelEstadisticasReporteros(
			BarChartModel barModelEstadisticasReporteros) {
		this.barModelEstadisticasReporteros = barModelEstadisticasReporteros;
	}

	public BarChartModel getBarModelReporterosResolucion() {
		return barModelReporterosResolucion;
	}

	public void setBarModelReporterosResolucion(
			BarChartModel barModelReporterosResolucion) {
		this.barModelReporterosResolucion = barModelReporterosResolucion;
	}

	public BarChartModel getBarModelUsuariosResolucion() {
		return barModelUsuariosResolucion;
	}

	public void setBarModelUsuariosResolucion(
			BarChartModel barModelUsuariosResolucion) {
		this.barModelUsuariosResolucion = barModelUsuariosResolucion;
	}

	public BarChartModel getBarModelActivos() {
		return barModelActivos;
	}

	public void setBarModelActivos(BarChartModel barModelActivos) {
		this.barModelActivos = barModelActivos;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public void setGs(GeneralServices gs) {
		this.gs = gs;
	}

	public List<Reportes> getReportesEstado() {
		return reportesEstado;
	}

	public void setReportesList(List<Reportes> reportesEstado) {
		this.reportesEstado = reportesEstado;
	}

	public List<Reportes> getReportesfiltrados() {
		return reportesfiltrados;
	}

	public void setReportesfiltrados(List<Reportes> reportesfiltrados) {
		this.reportesfiltrados = reportesfiltrados;
	}

	public ReportesController(){
		
	}
	
	public void reportesGeneral(ActionEvent e){
		try{
			reportesEstado = gs.getReportesEstado();
			reportesTiempoAbierto = gs.getReportesMayorTiempoAbierto();
			reportesFechaDias = gs.getReportesFechaDias();
			reportesCategoria = gs.getReportesCategoria();
			reportesPrioridad = gs.getReportesPrioridad();
			reportesEstadisticasUsuarios = gs.getReportesEstadisticasUsuarios();
			reportesEstadisticasReporteros = gs.getReportesEstadisticasReporteros();
			reportesReporterosResolucion = gs.getReportesReporterosResolucion();
			reportesUsuariosResolucion = gs.getReportesUsuariosResolucion();
			createBarModel(reportesEstado, reportesTiempoAbierto,reportesFechaDias,reportesCategoria,reportesPrioridad,reportesEstadisticasUsuarios,reportesEstadisticasReporteros,reportesReporterosResolucion,reportesUsuariosResolucion);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/views/reportes/reporteGeneral.jsf");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void createBarModel(List<Reportes> reportesEstado, List<Reportes> reportesTiempoAbierto, List<Reportes> reportesFechaDias, List<Reportes> reportesCategoria, List<Reportes> reportesPrioridad, List<Reportes> reportesEstadisticasUsuarios, List<Reportes> reportesEstadisticasReporteros, List<Reportes> reportesReporterosResolucion, List<Reportes> reportesUsuariosResolucion) {
        barModel = initBarModel(reportesEstado,1);
        //barModel.setExtender("plotAxisInterval");
        barModelTiempoAbierto = initBarModel(reportesTiempoAbierto,2);
        barModelFechaDias = initBarModel(reportesFechaDias,3);
        barModelFechaDias.setExtender("plotAxisInterval");
        barModelCategoria = initBarModel(reportesCategoria,4);
        barModelCategoria.setExtender("plotAxisInterval2");
        barModelPrioridad = initBarModel(reportesPrioridad,5);
        barModelPrioridad.setExtender("plotAxisInterval2");
        barModelEstadisticasUsuarios = initBarModel(reportesEstadisticasUsuarios,6);
        barModelEstadisticasUsuarios.setExtender("plotAxisInterval2");
        barModelEstadisticasReporteros = initBarModel(reportesEstadisticasReporteros,7);
        barModelEstadisticasReporteros.setExtender("plotAxisInterval2");
        barModelReporterosResolucion = initBarModel(reportesReporterosResolucion,8);
        barModelReporterosResolucion.setExtender("plotAxisInterval2");
        barModelUsuariosResolucion = initBarModel(reportesUsuariosResolucion,9);
        barModelUsuariosResolucion.setExtender("plotAxisInterval2");
         
        barModel.setTitle("Grafico Defectos por Estado");
        barModel.setLegendPosition("ne");
        
        barModelTiempoAbierto.setTitle("10 Defectos Mayor Tiempo Abiertos");
        barModelTiempoAbierto.setLegendPosition("ne");
        
        barModelFechaDias.setTitle("Grafico Defectos Intervalo en Dias");
        barModelFechaDias.setLegendPosition("ne");
        
        barModelCategoria.setTitle("Grafico Defectos por Categoria y Estado");
        barModelCategoria.setLegendPosition("ne");
        // LegendPosition = (n, s, w, e, se, sw, ne, nw)
        //barModelCategoria.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        //barModelCategoria.setShowPointLabels(true);
        
        barModelPrioridad.setTitle("Grafico Defectos por Prioridad y Estado");
        barModelPrioridad.setLegendPosition("ne");
        barModelPrioridad.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        //barModelPrioridad.setShowDatatip(false);
        
        barModelEstadisticasUsuarios.setTitle("Estadisticas Rol Administrador,Lider,Analista");
        barModelEstadisticasUsuarios.setLegendPosition("ne");
        barModelEstadisticasUsuarios.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        
        barModelEstadisticasReporteros.setTitle("Estadisticas Usuarios Reporteros");
        barModelEstadisticasReporteros.setLegendPosition("ne");
        barModelEstadisticasReporteros.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        
        barModelReporterosResolucion.setTitle("Usuarios Reporteros por Motivo Resolucion");
        barModelReporterosResolucion.setLegendPosition("ne");
        barModelReporterosResolucion.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        
        barModelUsuariosResolucion.setTitle("Usuarios Analista,Lider,Admin por Motivo Resolucion");
        barModelUsuariosResolucion.setLegendPosition("ne");
        barModelUsuariosResolucion.setLegendPlacement(LegendPlacement.OUTSIDE);
//        barModelUsuariosResolucion.setShowDatatip(false);
//        barModelUsuariosResolucion.setMouseoverHighlight(false);
//        barModelUsuariosResolucion.setShadow(false);
//        barModelUsuariosResolucion.setShowPointLabels(true);
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Estados");
        
        Axis xAxis2 = barModelTiempoAbierto.getAxis(AxisType.X);
        xAxis2.setLabel("Defectos Mayor Tiempo Abiertos");
        
        //Axis xAxis3 = barModelFechaDias.getAxis(AxisType.X);
        //xAxis3.setLabel("Defectos Intervalos Dias por Estado");
        
        Axis xAxis4 = barModelCategoria.getAxis(AxisType.X);
        xAxis4.setLabel("Defectos por Categoria y por Estado");
        
        Axis xAxis5 = barModelPrioridad.getAxis(AxisType.X);
        xAxis5.setLabel("Defectos por Prioridad y por Estado");
        
        Axis xAxis6 = barModelEstadisticasUsuarios.getAxis(AxisType.X);
        xAxis6.setLabel("Estadisticas Usuarios Analista,Lider,Admin");
        
        Axis xAxis7 = barModelEstadisticasReporteros.getAxis(AxisType.X);
        xAxis7.setLabel("Estadisticas Usuarios Reporteros");
        
        Axis xAxis8 = barModelReporterosResolucion.getAxis(AxisType.X);
        xAxis8.setLabel("Defectos Motivo Resolucion Reporteros");
        
        Axis xAxis9 = barModelUsuariosResolucion.getAxis(AxisType.X);
        xAxis9.setLabel("Defectos Motivo Resolucion Analista,Lider,Admin");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total Defectos");
        yAxis.setMin(0);
        int MaxTotalDefectos = ValorTotalMaximoReporte(reportesEstado);
        int CotaMaxY = CotaEjeY(MaxTotalDefectos);
        yAxis.setMax(CotaMaxY);
        
        Axis yAxis2 = barModelTiempoAbierto.getAxis(AxisType.Y);
        yAxis2.setLabel("Total Dias");
        yAxis2.setMin(0);
        int MaxTotalDefectos2 = ValorDiasMaximoReporte(reportesTiempoAbierto);
        int CotaMaxY2 = CotaEjeY(MaxTotalDefectos2);
        yAxis2.setMax(CotaMaxY2);
        
        //Axis yAxis3 = barModelFechaDias.getAxis(AxisType.Y); ////////////
        //yAxis3.setLabel("Total");                            ////////////
        //yAxis3.setMin(0);                                    ////////////
        //yAxis3.setMax(8);                                    ////////////
        
        Axis yAxis4 = barModelCategoria.getAxis(AxisType.Y);
        yAxis4.setLabel("Total Defectos");
        yAxis4.setMin(0);
        int MaxTotalDefectos4 = ValorTotalMaximoReporte(reportesCategoria);
        int CotaMaxY4 = CotaEjeY(MaxTotalDefectos4);
        yAxis4.setMax(CotaMaxY4);
        
        Axis yAxis5 = barModelPrioridad.getAxis(AxisType.Y);
        yAxis5.setLabel("Total Defectos");
        yAxis5.setMin(0);
        int MaxTotalDefectos5 = ValorTotalMaximoReporte(reportesPrioridad);
        int CotaMaxY5 = CotaEjeY(MaxTotalDefectos5);
        yAxis5.setMax(CotaMaxY5);
        
        Axis yAxis6 = barModelEstadisticasUsuarios.getAxis(AxisType.Y);
        yAxis6.setLabel("Total Defectos");
        yAxis6.setMin(0);
        int MaxTotalDefectos6 = ValorTotalMaximoReporte(reportesEstadisticasUsuarios);
        int CotaMaxY6 = CotaEjeY(MaxTotalDefectos6);
        yAxis6.setMax(CotaMaxY6);
        
        Axis yAxis7 = barModelEstadisticasReporteros.getAxis(AxisType.Y);
        yAxis7.setLabel("Total Defectos");
        yAxis7.setMin(0);
        int MaxTotalDefectos7 = ValorTotalMaximoReporte(reportesEstadisticasReporteros);
        int CotaMaxY7 = CotaEjeY(MaxTotalDefectos7);
        yAxis7.setMax(CotaMaxY7);
        
        Axis yAxis8 = barModelReporterosResolucion.getAxis(AxisType.Y);
        yAxis8.setLabel("Total Defectos");
        yAxis8.setMin(0);
        int MaxTotalDefectos8 = ValorTotalMaximoReporte(reportesReporterosResolucion);
        int CotaMaxY8 = CotaEjeY(MaxTotalDefectos8);
        yAxis8.setMax(CotaMaxY8);
        
        Axis yAxis9 = barModelUsuariosResolucion.getAxis(AxisType.Y);
        yAxis9.setLabel("Total Defectos");
        yAxis9.setMin(0);
        int MaxTotalDefectos9 = ValorTotalMaximoReporte(reportesUsuariosResolucion);
        int CotaMaxY9 = CotaEjeY(MaxTotalDefectos9);
        yAxis9.setMax(CotaMaxY9);
        
    }
	
	//Calcular valor maximo de la columna 'total' de los reportes:
	public int ValorTotalMaximoReporte(List<Reportes> lista){
		int max=lista.get(0).getTotal();
        for(int i=0;i<lista.size();i++){
        	if(lista.get(i).getTotal()>max)
        		max=lista.get(i).getTotal();
        }
        return max;
	}
	
	//Calcular valor maximo de la columna 'dias' de los reportes:
		public int ValorDiasMaximoReporte(List<Reportes> lista){
			int max=lista.get(0).getDias();
	        for(int i=0;i<lista.size();i++){
	        	if(lista.get(i).getDias()>max)
	        		max=lista.get(i).getDias();
	        }
	        return max;
		}
	
	//Valor multiplo de 4 ya que los BarChart tienen por defecto tickInterval=4
	public int CotaEjeY(int max){
		if(max<=4){
        	return (8)+24;
        }
		else{
        	if(max%4==0){
        		return (max+4)+24;
        	}else{
	        	while(max%4!=0){
	        		if(max%4!=0){
	        			max=max+1;
	        		}
	        		if(max%4==0){
	        			break;
	        		}
	        	}
	        return (max)+24;
        	}
        }
	}
     
    private BarChartModel initBarModel(List<Reportes> reportesList, int idreporte) {
        BarChartModel model = new BarChartModel();
        ChartSeries estados = new ChartSeries();
        ChartSeries estados2 = new ChartSeries();
        ChartSeries estados3 = new ChartSeries();
        ChartSeries estados4 = new ChartSeries();
        ChartSeries estados5 = new ChartSeries();
        
        if(idreporte==1){
	        estados.setLabel("Estados");
	        estados.set("Abierto", (Number)reportesList.get(0).getTotal());
	        estados.set("Asignado", (Number)reportesList.get(1).getTotal());
	        estados.set("Cerrado", (Number)reportesList.get(2).getTotal());
	        estados.set("Re-Abierto", (Number)reportesList.get(3).getTotal());
	        estados.set("Resuelto", (Number)reportesList.get(4).getTotal());
	        
	        model.addSeries(estados);
	        model.setDatatipFormat(getDatatipFormatX());
        }
        
        if(idreporte==2){
            estados.setLabel("DefectosAbiertosMasAntiguos");
            for(int i=0;i<reportesList.size();i++){
            	estados.set("ID "+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getDias());
            }
     
            model.addSeries(estados);
            model.setDatatipFormat(getDatatipFormatX());
        }
        
        if(idreporte==3){
        	estados.setLabel("Defectos Abiertos");
        	for(int i=0;i<reportesList.size();i++){
        		estados.set("id"+i, (Number)reportesList.get(i).getCantidadAbiertos());
        	}
        	estados2.setLabel("Defectos Resueltos");
        	for(int i=0;i<reportesList.size();i++){
        		estados2.set("id"+i, (Number)reportesList.get(i).getCantidadResueltos());
        	}
        	
        	model.addSeries(estados);
        	model.addSeries(estados2);
        	model.setDatatipFormat(getDatatipFormatX());
        }
        
		if(idreporte==4){
			estados.setLabel("Defectos Abiertos");
        	for(int i=0;i<reportesList.size();i=i+5){
        		estados.set("ID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados2.setLabel("Defectos Asignados");
        	for(int i=1;i<reportesList.size();i=i+5){
        		estados2.set("ID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados3.setLabel("Defectos Cerrados");
        	for(int i=2;i<reportesList.size();i=i+5){
        		estados3.set("ID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados4.setLabel("Defectos Re-Abiertos");
        	for(int i=3;i<reportesList.size();i=i+5){
        		estados4.set("ID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados5.setLabel("Defectos Resueltos");
        	for(int i=4;i<reportesList.size();i=i+5){
        		estados5.set("ID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	
        	model.addSeries(estados);
        	model.addSeries(estados2);
        	model.addSeries(estados3);
        	model.addSeries(estados4);
        	model.addSeries(estados5);
        	model.setDatatipFormat(getDatatipFormatX());
		}
		        
		if(idreporte==5){
			estados.setLabel("Defectos Abiertos");
        	for(int i=0;i<reportesList.size();i=i+5){
        		estados.set("ID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados2.setLabel("Defectos Asignados");
        	for(int i=1;i<reportesList.size();i=i+5){
        		estados2.set("ID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados3.setLabel("Defectos Cerrados");
        	for(int i=2;i<reportesList.size();i=i+5){
        		estados3.set("ID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados4.setLabel("Defectos Re-Abiertos");
        	for(int i=3;i<reportesList.size();i=i+5){
        		estados4.set("ID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados5.setLabel("Defectos Resueltos");
        	for(int i=4;i<reportesList.size();i=i+5){
        		estados5.set("ID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	
        	model.addSeries(estados);
        	model.addSeries(estados2);
        	model.addSeries(estados3);
        	model.addSeries(estados4);
        	model.addSeries(estados5);
        	model.setDatatipFormat(getDatatipFormatX());
		}
		
		if(idreporte==6){
			estados.setLabel("Defectos Asignados");
        	for(int i=0;i<reportesList.size();i=i+2){
        		estados.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados2.setLabel("Defectos Resueltos");
        	for(int i=1;i<reportesList.size();i=i+2){
        		estados2.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	
        	model.addSeries(estados);
        	model.addSeries(estados2);
        	model.setDatatipFormat(getDatatipFormatX());
		}
		
		if(idreporte==7){
			estados.setLabel("Defectos Abiertos");
        	for(int i=0;i<reportesList.size();i=i+5){
        		estados.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados2.setLabel("Defectos Asignados");
        	for(int i=1;i<reportesList.size();i=i+5){
        		estados2.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados3.setLabel("Defectos Cerrados");
        	for(int i=2;i<reportesList.size();i=i+5){
        		estados3.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados4.setLabel("Defectos Re-Abiertos");
        	for(int i=3;i<reportesList.size();i=i+5){
        		estados4.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados5.setLabel("Defectos Resueltos");
        	for(int i=4;i<reportesList.size();i=i+5){
        		estados5.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	
        	model.addSeries(estados);
        	model.addSeries(estados2);
        	model.addSeries(estados3);
        	model.addSeries(estados4);
        	model.addSeries(estados5);
        	model.setDatatipFormat(getDatatipFormatX());
		}
		
		if(idreporte==8){
			estados.setLabel("Resolucion Duplicada");
        	for(int i=0;i<reportesList.size();i=i+5){
        		estados.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados2.setLabel("Resolucion NoCorregible");
        	for(int i=1;i<reportesList.size();i=i+5){
        		estados2.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados3.setLabel("Resolucion NoReproducible");
        	for(int i=2;i<reportesList.size();i=i+5){
        		estados3.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados4.setLabel("Resolucion NoSeArreglara");
        	for(int i=3;i<reportesList.size();i=i+5){
        		estados4.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados5.setLabel("Resolucion Suspendida");
        	for(int i=4;i<reportesList.size();i=i+5){
        		estados5.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	
        	model.addSeries(estados);
        	model.addSeries(estados2);
        	model.addSeries(estados3);
        	model.addSeries(estados4);
        	model.addSeries(estados5);
        	model.setDatatipFormat(getDatatipFormatX());
		}
		
		if(idreporte==9){
			estados.setLabel("Resolucion Duplicada");
        	for(int i=0;i<reportesList.size();i=i+5){
        		estados.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados2.setLabel("Resolucion NoCorregible");
        	for(int i=1;i<reportesList.size();i=i+5){
        		estados2.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados3.setLabel("Resolucion NoReproducible");
        	for(int i=2;i<reportesList.size();i=i+5){
        		estados3.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados4.setLabel("Resolucion NoSeArreglara");
        	for(int i=3;i<reportesList.size();i=i+5){
        		estados4.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	estados5.setLabel("Resolucion Suspendida");
        	for(int i=4;i<reportesList.size();i=i+5){
        		estados5.set("UserID"+reportesList.get(i).getDefectoid(), (Number)reportesList.get(i).getTotal());
        	}
        	
        	model.addSeries(estados);
        	model.addSeries(estados2);
        	model.addSeries(estados3);
        	model.addSeries(estados4);
        	model.addSeries(estados5);
        	model.setDatatipFormat(getDatatipFormatX());
		}
		
		//Faltaria es reporte Nº 10 que son lo defectos 'Más Vistos' o 'Más Consultados'
		
        return model;
    }
    
    public String getDatatipFormatX() {
		return "<span style=display:none;>%s</span><span>%s</span>";
	}
    
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex()); 
        
        Object a = event.getSource();
        String b=a.getClass().getName();
        System.out.println("Tipo de Objeto b: "+b);
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void actualizarRenderReportes(){
    	if(reporteSeleccionado.equalsIgnoreCase("ReportePorEstado")){
    		reportesEstadoRender=true;
    		reportesTiempoAbiertoRender=false;                 
    		reportesFechaDiasRender=false;                     
    		reportesCategoriaRender=false;                     
    		reportesPrioridadRender=false;                     
    		reportesEstadisticasUsuariosRender=false;          
    		reportesEstadisticasReporterosRender=false;        
    		reportesReporterosResolucionRender=false;          
    		reportesUsuariosResolucionRender=false;            
    		reportesMasActivosRender=false;
    		graficoEstadoRender=true;
    		graficoTiempoAbiertoRender=false;                 
    		graficoFechaDiasRender=false;                     
    		graficoCategoriaRender=false;                     
    		graficoPrioridadRender=false;                     
    		graficoEstadisticasUsuariosRender=false;          
    		graficoEstadisticasReporterosRender=false;        
    		graficoReporterosResolucionRender=false;          
    		graficoUsuariosResolucionRender=false;            
    		graficoMasActivosRender=false;
    	}
    	
    	if(reporteSeleccionado.equalsIgnoreCase("ReporteDefectosMayorTiempoAbiertos")){
    		reportesTiempoAbiertoRender=true;
    		reportesEstadoRender=false;                                         
    		reportesFechaDiasRender=false;                     
    		reportesCategoriaRender=false;                     
    		reportesPrioridadRender=false;                     
    		reportesEstadisticasUsuariosRender=false;          
    		reportesEstadisticasReporterosRender=false;        
    		reportesReporterosResolucionRender=false;          
    		reportesUsuariosResolucionRender=false;            
    		reportesMasActivosRender=false;
    		graficoTiempoAbiertoRender=true;
    		graficoEstadoRender=false;                                        
    		graficoFechaDiasRender=false;                     
    		graficoCategoriaRender=false;                     
    		graficoPrioridadRender=false;                     
    		graficoEstadisticasUsuariosRender=false;          
    		graficoEstadisticasReporterosRender=false;        
    		graficoReporterosResolucionRender=false;          
    		graficoUsuariosResolucionRender=false;            
    		graficoMasActivosRender=false;
    	}
    	
    	if(reporteSeleccionado.equalsIgnoreCase("ReporteDefectosAbiertosResueltos(1-365Dias)")){
    		reportesFechaDiasRender=true;
    		reportesEstadoRender=false;                        
    		reportesTiempoAbiertoRender=false;                                    
    		reportesCategoriaRender=false;                     
    		reportesPrioridadRender=false;                     
    		reportesEstadisticasUsuariosRender=false;          
    		reportesEstadisticasReporterosRender=false;        
    		reportesReporterosResolucionRender=false;          
    		reportesUsuariosResolucionRender=false;            
    		reportesMasActivosRender=false;
    		graficoFechaDiasRender=true;
    		graficoEstadoRender=false;                        
    		graficoTiempoAbiertoRender=false;                                      
    		graficoCategoriaRender=false;                     
    		graficoPrioridadRender=false;                     
    		graficoEstadisticasUsuariosRender=false;          
    		graficoEstadisticasReporterosRender=false;        
    		graficoReporterosResolucionRender=false;          
    		graficoUsuariosResolucionRender=false;            
    		graficoMasActivosRender=false;
    	}
    	
    	if(reporteSeleccionado.equalsIgnoreCase("ReportePorCategoria")){
    		reportesCategoriaRender=true;
    		reportesEstadoRender=false;                        
    		reportesTiempoAbiertoRender=false;                 
    		reportesFechaDiasRender=false;                                          
    		reportesPrioridadRender=false;                     
    		reportesEstadisticasUsuariosRender=false;          
    		reportesEstadisticasReporterosRender=false;        
    		reportesReporterosResolucionRender=false;          
    		reportesUsuariosResolucionRender=false;            
    		reportesMasActivosRender=false;
    		graficoCategoriaRender=true;
    		graficoEstadoRender=false;                        
    		graficoTiempoAbiertoRender=false;                 
    		graficoFechaDiasRender=false;                                         
    		graficoPrioridadRender=false;                     
    		graficoEstadisticasUsuariosRender=false;          
    		graficoEstadisticasReporterosRender=false;        
    		graficoReporterosResolucionRender=false;          
    		graficoUsuariosResolucionRender=false;            
    		graficoMasActivosRender=false;
    	}
    	
    	if(reporteSeleccionado.equalsIgnoreCase("ReportePorPrioridad")){
    		reportesPrioridadRender=true;
    		reportesEstadoRender=false;                        
    		reportesTiempoAbiertoRender=false;                 
    		reportesFechaDiasRender=false;                     
    		reportesCategoriaRender=false;                     
    		reportesEstadisticasUsuariosRender=false;          
    		reportesEstadisticasReporterosRender=false;        
    		reportesReporterosResolucionRender=false;          
    		reportesUsuariosResolucionRender=false;            
    		reportesMasActivosRender=false;
    		graficoPrioridadRender=true;
    		graficoEstadoRender=false;                        
    		graficoTiempoAbiertoRender=false;                 
    		graficoFechaDiasRender=false;                     
    		graficoCategoriaRender=false;                     
    		graficoEstadisticasUsuariosRender=false;          
    		graficoEstadisticasReporterosRender=false;        
    		graficoReporterosResolucionRender=false;          
    		graficoUsuariosResolucionRender=false;            
    		graficoMasActivosRender=false;
    	}
    	
    	if(reporteSeleccionado.equalsIgnoreCase("ReporteEstadisticasUsuarios")){
    		reportesEstadisticasUsuariosRender=true;
    		reportesEstadoRender=false;                        
    		reportesTiempoAbiertoRender=false;                 
    		reportesFechaDiasRender=false;                     
    		reportesCategoriaRender=false;                     
    		reportesPrioridadRender=false;                     
    		reportesEstadisticasReporterosRender=false;        
    		reportesReporterosResolucionRender=false;          
    		reportesUsuariosResolucionRender=false;            
    		reportesMasActivosRender=false;
    		graficoEstadisticasUsuariosRender=true;
    		graficoEstadoRender=false;                        
    		graficoTiempoAbiertoRender=false;                 
    		graficoFechaDiasRender=false;                     
    		graficoCategoriaRender=false;                     
    		graficoPrioridadRender=false;                     
    		graficoEstadisticasReporterosRender=false;        
    		graficoReporterosResolucionRender=false;          
    		graficoUsuariosResolucionRender=false;            
    		graficoMasActivosRender=false;
    	}
    	
    	if(reporteSeleccionado.equalsIgnoreCase("ReporteEstadisticasReporteros")){
    		reportesEstadisticasReporterosRender= true;
    		reportesEstadoRender=false;                        
    		reportesTiempoAbiertoRender=false;                 
    		reportesFechaDiasRender=false;                     
    		reportesCategoriaRender=false;                     
    		reportesPrioridadRender=false;                     
    		reportesEstadisticasUsuariosRender=false;          
    		reportesReporterosResolucionRender=false;          
    		reportesUsuariosResolucionRender=false;            
    		reportesMasActivosRender=false;
    		graficoEstadisticasReporterosRender=true;
    		graficoEstadoRender=false;                        
    		graficoTiempoAbiertoRender=false;                 
    		graficoFechaDiasRender=false;                     
    		graficoCategoriaRender=false;                     
    		graficoPrioridadRender=false;                     
    		graficoEstadisticasUsuariosRender=false;          
    		graficoReporterosResolucionRender=false;          
    		graficoUsuariosResolucionRender=false;            
    		graficoMasActivosRender=false;
    	}
    	
    	if(reporteSeleccionado.equalsIgnoreCase("ReporteReporterosDefectosResolucion")){
    		reportesReporterosResolucionRender=true;
    		reportesEstadoRender=false;                        
    		reportesTiempoAbiertoRender=false;                 
    		reportesFechaDiasRender=false;                     
    		reportesCategoriaRender=false;                     
    		reportesPrioridadRender=false;                     
    		reportesEstadisticasUsuariosRender=false;          
    		reportesEstadisticasReporterosRender=false;        
    		reportesUsuariosResolucionRender=false;            
    		reportesMasActivosRender=false;
    		graficoReporterosResolucionRender=true;
    		graficoEstadoRender=false;                        
    		graficoTiempoAbiertoRender=false;                 
    		graficoFechaDiasRender=false;                     
    		graficoCategoriaRender=false;                     
    		graficoPrioridadRender=false;                     
    		graficoEstadisticasUsuariosRender=false;          
    		graficoEstadisticasReporterosRender=false;
    		graficoUsuariosResolucionRender=false;            
    		graficoMasActivosRender=false;
    	}
    	
    	if(reporteSeleccionado.equalsIgnoreCase("ReporteUsuariosDefectosResolucion")){
    		reportesUsuariosResolucionRender=true;
    		reportesEstadoRender=false;                        
    		reportesTiempoAbiertoRender=false;                 
    		reportesFechaDiasRender=false;                     
    		reportesCategoriaRender=false;                     
    		reportesPrioridadRender=false;                     
    		reportesEstadisticasUsuariosRender=false;          
    		reportesEstadisticasReporterosRender=false;        
    		reportesReporterosResolucionRender=false;          
    		reportesMasActivosRender=false;
    		graficoUsuariosResolucionRender=true;
    		graficoEstadoRender=false;                        
    		graficoTiempoAbiertoRender=false;                 
    		graficoFechaDiasRender=false;                     
    		graficoCategoriaRender=false;                     
    		graficoPrioridadRender=false;                     
    		graficoEstadisticasUsuariosRender=false;          
    		graficoEstadisticasReporterosRender=false;        
    		graficoReporterosResolucionRender=false;          
    		graficoMasActivosRender=false;
    	}
    	
    	/*if(reporteSeleccionado.equalsIgnoreCase("ReporteDefectosMasActivos")){
    		
    	}
    	*/
    }
    
    public void inicializarDropdownReportes(){
    	opcionesReportes= new ArrayList<Reportes>();
    	Reportes obj = new Reportes();
    	obj.setReporte("ReportePorEstado");
    	obj.setDefectoid(1);
    	opcionesReportes.add(obj);
    	Reportes obj2 = new Reportes();
    	obj2.setReporte("ReporteDefectosMayorTiempoAbiertos");
    	obj2.setDefectoid(2);
    	opcionesReportes.add(obj2);
    	Reportes obj3 = new Reportes();
    	obj3.setReporte("ReporteDefectosAbiertosResueltos(1-365Dias)");
    	obj3.setDefectoid(3);
    	opcionesReportes.add(obj3);
    	Reportes obj4 = new Reportes();
    	obj4.setReporte("ReportePorCategoria");
    	obj4.setDefectoid(4);
    	opcionesReportes.add(obj4);
    	Reportes obj5 = new Reportes();
    	obj5.setReporte("ReportePorPrioridad");
    	obj5.setDefectoid(5);
    	opcionesReportes.add(obj5);
    	Reportes obj6 = new Reportes();
    	obj6.setReporte("ReporteEstadisticasUsuarios");
    	obj6.setDefectoid(6);
    	opcionesReportes.add(obj6);
    	Reportes obj7 = new Reportes();
    	obj7.setReporte("ReporteEstadisticasReporteros");
    	obj7.setDefectoid(7);
    	opcionesReportes.add(obj7);
    	Reportes obj8 = new Reportes();
    	obj8.setReporte("ReporteReporterosDefectosResolucion");
    	obj8.setDefectoid(8);
    	opcionesReportes.add(obj8);
    	Reportes obj9 = new Reportes();
    	obj9.setReporte("ReporteUsuariosDefectosResolucion");
    	obj9.setDefectoid(9);
    	opcionesReportes.add(obj9);
    	System.out.println("Termino carga de opcionesReportes");
    } 
	
}
