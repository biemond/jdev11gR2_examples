package nl.amis.jpa.model.services;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import nl.amis.jpa.model.entities.Departments;
import nl.amis.jpa.model.entities.Employees;

@Local
public interface HrLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Departments mergeDepartments(Departments departments);

    List<Departments> getDepartmentsFindAll();

    Employees mergeEmployees(Employees employees);

    List<Employees> getEmployeesFindAll();

    List<Departments> getDepartmentsLocation1700();

    List<Departments> getDepartmentsFindByLocation(BigDecimal locationId);
}
