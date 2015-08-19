package beca.correo;

public class CarteroProxy implements beca.correo.Cartero {
  private String _endpoint = null;
  private beca.correo.Cartero cartero = null;
  
  public CarteroProxy() {
    _initCarteroProxy();
  }
  
  public CarteroProxy(String endpoint) {
    _endpoint = endpoint;
    _initCarteroProxy();
  }
  
  private void _initCarteroProxy() {
    try {
      cartero = (new beca.correo.CarteroServiceLocator()).getCartero();
      if (cartero != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cartero)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cartero)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cartero != null)
      ((javax.xml.rpc.Stub)cartero)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public beca.correo.Cartero getCartero() {
    if (cartero == null)
      _initCarteroProxy();
    return cartero;
  }
  
  public void enviar(java.lang.String smtpip, java.lang.String de, java.lang.String para, java.lang.String cc, java.lang.String cco, java.lang.String asunto, int formato, java.lang.String mensaje, int prioridad) throws java.rmi.RemoteException{
    if (cartero == null)
      _initCarteroProxy();
    cartero.enviar(smtpip, de, para, cc, cco, asunto, formato, mensaje, prioridad);
  }
  
  
}