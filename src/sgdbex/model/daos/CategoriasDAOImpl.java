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

import sgdbex.model.pojos.Categorias;
@SuppressWarnings("unchecked")
@Component
@Repository("categoriasDAO")
public class CategoriasDAOImpl implements CategoriasDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String createCategorias(Categorias Categoria) {
		Session session = sessionFactory.openSession();
		List<Categorias> list = null;
		try {
			Query query = session.createSQLQuery("SP_CrearCategorias :nombre, :descripcion, :usuario_creador")
					.setResultTransformer(Transformers.aliasToBean(Categorias.class))
					.setParameter("nombre", Categoria.getCategoria_nombre())
					.setParameter("descripcion", Categoria.getCategoria_descripcion())
					.setParameter("usuario_creador", Categoria.getCateg_usuario_modificacion());
			list = (List<Categorias>)query.list();
			if(list.get(0).getCategoria_id() != 0){
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
	public String updateCategorias(Categorias Categoria) {

		Session session = sessionFactory.openSession();
		List<Categorias> list = null;
		try {
			Query query = session.createSQLQuery("SP_ActualizarCategorias :idCategoria, :nombre, :descripcion")
					.setResultTransformer(Transformers.aliasToBean(Categorias.class))
					.setParameter("idCategoria", Categoria.getCategoria_id())
					.setParameter("nombre", Categoria.getCategoria_nombre())
					.setParameter("descripcion", Categoria.getCategoria_descripcion());
			list = (List<Categorias>)query.list();
			if(list.get(0).getCategoria_id().equals(Categoria.getCategoria_id())){
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
	public String deleteCategorias(Categorias Categoria) {
		Session session = sessionFactory.openSession();
		List<Categorias> list = null;
		try {
			Query query = session.createSQLQuery("SP_EliminarCategorias :idCategoria")
					.setResultTransformer(Transformers.aliasToBean(Categorias.class))
					.setParameter("idCategoria", Categoria.getCategoria_id());
			list = (List<Categorias>)query.list();
			if(list.get(0).getCategoria_id().equals(Categoria.getCategoria_id())){
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
	public List<Categorias> getCategorias() {
		Session session = sessionFactory.openSession();
		List<Categorias> list = null;
		try {
			Query query = session.createSQLQuery("SP_ListarCategorias").setResultTransformer(Transformers.aliasToBean(Categorias.class));
			list = (List<Categorias>)query.list();
		} catch (HibernateException e) {
			System.out.println("try Impl Categoria " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String validarCategoriaUnico(String categoria) {
		Session session = sessionFactory.openSession();
		List<Categorias> list = null;
		try {
			Query query = session.createSQLQuery("SP_BuscarCategoriaPorNombre :categoria")
					.setResultTransformer(Transformers.aliasToBean(Categorias.class))
					.setParameter("categoria", categoria);
			list = (List<Categorias>)query.list();
			if(list.size()>0)
				return list.get(0).getCategoria_descripcion();
			return "";
		} catch (HibernateException e) {
			System.out.println("try Impl Categoria " +e.getMessage() );
			return "";
		} finally {
			session.close();
		}
	}

}
