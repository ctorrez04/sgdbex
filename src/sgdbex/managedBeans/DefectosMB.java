/*************************************************************************************************************
Banco Exterior 
Sistema: Sistema de Gestion de Defectos Banco Exterior. 
Siglas: SGDBEX
Nombre: DefectosMB.java 
Fecha creación: 15/07/2015 
Versión: 1.0.0
 
Descripción: 
	Este managed bean controla todo lo relacionado al defecto en la pantalla detalle del defecto.
 
Autor: 
       Claudio Torrez
 ---o---

Bitácora de Modificaciones:
Autor             Descripción                                                      Fecha Inicio     Fecha Fin
Claudio Torrez    Creación del managed bean											15/07/2015		17/07/2015

---o---

NOTA: Recuerda no eliminar código ya desarrollado, debes comentar la porción de
código e incluir la nueva, documentando la fecha de la sustitución. 		                       
*****************************************************************************************************************/

package sgdbex.managedBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import sgdbex.controllers.GeneralController;
import sgdbex.model.pojos.ArchivosAdjuntos;
import sgdbex.model.pojos.Defectos;
import sgdbex.model.pojos.Estados;
import sgdbex.model.pojos.HistoricoEstados;
import sgdbex.model.pojos.Usuarios;
import sgdbex.services.GeneralServices;
		 
@ManagedBean 
@Service
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
@ViewScoped
public class DefectosMB implements Serializable{		     
    
	private static final long serialVersionUID = 1L;
	@Autowired
	private GeneralServices gs;
    @Autowired
	private GeneralController gc;
    @Autowired
	public Menu m;
    
    private boolean editarAbierto =false;
    private boolean editarReabierto =false;
    private boolean editarAsignado =false;
	private boolean asignar =false;
	private boolean validar =false;
	private boolean resolver =false;
	private boolean rechazar =false;
	private boolean aceptar =false;
	private boolean opciones =false;
	private boolean botones =true;
	
	private String id;
	
	private List<HistoricoEstados> historico = new ArrayList<HistoricoEstados>();
	private List<ArchivosAdjuntos> adjuntosList;
	private	List<UploadedFile> adjuntosSolucion =new ArrayList<UploadedFile>();
	private	List<Usuarios> usuariosAnalistas;
	private	List<Estados> estadosList;
	
	private StreamedContent archivo;
	
	private List<String> imagenes;
	
	public Defectos defecto = new Defectos();	
	
    public Menu getM() {
		return m;
	}
    public List<String> getImagenes() {
        return imagenes;
    }
	public void setM(Menu m) {
		this.m = m;
	}
	
	public StreamedContent getArchivo() {
		return archivo;
	}
	
	public List<HistoricoEstados> getHistorico() {
		return historico;
	}
	public void setHistorico(List<HistoricoEstados> historico) {
		this.historico = historico;
	}
	public boolean isEditarAbierto() {
		return editarAbierto;
	}
	public void setEditarAbierto(boolean editarAbierto) {
		this.editarAbierto = editarAbierto;
	}
	public boolean isEditarReabierto() {
		return editarReabierto;
	}
	public void setEditarReabierto(boolean editarReabierto) {
		this.editarReabierto = editarReabierto;
	}
	public boolean isEditarAsignado() {
		return editarAsignado;
	}
	public void setEditarAsignado(boolean editarAsignado) {
		this.editarAsignado = editarAsignado;
	}
	public boolean isAsignar() {
		return asignar;
	}

	public void setAsignar(boolean asignar) {
		this.asignar = asignar;
	}

	public boolean isResolver() {
		return resolver;
	}

	public void setResolver(boolean resolver) {
		this.resolver = resolver;
	}

	public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
    
    public Defectos getDefecto() {
		return defecto;
	}
    
    public void setDefecto(Defectos defecto) {
		this.defecto = defecto;
	}
    
	public boolean isValidar() {
		return validar;
	}

	public void setValidar(boolean validar) {
		this.validar = validar;
	}
	
	public List<ArchivosAdjuntos> getAdjuntosList() {
		return adjuntosList;
	}

	public void setAdjuntosList(List<ArchivosAdjuntos> adjuntos) {
		this.adjuntosList = adjuntos;
	}
	
	public List<Usuarios> getUsuariosAnalistas() {
		return usuariosAnalistas;
	}

	public void setUsuariosAnalistas(List<Usuarios> usuariosAnalistas) {
		this.usuariosAnalistas = usuariosAnalistas;
	}

	public List<Estados> getEstadosList() {
		return estadosList;
	}

