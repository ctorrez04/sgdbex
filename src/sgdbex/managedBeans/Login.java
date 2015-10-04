package sgdbex.managedBeans;

import beasa.generales.CargarUsuario;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Hashtable;


//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sgdbex.model.pojos.Auditoria;
import sgdbex.services.GeneralServices;
import beasa.servicios.WS_Validar;
import beasa.servicios.WS_ValidarProxy;

//@Named
//@SessionScoped
@SessionScoped
@ViewScoped
@ManagedBean
@Service
public class Login implements Serializable{  //Colocar este metodo que implemente de tipo Serializable

	private static final long serialVersionUID = 1L;

	@Autowired
	private GeneralServices gs;

	private String ip;
	
	private String usuario;
    
    private String clave;
    
    private String sistema;
 
	public GeneralServices getGs() {
		return gs;
	}

	public void setGs(GeneralServices gs) {
		this.gs = gs;
	}
    
    public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	
	public void limpiar(){
		setUsuario("");
		setClave("");
	}
	
	public void loguear() throws IOException{
		limpiar();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/indice");
	}

	public void logueadoInicial(ComponentSystemEvent event){
    	System.out.println("Entre antes de logueoInicial... ");
    	FacesContext context = FacesContext.getCurrentInstance();  
    	HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
    	HttpSession sesion = ((HttpServletRequest) request).getSession();
    	sesion.setAttribute("initlogin", "si");
    	sesion.setAttribute("logged", "no");
    	sesion.setAttribute("user", "ninguno");
    	String logged = sesion.getAttribute("initlogin").toString();
    	System.out.println("initlogin "+logged);
    	System.out.println("ID de session: "+sesion.getId());
    	limpiar();
	}
	
    @SuppressWarnings({ "unused", "rawtypes" })
	public void login() throws IOException {
        ip = InetAddress.getLocalHost().getHostAddress();
        sistema = "37";  //definido por nosotros en el sistema
        boolean loggedIn = false;
        
        Auditoria auditoria = new Auditoria();
        auditoria.setCarnet(usuario);
        auditoria.setDireccionIp(ip);
        auditoria.setModulo("LOGIN");
        auditoria.setAccion("LOGIN");
        auditoria.setEntidad("");
        auditoria.setDescripcion("INICIO DE SESIÓN");
        System.out.println("ip "+ip+" usuario "+ usuario+" contrasena "+clave+" aplicacion "+sistema);		
		WS_ValidarProxy proxy = new WS_ValidarProxy();
		WS_Validar servicio = proxy.getWS_Validar();
		String respuesta = servicio.verificar(ip, usuario, clave, sistema);
		System.out.println("Resultado XML: "+respuesta);
        /*Metodo que lee el XML que retorna el WS*/
		CargarUsuario XMLRead = new CargarUsuario();
		XMLRead.cargar(respuesta);
		Hashtable isError = XMLRead.isErrorXML();
		
//----------------------------------------------------------		
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
		HttpSession sesion = request.getSession(true);
		RequestContext requestContext = RequestContext.getCurrentInstance();
    	requestContext.addCallbackParam("isValid", "Activo");
//----------------------------------------------------------
		if(isError.isEmpty()){
			System.out.println("No hubo error en XML ^^: ");
			loggedIn = true;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido "+ usuario,"Inicio Sesion Exitoso"));
			XMLRead.inicializar();
			sesion.setAttribute("usuario", XMLRead);
			sesion.setAttribute("logged", "si");
			sesion.setAttribute("user", usuario);
			auditoria.setResultado("EXITOSO");
			auditoria.toString();
			//gs.insertarAuditoria(auditoria);
			limpiar();
			//FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/principal.jsf");
			FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/Principal");
			//return "principal?faces-redirect=true";
			//return "principal";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Error","Su usuario y/o contrasena no es valido"));		    
			loggedIn = false;
			sesion.setAttribute("logged", "ninguno");
			sesion.setAttribute("error", isError);
			
			auditoria.setResultado("ERROR");
			auditoria.setDescripcion("INICIO DE SESIÓN - No tiene permisos para ingresar al sistema.");
			auditoria.toString();
			gs.insertarAuditoria(auditoria);
			limpiar();
			//FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/login.jsf");
			FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/Autenticacion");
			//return "login";
		}
    }
}
