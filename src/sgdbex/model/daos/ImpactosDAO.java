package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.Impactos;

public interface ImpactosDAO {
//	public void createImpacto(Impactos impacto);
//
//	public void updateImpacto(Impactos impacto);
//
//	public void deleteImpacto(Impactos impacto);
	
	public List<Impactos> obtenerImpactos();

}
