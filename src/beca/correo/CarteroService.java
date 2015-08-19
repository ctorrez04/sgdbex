/**
 * CarteroService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package beca.correo;

public interface CarteroService extends javax.xml.rpc.Service {
    public java.lang.String getCarteroAddress();

    public beca.correo.Cartero getCartero() throws javax.xml.rpc.ServiceException;

    public beca.correo.Cartero getCartero(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
