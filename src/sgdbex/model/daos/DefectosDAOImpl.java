package sgdbex.model.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import sgdbex.model.pojos.ArchivosAdjuntos;
import sgdbex.model.pojos.Defectos;
import sgdbex.model.pojos.HistoricoEstados;
@SuppressWarnings("unchecked")
@Component
@Repository("defectosDAO")
public class DefectosDAOImpl implements DefectosDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String createDefectos(Defectos Defecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		List<ArchivosAdjuntos> list2 = null;
		try {
			Query query = session.createSQLQuery("SP_CrearDefectos :asunto, :descripcion, :prioridad,"
					+ " :impacto, :urgencia, :estado, :proyecto, :categoria, :reportero_carnet, :fecha_estimada, :usuario_modif, :comentarios,"
					+ " :reportero_nombre, :carnet_modif")
					.setResultTransformer(Transformers.aliasToBean(Defectos.class))
					.setParameter("asunto", Defecto.getAsunto())
					.setParameter("descripcion", Defecto.getDescripcion())
					.setParameter("prioridad", Defecto.getPrioridad_fk())
					.setParameter("impacto", Defecto.getImpacto_fk())
					.setParameter("urgencia", Defecto.getUrgencia_fk())
					.setParameter("estado", Defecto.getEstado_fk())
					.setParameter("proyecto", Defecto.getProyecto_fk())
					.setParameter("categoria", Defecto.getCategoria_fk())
					.setParameter("reportero_carnet", Defecto.getReportero_fk())
					.setParameter("fecha_estimada", Defecto.getFecha_estimada())
					.setParameter("usuario_modif", Defecto.getUsuario_modificacion())
					.setParameter("comentarios", Defecto.getComentarios())
					.setParameter("reportero_nombre", Defecto.getReportero_nombre())
					.setParameter("carnet_modif", Defecto.getCarnet_modificacion());
			list = (List<Defectos>)query.list();
			System.out.println("Defectos Create " + list.get(0).getDefecto_id());
			System.out.println("Adjuntos error: " + Defecto.getAdjuntos().size());
			if(list.get(0).getDefecto_id() != 0 && Defecto.getAdjuntos() != null && Defecto.getAdjuntos().size() > 0){
				for(int i=0;i<Defecto.getAdjuntos().size();i++){
					Defecto.getAdjuntos().get(i).setArchivo_defecto_fk(list.get(0).getDefecto_id());
					query = session.createSQLQuery("SP_CrearArchivosAdjuntos :nombre, :formato, :ubicacion,"
							+ " :comentarios, :tipo, :tamano, :usuario, :defecto")
							.setResultTransformer(Transformers.aliasToBean(ArchivosAdjuntos.class))
							.setParameter("nombre", Defecto.getAdjuntos().get(i).getArchivo_nombre())
							.setParameter("formato", Defecto.getAdjuntos().get(i).getArchivo_formato())
							.setParameter("ubicacion", Defecto.getAdjuntos().get(i).getArchivo_ubicacion())
							.setParameter("comentarios", Defecto.getAdjuntos().get(i).getArchivo_comentarios())
							.setParameter("tipo", Defecto.getAdjuntos().get(i).getArchivo_tipo())
							.setParameter("tamano", Defecto.getAdjuntos().get(i).getArchivo_tamano())
							.setParameter("usuario", Defecto.getAdjuntos().get(i).getArchivo_usuario_creacion())
							.setParameter("defecto", Defecto.getAdjuntos().get(i).getArchivo_defecto_fk());
					list2 = (List<ArchivosAdjuntos>)query.list();
					if(list2.get(0).getArchivo_id() == 0){
//						Error eliminar TODO
						return "fallo";
					}
				}
				return "exito";
			}
			return "exito";
		} catch (HibernateException e) {
			System.out.println("try Impl Defec " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

	@Override
	public String updateDefectos(Defectos Defecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		List<ArchivosAdjuntos> list2 = null;
		try {
			Query query = session.createSQLQuery("SP_ActualizarDefectos :idDefecto, :asunto, :descripcion, :prioridad,"
					+ " :impacto, :urgencia, :estado, :proyecto, :categoria, :carnet, :fecha_estimada, :usuario, :comentarios,"
					+ " :motivo, :responsable, :responsable_nombre, :direccionIp, :modulo, :accion")
					.setResultTransformer(Transformers.aliasToBean(Defectos.class))
					.setParameter("idDefecto", Defecto.getDefecto_id())
					.setParameter("asunto", Defecto.getAsunto())
					.setParameter("descripcion", Defecto.getDescripcion())
					.setParameter("prioridad", Defecto.getPrioridad_fk())
					.setParameter("impacto", Defecto.getImpacto_fk())
					.setParameter("urgencia", Defecto.getUrgencia_fk())
					.setParameter("estado", Defecto.getEstado_fk())
					.setParameter("proyecto", Defecto.getProyecto_fk())
					.setParameter("categoria", Defecto.getCategoria_fk())
					.setParameter("carnet", Defecto.getCarnet_modificacion())
					.setParameter("fecha_estimada", Defecto.getFecha_estimada())
					.setParameter("usuario", Defecto.getUsuario_modificacion())
					.setParameter("comentarios", Defecto.getComentarios())
					.setParameter("motivo", Defecto.getMotivo_fk())
					.setParameter("responsable", Defecto.getResponsable_fk())
					.setParameter("responsable_nombre", Defecto.getResponsable_nombre())
					.setParameter("direccionIp", Defecto.getDireccionIp())
					.setParameter("modulo", Defecto.getModulo())
					.setParameter("accion", Defecto.getAccion());
			list = (List<Defectos>)query.list();
			System.out.println("Defectos update " + list.get(0).toString());
			if(list.get(0).getDefecto_id() != 0){
				for(int i=0;i<Defecto.getAdjuntos().size();i++){
					Defecto.getAdjuntos().get(i).setArchivo_defecto_fk(list.get(0).getDefecto_id());
					query = session.createSQLQuery("SP_CrearArchivosAdjuntos :nombre, :formato, :ubicacion,"
							+ " :comentarios, :tipo, :tamano, :usuario, :defecto")
							.setResultTransformer(Transformers.aliasToBean(ArchivosAdjuntos.class))
							.setParameter("nombre", Defecto.getAdjuntos().get(i).getArchivo_nombre())
							.setParameter("formato", Defecto.getAdjuntos().get(i).getArchivo_formato())
							.setParameter("ubicacion", Defecto.getAdjuntos().get(i).getArchivo_ubicacion())
							.setParameter("comentarios", Defecto.getAdjuntos().get(i).getArchivo_comentarios())
							.setParameter("tipo", Defecto.getAdjuntos().get(i).getArchivo_tipo())
							.setParameter("tamano", Defecto.getAdjuntos().get(i).getArchivo_tamano())
							.setParameter("usuario", Defecto.getAdjuntos().get(i).getArchivo_usuario_creacion())
							.setParameter("defecto", Defecto.getAdjuntos().get(i).getArchivo_defecto_fk());
					list2 = (List<ArchivosAdjuntos>)query.list();
					if(list2.get(0).getArchivo_id() == 0){
//						Error eliminar TODO
						return "fallo";
					}
				}
				return "exito";
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl Defc " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

	@Override
	public String deleteDefectos(Defectos Defecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		try {
			Query query = session.createSQLQuery("SP_EliminarDefectos :idDefecto")
					.setResultTransformer(Transformers.aliasToBean(Defectos.class))
					.setParameter("idDefecto", Defecto.getDefecto_id());
			list = (List<Defectos>)query.list();
			System.out.println("Defecto delete " + list.get(0).toString());
			return "exito";
		} catch (HibernateException e) {
			System.out.println("try Impl delete defect " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

//	@Override
//	public List<Defectos> getAllDefectos() {
//		Session session = sessionFactory.openSession();
//		List<Defectos> list = null;
//		System.out.println("getAll Defectos");
//		try {
//
//			Query query = session.createSQLQuery("SELECT * FROM View_Defectos").setResultTransformer(Transformers.aliasToBean(Defectos.class));
//			list = (List<Defectos>)query.list();
//			System.out.println("Tamaño de la lista queryUSer" + list.size());
//			if(list.size() > 0)
//			System.out.println("Usuarios" + list.get(0).toString());
//		} catch (HibernateException e) {
//			System.out.println("try Impl Defectos " +e.getMessage() );
//		} finally {
//			session.close();
//		}
//		return list;
//	}
//	
	@Override
	public List<Defectos> getDefectosMonitorizados(String usuario, String rol, String proyecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		Query query = null;
		try {
			query = session.createSQLQuery("SP_ObtenerDefectosMonitorizados :rol, :usuario, :proyecto").setResultTransformer(Transformers.aliasToBean(Defectos.class))
					.setParameter("rol", rol)
					.setParameter("usuario", usuario)
					.setParameter("proyecto", proyecto);
			list = (List<Defectos>)query.list();
			System.out.println("Tamaño de la lista queryUSer" + list.size());
		} catch (HibernateException e) {
			System.out.println("try Impl Defectos " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Defectos> getDefectosModifReciente(String usuario, String rol, String proyecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		Query query = null;
		try {
			query = session.createSQLQuery("SP_ObtenerDefectosModifReciente :rol, :usuario, :proyecto").setResultTransformer(Transformers.aliasToBean(Defectos.class))
					.setParameter("rol", rol)
					.setParameter("usuario", usuario)
					.setParameter("proyecto", proyecto);
			list = (List<Defectos>)query.list();
			System.out.println("Tamaño de la lista queryUSer" + list.size());
		} catch (HibernateException e) {
			System.out.println("try Impl Defectos " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Defectos> getDefectosReportadosPorMi(String usuario, String rol, String proyecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		Query query = null;
		try {
			query = session.createSQLQuery("SP_ObtenerDefectosReportados :rol, :usuario, :proyecto").setResultTransformer(Transformers.aliasToBean(Defectos.class))
					.setParameter("rol", rol)
					.setParameter("usuario", usuario)
					.setParameter("proyecto", proyecto);
			list = (List<Defectos>)query.list();
			System.out.println("Tamaño de la lista queryUSer" + list.size());
		} catch (HibernateException e) {
			System.out.println("try Impl Defectos " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Defectos> getDefectosCerrados(String usuario, String rol, String proyecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		Query query = null;
		try {
			query = session.createSQLQuery("SP_ObtenerDefectosCerrados :rol, :usuario, :proyecto").setResultTransformer(Transformers.aliasToBean(Defectos.class))
					.setParameter("rol", rol)
					.setParameter("usuario", usuario)
					.setParameter("proyecto", proyecto);
			list = (List<Defectos>)query.list();
			System.out.println("Tamaño de la lista queryUSer" + list.size());
		} catch (HibernateException e) {
			System.out.println("try Impl Defectos " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public Defectos getDetalleDefecto(Integer id) {
		Session session = sessionFactory.openSession();
		Defectos detalle = new Defectos();
		List<ArchivosAdjuntos> adjuntos = new ArrayList<ArchivosAdjuntos>();
		System.out.println("detalle Defectos "+ id);
		Query query = null;
		try {
			query = session.createSQLQuery("SP_ObtenerDetalleDefecto :id").setResultTransformer(Transformers.aliasToBean(Defectos.class))
			.setParameter("id", id);
			detalle = (Defectos)query.list().get(0);
			System.out.println(detalle.toString());
		} catch (HibernateException e) {
			System.out.println("try Impl Defectos " +e.getMessage() );
		} 
		try {
			query = session.createSQLQuery("SP_ObtenerArchivosAdjuntosDefecto :id").setResultTransformer(Transformers.aliasToBean(ArchivosAdjuntos.class))
			.setParameter("id", id);
			adjuntos = (List<ArchivosAdjuntos>)query.list();
			System.out.println(adjuntos.toString());
			detalle.setAdjuntos(adjuntos);
		} catch (HibernateException e) {
			System.out.println("try array adjuntos " +e.getMessage());
		}finally {
			session.close();
		}
		return detalle;
	}
	
	@Override
	public String cambiarEstadoDefecto(Defectos defecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		List<ArchivosAdjuntos> list2 = null;
		System.out.println("Defecto recibido DAO " +defecto.toString());
		try {
			Query query = session.createSQLQuery("SP_ActualizarEstadoDefecto :defecto, :estado, :carnetUsuario,"
					+ " :nombreUsuario, :motivo, :carnetResponsable, :nombreResponsable,"
					+ " :comentarios, :direccion, :modulo, :accion")
					.setResultTransformer(Transformers.aliasToBean(Defectos.class))
					.setParameter("defecto", defecto.getDefecto_id())
					.setParameter("estado", defecto.getEstado_fk())
					.setParameter("carnetUsuario", defecto.getCarnet_modificacion())
					.setParameter("nombreUsuario", defecto.getUsuario_modificacion())
					.setParameter("motivo", defecto.getMotivo_fk())
					.setParameter("carnetResponsable", defecto.getResponsable_fk())
					.setParameter("nombreResponsable", defecto.getResponsable_nombre())
					.setParameter("comentarios", defecto.getComentarios())
					.setParameter("direccion", defecto.getDireccionIp())
					.setParameter("modulo", defecto.getModulo())
					.setParameter("accion", defecto.getAccion());
			list = (List<Defectos>)query.list();
			if(list.get(0).getDefecto_id() != 0 && defecto.getAdjuntos() != null){
				for(int i=0;i<defecto.getAdjuntos().size();i++){
					defecto.getAdjuntos().get(i).setArchivo_defecto_fk(list.get(0).getDefecto_id());
					query = session.createSQLQuery("SP_CrearArchivosAdjuntos :nombre, :formato, :ubicacion,"
							+ " :comentarios, :tipo, :tamano, :usuario, :defecto")
							.setResultTransformer(Transformers.aliasToBean(ArchivosAdjuntos.class))
							.setParameter("nombre", defecto.getAdjuntos().get(i).getArchivo_nombre())
							.setParameter("formato", defecto.getAdjuntos().get(i).getArchivo_formato())
							.setParameter("ubicacion", defecto.getAdjuntos().get(i).getArchivo_ubicacion())
							.setParameter("comentarios", defecto.getAdjuntos().get(i).getArchivo_comentarios())
							.setParameter("tipo", defecto.getAdjuntos().get(i).getArchivo_tipo())
							.setParameter("tamano", defecto.getAdjuntos().get(i).getArchivo_tamano())
							.setParameter("usuario", defecto.getAdjuntos().get(i).getArchivo_usuario_creacion())
							.setParameter("defecto", defecto.getAdjuntos().get(i).getArchivo_defecto_fk());
					list2 = (List<ArchivosAdjuntos>)query.list();
					if(list2.get(0).getArchivo_id() == 0){
//						Error eliminar TODO
						return "fallo";
					}
				}
				if (list.get(0).getDefecto_id().equals(defecto.getDefecto_id()))
					return "exitoso";
				else{
//					devolver todo
					return "fallo";
					}
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl Defec " +e.getMessage() );
			return "error";
		} finally {
			session.close();
		}
	}
	//Accion Modificar defectos, obtiene una lista de los defectos que pueden ser modificados por el usuario registrado
	@Override
	public List<Defectos> getDefectosModificar(String usuario, String rol, String proyecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		String consulta = "SELECT * FROM View_Defectos ";
		Query query = null;
		System.out.println("defectos a modificar");
		//System.out.println("Usuario " +usuario + " perfil "+rol+" proyecto "+proyecto);
		try {
			//Si es administrador puede modificar o eliminar cualquier defectos cuyo estatus esten ABIERTO O REABIERTO de cualquier proyecto
			if(rol.equals("ADMINISTRADOR")){
				System.out.println("Entre como administrador ");
				consulta+=" WHERE estado_nombre IN ('ABIERTO','RE-ABIERTO') ";
			}
			//Si es funcional puede modificar o eliminar cualquier defectos cuyo estatus esten ABIERTO O REABIERTO de cualquier proyecto o el proyecto al que este pertenezca
			if(rol.equals("FUNCIONAL") && proyecto != null && !proyecto.equals("")){
				System.out.println("Entre como funcional y un proyecto ");
//				consulta+=" WHERE estado_nombre IN ('ABIERTO','RE-ABIERTO') AND reportero_fk = '"+usuario+"' AND proyecto_fk IN ("+proyecto+")";
				consulta+=" WHERE estado_nombre IN ('ABIERTO','RE-ABIERTO') AND reportero_fk = '"+usuario+"'";
			}
			//Si es lider puede eliminar cualquier defectos cuyo estatus esten ABIERTO O REABIERTO del proyecto al que este pertenezca
			if(rol.equals("LIDER") && proyecto != null && !proyecto.equals("")){
				System.out.println("Entre como lider y un proyecto");
				consulta+=" WHERE estado_nombre IN ('ABIERTO','RE-ABIERTO') AND proyecto_fk IN ("+proyecto+")";
			}
			query = session.createSQLQuery(consulta)
					.setResultTransformer(Transformers.aliasToBean(Defectos.class));
			list = (List<Defectos>)query.list();
		} catch (HibernateException e) {
			System.out.println("Error obteniendo Defectos a modificar" +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}

	//Accion Validar defectos, obtiene una lista de los defectos con estado RESUELTO que pueden ser modificados para su validacion por el usuario que lo registro
	@Override
	public List<Defectos> getDefectosValidar(String usuario, String rol, String proyecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		String consulta = "SELECT * FROM View_Defectos ";
		Query query = null;
		System.out.println("defectos a validar");
		try {
			//Si es administrador puede validar cualquier defectos cuyo estatus esten RESUELTO de cualquier proyecto
			if(rol.equals("ADMINISTRADOR")){
				System.out.println("Entre como administrador ");
				query = session.createSQLQuery(consulta+" WHERE estado_nombre = 'RESUELTO' ")
						.setResultTransformer(Transformers.aliasToBean(Defectos.class));
			}
			//Si es funcional puede validar cualquier defectos cuyo estatus este RESUELTO de cualquier proyecto o el proyecto al que este pertenezca
			if(rol.equals("FUNCIONAL")){
				System.out.println("Entre como funcional y todos los proyectos "+ proyecto);
				consulta +=" WHERE estado_nombre = 'RESUELTO' AND reportero_fk = '"+usuario+"' AND proyecto_fk IN ("+proyecto+")";
				//consulta +=" WHERE estado_nombre = 'RESUELTO' AND reportero_fk = '"+usuario+"'";
				query = session.createSQLQuery(consulta)
						.setResultTransformer(Transformers.aliasToBean(Defectos.class));
			}
			list = (List<Defectos>)query.list();
		} catch (HibernateException e) {
			System.out.println("Error obteniendo Defectos a validar" +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
	//Accion Resolver defectos, obtiene una lista de los defectos con estado ASIGNADO que pueden ser resueltos por el usuario registrado
	@Override
	public List<Defectos> getDefectosAsignados(String usuario, String rol, String proyecto) {
		Session session = sessionFactory.openSession();
		String consulta = "SELECT * FROM View_Defectos ";
		List<Defectos> list = null;
		Query query = null;
		System.out.println("defectos a asignar");
		try {
			//Si es administrador puede resolver cualquier defectos cuyo estatus esten ASIGNADO de cualquier proyecto
			if(rol.equals("ADMINISTRADOR")){
				System.out.println("Entre como administrador ");
				consulta += "WHERE estado_nombre = 'ASIGNADO'";
				query = session.createSQLQuery(consulta)
						.setResultTransformer(Transformers.aliasToBean(Defectos.class));
			}
			//Si es funcional puede resolver cualquier defectos cuyo estatus este ASIGNADO de cualquier proyecto o el proyecto al que este pertenezca
			if(rol.equals("ANALISTA")){
				System.out.println("Entre como analista y un proyectos ");
				consulta += "WHERE estado_nombre = 'ASIGNADO' AND responsable_fk ='"+usuario+"' AND proyecto_fk IN ("+proyecto+")";
				query = session.createSQLQuery(consulta)
						.setResultTransformer(Transformers.aliasToBean(Defectos.class));
			}
			//Si es lider puede resolver cualquier defectos cuyo estatus esten ABIERTO O REABIERTO del proyecto al que este pertenezca
			if(rol.equals("LIDER")){
				System.out.println("Entre como lider y un proyectos ");
				consulta +="WHERE estado_nombre IN ('ABIERTO', 'RE-ABIERTO') AND proyecto_fk IN ("+proyecto+")";
				query = session.createSQLQuery(consulta)
						.setResultTransformer(Transformers.aliasToBean(Defectos.class));
			}
			list = (List<Defectos>)query.list();
		} catch (HibernateException e) {
			System.out.println("Error obteniendo Defectos a resolver" +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
	//Accion Asignar defectos, obtiene una lista de los defectos con estado ABIERTO O REABIERTO del proyecto al que el rol lider pertenezca	
	@Override
	public List<Defectos> getDefectosNoAsignados(String usuario, String rol, String proyecto) {
		Session session = sessionFactory.openSession();
		List<Defectos> list = null;
		String consulta = "SELECT * FROM View_Defectos ";
		Query query = null;
		//System.out.println("Usuario " +usuario + " perfil "+rol+" proyecto "+proyecto);
		try {
			//Si es administrador puede asignar cualquier defecto de cualquier proyecto
			if(rol.equals("ADMINISTRADOR")){
				System.out.println("Entre como administrador ");
				query = session.createSQLQuery(consulta +" WHERE estado_nombre IN ('ABIERTO', 'RE-ABIERTO') ")
						.setResultTransformer(Transformers.aliasToBean(Defectos.class));
			}
			if(rol.equals("LIDER") ){
				System.out.println("Entre lider y un proyecto ");
				consulta += " WHERE estado_nombre IN ('ABIERTO', 'RE-ABIERTO') AND proyecto_fk IN ("+proyecto+")";
				query = session.createSQLQuery(consulta).setResultTransformer(Transformers.aliasToBean(Defectos.class));
			}
			list = (List<Defectos>)query.list();
		} catch (HibernateException e) {
			System.out.println("Error obteniendo Defectos a asignar" +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<HistoricoEstados> getHistoricoEstados(Integer id) {
		Session session = sessionFactory.openSession();
		List<HistoricoEstados> list = null;
		Query query = null;
		try{
			query = session.createSQLQuery("SP_ObtenerHistoricoEstados :defecto_id")
					.setResultTransformer(Transformers.aliasToBean(HistoricoEstados.class))
					.setParameter("defecto_id", id);
			list = (List<HistoricoEstados>)query.list();
		} catch (HibernateException e) {
			System.out.println("Error obteniendo historico" +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
}
