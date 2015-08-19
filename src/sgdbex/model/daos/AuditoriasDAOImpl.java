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

import sgdbex.model.pojos.Auditoria;

@Component
@Repository("AuditoriasDAO")
public class AuditoriasDAOImpl implements AuditoriasDAO{

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
	public void insertarAuditoria(Auditoria auditoria){
		Session session = sessionFactory.openSession();
		List<Auditoria> list = null;
		try {
			Query query = session.createSQLQuery("SP_InsertarAuditoria :carnet, :direccionIp, :modulo,"
					+ " :entidad, :accion, :descripcion, :resultado")
					.setResultTransformer(Transformers.aliasToBean(Auditoria.class))
					.setParameter("carnet", auditoria.getCarnet())
					.setParameter("direccionIp", auditoria.getDireccionIp())
					.setParameter("modulo", auditoria.getModulo())
					.setParameter("entidad", auditoria.getEntidad())
					.setParameter("accion", auditoria.getAccion())
					.setParameter("descripcion", auditoria.getDescripcion())
					.setParameter("resultado", auditoria.getResultado());
			list = (List<Auditoria>)query.list();
			System.out.println("Insertar auditoria " + list.get(0).toString());
		} catch (HibernateException e) {
			System.out.println("tryInsertar auditoria" +e.getMessage() );
		} finally {
			session.close();
		}		
	}
}
