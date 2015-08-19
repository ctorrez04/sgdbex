package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.Urgencias;

public interface UrgenciasDAO {
//	public void createUrgencia(Urgencias urgencia);
//
//	public void updateUrgencia(Urgencias urgencia);
//
//	public void deleteUrgencia(Urgencias urgencia);
	
	public List<Urgencias> obtenerUrgencias();

}