	public void setEstadosList(List<Estados> estadosList) {
		this.estadosList = estadosList;
	}	
	public void detalle(ComponentSystemEvent event) throws IOException{
		try {
			if(getId() == null || getId().isEmpty()){
				FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/error.jsf");
			}
	        Integer idUrl=Integer.parseInt(getId());
	    
	    	defecto=gs.getDetalleDefecto(idUrl);
	    	historico = gs.getHistorico(idUrl);
	    	adjuntosList = new ArrayList<ArchivosAdjuntos>();
	    	adjuntosList=defecto.getAdjuntos();
	    	imagenes = new ArrayList<String>();
	        for (int i = 0; i < adjuntosList.size(); i++) {
	            imagenes.add(adjuntosList.get(i).getArchivo_ubicacion());
	        }
	    	setUsuariosAnalistas(gs.getUsuariosPorProyecto(defecto.getProyecto_fk().toString()));
	    	//obtener siguientes estados de defecto
	    	setEstadosList(gs.obtenerEstados());
	    	System.out.println("id defecto: "+getId());
			System.out.println("tamano historico" + historico.size());
			System.out.println("tamano adjuntos" + adjuntosList.size());
			if(!opciones){
				switch(defecto.getEstado_nombre()){
					case "ABIERTO" :
						if(m.getCarnet().equals(defecto.getReportero_fk())) //Puede editar si es el usuario que creo el defecto
							this.editarAbierto=true;
						if(m.getCarnet().equals(defecto.getResponsable_fk()) || m.getCarnet().equals(defecto.getProyecto_lider())){ //En este instante el lider es el responsable, el debe asignar
							this.asignar=true;
							this.resolver=true;
						}
						if(m.getPerfil().equals("ADMINISTRADOR")){
							this.editarAbierto=true;
							this.asignar=true;
							this.resolver=true;
						}
						break;
					case "ASIGNADO" :
						if(m.getCarnet().equals(defecto.getResponsable_fk())){ //El usuario responsable puede resolver el defecto
							this.editarAsignado=true;
							this.resolver=true;
							this.rechazar=true;
						}
						if(m.getCarnet().equals(defecto.getProyecto_lider())){ //Si soy el lider puedo editar el usuario asignado
							this.editarAsignado=true;
						}
						if(m.getPerfil().equals("ADMINISTRADOR")){
							this.editarAsignado=true;
							this.rechazar=true;
							this.resolver=true;
						}
						break;
					case "RESUELTO" :
						if(m.getCarnet().equals(defecto.getReportero_fk()) || m.getPerfil().equals("ADMINISTRADOR")){ //Puede editar si es el usuario que creo el defecto
							this.rechazar=true;
							this.aceptar=true;
						}
						break;
					case "CERRADO" :
						this.botones=false;
						this.opciones=false;
						break;
					case "RE-ABIERTO" :
						if(m.getCarnet().equals(defecto.getProyecto_lider()) || m.getPerfil().equals("ADMINISTRADOR")){ //Si soy el lider puedo editar el usuario asignado
							this.editarReabierto=true;
							this.asignar=true;
							this.resolver=true;
						}
						break;
				}
			}
		}catch(NumberFormatException ex){
			FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/error.jsf"); 
	    }
    }
	
