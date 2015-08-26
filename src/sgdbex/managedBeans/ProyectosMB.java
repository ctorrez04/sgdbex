package sgdbex.managedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sgdbex.model.pojos.Proyectos;
import sgdbex.services.GeneralServices;

@ManagedBean 
@Service
public class ProyectosMB {

	private List<Proyectos> proyectosList;
	private List<Proyectos> filtroProyectos;
	
	private Proyectos proyecto = new Proyectos();
	
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

	public List<Proyectos> getProyectosList() {
		return proyectosList;
	}

	public void setProyectosList(List<Proyectos> proyectosList) {
		this.proyectosList = proyectosList;
	}

	public List<Proyectos> getFiltroProyectos() {
		return filtroProyectos;
	}

	public void setFiltroProyectos(List<Proyectos> filtroProyectos) {
		this.filtroProyectos = filtroProyectos;
	}

	public Proyectos getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyectos proyecto) {
		this.proyecto = proyecto;
	}
	
	public void init(ComponentSystemEvent event){
		setProyectosList(gs.getProyectos());
	}
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void editar(RowEditEvent event) {
		String mensaje="";
		System.out.println("Entre a editar Vere a que clase pertenezco");
        if(event.getObject() instanceof Proyectos){
            Proyectos proy = ((Proyectos) event.getObject());
            System.out.println("Soy de Proyectos entro a editar con valores"+proy.toString());
            proy.setProy_usuario_modificacion(m.getCarnet());
            mensaje = gs.updateProyectos(proy);
            addMessage("El item seleccionado ha sido editado!");
        }
        if(mensaje.equalsIgnoreCase("fallo")){
        	cancelar(event);
        	addMessage("Hubo un error al actualizar el item seleccionado!");
        }
	}
	
	public void cancelar(RowEditEvent event) {
        if(event.getObject() instanceof Proyectos){
        	addMessage("Cambios cancelados");
        }
    }
	
    public void agregar(Object objeto) {
    	String mensaje;
    	System.out.println("Entre a agregar ");
    	if(objeto instanceof Proyectos){
	    	Proyectos p = (Proyectos)objeto;
	    	p.setProy_usuario_modificacion(m.getCarnet());
    		System.out.println("Soy de Proyectos Agregar" + p.toString());
        	mensaje = gs.createProyectos(p);
            if(mensaje.equalsIgnoreCase("fallo")){
            	addMessage("Fallo al crear!");
            }else{
            	setProyectosList(gs.getProyectos());
            	limpiar();
            	m.actualizarListaProyectosUsuario();
            	addMessage("El item seleccionado ha sido creado!");
            }
    	}
    }
    
    public void borrarItem(Object objeto){
    	System.out.println("Vere a que clase pertenezco");
    	String mensaje;
    	if(objeto instanceof Proyectos){
    		Proyectos p = (Proyectos)objeto;
    		p.setProy_usuario_modificacion(m.getNombre());
        	mensaje = gs.deleteProyectos(p);
            if(mensaje.equalsIgnoreCase("fallo")){
            	addMessage("Fallo al eliminar!");
            }else{
            	setProyectosList(gs.getProyectos());
            	m.actualizarListaProyectosUsuario();
            	addMessage("El item seleccionado ha sido eliminado!");
            }
    	}
    }
    public void limpiar(){
    	System.out.println("voy a limpiar");
    	setProyecto(new Proyectos());
    }
}
