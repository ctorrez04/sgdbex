package sgdbex.model.daos;

import sgdbex.model.pojos.Correo;

public interface CorreoDAO {
	public Correo enviarNotificacionCreacion(Integer defecto) throws Exception;

	public Correo enviarNotificacionActualizacion(Integer defecto) throws Exception;
}