	public void opcEdit(){
		this.opciones=true;
		this.botones=false;
		this.rechazar=false;
		this.aceptar=false;
		this.asignar=false;
		this.resolver=false;
	}
	public void opcAsignar(){
		this.opciones=true;
		this.botones=false;
		this.rechazar=false;
		this.aceptar=false;
		this.asignar=true;
		this.resolver=false;
		this.editarAbierto=false;
		this.editarReabierto=false;
		this.editarAsignado=false;
	}
	public void opcResolver(){
		this.opciones=true;
		this.botones=false;
		this.rechazar=false;
		this.aceptar=false;
		this.asignar=false;
		this.resolver=true;
		this.editarAbierto=false;
		this.editarReabierto=false;
		this.editarAsignado=false;
	}
	public void opcRechazar(){
		this.opciones=true;
		this.botones=false;
		this.rechazar=true;
		this.aceptar=false;
		this.asignar=false;
		this.resolver=false;
		this.editarAbierto=false;
		this.editarReabierto=false;
		this.editarAsignado=false;
	}
	public void opcAceptar(){
		this.opciones=true;
		this.botones=false;
		this.rechazar=false;
		this.aceptar=true;
		this.asignar=false;
		this.resolver=false;
		this.editarAbierto=false;
		this.editarReabierto=false;
		this.editarAsignado=false;
	}
	public void opcCancelar(){
		this.opciones=false;
		this.rechazar=false;
		this.aceptar=false;
		this.asignar=false;
		this.resolver=false;
		this.editarAbierto=false;
		this.editarReabierto=false;
		this.editarAsignado=false;
		this.botones=true;
	}
	public void opcGuardar(Object objeto){
		System.out.println(this.editarAbierto+" "+ this.editarReabierto +" "+ this.editarAsignado);
		System.out.println(this.asignar+" "+ this.rechazar +" "+ this.aceptar+" "+ this.resolver);
		if(this.editarAbierto || this.editarReabierto || this.editarAsignado)
			opcGuardarCambios(objeto);
		if(this.asignar) opcCambiarEstado(objeto, "ASIGNADO");
		if(this.rechazar) opcCambiarEstado(objeto, "RE-ABIERTO");
		if(this.aceptar) opcCambiarEstado(objeto, "CERRADO");
		if(this.resolver) opcCambiarEstado(objeto, "RESUELTO");
		this.opciones=false;
		this.rechazar=false;
		this.aceptar=false;
		this.asignar=false;
		this.resolver=false;
		this.editarAbierto=false;
		this.editarReabierto=false;
		this.editarAsignado=false;
		this.botones=true;
	}
		
	public List<UploadedFile> getAdjuntosSolucion() {
		return adjuntosSolucion;
	}

	public void setAdjuntosSolucion(List<UploadedFile> adjuntosSolucion) {
		this.adjuntosSolucion = adjuntosSolucion;
	}
    public void manejadorCargaArchivos(FileUploadEvent event) {
    	System.out.println("\nEntre ");
        boolean b= this.adjuntosSolucion.add(event.getFile());
        System.out.println("¿Inserto bien el archivo? "+b);
        System.out.println(adjuntosSolucion.size());
    }
    
