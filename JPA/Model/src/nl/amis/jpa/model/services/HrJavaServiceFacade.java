package nl.amis.jpa.model.services;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import nl.amis.jpa.model.entities.Departments;
import nl.amis.jpa.model.entities.Employees;

import oracle.binding.AttributeContext;
import oracle.binding.ManagedDataControl;
import oracle.binding.OperationBinding;
import oracle.binding.RowContext;
import oracle.binding.TransactionalDataControl;
import oracle.binding.UpdateableDataControl;

public class HrJavaServiceFacade implements TransactionalDataControl, UpdateableDataControl, ManagedDataControl {
    private static final boolean isAutoCommit = false;
    private final EntityManagerHelper entityManagerHelper;

    public HrJavaServiceFacade() {
        entityManagerHelper = new EntityManagerHelper("ModelLocal", isAutoCommit);
    }

    public void commitTransaction() {
        entityManagerHelper.commitTransaction();
    }

    public void rollbackTransaction() {
        entityManagerHelper.rollbackTransaction();
    }

    public boolean isTransactionDirty() {
        return entityManagerHelper.isTransactionDirty();
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = entityManagerHelper.getEntityManager().createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public Departments mergeDepartments(Departments departments) {
        return (Departments)entityManagerHelper.mergeEntity(departments);
    }

    /** <code>select o from Departments o</code> */
    public List<Departments> getDepartmentsFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("Departments.findAll").getResultList();
    }

    /** <code>select o from Departments o where o.locationId = 1700</code> */
    public List<Departments> getDepartmentsLocation1700() {
        return entityManagerHelper.getEntityManager().createNamedQuery("Departments.location1700").getResultList();
    }

    /** <code>select o from Departments o where o.locationId = :locationId</code> */
    public List<Departments> getDepartmentsFindByLocation(BigDecimal locationId) {
        return entityManagerHelper.getEntityManager().createNamedQuery("Departments.findByLocation").setParameter("locationId",
                                                                                                                  locationId).getResultList();
    }

    public Employees mergeEmployees(Employees employees) {
        return (Employees)entityManagerHelper.mergeEntity(employees);
    }

    /** <code>select o from Employees o</code> */
    public List<Employees> getEmployeesFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("Employees.findAll").getResultList();
    }

    public String getName() {
        return null;
    }

    public void release() {
    }

    public Object getDataProvider() {
        return null;
    }

    public boolean invokeOperation(Map p0, OperationBinding p1) {
        return false;
    }

    public boolean setAttributeValue(AttributeContext p0, Object p1) {
        return false;
    }

    public Object createRowData(RowContext p0) {
        return null;
    }

    public Object registerDataProvider(RowContext p0) {
        return null;
    }

    public boolean removeRowData(RowContext p0) {
        return false;
    }

    public void validate() {
    }

    public void beginRequest(HashMap p0) {
    }

    public void endRequest(HashMap p0) {
    }

    public boolean resetState() {
        return false;
    }

    private class EntityManagerHelper {
        final private EntityManagerFactory _entityManagerFactory;
        final private boolean _isAutoCommit;

        private EntityManager _entityManager;

        EntityManagerHelper(String persistenceUnit, boolean isAutoCommit) {
            _entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
            _isAutoCommit = isAutoCommit;
        }

        public EntityManager getEntityManager() {
            if (_entityManager == null) {
                _entityManager = _entityManagerFactory.createEntityManager();
            }

            return _entityManager;
        }

        public EntityTransaction getEntityTransaction() {
            return getEntityManager().getTransaction();
        }

        public void commitTransaction() {
            final EntityTransaction entityTransaction = getEntityTransaction();
            if (entityTransaction.isActive()) {
                entityTransaction.commit();
            }

            _closeEntityManager();
        }

        public void rollbackTransaction() {
            final EntityTransaction entityTransaction = getEntityTransaction();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }

            _closeEntityManager();
        }

        public boolean isTransactionDirty() {
            return (!_isAutoCommit && getEntityTransaction().isActive());
        }

        public Object persistEntity(Object entity) {
            _beginTransactionIfNeeded();
            _entityManager.persist(entity);
            _commitTransactionIfNeeded();

            return entity;
        }

        public Object mergeEntity(Object entity) {
            _beginTransactionIfNeeded();
            entity = _entityManager.merge(entity);
            _commitTransactionIfNeeded();

            return entity;
        }

        public void removeEntity(Object entity) {
            _beginTransactionIfNeeded();
            _entityManager.remove(entity);
            _commitTransactionIfNeeded();
        }

        private void _beginTransactionIfNeeded() {
            final EntityTransaction entityTransaction = getEntityTransaction();
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
        }

        private void _commitTransactionIfNeeded() {
            if (_isAutoCommit) {
                commitTransaction();
            }
        }

        private void _closeEntityManager() {
            if (_entityManager != null && _entityManager.isOpen()) {
                _entityManager.close();
            }

            _entityManager = null;
        }
    }
}
