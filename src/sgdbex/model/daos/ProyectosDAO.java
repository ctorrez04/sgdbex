package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.Proyectos;

public interface ProyectosDAO {

	public String createProyectos(Proyectos Proyecto);

	public String updateProyectos(Proyectos Proyecto);

	public String deleteProyectos(Proyectos Proyecto);

	public List<Proyectos> getProyectos();

	public List<Proyectos> getProyectosPorUsuario(String rol, String carnet);
}
