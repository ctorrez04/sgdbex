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

import sgdbex.model.pojos.Usuarios;

@Component
@Repository("usuariosDAO")
@SuppressWarnings("unchecked")
public class UsuariosDAOImpl implements UsuariosDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Integer sistema = 19003;
	
	public Integer getSistema() {
		return sistema;
	}

	public void setSistema(Integer sistema) {
		this.sistema = sistema;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	@Override
	public String createUsuarios(Usuarios usuario) {
		Session session = sessionFactory.openSession();
		List<Usuarios> list = null;
		try {
			Query query = session.createSQLQuery("SP_CrearUsuarios :nombre, :carnet, :correo, :departamento, :telefono, :proyecto_id, :rol_id ")
					.setResultTransformer(Transformers.aliasToBean(Usuarios.class))
					.setParameter("nombre", usuario.getNombre_completo())
					.setParameter("carnet", usuario.getCarnet())
					.setParameter("correo", usuario.getCorreo_electronico())
					.setParameter("departamento", usuario.getDepartamento())
					.setParameter("telefono", usuario.getTelefono())
					.setParameter("proyecto_id", usuario.getUsuario_proyecto_fk())
					.setParameter("rol_id", usuario.getUsuario_rol_fk());
			list = (List<Usuarios>)query.list();
			if(list.get(0).getUsuario_id() != 0){
				return "exito";
				}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("No se pudo crear usuario " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}
	}

	@Override
	public String updateUsuarios(Usuarios usuario) {
		Session session = sessionFactory.openSession();
		List<Usuarios> list = null;
		try {
			Query query = session.createSQLQuery("SP_ActualizarUsuarios :usuario_id, :nombre, :carnet, :correo, :departamento, :telefono, :proyecto_id, :rol_id")
					.setResultTransformer(Transformers.aliasToBean(Usuarios.class))
//					.setResultSetMapping(output)
					.setParameter("usuario_id", usuario.getUsuario_id())
					.setParameter("nombre", usuario.getNombre_completo())
					.setParameter("carnet", usuario.getCarnet())
					.setParameter("correo", usuario.getCorreo_electronico())
					.setParameter("carnet", usuario.getDepartamento())
					.setParameter("telefono", usuario.getTelefono())
					.setParameter("proyecto_id", usuario.getUsuario_proyecto_fk())
					.setParameter("rol_id", usuario.getUsuario_rol_fk());
			list = (List<Usuarios>)query.list();
			if(list.get(0).getUsuario_id().equals(usuario.getUsuario_id())){
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
	public String deleteUsuarios(Usuarios usuario) {
		Session session = sessionFactory.openSession();
		List<Usuarios> list = null;
		try {
			Query query = session.createSQLQuery("SP_EliminarUsuarios :idUsuario")
					.setResultTransformer(Transformers.aliasToBean(Usuarios.class))
					.setParameter("idUsuario", usuario.getUsuario_id());
			list = (List<Usuarios>)query.list();
			if(list.get(0).getUsuario_id().equals(usuario.getUsuario_id())){
				return "exito";
				}
			return "fallo";
		} catch (HibernateException e) {
			System.out.println("try Impl delete user " +e.getMessage() );
			return "fallo";
		} finally {
			session.close();
		}	
	}

	@Override
	public List<Usuarios> getUsuarios() {
		Session session = sessionFactory.openSession();
		List<Usuarios> list = null;
		try {
			Query query = session.createSQLQuery("SP_ListarUsuarios").setResultTransformer(Transformers.aliasToBean(Usuarios.class));
			list = (List<Usuarios>)query.list();
		} catch (HibernateException e) {
			System.out.println("try Impl Usuarios " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Usuarios> getUsuariosPorProyecto(String proyecto) {
		Session session = sessionFactory.openSession();
		List<Usuarios> list = null;
		try {
			Query query = session.createSQLQuery("SP_ListarUsuariosPorProyecto :proyecto").setResultTransformer(Transformers.aliasToBean(Usuarios.class))
						  .setParameter("proyecto", proyecto);
			list = (List<Usuarios>)query.list();
		} catch (HibernateException e) {
			System.out.println("try Impl Usuarios Error nvarchar " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Usuarios> getUsuariosLider() {
		Session session = sessionFactory.openSession();
		List<Usuarios> list = null;
		String rol="LIDER";
		try {
			Query query = session.createSQLQuery("SP_ListarUsuariosPorRol :rol, :sistema").setResultTransformer(Transformers.aliasToBean(Usuarios.class))
						  .setParameter("rol", rol)
						  .setParameter("sistema", getSistema());
			list = (List<Usuarios>)query.list();
		} catch (HibernateException e) {
			System.out.println("try Impl Usuarios " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Usuarios> getUsuariosAnalistas() {
		Session session = sessionFactory.openSession();
		List<Usuarios> list = null;
		String rol="ANALISTA";
		try {
			Query query = session.createSQLQuery("SP_ListarUsuariosPorRol :rol, :sistema").setResultTransformer(Transformers.aliasToBean(Usuarios.class))
						  .setParameter("rol", rol)
					      .setParameter("sistema", getSistema());
			list = (List<Usuarios>)query.list();
		} catch (HibernateException e) {
			System.out.println("try Impl Usuarios " +e.getMessage() );
		} finally {
			session.close();
		}
		return list;
	}
}
