/***************************************************************************f******************u************zz*******y**
 * File: EmployeePojoListener.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * @author Adam Mohr 040669681
 * 
 */
package com.algonquincollege.cst8277.employeedirectory.model;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Description: Listener for dates in Employee POJO
 */
public class EmployeePojoListener {

    /**
     * @param emp to set the new created date
     */
    @PrePersist
    public void setCreatedOnDate(EmployeePojo emp) {
        LocalDateTime now = LocalDateTime.now();
        emp.setCreatedDate(now);
        emp.setUpdatedDate(now);
    }

    /**
     * @param emp to set the new updated date
     */
    @PreUpdate
    public void setUpdatedDate(EmployeePojo emp) {
        emp.setUpdatedDate(LocalDateTime.now());
    }

}