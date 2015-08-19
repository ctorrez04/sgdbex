package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.Prioridades;


public interface PrioridadesDAO {

//	public void createPrioridad(Prioridades prioridad);
//
//	public void updatePrioridad(Prioridades prioridad);
//
//	public void deletePrioridad(List<Prioridades> prioridades);
	
	public List<Prioridades> obtenerPrioridades();
	
}
