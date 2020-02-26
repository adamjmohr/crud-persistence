/***************************************************************************f******************u************zz*******y**
 * File: EmployeeService.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * @author Adam Mohr 040669681
 * 
 */
package com.algonquincollege.cst8277.employeedirectory.ejb;

import static com.algonquincollege.cst8277.employeedirectory.model.EmployeePojo.ALL_EMPLOYEES_QUERY_NAME;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.employeedirectory.model.EmployeePojo;

/**
 * Stateless Session Bean - EJB EmployeeService
 */
@Stateless
public class EmployeeService implements Serializable {
    /** explicitly set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** explicitly declare our entity manager by name */
    @PersistenceContext(name = "employeeDirectory-PU")
    protected EntityManager em;

    /**
     * Default constructor.
     */
    public EmployeeService() {
    }

    /**
     * Find all employees in database by named query.
     * 
     * @return list of employees
     */
    public List<EmployeePojo> findAllEmployees() {
        TypedQuery<EmployeePojo> allEmpsQuery = em.createNamedQuery(ALL_EMPLOYEES_QUERY_NAME, EmployeePojo.class);
        return allEmpsQuery.getResultList();
    }

    /**
     * Persist an employee in database through a transaction.
     * 
     * @return persistent employee
     */
    @Transactional
    public EmployeePojo persistEmployee(EmployeePojo employee) {
        em.persist(employee);
        return employee;
    }

    /**
     * Find a specific employee in database by their primary key ID.
     * 
     * @return the employee found
     */
    public EmployeePojo findEmployeeByPrimaryKey(int empPK) {
        return em.find(EmployeePojo.class, empPK);
    }

    /**
     * Update and merge an employee in database through a transaction.
     * 
     * @return updated employee
     */
    @Transactional
    public EmployeePojo mergeEmployee(EmployeePojo employeeWithUpdates) {
        EmployeePojo employeeToBeUpdated = findEmployeeByPrimaryKey(employeeWithUpdates.getId());
        if (employeeToBeUpdated != null) {
            EmployeePojo mergedEmployeePojo = em.merge(employeeWithUpdates);
            return mergedEmployeePojo;
        }
        return employeeToBeUpdated;
    }

    /**
     * Delete an employee in database through a transaction.
     * 
     * @return deleted employee
     */
    @Transactional
    public void removeEmployee(int employeeId) {
        EmployeePojo employee = findEmployeeByPrimaryKey(employeeId);
        if (employee != null) {
            em.refresh(employee);
            em.remove(employee);
        }
    }

}