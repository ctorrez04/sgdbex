/*************************************************************************************************************
Banco Exterior 
Sistema: Sistema de Gestion de Defectos Banco Exterior. 
Siglas: SGDBEX
Nombre: DefectosMB.java 
Fecha creaci�n: 08/06/2015 
Versi�n: 1.0.0
 
Descripci�n: 
	Este managed bean controla todo lo relacionado al modulo mi cuenta.
 
Autor: 
       Claudio Torrez
 ---o---

Bit�cora de Modificaciones:
Autor             Descripci�n                                                      Fecha Inicio     Fecha Fin
Alvaro Marciales  Creaci�n del managed bean											08/06/2015		17/06/2015

---o---

NOTA: Recuerda no eliminar c�digo ya desarrollado, debes comentar la porci�n de
c�digo e incluir la nueva, documentando la fecha de la sustituci�n. 		                       
***************************************************************************************************************/
package sgdbex.managedBeans;

import java.io.Serializable;

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
public class MiCuenta implements Serializable{

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
