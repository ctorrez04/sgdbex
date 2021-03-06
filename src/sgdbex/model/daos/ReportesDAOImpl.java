package sgdbex.model.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Repository;

import sgdbex.model.pojos.Reportes;
@SuppressWarnings("unchecked")
@Repository("reportesDAO")
public class ReportesDAOImpl implements ReportesDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
		
	@Override
	public List<Reportes> getReportesEstado() {
		Session session = sessionFactory.openSession();
		List<Reportes> list = null;
		try {
			Query query = session.createSQLQuery("SP_ReportesEstados")
					.setResultTransformer(Transformers.aliasToBean(Reportes.class));
			list = (List<Reportes>)query.list();
			//System.out.println("Defectos loaded " + list.get(0).toString());
			return list;
		} catch (HibernateException e) {
			System.out.println("try Impl Defc " +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reportes> getReportesMayorTiempoAbierto(String proyecto) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<Reportes> list = null;
		try {
			Query query = session.createSQLQuery("SP_ReporteDefectosTiempoAbiertos")
					.setResultTransformer(Transformers.aliasToBean(Reportes.class));
			list = (List<Reportes>)query.list();
			//System.out.println("Defectos loaded MayorTiempoAbierto" + list.get(0).toString());
			return list;
		} catch (HibernateException e) {
			System.out.println("try Impl Defc " +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reportes> getReportesFechaDias() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<Reportes> list = null;
		List<Reportes> list2 = null;
		List<Number> valordias = new ArrayList<Number>();
		valordias.add(1);
		valordias.add(2);
		valordias.add(3);
		valordias.add(7);
		valordias.add(30);
		valordias.add(60);
		valordias.add(90);
		valordias.add(180);
		valordias.add(365);
		
		try {
			Query query = session.createSQLQuery("SP_ReporteFechaDiasEstadoAbierto")
					.setResultTransformer(Transformers.aliasToBean(Reportes.class));
			list = (List<Reportes>)query.list();
			//System.out.println("Defectos loaded " + list.get(0).toString());
			
			Query query2 = session.createSQLQuery("SP_ReportesFechaDiasEstadoResuelto")
					.setResultTransformer(Transformers.aliasToBean(Reportes.class));
			list2 = (List<Reportes>)query2.list();
			//System.out.println("Defectos loaded " + list2.get(0).toString());
			
			
			for(int i=0;i<list.size();i++){
				list.get(i).setCantidadResueltos(list2.get(i).getCantidadResueltos());
				list.get(i).setDefectoid((Integer) valordias.get(i));
			}
			//System.out.println("Defectos loaded complete " + list.get(0).toString());
			
			return list;
			
		} catch (HibernateException e) {
			System.out.println("try Impl Defc " +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reportes> getReportesCategoria() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<Reportes> list = null;
		try {
			Query query = session.createSQLQuery("SP_ReportesCategoria")
					.setResultTransformer(Transformers.aliasToBean(Reportes.class));
			list = (List<Reportes>)query.list();
			//System.out.println("Defectos loaded " + list.get(0).toString());
			return list;
		} catch (HibernateException e) {
			System.out.println("try Impl Defc " +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reportes> getReportesPrioridad() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<Reportes> list = null;
		try {
			Query query = session.createSQLQuery("SP_ReportesPrioridad")
					.setResultTransformer(Transformers.aliasToBean(Reportes.class));
			list = (List<Reportes>)query.list();
			//System.out.println("Defectos loaded " + list.get(0).toString());
			return list;
		} catch (HibernateException e) {
			System.out.println("try Impl Defc " +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reportes> getReportesEstadisticasUsuarios() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<Reportes> list = null;
		try {
			Query query = session.createSQLQuery("SP_ReportesEstadisticasUsuarios")
					.setResultTransformer(Transformers.aliasToBean(Reportes.class));
			list = (List<Reportes>)query.list();
			//System.out.println("Defectos loaded " + list.get(0).toString());
			return list;
		} catch (HibernateException e) {
			System.out.println("try Impl Defc " +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reportes> getReportesEstadisticasReporteros() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<Reportes> list = null;
		try {
			Query query = session.createSQLQuery("SP_ReportesEstadisticasReporteros")
					.setResultTransformer(Transformers.aliasToBean(Reportes.class));
			list = (List<Reportes>)query.list();
			//System.out.println("Defectos loaded " + list.get(0).toString());
			return list;
		} catch (HibernateException e) {
			System.out.println("try Impl Defc " +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reportes> getReportesReporterosResolucion() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<Reportes> list = null;
		try {
			Query query = session.createSQLQuery("SP_ReportesFuncionalesResolucion")
					.setResultTransformer(Transformers.aliasToBean(Reportes.class));
			list = (List<Reportes>)query.list();
			//System.out.println("Defectos loaded " + list.get(0).toString());
			if(list.isEmpty())System.out.println("la lista SP_ReportesFuncionalesResolucion esta vacia");
			System.out.println("tamano de lista SP_ReportesFuncionalesResolucion: "+list.size());
			return list;
		} catch (HibernateException e) {
			System.out.println("try Impl Defc " +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reportes> getReportesUsuariosResolucion() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<Reportes> list = null;
		try {
			Query query = session.createSQLQuery("SP_ReportesUsuariosResolucion")
					.setResultTransformer(Transformers.aliasToBean(Reportes.class));
			list = (List<Reportes>)query.list();
			//System.out.println("Defectos loaded " + list.get(0).toString());
			return list;
		} catch (HibernateException e) {
			System.out.println("try Impl Defc " +e.getMessage() );
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reportes> getReportesMasActivos() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
