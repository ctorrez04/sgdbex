/*************************************************************************************************************
Banco Exterior 
Sistema: Sistema de Gestion de Defectos Banco Exterior. 
Siglas: SGDBEX
Nombre: DefectosMB.java 
Fecha creación: 01/07/2015 
Versión: 1.0.0
 
Descripción: 
	Este managed bean controla todo lo relacionado a la pantalla principal.
 
Autor: 
       Claudio Torrez
 ---o---

Bitácora de Modificaciones:
Autor             Descripción                                                      Fecha Inicio     Fecha Fin
Claudio Torrez    Creación del managed bean											01/07/2015		13/07/2015

---o---

NOTA: Recuerda no eliminar código ya desarrollado, debes comentar la porción de
código e incluir la nueva, documentando la fecha de la sustitución. 		                       
***************************************************************************************************************/
package sgdbex.managedBeans;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
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
	
	public String titulo1="";
	public String titulo2="";
	public String titulo3="";
	public String titulo4="";
	
	private boolean title1=true;
	private boolean title2=true;
	private boolean title3=true;
	private boolean title4=true;
	
	public boolean isTitle1() {
		return title1;
	}

	public void setTitle1(boolean title1) {
		this.title1 = title1;
	}

	public boolean isTitle2() {
		return title2;
	}

	public void setTitle2(boolean title2) {
		this.title2 = title2;
	}

	public boolean isTitle3() {
		return title3;
	}

	public void setTitle3(boolean title3) {
		this.title3 = title3;
	}

	public boolean isTitle4() {
		return title4;
	}

	public void setTitle4(boolean title4) {
		this.title4 = title4;
	}

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
		if(proyectos == null  || (proyectos != null && proyectos.equals("0"))){
			proyectos = gc.buscarEnProyectos(m.getProyectosUsuarioList());
		}
		if (proyectos !=null && !proyectos.isEmpty()){
			if(m.getPerfil().equalsIgnoreCase("ADMINISTRADOR")){
				title1=false;
				title2=false;
				title3=true;
				title4=true;
				titulo1="Actividad Reciente";
				titulo2="Cerrados";
				setLista1(gs.getDefectosModifReciente(m.getCarnet(), m.getPerfil(), proyectos));
				setLista2(gs.getDefectosCerrados(m.getCarnet(), m.getPerfil(), proyectos));
			}
			if(m.getPerfil().equalsIgnoreCase("LIDER")){
				title1=false;
				title2=false;
				title3=false;
				title4=true;
				titulo1="Sin asignar";
				titulo2="Actividad Reciente";
				titulo3="Cerrados";
				setLista1(gs.getDefectosNoAsignados(m.getCarnet(), m.getPerfil(), proyectos));
				setLista2(gs.getDefectosModifReciente(m.getCarnet(), m.getPerfil(), proyectos));
				setLista3(gs.getDefectosCerrados(m.getCarnet(), m.getPerfil(), proyectos));
			}
			if(m.getPerfil().equalsIgnoreCase("ANALISTA")){
				title1=false;
				title2=false;
				title3=false;
				title4=false;
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
				title1=false;
				title2=false;
				title3=false;
				title4=false;
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
/*		else{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/Autenticacion");
			} catch (IOException e) {
				e.getMessage();
			}
		}*/
	}
	public void cargarDatosAjax(){
		cargarDatos(null);
	}
	public void ReinicializarBooleanos(){
		title1=true;
		title2=true;
		title3=true;
		title4=true;
	}

}
