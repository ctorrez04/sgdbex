package sgdbex.model.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import sgdbex.model.pojos.ArchivosAdjuntos;

@Component
@Repository("archivos_AdjuntosDAO")
public class ArchivosAdjuntosDAOImpl implements ArchivosAdjuntosDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void borrarArchivos_Adjuntos(List<ArchivosAdjuntos> Lista_Archivos_Adjuntos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArchivosAdjuntos> obtenerArchivos_Adjuntos() {
		Session session = sessionFactory.openSession();
		List<ArchivosAdjuntos> list = null;
		System.out.println("getAll Archivos juntos");
		try {			
//			Query query = session.createSQLQuery("SP_ListarArchivos").addEntity(Archivos_Adjuntos.class);
			System.out.println("Hice consulta lacra");
//			list = (List<Prioridades>)query.list();
//			System.out.println("Tamaño de la lista queryUSer" + queryUser.list().size());
		} catch (HibernateException e) {
			System.out.println("try Impl" +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}

}
