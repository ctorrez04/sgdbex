/*************************************************************************************************************
Banco Exterior 
Sistema: Sistema de Gestion de Defectos Banco Exterior. 
Siglas: SGDBEX
Nombre: DefectosMB.java 
Fecha creación: 20/07/2015 
Versión: 1.0.0
 
Descripción: 
	Este managed bean controla todo lo relacionado a las busqueda de defecto en la pantalla buscar defectos.
 
Autor: 
       Claudio Torrez
 ---o---

Bitácora de Modificaciones:
Autor             Descripción                                                      Fecha Inicio     Fecha Fin
Claudio Torrez    Creación del managed bean											20/07/2015		02/08/2015

---o---

NOTA: Recuerda no eliminar código ya desarrollado, debes comentar la porción de
código e incluir la nueva, documentando la fecha de la sustitución. 		                       
***************************************************************************************************************/
package sgdbex.managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sgdbex.model.pojos.Defectos;
import sgdbex.model.pojos.Proyectos;
import sgdbex.services.GeneralServices;

@ManagedBean 
@Service
public class BusquedaMB {
	
	@Autowired
	private GeneralServices gs;
    @Autowired
	private Menu m;

	private String buscar;
	private Integer proyecto =0;
	private Integer prioridad =0;
	private Integer categoria =0;
	private List<Proyectos> proyectosList;
	
	private List<Defectos> defectosEncontrados;
	
	private List<Defectos> filtroDefectos;
	
    public GeneralServices getGs() {
		return gs;
	}

	public void setGs(GeneralServices gs) {
		this.gs = gs;
	}

	public Menu getM() {
		return m;
	}

	public void setM(Menu m) {
		this.m = m;
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}
	public void search(){
		System.out.println("\n\na buscar "+getBuscar());
		System.out.println("a proyecto "+getProyecto());
		System.out.println("a categoria "+getCategoria());
		System.out.println("a prioridad "+getPrioridad());
		String conProyectos= getProyecto() == 0 ? todosProyectos() : String.valueOf(getProyecto());
		String conCategorias= getCategoria() == 0 ? todosCategorias() : String.valueOf(getCategoria());
		String conPrioridad= getPrioridad() == 0 ? todasPrioridad() : String.valueOf(getPrioridad());
		System.out.println("\n\ncon proyecto "+conProyectos);
		System.out.println("con categoria "+conCategorias);
		System.out.println("con prioridad "+conPrioridad);
		defectosEncontrados=gs.buscarDefectos(getBuscar(),conProyectos, conCategorias, conPrioridad);
		setProyectosList(m.getProyectosUsuarioList());
	}
	private String todosProyectos(){
		String proyectos ="";
		for(int i=0;i<m.getProyectosUsuarioList().size();i++){
			proyectos+=m.getProyectosUsuarioList().get(i).getProyecto_id()+",";
		}
		return proyectos;
	}
	private String todosCategorias(){
		String categorias ="";
		for(int i=0;i<gs.getCategorias().size();i++){
			categorias+=gs.getCategorias().get(i).getCategoria_id()+",";
		}
		return categorias;
	}
	private String todasPrioridad(){
		String prioridades ="";
		for(int i=0;i<gs.obtenerPrioridades().size();i++){
			prioridades+=gs.obtenerPrioridades().get(i).getPrioridad_id()+",";
		}
		return prioridades;
	}

	public List<Defectos> getDefectosEncontrados() {
		return defectosEncontrados;
	}

	public void setDefectosEncontrados(List<Defectos> defectosEncontrados) {
		this.defectosEncontrados = defectosEncontrados;
	}
	public List<Defectos> getFiltroDefectos() {
		return filtroDefectos;
	}

	public void setFiltroDefectos(List<Defectos> filtroDefectos) {
		this.filtroDefectos = filtroDefectos;
	}

	public Integer getProyecto() {
		return proyecto;
	}

	public void setProyecto(Integer proyecto) {
		this.proyecto = proyecto;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public List<Proyectos> getProyectosList() {
		return proyectosList;
	}

	public void setProyectosList(List<Proyectos> proyectosList) {
		this.proyectosList = proyectosList;
	}
}
