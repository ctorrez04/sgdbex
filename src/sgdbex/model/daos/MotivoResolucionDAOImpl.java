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

import sgdbex.model.pojos.MotivoResolucion;

@Component
@Repository("motivoResolucionDAO")
@SuppressWarnings("unchecked")
public class MotivoResolucionDAOImpl implements MotivoResolucionDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String createMotivos(MotivoResolucion motivo) {
		Session session = sessionFactory.openSession();
		List<MotivoResolucion> list = null;
		try {
			Query query = session.createSQLQuery("SP_CrearMotivos :nombre, :descripcion, :usuario_creador")
					.setResultTransformer(Transformers.aliasToBean(MotivoResolucion.class))
					.setParameter("nombre", motivo.getMotivo_nombre())
					.setParameter("descripcion", motivo.getMotivo_descripcion())
					.setParameter("usuario_creador", motivo.getMotivo_usuario_modificacion());
			list = (List<MotivoResolucion>)query.list();
			if(list.get(0).getMotivo_id() != 0){
				return "exito";
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl Cat " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

	@Override
	public String updateMotivos(MotivoResolucion motivo) {
		Session session = sessionFactory.openSession();
		List<MotivoResolucion> list = null;
		try {
			Query query = session.createSQLQuery("SP_ActualizarMotivos :idMotivo, :nombre, :descripcion")
					.setResultTransformer(Transformers.aliasToBean(MotivoResolucion.class))
					.setParameter("idMotivo", motivo.getMotivo_id())
					.setParameter("nombre", motivo.getMotivo_nombre())
					.setParameter("descripcion", motivo.getMotivo_descripcion());
			list = (List<MotivoResolucion>)query.list();
			if(list.get(0).getMotivo_id().equals(motivo.getMotivo_id())){
				return "exito";
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl Cat " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

	@Override
	public String deleteMotivos(MotivoResolucion motivo) {
		Session session = sessionFactory.openSession();
		List<MotivoResolucion> list = null;
		try {
			Query query = session.createSQLQuery("SP_EliminarMotivos :idMotivo")
					.setResultTransformer(Transformers.aliasToBean(MotivoResolucion.class))
					.setParameter("idMotivo", motivo.getMotivo_id());
			list = (List<MotivoResolucion>)query.list();
			if(list.get(0).getMotivo_id().equals(motivo.getMotivo_id())){
				return "exito";
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl delete categ " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

	@Override
	public List<MotivoResolucion> getMotivos() {
		Session session = sessionFactory.openSession();
		List<MotivoResolucion> list = null;
		try {
			Query query = session.createSQLQuery("SP_ListarMotivosResolucion").setResultTransformer(Transformers.aliasToBean(MotivoResolucion.class));
			list = (List<MotivoResolucion>)query.list();
		} catch (HibernateException e) {
			System.out.println("try getMotivos " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
	
}
