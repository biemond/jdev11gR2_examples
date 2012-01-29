package nl.amis.jpa.model.services;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.amis.jpa.model.entities.Departments;
import nl.amis.jpa.model.entities.Employees;

@Stateless(name = "HrRemote", mappedName = "JPA-Model-HrRemote")
public class HrRemoteBean implements HrRemote {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public HrRemoteBean() {
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public Departments mergeDepartments(Departments departments) {
        return em.merge(departments);
    }

    /** <code>select o from Departments o</code> */
    public List<Departments> getDepartmentsFindAll() {
        return em.createNamedQuery("Departments.findAll").getResultList();
    }

    /** <code>select o from Departments o where o.locationId = 1700</code> */
    public List<Departments> getDepartmentsLocation1700() {
        return em.createNamedQuery("Departments.location1700").getResultList();
    }

    /** <code>select o from Departments o where o.locationId = :locationId</code> */
    public List<Departments> getDepartmentsFindByLocation(BigDecimal locationId) {
        return em.createNamedQuery("Departments.findByLocation").setParameter("locationId",
                                                                              locationId).getResultList();
    }

    public Employees mergeEmployees(Employees employees) {
        return em.merge(employees);
    }

    /** <code>select o from Employees o</code> */
    public List<Employees> getEmployeesFindAll() {
        return em.createNamedQuery("Employees.findAll").getResultList();
    }
}
