/***************************************************************************f******************u************zz*******y**
 * File: SalaryGreaterThanZeroValidator.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * @author Adam Mohr 040669681
 * 
 */
package com.algonquincollege.cst8277.employeedirectory.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Custom validator to check for a valid salary number.
 */
@FacesValidator("salaryValidator")
public class SalaryGreaterThanZeroValidator implements Validator<Object> {

    /**
     * Validate a salary number.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || (Double) value <= 0) {
            FacesMessage msg = new FacesMessage("Salary validation failed.",
                    "Salary must be a number greater than zero");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}