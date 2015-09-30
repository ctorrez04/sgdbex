/*************************************************************************************************************
Banco Exterior 
Sistema: Sistema de Gestion de Defectos Banco Exterior. 
Siglas: SGDBEX
Nombre: DefectosMB.java 
Fecha creación: 08/06/2015 
Versión: 1.0.0
 
Descripción: 
	Este managed bean controla todo lo relacionado a los motivos de resolucion en la pantalla admnistracion de motivos.
 
Autor: 
       Claudio Torrez
 ---o---

Bitácora de Modificaciones:
Autor             Descripción                                                      Fecha Inicio     Fecha Fin
Claudio Torrez    Creación del managed bean											08/06/2015		17/06/2015

---o---

NOTA: Recuerda no eliminar código ya desarrollado, debes comentar la porción de
código e incluir la nueva, documentando la fecha de la sustitución. 		                       
***************************************************************************************************************/
package sgdbex.managedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sgdbex.model.pojos.MotivoResolucion;
import sgdbex.services.GeneralServices;

@ManagedBean 
@Service
public class MotivosMB {
	private List<MotivoResolucion> motivosList;
	private List<MotivoResolucion> filtroMotivos;
	
	private MotivoResolucion motivo = new MotivoResolucion();
	
	@Autowired
	private GeneralServices gs;
	
	@Autowired
	private Menu m;	
	
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

	public List<MotivoResolucion> getMotivosList() {
		return motivosList;
	}

	public void setMotivosList(List<MotivoResolucion> motivosList) {
		this.motivosList = motivosList;
	}

	public List<MotivoResolucion> getFiltroMotivos() {
		return filtroMotivos;
	}

	public void setFiltroMotivos(List<MotivoResolucion> filtroMotivos) {
		this.filtroMotivos = filtroMotivos;
	}

	public MotivoResolucion getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoResolucion motivo) {
		this.motivo = motivo;
	}

	public void init(ComponentSystemEvent event){
		setMotivosList(gs.getMotivos());
	}
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void borrarItem(Object objeto){
    	System.out.println("Vere a que clase pertenezco");
    	String mensaje;
    	if(objeto instanceof MotivoResolucion){
    		MotivoResolucion mot = (MotivoResolucion) objeto;
        	mensaje = gs.deleteMotivos(mot);
            if(mensaje.equalsIgnoreCase("fallo")){
            	addMessage("Algo salio mal, fallo al eliminar!");
            }else{
            	setMotivosList(gs.getMotivos());
            	addMessage("El item fue eliminado!");
            }
    	}
	}
	
	public void editar(RowEditEvent event) {
		String mensaje="";
		System.out.println("Entre a editar Vere a que clase pertenezco");
        if(event.getObject() instanceof MotivoResolucion){
        	MotivoResolucion mot = ((MotivoResolucion) event.getObject());
        	mensaje = gs.updateMotivos(mot);
        	addMessage("El item seleccionado ha sido editado!");
        }
        if(mensaje.equalsIgnoreCase("fallo")){
        	cancelar(event);
        	addMessage("Hubo un error al actualizar el item seleccionado!");
        }
    }

	public void cancelar(RowEditEvent event) {
        if(event.getObject() instanceof MotivoResolucion){
        	addMessage("Cambios cancelados");	
        }
    }
	
	public void agregar(Object objeto) {
    	String mensaje;
    	System.out.println("Entre a agregar ");
    	if(objeto instanceof MotivoResolucion){
    		MotivoResolucion mot = (MotivoResolucion)objeto;
    		mot.setMotivo_usuario_modificacion(m.getCarnet());
        	mensaje = gs.createMotivos(mot);
            if(mensaje.equalsIgnoreCase("fallo")){
            	addMessage("El item seleccionado no pudo ser creado. Por favor intentelo nuevamente!");
            }else{
            	addMessage("El item seleccionado ha sido creado!");
            	limpiar();
            	setMotivosList(gs.getMotivos());
            }
    	}
	}
	public void limpiar(){
		setMotivo(new MotivoResolucion());
	}
}
