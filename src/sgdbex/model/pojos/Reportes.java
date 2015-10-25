package sgdbex.model.pojos;

public class Reportes {
	String reporte;
	String asunto;
	String tipoReporte;
	String nombreproyecto;
	int cantidadAbiertos;
	int cantidadCerrados;
	int cantidadResueltos;
	int cantidadReabiertos;
	int cantidadAsignados;
	int total;
	int dias;
	int defectoid;
	
	public Reportes(){
		
	}
	
	public Reportes(String reporte, int cantidadAbiertos, int cantidadCerrados,
			int cantidadResueltos, int cantidadReabiertos,
			int cantidadAsignados, int total, String asunto, int dias, int defectoid, String tipoReporte, String nombreproyecto) {
		this.reporte = reporte;
		this.cantidadAbiertos = cantidadAbiertos;
		this.cantidadCerrados = cantidadCerrados;
		this.cantidadResueltos = cantidadResueltos;
		this.cantidadReabiertos = cantidadReabiertos;
		this.cantidadAsignados = cantidadAsignados;
		this.total = total;
		this.asunto= asunto;
		this.dias= dias;
		this.defectoid= defectoid;
		this.tipoReporte= tipoReporte;
		this.nombreproyecto= nombreproyecto;
	}
	
	public String getNombreproyecto() {
		return nombreproyecto;
	}

	public void setNombreproyecto(String nombreproyecto) {
		this.nombreproyecto = nombreproyecto;
	}
	
	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public int getDefectoid() {
		return defectoid;
	}

	public void setDefectoid(int defectoid) {
		this.defectoid = defectoid;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getReporte() {
		return reporte;
	}
	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	public int getCantidadAbiertos() {
		return cantidadAbiertos;
	}
	public void setCantidadAbiertos(int cantidadAbiertos) {
		this.cantidadAbiertos = cantidadAbiertos;
	}
	public int getCantidadCerrados() {
		return cantidadCerrados;
	}
	public void setCantidadCerrados(int cantidadCerrados) {
		this.cantidadCerrados = cantidadCerrados;
	}
	public int getCantidadResueltos() {
		return cantidadResueltos;
	}
	public void setCantidadResueltos(int cantidadResueltos) {
		this.cantidadResueltos = cantidadResueltos;
	}
	public int getCantidadReabiertos() {
		return cantidadReabiertos;
	}
	public void setCantidadReabiertos(int cantidadReabiertos) {
		this.cantidadReabiertos = cantidadReabiertos;
	}
	public int getCantidadAsignados() {
		return cantidadAsignados;
	}
	public void setCantidadAsignados(int cantidadAsignados) {
		this.cantidadAsignados = cantidadAsignados;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Reportes [reporte=" + reporte + ", cantidadAbiertos="
				+ cantidadAbiertos + ", cantidadCerrados=" + cantidadCerrados
				+ ", cantidadResueltos=" + cantidadResueltos
				+ ", cantidadReabiertos=" + cantidadReabiertos
				+ ", cantidadAsignados=" + cantidadAsignados + ", total="
				+ total + ", asunto=" + asunto +", dias=" + dias +", defectoid=" +defectoid + ", tipoReporte="+ tipoReporte +"]";
	}
}
