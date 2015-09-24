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

import sgdbex.model.pojos.Correo;

@Component
@Repository("correoDAO")
@SuppressWarnings("unchecked")
public class CorreoDAOImpl implements CorreoDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Correo enviarNotificacionCreacion(Integer defecto) throws Exception {
		System.out.println("defecto:"+defecto);
		Session session = sessionFactory.openSession();
		Correo correo=null;
		List<Correo> list = null;
		try {
			Query query = session.createSQLQuery("SP_EnviarEmailCreacionDefecto :idDefecto")
					.setResultTransformer(Transformers.aliasToBean(Correo.class))
					.setParameter("idDefecto", defecto);
			list = (List<Correo>)query.list();
			System.out.println("\n\n Correo: " +list.get(0).toString());
			System.out.println("Para: " +list.get(0).getPara());
			System.out.println("Aunto: " +list.get(0).getAsunto());
			System.out.println("Cuerpo: " +list.get(0).getCuerpo());
			System.out.println("Encab: " +list.get(0).getEncabezado());
			System.out.println("Firma " +list.get(0).getFirma());
			correo=list.get(0);
			return correo;
		} catch (HibernateException e) {
			System.out.println("Error al crear el cuerpo del Correo" +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}
	
	@Override
	public Correo enviarNotificacionActualizacion(Integer defecto) throws Exception {
		System.out.println("defecto:"+defecto);
		Session session = sessionFactory.openSession();
		Correo correo=null;
		List<Correo> list = null;
		try {
			Query query = session.createSQLQuery("SP_EnviarEmailActualizacionDefecto :idDefecto")
					.setResultTransformer(Transformers.aliasToBean(Correo.class))
					.setParameter("idDefecto", defecto);
			list = (List<Correo>)query.list();
			System.out.println("\n\n Correo: " +list.get(0).toString());
			System.out.println("Para: " +list.get(0).getPara());
			System.out.println("Aunto: " +list.get(0).getAsunto());
			System.out.println("Cuerpo: " +list.get(0).getCuerpo());
			System.out.println("Encab: " +list.get(0).getEncabezado());
			System.out.println("Firma " +list.get(0).getFirma());
			correo=list.get(0);
			return correo;
		} catch (HibernateException e) {
			System.out.println("Error al crear el cuerpo del Correo" +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}
}