	public void archivosResolucion(Defectos d){
		System.out.println("entre a archivos resolucion ");
		if(adjuntosSolucion!= null) {
			System.out.println(adjuntosSolucion.size());
	    	List<ArchivosAdjuntos> archAdjuntos=new ArrayList<ArchivosAdjuntos>();
	    	for(int i=0;i<adjuntosSolucion.size();i++){
	        	try {
					//String directorio="C:/Users/ctorrez/Desktop/XML_SGDBEX/"+ adjuntosSolucion.get(i).getFileName();
	        		//String directorioPath = System.getProperty("user.dir").replaceAll("\\\\", "/");
	        		String raiz = "C:/workspace/sgdbex/WebContent/Archivos_Adjuntos/";
	                String directorio = raiz + adjuntosSolucion.get(i).getFileName();
	                System.out.println(directorio);
	                /*System.out.println(adjuntosSolucion.get(i).getFileName());
	                System.out.println(adjuntosSolucion.get(i).getContentType());
	                System.out.println(adjuntosSolucion.get(i).getSize());*/
	                ArchivosAdjuntos adjuntos = new ArchivosAdjuntos();
	                adjuntos.setArchivo_nombre(adjuntosSolucion.get(i).getFileName());
	                adjuntos.setArchivo_formato(adjuntosSolucion.get(i).getContentType());
	                adjuntos.setArchivo_tipo('S');
	                adjuntos.setArchivo_ubicacion(raiz+URLEncoder.encode(adjuntosSolucion.get(i).getFileName().toString(), "UTF-8"));
	                adjuntos.setArchivo_tamano(Long.toString(adjuntosSolucion.get(i).getSize()));
	                adjuntos.setArchivo_usuario_creacion(m.getNombre());
	                archAdjuntos.add(adjuntos);
	                InputStream inputStream = adjuntosSolucion.get(i).getInputstream();
	                OutputStream out = new FileOutputStream(new File(directorio));
	                     int read = 0;
	                byte[] bytes = new byte[6144]; //valor multiplo de 1024 bytes.
	
	                while ((read = inputStream.read(bytes)) != -1) {
	                    out.write(bytes, 0, read);
	                }
	                inputStream.close();
	                out.flush();
	                out.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        	System.out.println("tamano "+archAdjuntos.size());
	    	}
	    	d.setAdjuntos(archAdjuntos);
	    	setAdjuntosSolucion(null);
	    	adjuntosSolucion =new ArrayList<UploadedFile>();
    	}
    }

	public void opcGuardarCambios(Object objeto) {
	    //this.editar =false;
		this.asignar =false;
		this.validar =false;
		this.resolver =false;
	    if(objeto instanceof Defectos){
    		Defectos d = (Defectos)objeto;
    		//d.setReportero_fk(m.getCarnet());
			d.setCarnet_modificacion(m.getCarnet().toString());
    		d.setUsuario_modificacion(m.getNombre());
			d.setAccion("GUARDAR CAMBIOS");
			d.setResponsable_nombre(obtenerNombreUsuario(d.getResponsable_fk()));
			try {
				d.setDireccionIp(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
    		archivosResolucion(d);
    		System.out.println("Soy de Defectos Guardar Cambios" + d.toString());
        	String mensaje = gs.updateDefectos(d);
            if(mensaje.equalsIgnoreCase("fallo")){
            	System.out.println("Fallo al crear");
            }else{
            	try {
					gc.invocarBeca(gs.enviarNotificacionDefectoActualizado(Integer.valueOf(mensaje)));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			//	this.editar =false;
				this.asignar =false;
				this.validar =false;
				this.resolver =false;
            	//defecto=gs.getDetalleDefecto(Integer.parseInt(getId()));
//            	gs.defectosList =  gs.getAllDefectos();
            }
    	}
	}
	private String obtenerNombreUsuario(String carnet){
		if(carnet == null || usuariosAnalistas == null) return null;
		else{
			for(int i=0; i<usuariosAnalistas.size(); i++){
				if(usuariosAnalistas.get(i).getCarnet().equals(carnet)){
					return usuariosAnalistas.get(i).getNombre_completo();
				}
			}
		}
		return null;
	}
	private String definirAccion(){
		if(isValidar()) return "VALIDAR";
		if(isAsignar()) return "ASIGNAR";
		if(isResolver()) return "RESOLVER";
		if(isRechazar()) return "RECHAZAR";
		if(isAceptar()) return "CERRAR";
		return null;
	}
	private Integer buscarIdEstado(String nombre){
		if(estadosList == null) return 0;
		else {
			for (int i=0; i<estadosList.size();i++)
				if(estadosList.get(i).getEstado_nombre().equalsIgnoreCase(nombre))
					return estadosList.get(i).getEstado_id();
		}
		return 0;
	}
	public void opcCambiarEstado(Object objeto, Object estado) {
	    if(objeto instanceof Defectos){
    		Defectos d = (Defectos)objeto;
    		String estado_nombre = (String) estado;
    		d.setCarnet_modificacion(m.getCarnet().toString());
    		d.setUsuario_modificacion(m.getNombre());
			d.setResponsable_nombre(obtenerNombreUsuario(d.getResponsable_fk()));
    		d.setAccion(definirAccion());
    		d.setEstado_nombre(estado_nombre);
    		d.setEstado_fk(buscarIdEstado(estado_nombre));
    		try {
				d.setDireccionIp(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
    		archivosResolucion(d);
    	//	System.out.println("Soy de Defectos cambiar estaos" + d.toString());
        	String mensaje = gs.cambiarEstadoDefectos(d);
            if(mensaje.equalsIgnoreCase("fallo")){
            	System.out.println("Fallo al cambiar estatus");
            }else{
    			try {
					gc.invocarBeca(gs.enviarNotificacionDefectoActualizado(Integer.valueOf(mensaje)));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//this.editar =false;
				this.asignar =false;
				this.validar =false;
				this.resolver =false;
            	defecto=gs.getDetalleDefecto(Integer.parseInt(getId()));
//            	gs.defectosList =  gs.getAllDefectos();
            }
    	}
	}
	public void descargarArchivo(Object objeto) throws FileNotFoundException {
		ArchivosAdjuntos adjuntos = (ArchivosAdjuntos)objeto;
		InputStream stream = new FileInputStream(adjuntos.getArchivo_ubicacion());
        archivo = new DefaultStreamedContent(stream, adjuntos.getArchivo_formato(), adjuntos.getArchivo_nombre());
    }
	
	public boolean isRechazar() {
		return rechazar;
	}
	public void setRechazar(boolean rechazar) {
		this.rechazar = rechazar;
	}
	public boolean isAceptar() {
		return aceptar;
	}
	public void setAceptar(boolean aceptar) {
		this.aceptar = aceptar;
	}
	public boolean isOpciones() {
		return opciones;
	}
	public void setOpciones(boolean opciones) {
		this.opciones = opciones;
	}
	public boolean isBotones() {
		return botones;
	}
	public void setBotones(boolean botones) {
		this.botones = botones;
	}

}
