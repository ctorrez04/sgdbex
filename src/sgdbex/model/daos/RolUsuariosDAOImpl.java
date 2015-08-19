package sgdbex.model.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import sgdbex.model.pojos.RolUsuarios;

@Component
@Repository("rol_UsuariosDAO")
@SuppressWarnings("unchecked")
public class RolUsuariosDAOImpl implements RolUsuariosDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<RolUsuarios> obtenerRolesUsuarios() {
		Session session = sessionFactory.openSession();
		List<RolUsuarios> list = null;
		try {			
			Query query = session.createSQLQuery("SP_ListarRoles").addEntity(RolUsuarios.class);
			list = (List<RolUsuarios>)query.list();
		} catch (HibernateException e) {
			System.out.println("try Impl Roles " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}

}
