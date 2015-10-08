package sgdbex.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import beca.correo.Cartero;
import beca.correo.CarteroProxy;
import sgdbex.services.GeneralServices;
import sgdbex.managedBeans.Menu;
import sgdbex.model.pojos.*;


@ManagedBean
@ViewScoped
@Controller
public class GeneralController{


	@Autowired
	private GeneralServices gs;
	
	public String filtro_proyecto;
	
	public List<Categorias> categoriasList;
	public List<Estados> estadosList;
	public List<Usuarios> usuariosList;
	public List<Usuarios> lideresList;
	public List<Usuarios> analistasList;
	public List<Prioridades> prioridadesList;
	public List<Proyectos> proyectosList;
	public List<RolUsuarios> rolesList;
	public List<Defectos> defectosList;
	public List<Impactos> impactosList;
	public List<Urgencias> urgenciasList;
	//public List<Reportes> reportesList;
	public List<MotivoResolucion> motivosList;
	public List<UploadedFile> adjuntosList;
	
	//public List<Reportes> filteredvalues;
	//--------------------------------------------------------------------
	// Variables para realizar filtros en administracion
	private List<Categorias> filtroCategorias;
	private List<Usuarios> filtroUsuarios;
	private List<Proyectos> filtroProyectos;
	private List<MotivoResolucion> filtroMotivos;
	private List<Defectos> filtroDefectos;
	//-----------------------------------------------------------------

	public Estados estado;
	public Prioridades prioridad;
	public Defectos defecto;
	public Proyectos proyecto;
	public RolUsuarios rol;
	public MotivoResolucion motivo;
	
	private String id;
	
	@Autowired
	public Menu infoUsuario;
	
	@PostConstruct
    public void init() {
//		System.out.println("Post Construct List");
		try{
			categoriasList =  listarCategorias();
			estadosList =  obtenerEstados();
			prioridadesList=obtenerPrioridades();
			usuariosList =  listarUsuarios();
			lideresList = listarLideres();
			analistasList = listarAnalistas();
			proyectosList =  listarProyectos();
			rolesList =  obtenerRolesUsuarios();
//			defectosList =  getAllDefectos();
			impactosList = obtenerImpactos();
			urgenciasList = obtenerUrgencias();
			motivosList = listarMotivos();
		}catch(Exception e){
			System.out.println("Exception List" +e.getMessage());
		}
    }
	
	public GeneralController() {
		proyecto=new Proyectos();
		defecto=new Defectos();
		adjuntosList=new ArrayList<UploadedFile>();
	}

	public GeneralServices getGs() {
		return gs;
	}

	public void setGs(GeneralServices gs) {
		this.gs = gs;
	}
	public Menu getInfoUsuario() {
		return infoUsuario;
	}
	public void setInfoUsuario(Menu infoUsuario) {
		this.infoUsuario = infoUsuario;
	}
	public String getFiltro_proyecto() {
		return filtro_proyecto;
	}

	public void setFiltro_proyecto(String filtro_proyecto) {
		this.filtro_proyecto = filtro_proyecto;
	}
//----------------------------------------------------------------------------------------
//--- Getters y Setters de Filtros en administracion---------------------------------------
	public List<Categorias> getFiltroCategorias() {
		return filtroCategorias;
	}

	public void setFiltroCategorias(List<Categorias> filtroCategorias) {
		this.filtroCategorias = filtroCategorias;
	}

	public List<Usuarios> getFiltroUsuarios() {
		return filtroUsuarios;
	}

	public void setFiltroUsuarios(List<Usuarios> filtroUsuarios) {
		this.filtroUsuarios = filtroUsuarios;
	}

	public List<Proyectos> getFiltroProyectos() {
		return filtroProyectos;
	}

	public void setFiltroProyectos(List<Proyectos> filtroProyectos) {
		this.filtroProyectos = filtroProyectos;
	}

	public List<MotivoResolucion> getFiltroMotivos() {
		return filtroMotivos;
	}

	public void setFiltroMotivos(List<MotivoResolucion> filtroMotivos) {
		this.filtroMotivos = filtroMotivos;
	}
	
