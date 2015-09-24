package sgdbex.model.pojos;

import java.sql.Clob;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Correo {
	private Clob para;
	private Clob asunto;
	private Clob encabezado;
	private Clob cuerpo;
	private Clob firma;
	
	public String getPara() throws Exception {
		return para.getSubString(1, (int) para.length());
	}
	public void setPara(Clob para) {
		this.para = para;
	}
	public String getAsunto() throws Exception {
		return asunto.getSubString(1, (int) asunto.length());
	}
	public void setAsunto(Clob asunto) {
		this.asunto = asunto;
	}
	public String getEncabezado() throws Exception {
		return encabezado.getSubString(1, (int) encabezado.length());
	}
	public void setEncabezado(Clob encabezado) {
		this.encabezado = encabezado;
	}
	public String getCuerpo() throws Exception {
		return cuerpo.getSubString(1, (int) cuerpo.length());
	}
	public void setCuerpo(Clob cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getFirma() throws Exception {
		return firma.getSubString(1, (int) firma.length());
	}
	public void setFirma(Clob firma) {
		this.firma = firma;
	}
	@Override
	public String toString() {
		try {
			return "Correo [para=" + getPara() + ", asunto=" + getAsunto() + ", encabezado="
					+ getEncabezado() + ", cuerpo=" + getCuerpo() + ", firma=" + getFirma() + "]";
		} catch (Exception e) {
			return "algiun error";
		}
	}
		
}