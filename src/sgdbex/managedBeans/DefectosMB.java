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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
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
//@Join(path = "/customer/{id}", to="/faces/views/reportarDefecto/detalleDefecto")
public class DefectosMB {		     
    @Autowired
	private GeneralServices gs;
    @Autowired
	private GeneralController gc;
    @Autowired
	public Menu m;
    
    private boolean editar =false;
	private boolean asignar =false;
	private boolean validar =false;
	private boolean resolver =false;
	
	//@Parameter
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

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
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
	public void detalle(ComponentSystemEvent event){
    	defecto=gs.getDetalleDefecto(Integer.parseInt(getId()));
    	historico = gs.getHistorico(Integer.parseInt(getId()));
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
    }
	public void opcEditar() {
	    this.editar = !this.editar;
		this.asignar = puedeAsignar();
		this.resolver = puedeResolver();
		this.validar = puedeValidar();
	}
	public void opcValidar() {
	    this.validar = !this.validar ;
	}
	public void opcAsignar() {
	    this.asignar = !this.asignar ;
	}
	public void opcResolver() {
	    this.resolver = !this.resolver ;
	}
	
	public void opcCancelar() {
		this.editar =false;
		this.asignar =false;
		this.validar =false;
		this.resolver =false;
	}
	public boolean puedeAsignar() {
		if(m.getPerfil().equalsIgnoreCase("ADMINISTRADOR")
				&& (defecto.getEstado_nombre().equalsIgnoreCase("ABIERTO") || defecto.getEstado_nombre().equalsIgnoreCase("RE-ABIERTO")))
			return true;
		if(m.getPerfil().equalsIgnoreCase("LIDER") && 
			(defecto.getEstado_nombre().equalsIgnoreCase("ABIERTO") || defecto.getEstado_nombre().equalsIgnoreCase("RE-ABIERTO")))
		{
			return true;
		}
		return false;
	}
	public boolean puedeValidar() {
		if(m.getPerfil().equalsIgnoreCase("ADMINISTRADOR") && defecto.getEstado_nombre().equalsIgnoreCase("RESUELTO"))
			return true;
		if(defecto.getReportero_fk().equals(m.getCarnet()) && defecto.getEstado_nombre().equalsIgnoreCase("RESUELTO"))
		{
			return true;
		}
		return false;
	}
	public boolean puedeResolver() {
		if(m.getPerfil().equalsIgnoreCase("ADMINISTRADOR") && defecto.getEstado_nombre().equalsIgnoreCase("ASIGNADO")){
			return true;
			}
		if(defecto.getResponsable_fk() !=null && defecto.getResponsable_fk().equals(m.getCarnet()) && defecto.getEstado_nombre().equalsIgnoreCase("ASIGNADO")){
			return true;
		}
		return false;
	}
	public boolean puedeEditar() {
		if(m.getPerfil().equalsIgnoreCase("ADMINISTRADOR") && defecto.getEstado_nombre().equalsIgnoreCase("ABIERTO"))
			return true;
		else if(defecto.getReportero_fk().equals(m.getCarnet()) && defecto.getEstado_nombre().equalsIgnoreCase("ABIERTO") )
			{
			return true;
			}
			else if(puedeResolver() || puedeAsignar() || puedeValidar()){
				return true;
			}
		return false;
	}
	
	public List<UploadedFile> getAdjuntosSolucion() {
		return adjuntosSolucion;
	}

	public void setAdjuntosSolucion(List<UploadedFile> adjuntosSolucion) {
		this.adjuntosSolucion = adjuntosSolucion;
	}
    public void manejadorCargaArchivos(FileUploadEvent event) {
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
        boolean b= adjuntosSolucion.add(event.getFile());
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
	                String directorio="C:/Users/ctorrez/Desktop/XML_SGDBEX/"+ adjuntosSolucion.get(i).getFileName();
	                System.out.println(adjuntosSolucion.get(i).getFileName());
	                System.out.println(adjuntosSolucion.get(i).getContentType());
	                System.out.println(adjuntosSolucion.get(i).getSize());
	                ArchivosAdjuntos adjuntos = new ArchivosAdjuntos();
	                adjuntos.setArchivo_nombre(adjuntosSolucion.get(i).getFileName());
	                adjuntos.setArchivo_formato(adjuntosSolucion.get(i).getContentType());
	                adjuntos.setArchivo_tipo('S');
	                adjuntos.setArchivo_ubicacion(directorio);
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
	    this.editar =false;
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
				this.editar =false;
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
				this.editar =false;
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

}
