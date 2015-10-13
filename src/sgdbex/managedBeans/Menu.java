/*************************************************************************************************************
Banco Exterior 
Sistema: Sistema de Gestion de Defectos Banco Exterior. 
Siglas: SGDBEX
Nombre: DefectosMB.java 
Fecha creación: 20/02/2015 
Versión: 1.0.0
 
Descripción: 
	Este managed bean controla todo lo relacionado al menu del sistema.
 
Autor: 
       Alvaro Marciales
 ---o---

Bitácora de Modificaciones:
Autor             Descripción                                                      Fecha Inicio     Fecha Fin
Alvaro Marciales  Creación del managed bean											20/02/2015		10/04/2015
Claudio Torrez	  Agregados nuevos campos y valores									01/05/2015		17/06/2015

---o---

NOTA: Recuerda no eliminar código ya desarrollado, debes comentar la porción de
código e incluir la nueva, documentando la fecha de la sustitución. 		                       
***************************************************************************************************************/
package sgdbex.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.List;

import beasa.generales.CargarUsuario;





//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import sgdbex.model.pojos.Auditoria;
import sgdbex.model.pojos.Proyectos;
import sgdbex.services.GeneralServices;
 

@ManagedBean
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
@ViewScoped
@Service
public class Menu implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String carnet;
	private String email;
	private String departamento;	
	private String perfil;
	
	private String buscar;
	
	private List<Proyectos> proyectosUsuarioList;
	
	//@Inject
	@Autowired
	private GeneralServices gs;
	
	public Menu(){
	}
	
	public GeneralServices getGs() {
		return gs;
	}

	public void setGs(GeneralServices gs) {
		this.gs = gs;
	}
	
	public String getNombre() {
		
		return nombre;
	}

	public void setNombre(String user) {
		this.nombre = user;
	}
	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public List<Proyectos> getProyectosUsuarioList() {
		return proyectosUsuarioList;
	}

	public void setProyectosUsuarioList(List<Proyectos> proyectosUsuarioList) {
		this.proyectosUsuarioList = proyectosUsuarioList;
	}
	
	public void save() {
        addMessage("Success", "Data saved");
    }
     
    public void update() {
        addMessage("Success", "Data updated");
    }
     
    public void delete() {
        addMessage("Success", "Data deleted");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void estaLogueado(ComponentSystemEvent event){
    	//System.out.println("Entre antes de loguear... ");
    	FacesContext context = FacesContext.getCurrentInstance();  
    	HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
    	HttpSession sesion = ((HttpServletRequest) request).getSession();

    	if(sesion.getAttribute("logged")==null){
    		RequestContext contexto = RequestContext.getCurrentInstance();
    		contexto.execute("PF('dlg1').show();");
    	}else{
    		if(sesion.getAttribute("logged")==null || sesion.getAttribute("logged").equals("no")){
		    	sesion.setAttribute("initlogin", "no");
		    	String logged = sesion.getAttribute("logged").toString();
		    	System.out.println("logged "+logged);
		    	/*Este if es para cuando el usuario se loguea y luego presiona el boton "cerrar sesion" y el
		    	atributo "logged" es seteado a "no", por lo que si se coloca la url "http://localhost:8080/sgdbex/principal.jsf"
		    	cuando la app nos re-direcciona al login.jsf entra en esta validacion.*/
		    	if(logged ==null || logged.equalsIgnoreCase("no")){
		    		//nav.performNavigation("login");
		    		try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/Autenticacion");
					} catch (IOException e) {
						e.getMessage();
					}
		    	}
    		}else{
    	    	CargarUsuario u = (CargarUsuario)sesion.getAttribute("usuario");
    	    	if(sesion.getAttribute("usuario")!=null) //Escoger entre ambas formas de adquirir los datos del usuario especificadas en el constructor de la clase, i.e, atributo "usuario"(nombreusuario del XML) o "user" (nombreusuario que ingresa por interfaz login.xhtml)
    	        {
    					nombre=u.getNombre();
    					carnet=u.getCarnet();
    					email=u.getEmail();
    					departamento=u.getDepartamento();
    					perfil=u.getPerfil();
    	        }
    	    	if(perfil != null && carnet != null){
    				System.out.println("sacando proyectos de usuario por perfil: "+perfil+" carnet: "+carnet);
//    				proyectosUsuarioList = gs.getProyectosPorUsuario(perfil, carnet);
    				actualizarListaProyectosUsuario();
    			}
    		}
    	}
	}
    public void actualizarListaProyectosUsuario(){
    	proyectosUsuarioList = gs.getProyectosPorUsuario(perfil, carnet);
    }
    /*---------------------------------------------------------------------------
     * 1/4/2015 Claudio Torrez
     * 
     * Funcion Mostrar
     * 
     * Recibe el nombre de la opcion del menu principal
     * Verifica si el usuario registrado tiene acceso a esa accion a partir del XML
     * 
     * Devuelve: true si el usuario puede realizar la accion
     * 			false, en caso contrario.
     * 
     *---------------------------------------------------------------------------*/

	 public boolean mostrar(String modulo, String accion){
    	FacesContext context = FacesContext.getCurrentInstance();  
    	HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
    	HttpSession sesion = ((HttpServletRequest) request).getSession();
    	if(sesion.getAttribute("logged")==null){
    		/*try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/login.jsf");

			} catch (IOException e) {
				e.getMessage();
			}*/
    		return true;
    	}else{
		    CargarUsuario u = (CargarUsuario)sesion.getAttribute("usuario");
		    if (u.tieneAccion(modulo, accion)){
		    	return true;
		    }
    	}
		return false;
    }
    public void miCuenta() throws IOException{  
		FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/InformacionAutenticacion");
    }
    public void logout() throws IOException{
    	FacesContext context = FacesContext.getCurrentInstance();  
    	HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
    	HttpSession sesion = ((HttpServletRequest) request).getSession();
    	sesion.setAttribute("logged", "no");
		//sesion.setAttribute("usuario", new CargarUsuario()); //Me parece que en lugar de asignar valores nulos a estos 2 atributos de la sesion (usuario y user) deberian de removerse dichos valores
		sesion.setAttribute("user", "ninguno");
		String ip = InetAddress.getLocalHost().getHostAddress();
        
        Auditoria auditoria = new Auditoria();
        auditoria.setCarnet(carnet);
        auditoria.setDireccionIp(ip);
        auditoria.setModulo("LOGOUT");
        auditoria.setAccion("LOGOUT");
        auditoria.setEntidad("");
        auditoria.setDescripcion("CERRAR SESIÓN");
        auditoria.setResultado("EXITOSO");
        gs.insertarAuditoria(auditoria);
        sesion.invalidate();
        /*Otra forma de invalidar la session:
         *FacesContext.getCurrentInstance().getExternalContext().invalidateSession();*/
        
		//FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/login.jsf");
        FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/Autenticacion");
    }
    
    public void SesionExpirada(){
    	try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/Autenticacion");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
    }

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}
	public void doSearch(String valor) throws IOException{
		System.out.println("value"+valor);
    	FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/views/reportarDefecto/buscarDefecto.jsf?q="+valor);
	}
	
}
