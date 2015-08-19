package beasa.servicios;

public class WS_ValidarProxy implements beasa.servicios.WS_Validar {
  private String _endpoint = null;
  private beasa.servicios.WS_Validar wS_Validar = null;
  
  public WS_ValidarProxy() {
    _initWS_ValidarProxy();
  }
  
  public WS_ValidarProxy(String endpoint) {
    _endpoint = endpoint;
    _initWS_ValidarProxy();
  }
  
  private void _initWS_ValidarProxy() {
    try {
      wS_Validar = (new beasa.servicios.WS_ValidarServiceLocator()).getWS_Validar();
      if (wS_Validar != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wS_Validar)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wS_Validar)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wS_Validar != null)
      ((javax.xml.rpc.Stub)wS_Validar)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public beasa.servicios.WS_Validar getWS_Validar() {
    if (wS_Validar == null)
      _initWS_ValidarProxy();
    return wS_Validar;
  }
  
  public java.lang.String verificar(java.lang.String ip, java.lang.String usuario, java.lang.String clave, java.lang.String sistema) throws java.rmi.RemoteException{
    if (wS_Validar == null)
      _initWS_ValidarProxy();
    return wS_Validar.verificar(ip, usuario, clave, sistema);
  }
  
  
}