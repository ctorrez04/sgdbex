package beasa.generales;

import java.io.StringReader;
import java.util.Hashtable;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class CargarUsuario {

		private String nombre       = null;
		private String carnet       = null;
		private String email        = null;
		private String departamento = null;
		
		private String perfil       = null;
		@SuppressWarnings("rawtypes")
		private Hashtable modulos   = new Hashtable();
		private Document doc        = null;

		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  Se carga el archivo XML de la respuesta del WS
		
		Parametros:
		  cadena            la cadena del XML
		
		Valor de retorno:
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/	
		public void cargar(String cadena) {
		
			try {
				
				/*SE CARGA EL ARCHIVO XML*/
				
				/*Leer con String XML*/
			    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			    InputSource file = new InputSource();
			    file.setCharacterStream(new StringReader(cadena));
			    doc = db.parse(file);
				doc.getDocumentElement().normalize();
				
				/*Leer con Archivo XML*/
				/*File file = new File(cadena);			
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				doc = db.parse(file);
				doc.getDocumentElement().normalize();*/
				/**************************************/				
				
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  Se inicializa el objeto con los datos del usuario
		
		Parametros:
		
		Valor de retorno:
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/
		public void inicializar(){
			
			Element elem = null;
			Node one = null;
			
			/* SE REGISTRAN LOS DATOS DEL USUARIO */
			NodeList datos = doc.getElementsByTagName("datos");									
			one = datos.item(0);
			elem = (Element) one;			
			registrarDatos(elem);
			/**************************************/
			
			/* SE REGISTRAN LOS DATOS DEL PERFIL */
			NodeList perfil = doc.getElementsByTagName("perfil");									
			one = perfil.item(0);
			elem = (Element) one;			
			registrarPerfil(elem);	
			/**************************************/		
		}
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  Se descompone el XML y se registran los datos
		
		Parametros:
		  _e            Nodo completo de los datos del usuario
		
		Valor de retorno:
		  
		Autor:
		  Alexander Valera
		  
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/
		private void registrarDatos(Element _e){
			
			NodeList listado = null;
			Element elem = null;
				
			try {		
				listado      = _e.getElementsByTagName("nombre");
				elem         = (Element) listado.item(0);
				nombre       = elem.getFirstChild().getNodeValue().toString();
					
				listado      = _e.getElementsByTagName("carnet");
				elem         = (Element) listado.item(0);
				carnet       = elem.getFirstChild().getNodeValue().toString();
				
				listado      = _e.getElementsByTagName("email");
				elem         = (Element) listado.item(0);
				email        = elem.getFirstChild().getNodeValue().toString();
				
				listado      = _e.getElementsByTagName("departamento");
				elem         = (Element) listado.item(0);
				departamento = elem.getFirstChild().getNodeValue().toString();
				
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  Se registra el perfil del usuario
		
		Parametros:
		  _e               nodo completo con los datos del perfil
		
		Valor de retorno:
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void registrarPerfil(Element _e){
				
			NodeList listado = null;
			Element elem = null;
			Hashtable mod = new Hashtable();
			Element aux = null;
				
			try {		
				listado      = _e.getElementsByTagName("nombrePerfil");
				elem         = (Element) listado.item(0);
				perfil       = elem.getFirstChild().getNodeValue().toString();
			} catch (Exception e) {
				//e.printStackTrace();
			}	
			
			try {		
				NodeList modulosList = _e.getElementsByTagName("modulo");
				
				for(int i=0; i<modulosList.getLength(); i++){				
					
					/*MODULO ACTUAL*/
					elem = (Element)modulosList.item(i);
					mod = new Hashtable();				
					
					/*SE CARGAN LOS DATOS DESCRIPTIVOS DEL MODULO*/
					listado = elem.getElementsByTagName("identificador");
					aux = (Element) listado.item(0);
					mod.put("IDENTIFICADOR", aux.getFirstChild().getNodeValue().toString());
					
					listado = elem.getElementsByTagName("nombre");
					aux = (Element) listado.item(0);
					mod.put("NOMBRE", aux.getFirstChild().getNodeValue().toString());
					/***********************************************/
					
					/*SE CARGAN LAS ACCIONES PRINCIPALES*/
					listado = elem.getElementsByTagName("permisos");
					aux = (Element)listado.item(0);
					if(aux.getParentNode().getNodeName().toString().compareToIgnoreCase("modulo")==0){
					    //System.out.println("Acciones: ");
						ArrayList acciones = cargarAcciones(aux);					
						mod.put("ACCIONES", acciones);
						//System.out.println("Acciones Total: " + ((ArrayList)mod.get("ACCIONES")).size());
					}else{
						mod.put("ACCIONES", new ArrayList());
					}				
					/***************************************/
					
					/*SE CARGA EL NIVEL 1*/				
					NodeList listadoN1 = elem.getElementsByTagName("nivel1");
					
					if(listadoN1.getLength() > 0){
						Hashtable nivel1 = new Hashtable();
						for(int j=0; j<listadoN1.getLength(); j++){					
							Element N1 = (Element)listadoN1.item(j);
							Hashtable resN1 = cargarNivel(N1,"nivel1");
							String identificadorN1 = (String)resN1.get("IDENTIFICADOR").toString().toUpperCase();
							nivel1.put(identificadorN1, resN1);
							
							/*SE CARGA EL NIVEL 2*************************/
							NodeList listadoN2 = N1.getElementsByTagName("nivel2");
							
							if(listadoN2.getLength() > 0){
								Hashtable nivel2 = new Hashtable();
								
								for(int y=0; y<listadoN2.getLength(); y++){
									Element N2 = (Element)listadoN2.item(y);								
									Hashtable resN2 = cargarNivel(N2,"nivel2");
									nivel2.put((String)resN2.get("IDENTIFICADOR").toString().toUpperCase(), resN2);
									
									/*SE CARGA EL NIVEL 3*****************/
									NodeList listadoN3 = N2.getElementsByTagName("nivel3");
									
									if(listadoN3.getLength() > 0){
										Hashtable nivel3 = new Hashtable();
										for(int z=0; z<listadoN3.getLength(); z++){
											Element N3 = (Element)listadoN3.item(z);										
											Hashtable resN3 = cargarNivel(N3,"nivel3");
											nivel3.put((String)resN3.get("IDENTIFICADOR").toString().toUpperCase(), resN3);
										}
										nivel2.put("NIVEL3", nivel3);
									}
									/**************************************/
								}
								nivel1.put("NIVEL2", nivel2);
							}
							/**********************************************/
						}
						mod.put("NIVEL1", nivel1);
					}
					/***************************************/				
					
					/* SE ALMACENA EL MODULO */
					String ident =(String)mod.get("IDENTIFICADOR");
					modulos.put(ident.toString().toUpperCase(), mod);
					//System.out.println("Tamaño del modulo: "+mod.size());
					/**************************/
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}					
		}
		
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  Se cargan las acciones de un modulo
		
		Parametros:
		  permisos          nodo del modulo
		
		Valor de retorno:
		  array con las acciones
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private ArrayList cargarAcciones(Element permisos){
			
			ArrayList acciones = new ArrayList();		
			NodeList actions = permisos.getElementsByTagName("accion");
			try{
				for(int a=0; a<actions.getLength(); a++){		
					Element accion = (Element)actions.item(a);
					String accionValor = accion.getFirstChild().getNodeValue();
					acciones.add(accionValor);
					//System.out.println(acciones.get(a));
				}
			}catch(Exception e){
				//e.printStackTrace();
			}
			return acciones;		
		}
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  Se carga un nivel del perfil del usuario
		
		Parametros:
		  nivel           nodo del nivel
		  
		  padre           nombre del nodo padre
		
		Valor de retorno:
			retorna un hashtable con los permisos del nivel solicitado
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private Hashtable cargarNivel(Element nivel, String padre){
		
			Hashtable lv = new Hashtable();
			try{
				NodeList NodoNombre = nivel.getElementsByTagName("nombre");
				String NombreValor = NodoNombre.item(0).getFirstChild().getNodeValue().toString();
		 		lv.put("NOMBRE", NombreValor);
				NodeList NodoIdentificador = nivel.getElementsByTagName("identificador");
				String IdentificadorValor = NodoIdentificador.item(0).getFirstChild().getNodeValue().toString();
		 		lv.put("IDENTIFICADOR", IdentificadorValor);
				//System.out.println("nivel " + padre + ": " + IdentificadorValor);
				
				NodeList permisos = nivel.getElementsByTagName("permisos");
				Element aux = (Element)permisos.item(0);
				if(aux.getParentNode().getNodeName().toString().compareToIgnoreCase(padre)==0){
					ArrayList acciones = cargarAcciones(aux);
					lv.put("ACCIONES", acciones);					
				}else{
					lv.put("ACCIONES", new ArrayList());
				}			
			}catch(Exception e){
				//e.printStackTrace();
			}	
			return lv;
		}
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  Se valida si el XML que retorna el WS fue ERROR o EXITOSO
		
		Parametros:
		  
		Valor de retorno:
		  hashtable con el error
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/
		@SuppressWarnings("rawtypes")
		public Hashtable isErrorXML(){		
			
			Hashtable registroError = getErrorXML();
			
			try{
				if(!registroError.isEmpty()){
					return registroError;
				}
			}catch(Exception e){
				//e.printStackTrace();
			}
			return new Hashtable();
		} 
		/**************************************/	
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  obtiene el error enviado por el WS
		
		Parametros:
		  
		Valor de retorno:
		  hashtable con el error
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Hashtable getErrorXML(){
			
			Hashtable res = new Hashtable();
			try{					
				NodeList cod = doc.getElementsByTagName("codigo");
				//System.out.println(cod.getLength());
				if(cod.getLength()>0){
					Element codNodo = (Element)cod.item(0);
					String codigo = codNodo.getFirstChild().getNodeValue().toString();
					res.put("CODIGO",codigo);
				
					NodeList des = doc.getElementsByTagName("descripcion");
					Element desNodo = (Element)des.item(0);
					String descripcion = desNodo.getFirstChild().getNodeValue().toString();
					res.put("DESCRIPCION",descripcion);
				}
			}catch(Exception e){
				//e.printStackTrace();
			}
			return res;
		}
		
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  obtiene valor almacenado
		
		Parametros:
		  
		Valor de retorno:
		  nombre
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/
		public String getNombre(){
			return nombre;
		}
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  obtiene valor almacenado
		
		Parametros:
		  
		Valor de retorno:
		  carnet del usuario
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/
		
		public String getCarnet(){
			return carnet;
		}
		
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  obtiene valor almacenado
		
		Parametros:
		  
		Valor de retorno:
		  email del usuario
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/	
		public String getEmail(){
			return email;
		}
		
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  obtiene valor almacenado
		
		Parametros:
		  
		Valor de retorno:
		  departamento del usuario
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/
		public String getDepartamento(){
			return departamento;
		}
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  obtiene valor almacenado
		
		Parametros:
		  
		Valor de retorno:
		  tipo de perfil del usuario
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/	
		public String getPerfil(){
			return perfil;
		}
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  obtiene valor almacenado
		
		Parametros:
		
		mod           nombre del modulo solicitado
		  
		Valor de retorno:
		  hashtable con el modulo solicitado
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/	
		@SuppressWarnings("rawtypes")
		public Hashtable getModulo(String mod){
			return (Hashtable)modulos.get(mod);
		}
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  obtiene valor almacenado
		
		Parametros:
			mod			nombre del modulo solicitado
		  
		Valor de retorno:
		  array con las acciones del modulo
		
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/	
		@SuppressWarnings("rawtypes")
		public ArrayList getAcciones(String mod){
			ArrayList acc = new ArrayList();
			try{					
				Hashtable mo = (Hashtable)modulos.get(mod);
				acc = (ArrayList)mo.get("ACCIONES");
			}catch(Exception e){
				//e.printStackTrace();
			}
			return acc;
		}
		
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  valida si existe el modulo
		
		Parametros:
		  _modulo          nombre del modulo
		Valor de retorno:
		  verdadero o falso
		  	
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/	
		public boolean existeModulo(String _modulo){
			
			if(modulos.containsKey(_modulo)){
				return true;
			}else{
				return false;
			}		
		}
		
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  valida si existe el modulo de nivel 2
		
		Parametros:
		  _modulo1          nombre del modulo nivel 1
		  
		  _modulo           nombre del modulo nivel 2
		Valor de retorno:
		  verdadero o falso
		  	
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/	
		public boolean existeModuloN2(String _modulo1, String _modulo2){
			
			if(modulos.containsKey(_modulo1)){
				return true;
			}else{
				return false;
			}		
		}
		
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  obtener las acciones del nivel 1
		
		Parametros:
		  mod          nombre del modulo
		  nivel1       nombre del nivel 1
		  
		Valor de retorno:
		  array con las acciones
		  	
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/	
		@SuppressWarnings("rawtypes")
		public ArrayList getAccionesNivel1(String mod, String nivel1){
			ArrayList acc = new ArrayList();
			try{					
				Hashtable mo = (Hashtable)modulos.get(mod);
				Hashtable bloqueN1  = (Hashtable)mo.get("NIVEL1");
				Hashtable res  = (Hashtable)bloqueN1.get(nivel1);
				acc = (ArrayList)res.get("ACCIONES");
			}catch(Exception e){
				//e.printStackTrace();
			}
			return acc;
		}
		
		
		/*************************************************************************************
		Fecha creación:  16/05/2011
		
		Descripción:
		  valida si tiene la accion
		
		Parametros:
		  _modulo          nombre del modulo
		  
		  _accion          nombre de la accion
		Valor de retorno:
		  verdadero o falso
		  	
		Autor:
		  Alexander Valera
		
		---o---
		
		Bitácora de Modificaciones:
		Autor                                    Fecha Inicio     Fecha Fin
		  Descripción
							       
		**************************************************************************************/	
		@SuppressWarnings("rawtypes")
		public boolean tieneAccion(String _modulo, String _accion){
			ArrayList acc = new ArrayList();
			try{					
				if(modulos.containsKey(_modulo)){
					Hashtable mo = (Hashtable)modulos.get(_modulo);				
					if(mo.containsKey("ACCIONES")){
						acc = (ArrayList)mo.get("ACCIONES");
						if(acc.contains(_accion)){
							return true;
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return false;
		}
		
		@SuppressWarnings("rawtypes")
		public Hashtable getHash(Hashtable bloque, String clave){
			return (Hashtable)bloque.get(clave);
		}
}


