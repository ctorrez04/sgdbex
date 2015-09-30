/*************************************************************************************************************
Banco Exterior 
Sistema: Sistema de Gestion de Defectos Banco Exterior. 
Siglas: SGDBEX
Nombre: DefectosMB.java 
Fecha creación: 21/04/2015 
Versión: 1.0.0
 
Descripción: 
	Este managed bean controla todo lo relacionado a las categorias en la pantalla administracion de categorias.
 
Autor: 
       Claudio Torrez
 ---o---

Bitácora de Modificaciones:
Autor             Descripción                                                      Fecha Inicio     Fecha Fin
Claudio Torrez    Creación del managed bean											21/04/2015		29/04/2015

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

import sgdbex.services.GeneralServices;
import sgdbex.model.pojos.Categorias;

@ManagedBean 
@Service
public class CategoriasMB {
	
	private List<Categorias> categoriasList;
	private List<Categorias> filtroCategorias;
	
	private Categorias categoria = new Categorias();
	
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

	public List<Categorias> getCategoriasList() {
		return categoriasList;
	}

	public void setCategoriasList(List<Categorias> categoriasList) {
		this.categoriasList = categoriasList;
	}
	
	public List<Categorias> getFiltroCategorias() {
		return filtroCategorias;
	}

	public void setFiltroCategorias(List<Categorias> filtroCategorias) {
		this.filtroCategorias = filtroCategorias;
	}

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}
	
	public void init(ComponentSystemEvent event){
		setCategoriasList(gs.getCategorias());
	}
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void borrarItem(Object objeto){
    	System.out.println("Vere a que clase pertenezco");
    	String mensaje;
    	if(objeto instanceof Categorias){
    		Categorias cat = (Categorias) objeto;
        	mensaje = gs.deleteCategorias(cat);
            if(mensaje.equalsIgnoreCase("fallo")){
            	addMessage("Algo salio mal, fallo al eliminar!");
            }else{
            	setCategoriasList(gs.getCategorias());
            	addMessage("El item fue eliminado!");
            }
    	}
	}

	public void editar(RowEditEvent event) {
		String mensaje="";
		System.out.println("Entre a editar Vere a que clase pertenezco");
        if(event.getObject() instanceof Categorias){
        	Categorias cat = ((Categorias) event.getObject());
        	mensaje = gs.updateCategorias(cat);
        	addMessage("El item seleccionado ha sido editado!");
        }
        if(mensaje.equalsIgnoreCase("fallo")){
        	cancelar(event);
        	addMessage("Hubo un error al actualizar el item seleccionado!");
        }
    }

	public void cancelar(RowEditEvent event) {
        if(event.getObject() instanceof Categorias){
        	addMessage("Cambios cancelados");	
        }
    }
	
	public void agregar(Object objeto) {
    	String mensaje;
    	System.out.println("Entre a agregar ");
    	if(objeto instanceof Categorias){
    		Categorias cat = (Categorias)objeto;
    		cat.setCateg_usuario_modificacion(m.getCarnet());
        	mensaje = gs.createCategorias(cat);
            if(mensaje.equalsIgnoreCase("fallo")){
            	addMessage("El item seleccionado no pudo ser creado. Por favor intentelo nuevamente!");
            }else{
            	addMessage("El item seleccionado ha sido creado!");
            	limpiar();
            	setCategoriasList(gs.getCategorias());
            }
    	}
	}
	
	public void limpiar(){
		setCategoria(new Categorias());
	}

}
