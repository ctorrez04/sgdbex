package sgdbex.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beasa.generales.CargarUsuario;
/**
 * Servlet Filter implementation class AccessFilter
 */
@WebFilter("/*")
public class AccessFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AccessFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
	   // Obtengo el bean que representa el usuario desde el scope sesión
		Object logged = req.getSession().getAttribute("logged");
	    String loginBean = (logged == null) ? "" : logged.toString();
	  
	  //Proceso la URL que está requiriendo el cliente
	    String urlStr = req.getRequestURL().toString().toLowerCase();
	  //System.out.println(urlStr + " - desprotegido=[" + noProteger + "]");	  
	  //Si no requiere protección continúo normalmente.
	    if (noProteger(urlStr)) {
	      chain.doFilter(request, response);
	      return;
	    }
	  
  	  //El usuario no está logueado
	    if (loginBean == null || loginBean.isEmpty()) {
	      res.sendRedirect(req.getContextPath() + "/Autenticacion");
	      return;
	    }
	    
	    if(urlStr.indexOf("administracion") != -1 || urlStr.indexOf("modulo") != -1){
	    	Object infoUsuario =req.getSession().getAttribute("usuario");
			if(infoUsuario == null){
				res.sendRedirect(req.getContextPath() + "/Autenticacion");
				return;
			}else{
				CargarUsuario u = (CargarUsuario)infoUsuario;
				String perfil=u.getPerfil();
				if(!perfil.equalsIgnoreCase("ADMINISTRADOR") && !(visiblePorLider(urlStr) && perfil.equalsIgnoreCase("LIDER"))){
					res.sendRedirect(req.getContextPath() + "/404");
					return;
				}
			}	
	    }
  //	El recurso requiere protección, pero el usuario ya está logueado.
	    chain.doFilter(request, response);
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	private boolean noProteger(String urlStr) {

		  if (urlStr.endsWith("login.jsf") || urlStr.endsWith("autenticacion"))
		    return true;
		  if (urlStr.indexOf("/javax.faces.resource/") != -1)
		    return true;
		  if (urlStr.indexOf("/images/") != -1)
			    return true;
		  return false;
	}
	private boolean visiblePorLider(String urlStr) {

		  if (urlStr.endsWith("usuarios.jsf") || urlStr.endsWith("categorias.jsf") || urlStr.endsWith("motivos.jsf"))
		    return true;
		  if (urlStr.endsWith("modulocategorias") || urlStr.endsWith("modulomotivosrechazo") || urlStr.endsWith("modulousuarios"))
			  return true;
		  if (urlStr.indexOf("/javax.faces.resource/") != -1)
		    return true;
		  if (urlStr.indexOf("/images/") != -1)
			    return true;
		  return false;
		}

}
