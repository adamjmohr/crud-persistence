/***************************************************************************f******************u************zz*******y**
 * File: NewEmployeeView.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * @author Adam Mohr 040669681
 * 
 */
package com.algonquincollege.cst8277.employeedirectory.jsf;

import java.io.Serializable;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algonquincollege.cst8277.employeedirectory.model.EmployeePojo;

/**
 * Named and view scoped managed bean - newEmployee view
 */
@Named("newEmployee")
@ViewScoped
public class NewEmployeeView implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** first name of new employee */
    protected String firstName;
    /** last name of new employee */
    protected String lastName;
    /** email of new employee */
    protected String email;
    /** title of new employee */
    protected String title;
    /** salary of new employee */
    protected Double salary;

    /**
     * Inject our Employee Controller since it is a single page application (SPA)
     * and no session map.
     */
    @Inject
    @ManagedProperty("#{employeeController}")
    protected EmployeeController employeeController;

    /**
     * Default constructor.
     */
    public NewEmployeeView() {
    }

    /**
     * @return  firstName
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @param firstName  firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return  lastName
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * @param LastName  LastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return  email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return  title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return  salary
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * @param salary to set
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    /**
     * Create a new employee and add it to list in Employee Controller.
     */
    public void addEmployee() {
        if (allNotNullOrEmpty(firstName, lastName)) {
            EmployeePojo theNewEmployee = new EmployeePojo();
            theNewEmployee.setFirstName(getFirstName());
            theNewEmployee.setLastName(getLastName());
            theNewEmployee.setEmail(getEmail());
            theNewEmployee.setTitle(getTitle());
            theNewEmployee.setSalary(getSalary());

            // this Managed Bean does not know how to 'do' anything, ask controller
            employeeController.addNewEmployee(theNewEmployee);

            // clean up
            employeeController.toggleAdding();
            setFirstName(null);
            setLastName(null);
        }
    }

    /**
     * Check to see if all objects are null or empty.
     */
    static boolean allNotNullOrEmpty(final Object... values) {
        if (values == null) {
            return false;
        }
        for (final Object val : values) {
            if (val == null) {
                return false;
            }
            if (val instanceof String) {
                String str = (String) val;
                if (str.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}