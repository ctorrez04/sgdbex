/**
 * Cartero.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package beca.correo;

public interface Cartero extends java.rmi.Remote {
    public void enviar(java.lang.String smtpip, java.lang.String de, java.lang.String para, java.lang.String cc, java.lang.String cco, java.lang.String asunto, int formato, java.lang.String mensaje, int prioridad) throws java.rmi.RemoteException;
}
