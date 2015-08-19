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
		return null;
	}

	@Override
	public String updateMotivos(MotivoResolucion motivo) {
		return null;
	}

	@Override
	public String deleteMotivos(MotivoResolucion motivo) {
		return null;
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
