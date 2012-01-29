package nl.amis.jpa.model.services;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import nl.amis.jpa.model.entities.Departments;
import nl.amis.jpa.model.entities.Employees;

@Remote
public interface HrRemote {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Departments mergeDepartments(Departments departments);

    List<Departments> getDepartmentsFindAll();

    List<Departments> getDepartmentsLocation1700();

    List<Departments> getDepartmentsFindByLocation(BigDecimal locationId);

    Employees mergeEmployees(Employees employees);

    List<Employees> getEmployeesFindAll();
}
