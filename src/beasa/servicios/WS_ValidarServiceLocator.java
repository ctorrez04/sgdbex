/**
 * WS_ValidarServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package beasa.servicios;
@SuppressWarnings({ "serial","rawtypes", "unchecked" })
public class WS_ValidarServiceLocator extends org.apache.axis.client.Service implements beasa.servicios.WS_ValidarService {

    public WS_ValidarServiceLocator() {
    }


    public WS_ValidarServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WS_ValidarServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WS_Validar
    private java.lang.String WS_Validar_address = "http://localhost:8090/provider/services/WS_Validar";

    public java.lang.String getWS_ValidarAddress() {
        return WS_Validar_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WS_ValidarWSDDServiceName = "WS_Validar";

    public java.lang.String getWS_ValidarWSDDServiceName() {
        return WS_ValidarWSDDServiceName;
    }

    public void setWS_ValidarWSDDServiceName(java.lang.String name) {
        WS_ValidarWSDDServiceName = name;
    }

    public beasa.servicios.WS_Validar getWS_Validar() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WS_Validar_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWS_Validar(endpoint);
    }

    public beasa.servicios.WS_Validar getWS_Validar(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            beasa.servicios.WS_ValidarSoapBindingStub _stub = new beasa.servicios.WS_ValidarSoapBindingStub(portAddress, this);
            _stub.setPortName(getWS_ValidarWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWS_ValidarEndpointAddress(java.lang.String address) {
        WS_Validar_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (beasa.servicios.WS_Validar.class.isAssignableFrom(serviceEndpointInterface)) {
                beasa.servicios.WS_ValidarSoapBindingStub _stub = new beasa.servicios.WS_ValidarSoapBindingStub(new java.net.URL(WS_Validar_address), this);
                _stub.setPortName(getWS_ValidarWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WS_Validar".equals(inputPortName)) {
            return getWS_Validar();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.beasa", "WS_ValidarService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.beasa", "WS_Validar"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WS_Validar".equals(portName)) {
            setWS_ValidarEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
