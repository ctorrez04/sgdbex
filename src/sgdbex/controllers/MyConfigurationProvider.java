package sgdbex.controllers;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

@RewriteConfiguration
public class MyConfigurationProvider extends HttpConfigurationProvider{

	@Override
	public Configuration getConfiguration(ServletContext context) {
		return ConfigurationBuilder.begin()
				
		.addRule(Join.path("/Autenticacion").to("/faces/login.xhtml"))//login
				
		.addRule(Join.path("/Principal").to("/faces/principal.xhtml"))//principal
		
		.addRule(Join.path("/Detalle/Defecto/id={id}").to("/faces/views/reportarDefecto/detalleDefecto.xhtml"))//Detalle Defectos
		
		.addRule(Join.path("/Validar").to("/faces/views/miVista/defectosResueltos.xhtml"))//Validar Defectos
		
		.addRule(Join.path("/Resolver").to("/faces/views/miVista/defectosAsignados.xhtml"))//Resolver Defectos
		
		.addRule(Join.path("/Asignar").to("/faces/views/miVista/defectosAsignados.xhtml"))//Asignar Defectos
		
		.addRule(Join.path("/ReporteGeneral").to("/faces/views/reportes/reporteGeneral.xhtml"))//Reporte General
		
		.addRule(Join.path("/ModuloProyectos").to("/faces/views/administracion/moduloProyectos.xhtml"))//Administracion Proyectos 
		
		.addRule(Join.path("/ModuloUsuarios").to("/faces/views/administracion/moduloUsuarios.xhtml"))//Administracion Usuarios
		
		.addRule(Join.path("/ModuloCategorias").to("/faces/views/administracion/moduloCategorias.xhtml"))//Administracion Categorias
		
		.addRule(Join.path("/ModuloMotivosRechazo").to("/faces/views/administracion/moduloMotivos.xhtml"))//Administracion Motivos Resolucion
		
		.addRule(Join.path("/InformacionAutenticacion").to("/faces/views/miCuenta/miCuenta.xhtml"))//Mi Cuenta
		 
		.addRule(Join.path("/404").to("/faces/error.xhtml"));
	}

	@Override
	public int priority() {
		// TODO Auto-generated method stub
		return 0;
	}

}
