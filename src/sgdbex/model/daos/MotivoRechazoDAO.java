package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.MotivoRechazo;

public interface MotivoRechazoDAO {
	
	public String createMotivos(MotivoRechazo motivo);

	public String updateMotivos(MotivoRechazo motivo);

	public String deleteMotivos(MotivoRechazo motivo);

	public List<MotivoRechazo> getMotivos();

	public String validarMotivoUnico(String motivo);
}
