package sgdbex.model.daos;

import java.util.List;

import sgdbex.model.pojos.Categorias;

public interface CategoriasDAO {

	public String createCategorias(Categorias Categoria);

	public String updateCategorias(Categorias Categoria);

	public String deleteCategorias(Categorias Categoria);

	public List<Categorias> getCategorias();
}
