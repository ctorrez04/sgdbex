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
import sgdbex.model.pojos.RolUsuarios;
import sgdbex.model.pojos.Usuarios;
import sgdbex.services.GeneralServices;

@ManagedBean 
@Service
public class UsuariosMB {
	
	@Autowired
	private GeneralServices gs;
    
	private List<Proyectos> proyectosList;
	
	private List<Usuarios> usuariosList;
	
	private List<Usuarios> usuariosBeasaList;
	
	public String proyectos="";
	
	public String filtroUsuarios;
	
	public String rolSeleccionado;
	private String rol;
	
	private Usuarios usuario = new Usuarios();
	
	@Autowired
	public Menu m;	
	
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

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public List<Proyectos> getProyectosList() {
		return proyectosList;
	}

	public void setProyectosList(List<Proyectos> proyectosList) {
		this.proyectosList = proyectosList;
	}
	
	public List<Usuarios> getUsuariosList() {
		return usuariosList;
	}

	public void setUsuariosList(List<Usuarios> usuariosList) {
		this.usuariosList = usuariosList;
	}
	
	public void listar(ComponentSystemEvent event){
		proyectos="";
		proyectosList = m.getProyectosUsuarioList();
		for (int i=0;i<proyectosList.size();i++){
			proyectos += proyectosList.get(i).getProyecto_id()+",";
		}
		System.out.println("buscando en proyectos "+proyectos);
		System.out.println("buscando en proyectos "+proyectos.substring(0, 1));
		setUsuariosList(gs.getUsuariosPorProyecto(proyectos.substring(0,1)));
		//System.out.println("cantidad usuarios toString "+getUsuariosList().get(0).toString());
		System.out.println("cantidad usuarios "+getUsuariosList().size());
		setRol(m.getPerfil());
		if(getRol().equalsIgnoreCase("LIDER")){
			usuariosBeasaList = gs.getUsuariosAnalistas();
		}
		System.out.println("rol "+getRol());
	}
	public void borrarItem(Object objeto){
    	System.out.println("Vere a que clase pertenezco");
    	String mensaje;
    	if(objeto instanceof Usuarios){
    		Usuarios user = (Usuarios)objeto;
    		System.out.println("Soy de Proyectos Eliminado" + user.toString());//delete(itemId);
        	mensaje = gs.deleteUsuarios(user);
            //addMessage("El item seleccionado ha sido eliminado!");
            if(mensaje.equalsIgnoreCase("fallo")){
            	System.out.println("Fallo al eliminar");
            }else{
            	setUsuariosList(gs.getUsuariosPorProyecto(proyectos));
            	System.out.println("Cantidad "+ usuariosList.size());
            }
    	}
	}
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public void editar(RowEditEvent event) {
		String mensaje="";
		System.out.println("Entre a editar Vere a que clase pertenezco");
        if(event.getObject() instanceof Usuarios){
        	Usuarios user = ((Usuarios) event.getObject());
        	System.out.println("Soy de Usuarios entro a editar con valores"+user.toString());
        	mensaje = gs.updateUsuarios(user);
        	addMessage("El item seleccionado ha sido editado!");
        }
        if(mensaje.equalsIgnoreCase("fallo")){
        	cancelar(event);
        	addMessage("Hubo un error al actualizar el item seleccionado!");
        }
    }

	public void cancelar(RowEditEvent event) {
		System.out.println("Vere a que clase pertenezco");
        if(event.getObject() instanceof Usuarios){
        	addMessage("Cancelar cambios");	
        }
        else{
        	System.out.println("No soy un objeto valido");
        }
    }
	
	public void agregar(Object objeto) {
    	String mensaje;
    	System.out.println("Entre a agregar ");
    	if(objeto == null)System.out.println("null");
    	if(objeto instanceof Usuarios){
    		Usuarios user = (Usuarios)objeto;
    		List<RolUsuarios> roles = gs.obtenerRolesUsuarios();
    		for(int i=0;i<roles.size();i++){
    			if(roles.get(i).getRol_nombre().equalsIgnoreCase(rolSeleccionado)){
    				user.setUsuario_rol_fk(roles.get(i).getRol_id());
    				rolSeleccionado="";
    				break;
    			}
    		}
    		for(int i=0;i<usuariosBeasaList.size();i++){
        		if(usuariosBeasaList.get(i).getCarnet().equals(user.getCarnet())){
        			user.setNombre_completo(usuariosBeasaList.get(i).getNombre_completo());
        			user.setCorreo_electronico(usuariosBeasaList.get(i).getCorreo_electronico());
        			break;
        		}
        	}
        	mensaje = gs.createUsuarios(user);
            if(mensaje.equalsIgnoreCase("fallo")){
            	addMessage("El item seleccionado no pudo ser creado. Por favor intentelo nuevamente!");
            }else{
            	addMessage("El item seleccionado ha sido creado!");
            	setUsuariosList(gs.getUsuariosPorProyecto(proyectos));
            }
    	}
	}

	public void cargarUsuarios(){
	//ValueChangeEvent event) throws AbortProcessingException{
		System.out.println("cargar "+ rolSeleccionado);
		if(rolSeleccionado.equalsIgnoreCase("ANALISTA")){
			usuario.setUsuario_rol_fk(3);
			usuariosBeasaList = gs.getUsuariosAnalistas();
		}
		if(rolSeleccionado.equalsIgnoreCase("LIDER_PROYECTO")){
			usuario.setUsuario_rol_fk(2);
			usuariosBeasaList = gs.getUsuariosLider();
		}

	}

	public List<Usuarios> getUsuariosBeasaList() {
		return usuariosBeasaList;
	}

	public void setUsuariosBeasaList(List<Usuarios> usuariosBeasaList) {
		this.usuariosBeasaList = usuariosBeasaList;
	}
	public String getFiltroUsuarios() {
		return filtroUsuarios;
	}

	public void setFiltroUsuarios(String filtroUsuarios) {
		this.filtroUsuarios = filtroUsuarios;
	}
	
	public String getRolSeleccionado() {
		return rolSeleccionado;
	}

	public void setRolSeleccionado(String rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}


}
