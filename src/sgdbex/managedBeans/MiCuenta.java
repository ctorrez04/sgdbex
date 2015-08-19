package sgdbex.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beasa.generales.CargarUsuario;

@ManagedBean
@SessionScoped
@ViewScoped
public class MiCuenta {

	private String nombre;
	private String carnet;
	private String email;
	private String departamento;
	private String perfil;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public void CargarUsuarioAutenticado(ComponentSystemEvent event){
		FacesContext context = FacesContext.getCurrentInstance();  
    	HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
    	HttpSession sesion = ((HttpServletRequest) request).getSession(true);
    	CargarUsuario u = (CargarUsuario)sesion.getAttribute("usuario");
    	if(sesion.getAttribute("usuario")!=null)
        {
            nombre=u.getNombre();
            carnet=u.getCarnet();
            email=u.getEmail();
            departamento=u.getDepartamento();
            perfil=u.getPerfil();
        }
	}
	
}
