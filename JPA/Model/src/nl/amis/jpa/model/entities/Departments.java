package nl.amis.jpa.model.entities;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries( { @NamedQuery(name = "Departments.findAll", 
                             query = "select o from Departments o"),
                 @NamedQuery(name = "Departments.location1700", 
                             query = "select o from Departments o where o.locationId = 1700"),
                 @NamedQuery(name = "Departments.findByLocation", 
                            query = "select o from Departments o where o.locationId = :locationId")
                })
public class Departments implements Serializable {
    @Id
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private BigDecimal departmentId;
    @Column(name = "DEPARTMENT_NAME", nullable = false, length = 30)
    private String departmentName;
    @Column(name = "LOCATION_ID")
    private BigDecimal locationId;

    @Column(name = "MANAGER_ID")
    private BigDecimal managerId;

    @ManyToOne(optional = true, targetEntity = Employees.class)
    @JoinColumn(name = "MANAGER_ID", nullable = true, insertable = false, updatable = false)
    private Employees departmentManager;

    @OneToMany(mappedBy = "departments")
    private List<Employees> employees;

    public Departments() {
    }

    public Departments(BigDecimal departmentId, String departmentName, BigDecimal locationId, Employees manager) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.locationId = locationId;
        this.departmentManager = manager;
    }


    public BigDecimal getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(BigDecimal departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public BigDecimal getLocationId() {
        return locationId;
    }

    public void setLocationId(BigDecimal locationId) {
        this.locationId = locationId;
    }



    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

    public Employees addEmployees(Employees employees) {
        getEmployees().add(employees);
        employees.setDepartments(this);
        return employees;
    }

    public Employees removeEmployees(Employees employees) {
        getEmployees().remove(employees);
        return employees;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName()+"@"+Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("departmentId=");
        buffer.append(getDepartmentId());
        buffer.append(',');
        buffer.append("departmentName=");
        buffer.append(getDepartmentName());
        buffer.append(',');
        buffer.append("locationId=");
        buffer.append(getLocationId());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }

    public void setDepartmentManager(Employees departmentManager) {
        this.departmentManager = departmentManager;
    }

    public Employees getDepartmentManager() {
        return departmentManager;
    }

    public void setManagerId(BigDecimal managerId) {
        this.managerId = managerId;
    }

    public BigDecimal getManagerId() {
        return managerId;
    }
}
