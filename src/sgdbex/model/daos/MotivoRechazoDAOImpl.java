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

import sgdbex.model.pojos.MotivoRechazo;

@Component
@Repository("motivoRechazoDAO")
@SuppressWarnings("unchecked")
public class MotivoRechazoDAOImpl implements MotivoRechazoDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String createMotivos(MotivoRechazo motivo) {
		Session session = sessionFactory.openSession();
		List<MotivoRechazo> list = null;
		try {
			Query query = session.createSQLQuery("SP_CrearMotivos :nombre, :descripcion, :usuario_creador")
					.setResultTransformer(Transformers.aliasToBean(MotivoRechazo.class))
					.setParameter("nombre", motivo.getMotivo_nombre())
					.setParameter("descripcion", motivo.getMotivo_descripcion())
					.setParameter("usuario_creador", motivo.getMotivo_usuario_modificacion());
			list = (List<MotivoRechazo>)query.list();
			if(list.get(0).getMotivo_id() != 0){
				return "exito";
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl create Mot " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

	@Override
	public String updateMotivos(MotivoRechazo motivo) {
		Session session = sessionFactory.openSession();
		List<MotivoRechazo> list = null;
		try {
			Query query = session.createSQLQuery("SP_ActualizarMotivos :idMotivo, :nombre, :descripcion")
					.setResultTransformer(Transformers.aliasToBean(MotivoRechazo.class))
					.setParameter("idMotivo", motivo.getMotivo_id())
					.setParameter("nombre", motivo.getMotivo_nombre())
					.setParameter("descripcion", motivo.getMotivo_descripcion());
			list = (List<MotivoRechazo>)query.list();
			if(list.get(0).getMotivo_id().equals(motivo.getMotivo_id())){
				return "exito";
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl Update Mot " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

	@Override
	public String deleteMotivos(MotivoRechazo motivo) {
		Session session = sessionFactory.openSession();
		List<MotivoRechazo> list = null;
		try {
			Query query = session.createSQLQuery("SP_EliminarMotivos :idMotivo")
					.setResultTransformer(Transformers.aliasToBean(MotivoRechazo.class))
					.setParameter("idMotivo", motivo.getMotivo_id());
			list = (List<MotivoRechazo>)query.list();
			if(list.get(0).getMotivo_id().equals(motivo.getMotivo_id())){
				return "exito";
			}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl delete Mot " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

	@Override
	public List<MotivoRechazo> getMotivos() {
		Session session = sessionFactory.openSession();
		List<MotivoRechazo> list = null;
		try {
			Query query = session.createSQLQuery("SP_ListarMotivosRechazo").setResultTransformer(Transformers.aliasToBean(MotivoRechazo.class));
			list = (List<MotivoRechazo>)query.list();
		} catch (HibernateException e) {
			System.out.println("try getMotivos " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
	
}
