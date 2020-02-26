/***************************************************************************f******************u************zz*******y**
 * File: EmployeePojo_.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * @author Adam Mohr 040669681
 * 
 */
package com.algonquincollege.cst8277.employeedirectory.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * This class was provided in skeleton starter package. I have not modified it and
 * do not know what it does but I have kept it here.
 */
@Generated(value="Dali", date="2020-02-21T10:14:39.777-0500")
@StaticMetamodel(EmployeePojo.class)
public class EmployeePojo_ {
	public static volatile SingularAttribute<EmployeePojo, Integer> id;
	public static volatile SingularAttribute<EmployeePojo, String> firstName;
	public static volatile SingularAttribute<EmployeePojo, String> lastName;
	public static volatile SingularAttribute<EmployeePojo, String> email;
	public static volatile SingularAttribute<EmployeePojo, String> title;
	public static volatile SingularAttribute<EmployeePojo, Double> salary;
	public static volatile SingularAttribute<EmployeePojo, Integer> version;
	public static volatile SingularAttribute<EmployeePojo, LocalDateTime> createdDate;
	public static volatile SingularAttribute<EmployeePojo, LocalDateTime> updatedDate;
}
