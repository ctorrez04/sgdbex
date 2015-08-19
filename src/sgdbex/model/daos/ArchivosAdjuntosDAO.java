package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.ArchivosAdjuntos;

public interface ArchivosAdjuntosDAO {

	public void borrarArchivos_Adjuntos(List<ArchivosAdjuntos> Lista_Archivos_Adjuntos);
	
	public List<ArchivosAdjuntos> obtenerArchivos_Adjuntos();
}
