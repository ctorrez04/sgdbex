package sgdbex.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sgdbex.services.GeneralServices;

@ManagedBean 
@Service
public class BusquedaMB {
	
	@Autowired
	private GeneralServices gs;
    @Autowired
	private Menu m;

	private String buscar;
	
    public GeneralServices getGs() {
		return gs;
	}

	public void setGs(GeneralServices gs) {
		this.gs = gs;
	}

	public Menu getM() {
		return m;
	}

	public void setM(Menu m) {
		this.m = m;
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}
	public void search(ComponentSystemEvent event){
		System.out.println("\n\na buscar "+getBuscar());
		gs.buscarDefectos(getBuscar(),todosProyectos(), todosCategorias(), todasPrioridad());
	}
	private String todosProyectos(){
		String proyectos ="";
		for(int i=0;i<m.getProyectosUsuarioList().size();i++){
			proyectos+=m.getProyectosUsuarioList().get(i).getProyecto_id()+",";
		}
		return proyectos;
	}
	private String todosCategorias(){
		String categorias ="";
		for(int i=0;i<gs.getCategorias().size();i++){
			categorias+=gs.getCategorias().get(i).getCategoria_id()+",";
		}
		return categorias;
	}
	private String todasPrioridad(){
		String prioridades ="";
		for(int i=0;i<gs.obtenerPrioridades().size();i++){
			prioridades+=gs.obtenerPrioridades().get(i).getPrioridad_id()+",";
		}
		return prioridades;
	}
}
