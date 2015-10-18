/*************************************************************************************************************
Banco Exterior 
Sistema: Sistema de Gestion de Defectos Banco Exterior. 
Siglas: SGDBEX
Nombre: DefectosMB.java 
Fecha creaci�n: 08/06/2015 
Versi�n: 1.0.0
 
Descripci�n: 
	Este managed bean controla todo lo relacionado a los motivos de resolucion en la pantalla admnistracion de motivos.
 
Autor: 
       Claudio Torrez
 ---o---

Bit�cora de Modificaciones:
Autor             Descripci�n                                                      Fecha Inicio     Fecha Fin
Claudio Torrez    Creaci�n del managed bean											08/06/2015		17/06/2015

---o---

NOTA: Recuerda no eliminar c�digo ya desarrollado, debes comentar la porci�n de
c�digo e incluir la nueva, documentando la fecha de la sustituci�n. 		                       
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

import sgdbex.model.pojos.MotivoRechazo;
import sgdbex.services.GeneralServices;

@ManagedBean 
@Service
public class MotivosMB {
	private List<MotivoRechazo> motivosList;
	private List<MotivoRechazo> filtroMotivos;
	
	private MotivoRechazo motivo = new MotivoRechazo();
	
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

	public List<MotivoRechazo> getMotivosList() {
		return motivosList;
	}

	public void setMotivosList(List<MotivoRechazo> motivosList) {
		this.motivosList = motivosList;
	}

	public List<MotivoRechazo> getFiltroMotivos() {
		return filtroMotivos;
	}

	public void setFiltroMotivos(List<MotivoRechazo> filtroMotivos) {
		this.filtroMotivos = filtroMotivos;
	}

	public MotivoRechazo getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoRechazo motivo) {
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
    	if(objeto instanceof MotivoRechazo){
    		MotivoRechazo mot = (MotivoRechazo) objeto;
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
        if(event.getObject() instanceof MotivoRechazo){
        	MotivoRechazo mot = ((MotivoRechazo) event.getObject());
        	mensaje = gs.updateMotivos(mot);
        	addMessage("El item seleccionado ha sido editado!");
        }
        if(mensaje.equalsIgnoreCase("fallo")){
        	cancelar(event);
        	addMessage("Hubo un error al actualizar el item seleccionado!");
        }
    }

	public void cancelar(RowEditEvent event) {
        if(event.getObject() instanceof MotivoRechazo){
        	addMessage("Cambios cancelados");	
        }
    }
	
	public void agregar(Object objeto) {
    	String mensaje;
    	System.out.println("Entre a agregar ");
    	if(objeto instanceof MotivoRechazo){
    		MotivoRechazo mot = (MotivoRechazo)objeto;
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
		setMotivo(new MotivoRechazo());
	}
}
