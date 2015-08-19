package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.Usuarios;

public interface UsuariosDAO {
	public String createUsuarios(Usuarios usuario);

	public String updateUsuarios(Usuarios usuario);

	public String deleteUsuarios(Usuarios usuario);
	
	public List<Usuarios> getUsuarios();
	
	public List<Usuarios> getUsuariosPorProyecto(String proyecto);
	
	public List<Usuarios> getUsuariosLider();
	
	public List<Usuarios> getUsuariosAnalistas();
}
