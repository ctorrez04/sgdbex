package beasa.generales;

import java.rmi.RemoteException;

import beca.correo.Cartero;
import beca.correo.CarteroProxy;



public class CargarCorreo {
	
	public void enviar(String smtp_address,String de,String para,String copia,String copiaOculta,
						String asunto,int formato,String mensaje,int prioridad){
//		System.out.println("ip "+ip+" usuario "+ usuario+" contrasena "+clave+" aplicacion "+sistema);		
		CarteroProxy proxy = new CarteroProxy();
		Cartero servicio = proxy.getCartero();
		try {
			servicio.enviar(smtp_address, de, para, copia, copiaOculta, asunto, formato, mensaje, prioridad);
			System.out.println("Resultado Correo ");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