	public List<Defectos> getFiltroDefectos() {
		return filtroDefectos;
	}

	public void setFiltroDefectos(List<Defectos> filtroDefectos) {
		this.filtroDefectos = filtroDefectos;
	}
//----------USUARIOS----------------------------------------------------------------------------------------------------------------------
	public void createUsuarios(Usuarios usuario) {
		gs.createUsuarios(usuario);
	}

	public void updateUsuarios(Usuarios usuario) {
		gs.updateUsuarios(usuario);
	}

	public void deleteUsuarios(Usuarios usuario) {
		gs.deleteUsuarios(usuario);
	}
	public List<Usuarios> listarUsuarios() {
		return gs.getUsuarios();
	}
	public List<Usuarios> listarAnalistas() {
		return gs.getUsuariosAnalistas();
	}
	public List<Usuarios> listarLideres() {
		return gs.getUsuariosLider();
	}
	public List<Usuarios> getAnalistasList() {
		return analistasList;
	}

	public void setAnalistasList(List<Usuarios> analistasList) {
		this.analistasList = analistasList;
	}

	public List<Usuarios> getLideresList() {
		return lideresList;
	}

	public void setLideresList(List<Usuarios> lideresList) {
		this.lideresList = lideresList;
	}

	public List<Usuarios> getUsuariosList() {
		return usuariosList;
	}
	
	public void setUsuariosList(List<Usuarios> usuariosList) {
		this.usuariosList = usuariosList;
	}
	
//----PRIORIDADES----------------------------------------------------------------------------------------------------------------------------
	public List<Prioridades> obtenerPrioridades(){
		return gs.obtenerPrioridades();
	}
	public List<Prioridades> getPrioridadesList() {
		return prioridadesList;
	}
	public void setPrioridadesList(List<Prioridades> prioridadesList) {
		this.prioridadesList = prioridadesList;
	}
	public Prioridades getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Prioridades prioridad) {
		this.prioridad = prioridad;
	}
//---------CATEGORIAS-----------------------------------------------------------------------------------------------------------------------
	public List<Categorias> listarCategorias() {
		return gs.getCategorias();
	}
	public List<Categorias> getCategoriasList() {
		return categoriasList;
	}
	public void setCategoriasList(List<Categorias> categoriasList) {
		this.categoriasList = categoriasList;
	}
	
//--------IMPACTOS------------------------------------------------------------------------------------------------------------------------
	public List<Impactos> obtenerImpactos() {
		return gs.obtenerImpactos();
	}
	
	public void setImpactosList(List<Impactos> impactosList) {
		this.impactosList = impactosList;
	}

	public List<Impactos> getImpactosList() {
		return impactosList;
	}

	//--------URGENCIAS------------------------------------------------------------------------------------------------------------------------
	public List<Urgencias> obtenerUrgencias() {
		return gs.obtenerUrgencias();
	}
	public void setUrgenciasList(List<Urgencias> urgenciasList) {
		this.urgenciasList = urgenciasList;
	}
	public List<Urgencias> getUrgenciasList() {
		return urgenciasList;
	}

//--------ESTADOS------------------------------------------------------------------------------------------------------------------------
	public List<Estados> obtenerEstados() {
		return gs.obtenerEstados();
	}
	public List<Estados> getEstadosList() {
		return estadosList;
	}
	public void setEstadosList(List<Estados> estadosList) {
		this.estadosList = estadosList;
	}
	public Estados getEstado() {
		return estado;
	}
	public void setEstado(Estados estado) {
		this.estado = estado;
	}
