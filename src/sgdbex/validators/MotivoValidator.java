package sgdbex.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sgdbex.services.GeneralServices;

@Component
@Scope("request")
public class MotivoValidator implements Validator {
		
		@Autowired
		private GeneralServices gs;
		
		public GeneralServices getGs() {
			return gs;
		}

		public void setGs(GeneralServices gs) {
			this.gs = gs;
		}
		
		@Override
	    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
			String mensaje = gs.validarMotivoUnico(value.toString());
	        if (mensaje !=null && !mensaje.isEmpty()) {
	            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","Las siglas del proyecto ya han sido usados anteriormente"));
	        }
		}

}
