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

import sgdbex.model.pojos.Estados;

@Component
@Repository("estadosDAO")
@SuppressWarnings("unchecked")
public class EstadosDAOImpl implements EstadosDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Estados> obtenerEstados() {
		Session session = sessionFactory.openSession();
		List<Estados> list = null;
		try {
			Query query = session.createSQLQuery("SP_ListarEstados").setResultTransformer(Transformers.aliasToBean(Estados.class));
			list = (List<Estados>)query.list();
		} catch (HibernateException e) {
			System.out.println("try Impl Estados " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}

}
