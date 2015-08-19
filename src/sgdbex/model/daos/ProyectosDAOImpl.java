package sgdbex.model.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import sgdbex.model.pojos.Proyectos;

@Component
@Repository("proyectosDAO")

public class ProyectosDAOImpl implements ProyectosDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String createProyectos(Proyectos Proyecto) {
		Session session = sessionFactory.openSession();
		List<Proyectos> list = null;
		try {
			Query query = session.createSQLQuery("SP_CrearProyectos :nombre, :descripcion, :idLider, :usuarioCreacion")
					.setResultTransformer(Transformers.aliasToBean(Proyectos.class))
					.setParameter("nombre", Proyecto.getProyecto_nombre())
					.setParameter("descripcion", Proyecto.getProyecto_descripcion())
					.setParameter("idLider", Proyecto.getProyecto_lider())
					.setParameter("usuarioCreacion", Proyecto.getProy_usuario_modificacion());
			list = (List<Proyectos>)query.list();
			if(list.get(0).getProyecto_id() != 0){
				return "exito";	
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl Proy " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String updateProyectos(Proyectos Proyecto) {
		
		System.out.println("llegue primero" );
		Session session = sessionFactory.openSession();
		List<Proyectos> list = null;
		try {
			Query query = session.createSQLQuery("SP_ActualizarProyectos :idProyecto, :nombre, :descripcion, :idLider, :usuarioModificador")
					.setResultTransformer(Transformers.aliasToBean(Proyectos.class))
					.setParameter("idProyecto", Proyecto.getProyecto_id())
					.setParameter("nombre", Proyecto.getProyecto_nombre())
					.setParameter("descripcion", Proyecto.getProyecto_descripcion())
					.setParameter("idLider", Proyecto.getProyecto_lider())
					.setParameter("usuarioModificador", Proyecto.getProy_usuario_modificacion());
			list = (List<Proyectos>)query.list();
			if(list.get(0).getProyecto_id().equals(Proyecto.getProyecto_id())){
				return "exito";	
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl Proy " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public String deleteProyectos(Proyectos Proyecto) {
		Session session = sessionFactory.openSession();
		List<Proyectos> list = null;
		try {
			Query query = session.createSQLQuery("SP_EliminarProyectos :idProyecto")
					.setResultTransformer(Transformers.aliasToBean(Proyectos.class))
					.setParameter("idProyecto", Proyecto.getProyecto_id());
			list = (List<Proyectos>)query.list();
			if(list.get(0).getProyecto_id().equals(Proyecto.getProyecto_id())){
				return "exito";	
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl Proy " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proyectos> getProyectos() {
		Session session = sessionFactory.openSession();
		List<Proyectos> list = null;
		try {
			Query query = session.createSQLQuery("SP_ListarProyectos").setResultTransformer(Transformers.aliasToBean(Proyectos.class));
			list = (List<Proyectos>)query.list();
		} catch (HibernateException e) {
			System.out.println("try Impl Proy " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Proyectos> getProyectosPorUsuario(String rol, String carnet) {
		Session session = sessionFactory.openSession();
		List<Proyectos> list = null;
		System.out.println("get user proyects");
		try {
			Query query = session.createSQLQuery("SP_ObtenerProyectosPorUsuario :carnet, :rol")
						.setResultTransformer(Transformers.aliasToBean(Proyectos.class))
						.setParameter("carnet", carnet)
						.setParameter("rol", rol);
			list = (List<Proyectos>)query.list();

		} catch (HibernateException e) {
			System.out.println("try Impl Proy " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}

}
