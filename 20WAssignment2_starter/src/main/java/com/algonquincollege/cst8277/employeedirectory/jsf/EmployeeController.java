/***************************************************************************f******************u************zz*******y**
 * File: EmployeeController.java
 * Course materials (20W) CST 8277
 * @author (original) Mike Norman
 * @author Adam Mohr 040669681
 * 
 */
package com.algonquincollege.cst8277.employeedirectory.jsf;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.algonquincollege.cst8277.employeedirectory.dao.EmployeeDao;
import com.algonquincollege.cst8277.employeedirectory.model.EmployeePojo;

/**
 * Description: Responsible for collection of Employee DTO's in XHTML (list) <h:dataTable> </br>
 * Delegates all C-R-U-D behaviour to DAO
 */
@Named("employeeController")
@SessionScoped
public class EmployeeController implements Serializable {
    /** explicitly set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** declare FacesContext variable */
    protected FacesContext facesContext;
    /** declare ServletContext variable */
    protected ServletContext sc;
    /** declare EmployeeDAO interface */
    protected EmployeeDao employeeDao;
    /** declare List of employee POJO objects */
    protected List<EmployeePojo> employees;
    /** boolean to toggle form */
    protected boolean adding;

    /**
     * Initialized constructor to set instance variables and inject them.
     */
    @Inject
    public EmployeeController(FacesContext facesContext, ServletContext sc, EmployeeDao employeeDao) {
        this.facesContext = facesContext;
        this.sc = sc;
        this.employeeDao = employeeDao;
    }

    /**
     * Use DAO to load all employees and log message.
     */
    public void loadEmployees() {
        sc.log("refreshing employees");
        employees = employeeDao.readAllEmployees();
    }

    /**
     * Get the list of employee objects.
     * 
     * @return list of employees
     */
    public List<EmployeePojo> getEmployees() {
        return this.employees;
    }
    
    /**
     * Set the list of employees and initialize.
     */
    public void setEmployees(List<EmployeePojo> employees) {
        this.employees = employees;
    }

    /**
     * boolean Getter to see if we're adding a new employee.
     * 
     * @return boolean
     */
    public boolean isAdding() {
        return adding;
    }

    /**
     * boolean Setter to toggle if we're adding a new employee.
     */
    public void setAdding(boolean adding) {
        this.adding = adding;
    }

    /**
     * Toggles/inverts the add employee mode which determines whether the
     * addEmployee form is rendered.
     */
    public void toggleAdding() {
        setAdding(!adding);
    }

    /**
     * Toggle edit mode in the view for a specific employee.
     * 
     * @return null for current page
     */
    public String editEmployee(EmployeePojo employee) {
        employee.setEditable(true);
        return null; // current page
    }

    /**
     * Toggle update mode in the view for a specific employee.
     * 
     * @return null for current page
     */
    public String updateEmployee(EmployeePojo employee) {
        EmployeePojo employeeToUpdateEmployeed = employeeDao.readEmployeeById(employee.getId());
        if (employeeToUpdateEmployeed == null) {
            // someone else deleted it
            facesContext.addMessage(null,
                    new FacesMessage(SEVERITY_ERROR, "Employee record missing, please refresh", null));
        } else {
            employeeToUpdateEmployeed = employeeDao.updateEmployee(employee);
            if (employeeToUpdateEmployeed == null) {
                // OptimisticLockException - someone else altered it 'faster' than we could hit
                // the 'save' button
                facesContext.addMessage(null,
                        new FacesMessage(SEVERITY_ERROR, "Employee record out-of-date, please refresh", null));
            } else {
                employeeToUpdateEmployeed.setEditable(false);
                int idx = employees.indexOf(employee);
                employees.remove(idx);
                employees.add(idx, employeeToUpdateEmployeed);
            }
        }
        return null; // current page
    }

    /**
     * Cancel update mode in the view for a specific employee.
     * 
     * @return null for current page
     */
    public String cancelUpdate(EmployeePojo employee) {
        employee.setEditable(false);
        return null; // current page
    }

    /**
     * Delete a specific employee.
     */
    public void deleteEmployee(int empId) {
        EmployeePojo employeePojoToBeRemoted = employeeDao.readEmployeeById(empId);
        if (employeePojoToBeRemoted != null) {
            employeeDao.deleteEmployeeById(empId);
            employees.remove(employeePojoToBeRemoted);
        }
    }

    /**
     * Add a new employee.
     */
    public void addNewEmployee(EmployeePojo theNewEmployee) {
        if (theNewEmployee != null) {
            employeeDao.createEmployee(theNewEmployee);
            employees.add(theNewEmployee);
        }
    }
}