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

import sgdbex.model.pojos.*;


@Component
@Repository("impactosDAO")
public class ImpactosDAOImpl implements ImpactosDAO {
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
	public List<Impactos> obtenerImpactos(){
		Session session = sessionFactory.openSession();
		List<Impactos> list = null;
		try {
			Query query = session.createSQLQuery("SP_ListarImpactos").setResultTransformer(Transformers.aliasToBean(Impactos.class));
			list = (List<Impactos>)query.list();
		} catch (HibernateException e) {
			System.out.println("try Impl" +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
}
