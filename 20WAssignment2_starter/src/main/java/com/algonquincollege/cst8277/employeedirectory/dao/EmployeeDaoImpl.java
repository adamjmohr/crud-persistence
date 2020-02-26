/***************************************************************************f******************u************zz*******y**
 * File: EmployeeDaoImpl.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * @author Adam Mohr 040669681
 * 
 */
package com.algonquincollege.cst8277.employeedirectory.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.algonquincollege.cst8277.employeedirectory.ejb.EmployeeService;
import com.algonquincollege.cst8277.employeedirectory.model.EmployeePojo;

/**
* Description: Implements the C-R-U-D API for the database
*/
@Named
@ApplicationScoped
public class EmployeeDaoImpl implements EmployeeDao, Serializable {
    /** explicitly set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Declare our Servlet to init later */
    protected ServletContext sc;

    /** Delcare our EmployeeService dependency */
    @EJB
    protected EmployeeService employeeService;

    /**
     * Set and init our servlet.
     * 
     * @param ServletContextEvent sc
     */
    @Inject
    public EmployeeDaoImpl(ServletContext sc) {
        super();
        this.sc = sc;
    }
    
    // delegate all C-R-U-D operations to EmployeeService
    
    /**
     * Read all employees from the database.
     * 
     * @return List of Employees
     */
    @Override
    public List<EmployeePojo> readAllEmployees() {
        sc.log("reading all employees");
        List<EmployeePojo> employees = new ArrayList<>();
        try {
            employees = employeeService.findAllEmployees();
        } catch (Exception e) {
            sc.log("something went wrong finding all employees: " + e.getLocalizedMessage());
        }
        return employees;
    }

    /**
     * Create a new employee and insert into database.
     * 
     * @return newly created EmployeePojo
     */
    @Override
    public EmployeePojo createEmployee(EmployeePojo employee) {
        sc.log("creating an employee");
        try {
            employee = employeeService.persistEmployee(employee);
        } catch (Exception e) {
            sc.log("something went wrong persisting employee: " + e.getLocalizedMessage());
        }
        return employee;
    }

    /**
     * Read a specific employee by their ID from database.
     * 
     * @return the specific employee
     */
    @Override
    public EmployeePojo readEmployeeById(int employeeId) {
        EmployeePojo employee = null;
        try {
            employee = employeeService.findEmployeeByPrimaryKey(employeeId);
        } catch (Exception e) {
            sc.log("something went wrong finding employee: " + employeeId + " " + e.getLocalizedMessage());
        }
        return employee;
    }

    /**
     * Update a specific employee in database.
     * 
     * @return the updated employee
     */
    @Override
    public EmployeePojo updateEmployee(EmployeePojo employee) {
        sc.log("updating specific employee");
        EmployeePojo updatedEmployee = null;
        try {
            updatedEmployee = employeeService.mergeEmployee(employee);
        } catch (Exception e) {
            sc.log("something went wrong merging changes to employee: " + e.getLocalizedMessage());
        }
        return updatedEmployee;
    }

    /**
     * Delete a specific employee by their ID.
     */
    @Override
    public void deleteEmployeeById(int employeeId) {
        sc.log("deleting a specific employee");
        try {
            employeeService.removeEmployee(employeeId);
        } catch (Exception e) {
            sc.log("something went wrong removing employee: " + employeeId + " " + e.getLocalizedMessage());
        }
    }

}