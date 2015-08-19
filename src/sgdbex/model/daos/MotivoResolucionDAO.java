package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.MotivoResolucion;

public interface MotivoResolucionDAO {
	
	public String createMotivos(MotivoResolucion motivo);

	public String updateMotivos(MotivoResolucion motivo);

	public String deleteMotivos(MotivoResolucion motivo);

	public List<MotivoResolucion> getMotivos();
}