//----------MOTIVOS DE RESOLUCION----------------------------------------------------------------------------------------------------------------------
	
	public List<MotivoResolucion> getMotivosList() {
		return motivosList;
	}

	public void setMotivosList(List<MotivoResolucion> motivosList) {
		this.motivosList = motivosList;
	}
	
	public List<MotivoResolucion> listarMotivos() {
		return gs.getMotivos();
	}

	public MotivoResolucion getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoResolucion motivo) {
		this.motivo = motivo;
	}
	//----------Adjuntos----------------------------------------------------------------------------------------------------------------------	
	public List<UploadedFile> getAdjuntosList() {
		return adjuntosList;
	}

	public void setAdjuntosList(List<UploadedFile> adjuntosList) {
		this.adjuntosList = adjuntosList;
	}
	//----------PROYECTOS----------------------------------------------------------------------------------------------------------------------
	public List<Proyectos> listarProyectos() {
		return gs.getProyectos();
	}

	public List<Proyectos> getProyectosList() {
		return proyectosList;
	}
	public void setProyectosList(List<Proyectos> proyectosList) {
		this.proyectosList = proyectosList;
	}
	public Proyectos getProyecto() {
		return proyecto;
	}
	
	public void editar(RowEditEvent event) {
		String mensaje="";
		System.out.println("Entre a editar Vere a que clase pertenezco");

        if(event.getObject() instanceof Defectos){
        	Defectos def = ((Defectos) event.getObject());
        	def.setUsuario_modificacion(infoUsuario.getNombre());
        	System.out.println("Soy de Defctos entro a editar con valores"+def.toString());
        	mensaje = gs.updateDefectos(def);
        	addMessage("El item seleccionado ha sido editado!");
        }
        if(mensaje.equalsIgnoreCase("fallo")){
        	cancelar(event);
        	addMessage("Hubo un error al actualizar el item seleccionado!");
        }
    }

	public void cancelar(RowEditEvent event) {
		System.out.println("Vere a que clase pertenezco");
//        FacesMessage msg = new FacesMessage("Edicion Cancelada", ((Proyectos) event.getObject()).getProyecto_id().toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        if(event.getObject() instanceof Proyectos){
        	System.out.println("Soy de Proyectos en cancelar");	
        }
        if(event.getObject() instanceof Defectos){
        	System.out.println("Soy de defectos en cancelar");	
        }
        else{
        	System.out.println("Nop, tampoco soy de Categorias");
        }
    }
	public void invocarBeca(Correo cuerpoCorreo) throws Exception{
	    //ip = InetAddress.getLocalHost().getHostAddress();
		System.out.println("para "+cuerpoCorreo.getPara()+" Asunto "+ cuerpoCorreo.getAsunto());
		System.out.println("encabezado "+cuerpoCorreo.getEncabezado());
		System.out.println("firma "+cuerpoCorreo.getFirma());
		System.out.println("cuerpo "+cuerpoCorreo.getCuerpo());
		String smtpip,de, para,cc,cco,asunto,mensaje;
		smtpip="";
		de="SGDBEX";
		para=cuerpoCorreo.getPara();
		cc="";
		cco="";
		asunto=cuerpoCorreo.getAsunto();
		mensaje=cuerpoCorreo.getEncabezado()+cuerpoCorreo.getCuerpo()+cuerpoCorreo.getFirma();
		int formato=1,prioridad=1;
		CarteroProxy proxy = new CarteroProxy();
		Cartero servicio = proxy.getCartero();
		servicio.enviar(smtpip, de, para, cc, cco, asunto, formato, mensaje, prioridad);
		System.out.println("Enviado");
	}

    public void agregar(Object objeto) {
    	String mensaje;
    	System.out.println("Entre a agregar ");
    	if(objeto == null)System.out.println("null");
    	if(objeto instanceof Defectos){
    		Defectos d = (Defectos)objeto;
    		d.setReportero_fk(infoUsuario.getCarnet());
    		d.setReportero_nombre(infoUsuario.getNombre());
    		d.setUsuario_modificacion(infoUsuario.getNombre());
    		d.setCarnet_modificacion(infoUsuario.getCarnet());
    		d.setEstado_fk(obtenerIdEstado("ABIERTO"));
    		System.out.println("Carnet del usuario en menu" + infoUsuario.getCarnet());//delete(itemId);
    		System.out.println("Soy de Defectos Agregar" + d.toString());
    		d.setPrioridad_fk(asignarPrioridad(d.getImpacto_fk(),d.getUrgencia_fk()));
    		//-------empiezo adjuntos
    		try {
				d= anadirArchivos(adjuntosList,d);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		mensaje = gs.createDefectos(d);
    		System.out.println("mensaje "+mensaje);
    		if(!mensaje.equalsIgnoreCase("fallo")){
    			try {
					invocarBeca(gs.enviarNotificacionDefectoCreado(Integer.valueOf(mensaje)));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
            if(adjuntosList != null && !mensaje.equalsIgnoreCase("fallo")) {
            	List<ArchivosAdjuntos> archAdjuntos=new ArrayList<ArchivosAdjuntos>();
            	for(int i=0;i<adjuntosList.size();i++){
            		System.out.println("Agregando");
                	try {
                        String directorio="C:/Users/ctorrez/Desktop/XML_SGDBEX/"+ adjuntosList.get(i).getFileName();
                        System.out.println(adjuntosList.get(i).getFileName());
                        System.out.println(adjuntosList.get(i).getContentType());
                        System.out.println(adjuntosList.get(i).getSize());
                        ArchivosAdjuntos adjuntos = new ArchivosAdjuntos();
                        adjuntos.setArchivo_nombre(adjuntosList.get(i).getFileName());
                        adjuntos.setArchivo_formato(adjuntosList.get(i).getContentType());
                        adjuntos.setArchivo_tipo('A');
                        adjuntos.setArchivo_ubicacion(directorio);
                        adjuntos.setArchivo_tamano(Long.toString(adjuntosList.get(i).getSize()));
                        adjuntos.setArchivo_usuario_creacion(infoUsuario.getNombre());
                        archAdjuntos.add(adjuntos);
//                        adjuntos.setArchivo_defecto_fk()
                        InputStream inputStream = adjuntosList.get(i).getInputstream();
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
            	}
            	System.out.println("tamano "+archAdjuntos.size());
            	d.setAdjuntos(archAdjuntos);
				limpiar();
            	addMessage("El item seleccionado ha sido creado!");
            }
    		//-----------termino adjuntos
        	adjuntosList = null;
            if(mensaje.equalsIgnoreCase("fallo")){
            	addMessage("Fallo al crear elemento, por favor intente de nuevo.");

            }
    	}
    }
    private Defectos anadirArchivos(List<UploadedFile> adjuntosList, Defectos d) throws IOException {
        if(adjuntosList != null) {
        	List<ArchivosAdjuntos> archAdjuntos=new ArrayList<ArchivosAdjuntos>();
        	for(int i=0;i<adjuntosList.size();i++){
        		System.out.println("Anadir");
            	String directorio="C:/Users/ctorrez/Desktop/XML_SGDBEX/"+ adjuntosList.get(i).getFileName();
				ArchivosAdjuntos adjuntos = new ArchivosAdjuntos();
				adjuntos.setArchivo_nombre(adjuntosList.get(i).getFileName());
				adjuntos.setArchivo_formato(adjuntosList.get(i).getContentType());
				adjuntos.setArchivo_tipo('A');
				adjuntos.setArchivo_ubicacion(directorio);
				adjuntos.setArchivo_tamano(Long.toString(adjuntosList.get(i).getSize()));
				adjuntos.setArchivo_usuario_creacion(infoUsuario.getNombre());
				archAdjuntos.add(adjuntos);
        	}
        	d.setAdjuntos(archAdjuntos);
        	return d;
        }else return d;
	}

	private Integer obtenerIdEstado(String nombre) {
    	System.out.println(estadosList.size());
    	for (int i=0; i< estadosList.size();i++){
    		if(estadosList.get(i).getEstado_nombre().equalsIgnoreCase(nombre))
    			return estadosList.get(i).getEstado_id();
    	}
		return 0;
	}

	private Integer obtenerIdPrioridad(String prioridad_nombre){
    	for (int i=0; i< prioridadesList.size();i++){
    		if(prioridadesList.get(i).getPrioridad_tipo().equalsIgnoreCase(prioridad_nombre))
    			return prioridadesList.get(i).getPrioridad_id();
    	}
    	return 0;
    }
	private String obtenerNombreImpacto(Integer impacto){
    	for (int i=0; i< impactosList.size();i++){
    		if(impactosList.get(i).getImpacto_id() ==impacto)
    			return impactosList.get(i).getImpacto_tipo();
    	}
    	return "";
    }
	private String obtenerNombreUrgencia(Integer urgencia){
    	for (int i=0; i< urgenciasList.size();i++){
    		if(urgenciasList.get(i).getUrgencia_id() == urgencia)
    			return urgenciasList.get(i).getUrgencia_tipo();
    	}
    	return "";
    }
    private Integer asignarPrioridad(Integer impacto_id, Integer urgencia_id) {
    	String prioridad="";
    	String impacto=obtenerNombreImpacto(impacto_id);
    	String urgencia= obtenerNombreUrgencia(urgencia_id);
    	if(impacto.equalsIgnoreCase("ALTA") && urgencia.equalsIgnoreCase("ALTA")){
    		prioridad="CRITICA";
    	}
    	if(impacto.equalsIgnoreCase("MEDIA") && urgencia.equalsIgnoreCase("ALTA")){
    		prioridad="ALTA";
    	}
		if(impacto.equalsIgnoreCase("BAJA") && urgencia.equalsIgnoreCase("ALTA")){
			prioridad="MEDIA";
		}
		if(impacto.equalsIgnoreCase("ALTA") && urgencia.equalsIgnoreCase("MEDIA")){
			prioridad="ALTA";
    	}
    	if(impacto.equalsIgnoreCase("MEDIA") && urgencia.equalsIgnoreCase("MEDIA")){
    		prioridad="MEDIA";
    	}
		if(impacto.equalsIgnoreCase("BAJA") && urgencia.equalsIgnoreCase("MEDIA")){
			prioridad="BAJA";
		}
		if(impacto.equalsIgnoreCase("ALTA") && urgencia.equalsIgnoreCase("BAJA")){
			prioridad="MEDIA";
    	}
    	if(impacto.equalsIgnoreCase("MEDIA") && urgencia.equalsIgnoreCase("BAJA")){
    		prioridad="BAJA";
    	}
		if(impacto.equalsIgnoreCase("BAJA") && urgencia.equalsIgnoreCase("BAJA")){
			prioridad="PLANIFICADA";
		}
		System.out.println("prioridad nombre "+prioridad);
		return obtenerIdPrioridad(prioridad);
	}

	public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        boolean b= adjuntosList.add(event.getFile());
        System.out.println("¿Inserto bien el archivo? "+b);
    }
    public void borrarItem(Object objeto){
    	System.out.println("Vere a que clase pertenezco");
    	String mensaje;
    	if(objeto instanceof Defectos){
    		Defectos def = (Defectos)objeto;
    		System.out.println("Soy de Proyectos Eliminado" + def.toString());
    		def.setUsuario_modificacion(infoUsuario.getNombre());
        	mensaje = gs.deleteDefectos(def);
            addMessage("El item seleccionado ha sido eliminado!");
            if(mensaje.equalsIgnoreCase("fallo")){
            	System.out.println("Fallo al eliminar");
            }else{
//            	defectosList =  getAllDefectos();
            	System.out.println("Cantidad "+ defectosList.size());
            }
    	}

    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

//--------------ROLES USUARIOS------------------------------------------------------------------------------------------------------------------
	public List<RolUsuarios> obtenerRolesUsuarios() {
		return gs.obtenerRolesUsuarios();
	}
	public List<RolUsuarios> getRolesList() {
		return rolesList;
	}
	public void setRolesList(List<RolUsuarios> rolesList) {
		this.rolesList = rolesList;
	}
	public RolUsuarios getRol() {
		return rol;
	}
	public void setRol(RolUsuarios rol) {
		this.rol = rol;
	}
	
//-------------------------------------------Getters y Setters de las listas------------------------------------------------------
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
//--------------------------------------------------------------------------------------------------------------------------------
	
	public void limpiar(){
		setDefecto(new Defectos());
	}

	public Defectos getDefecto() {
		return defecto;
	}

	public void setDefecto(Defectos defecto) {
		this.defecto = defecto;
	}
	
    public List<Defectos> getDefectosList() {
		return defectosList;
	}

	public void setDefectosList(List<Defectos> defectosList) {
		this.defectosList = defectosList;
	}
	public void detalleDefecto(ActionEvent e) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/views/reportarDefecto/detalleDefecto.jsf");
	}
	public String buscarEnProyectos(List<Proyectos> ListaProyectosUsuario) {
		String proyectos="";
		for(int i=0; i<ListaProyectosUsuario.size();i++){
			proyectos += ListaProyectosUsuario.get(i).getProyecto_id().toString();
			if(i+1<ListaProyectosUsuario.size()) proyectos +=",";
		}
		System.out.println("proyectos a buscar"+proyectos);
		return proyectos;
	}

	public void defectosValidar(ActionEvent e) throws IOException { //valida los defectos
		if(filtro_proyecto.equals("0")){
			//Son todos los proyectos relacionados al usuario, entonces hago un concat de la lista de proyectos
			filtro_proyecto= buscarEnProyectos(infoUsuario.getProyectosUsuarioList());
		}
		defectosList = gs.getDefectosValidar(infoUsuario.getCarnet(), infoUsuario.getPerfil(), filtro_proyecto);
    	FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/Validar");
    }
	
	public void defectosModificar(ActionEvent e) throws IOException { //puede modificar los defectos creados por el
		if(filtro_proyecto.equals("0")){
			//Son todos los proyectos relacionados al usuario, entonces hago un concat de la lista de proyectos
			filtro_proyecto= buscarEnProyectos(infoUsuario.getProyectosUsuarioList());
		}
		defectosList = gs.getDefectosModificar(infoUsuario.getCarnet(), infoUsuario.getPerfil(), filtro_proyecto);
    	FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/views/miVista/defectosModificados.jsf");
    }

	public void defectosEliminar(ActionEvent e) throws IOException { //opcion eliminar defectos
		if(filtro_proyecto.equals("0")){
			//Son todos los proyectos relacionados al usuario, entonces hago un concat de la lista de proyectos
			filtro_proyecto= buscarEnProyectos(infoUsuario.getProyectosUsuarioList());
		}
		defectosList = gs.getDefectosModificar(infoUsuario.getCarnet(), infoUsuario.getPerfil(), filtro_proyecto);
//		Redirigir a interfaz eliminar
    	FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/Validar");
    }
	public void defectosResolver(ActionEvent e) throws IOException { //puede resolver el defecto que le fue asignado
		if(filtro_proyecto.equals("0")){
			//Son todos los proyectos relacionados al usuario, entonces hago un concat de la lista de proyectos
			filtro_proyecto= buscarEnProyectos(infoUsuario.getProyectosUsuarioList());
		}
		defectosList = gs.getDefectosAsignados(infoUsuario.getCarnet(), infoUsuario.getPerfil(), filtro_proyecto);
    	FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/Resolver");
    }
	public void defectosPorAsignar(ActionEvent e) throws IOException { //Defectos no asignados a un desarrollador
		if(filtro_proyecto.equals("0")){
			//Son todos los proyectos relacionados al usuario, entonces hago un concat de la lista de proyectos
			filtro_proyecto= buscarEnProyectos(infoUsuario.getProyectosUsuarioList());
		}
		defectosList = gs.getDefectosNoAsignados(infoUsuario.getCarnet(), infoUsuario.getPerfil(), filtro_proyecto);
    	FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/Asignar");
    }
	
	public void defectosMonitorizados(ActionEvent e) throws IOException { //recientemente modificados y monitorizados, reportados por el
		if(filtro_proyecto.equals("0")){
			//Son todos los proyectos relacionados al usuario, entonces hago un concat de la lista de proyectos
			filtro_proyecto= buscarEnProyectos(infoUsuario.getProyectosUsuarioList());
		}
		defectosList = gs.getDefectosMonitorizados(infoUsuario.getCarnet(), infoUsuario.getPerfil(), filtro_proyecto);
    	FacesContext.getCurrentInstance().getExternalContext().redirect("/sgdbex/views/miVista/defectosModificados.jsf");
    }
		
}
