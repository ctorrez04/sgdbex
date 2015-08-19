package sgdbex.managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sgdbex.controllers.GeneralController;
import sgdbex.model.pojos.Defectos;
import sgdbex.services.GeneralServices;

@ManagedBean 
@Service
public class PrincipalMB {

	@Autowired
	private GeneralServices gs;
    
	@Autowired
	public Menu m;
	
	@Autowired
	private GeneralController gc;
	
	private List<Defectos> lista1;
	private List<Defectos> lista2;
	private List<Defectos> lista3;
	private List<Defectos> lista4;
	
/*	private List<Defectos> modifReciente;
	private List<Defectos> asignados;
	private List<Defectos> porAsignar;
	private List<Defectos> monitorizados;
	private List<Defectos> reportados;
	private List<Defectos> validar;
	private List<Defectos> cerrados;*/
	public String titulo1="";
	public String titulo2="";
	public String titulo3="";
	public String titulo4="";
	
	public String getTitulo1() {
		return titulo1;
	}

	public void setTitulo1(String titulo1) {
		this.titulo1 = titulo1;
	}

	public String getTitulo2() {
		return titulo2;
	}

	public void setTitulo2(String titulo2) {
		this.titulo2 = titulo2;
	}

	public String getTitulo3() {
		return titulo3;
	}

	public GeneralController getGc() {
		return gc;
	}

	public void setGc(GeneralController gc) {
		this.gc = gc;
	}

	public void setTitulo3(String titulo3) {
		this.titulo3 = titulo3;
	}

	public String getTitulo4() {
		return titulo4;
	}

	public void setTitulo4(String titulo4) {
		this.titulo4 = titulo4;
	}

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
	
	public List<Defectos> getLista1() {
		return lista1;
	}

	public void setLista1(List<Defectos> lista1) {
		this.lista1 = lista1;
	}

	public List<Defectos> getLista2() {
		return lista2;
	}

	public void setLista2(List<Defectos> lista2) {
		this.lista2 = lista2;
	}

	public List<Defectos> getLista3() {
		return lista3;
	}

	public void setLista3(List<Defectos> lista3) {
		this.lista3 = lista3;
	}

	public List<Defectos> getLista4() {
		return lista4;
	}

	public void setLista4(List<Defectos> lista4) {
		this.lista4 = lista4;
	}
	/*--------------------------------------------------------------------------------
 *carga datos de vista principal
 *Si usuario es administrador: los modificados recientemente, los cerrados.
 *Si usuario es lider: mostrar los pendiente por asignar, los modificados recientemente del proyecto, los cerrados
 *Si usuario es analista: mostrar los asignados a mi, los modificados recientemente del proyecto, los cerrados por mi, los monitorizados
 * Si usuario es funcional: mostrar todos los reportados por mi sin respuesta (ABIERTO Y REABIERTO), y los por validar, quizas los cerrados, los monitorizados 
 * Si usuario es gerente: muestra saludo
//--------------------------------------------------------------------------------*/
	public void cargarDatos(ComponentSystemEvent event){
		titulo1="";
		titulo2="";
		titulo3="";
		titulo4="";
		String proyectos = gc.getFiltro_proyecto();
		if(proyectos == null || (proyectos != null && proyectos.equals("0"))){
			proyectos = gc.buscarEnProyectos(m.getProyectosUsuarioList());
		}
		/*if(proyectos.equals("0")){
			//Son todos los proyectos relacionados al usuario, entonces hago un concat de la lista de proyectos
			proyectos = gc.buscarEnProyectos(m.getProyectosUsuarioList());
		}*/
		if (proyectos !=null){
			if(m.getPerfil().equalsIgnoreCase("ADMINISTRADOR")){
				titulo1="Actividad Reciente";
				titulo2="Cerrados";
				setLista1(gs.getDefectosModifReciente(m.getCarnet(), m.getPerfil(), proyectos));
				setLista2(gs.getDefectosCerrados(m.getCarnet(), m.getPerfil(), proyectos));
			}
			if(m.getPerfil().equalsIgnoreCase("LIDER")){
				titulo1="Sin asignar";
				titulo2="Actividad Reciente";
				titulo3="Cerrados";
				setLista1(gs.getDefectosNoAsignados(m.getCarnet(), m.getPerfil(), proyectos));
				setLista2(gs.getDefectosModifReciente(m.getCarnet(), m.getPerfil(), proyectos));
				setLista3(gs.getDefectosCerrados(m.getCarnet(), m.getPerfil(), proyectos));
			}
			if(m.getPerfil().equalsIgnoreCase("ANALISTA")){
				titulo1="Asignados";
				titulo2="Actividad Reciente";
				titulo3="Monitorizados";
				titulo4="Cerrados";
				setLista1(gs.getDefectosAsignados(m.getCarnet(), m.getPerfil(), proyectos));
				setLista2(gs.getDefectosModifReciente(m.getCarnet(), m.getPerfil(), proyectos));
				setLista3(gs.getDefectosMonitorizados(m.getCarnet(), m.getPerfil(), proyectos));
				setLista4(gs.getDefectosCerrados(m.getCarnet(), m.getPerfil(), proyectos));
			}
			if(m.getPerfil().equalsIgnoreCase("FUNCIONAL")){
				titulo1="Reportados por mi";
				titulo2="Por Validar";
				titulo3="Monitorizados";
				titulo4="Cerrados";
				setLista1(gs.getDefectosReportadosPorMi(m.getCarnet(), m.getPerfil(), proyectos));
				setLista2(gs.getDefectosValidar(m.getCarnet(), m.getPerfil(), proyectos));
				setLista3(gs.getDefectosMonitorizados(m.getCarnet(), m.getPerfil(), proyectos));
				setLista4(gs.getDefectosCerrados(m.getCarnet(), m.getPerfil(), proyectos));
			}
		}
	}

}
