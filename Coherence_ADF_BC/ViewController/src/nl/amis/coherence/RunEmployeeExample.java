package nl.amis.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import com.tangosol.util.filter.IsNotNullFilter;

import java.lang.reflect.Method;
import java.util.Set;

import nl.amis.jpa.model.Departments;
import nl.amis.jpa.model.Employees;


public class RunEmployeeExample {
    public RunEmployeeExample() {
    }

    public static void main(String[] args) {
        long empId = 190L;  // emp 190 - Timothy Gates
        
        NamedCache employees = CacheFactory.getCache("Employees");

        Employees emp = (Employees) employees.get(empId);
        
        System.out.println("Employee " + emp.getFirstName() + " " + 
                          emp.getLastName() + ", salary = $" + emp.getSalary() );
        
        NamedCache cache = CacheFactory.getCache("Departments");     

        Departments dept = (Departments) cache.get(Long.valueOf(10) );
        System.out.println("Department " + dept.getDepartmentName() );

        IsNotNullFilter filter =  new IsNotNullFilter("getDepartmentId");
        Set departmentsSet = cache.entrySet(filter);
        
        System.out.println("count departments " + departmentsSet.size() );

        Departments deptNew = new Departments();
        deptNew.setDepartmentId(Long.valueOf(2));
        deptNew.setDepartmentName("Edwin");
        deptNew.setLocationId(Long.valueOf(1700));
        deptNew.setManagerId(Long.valueOf(200));
        cache.put(Long.valueOf(2) ,deptNew);
        
        
        dept = (Departments) cache.get(Long.valueOf(2) );
        System.out.println("Department " + dept.getDepartmentName() );

        cache.remove(Long.valueOf(2));

        try {
            Class department = Class.forName("nl.amis.jpa.model.Departments" );
            Object departmentInst = department.newInstance();
            Method m = department.getMethod("setDepartmentId", new Class[] { Long.class });
            m.invoke(departmentInst, new Object[] {Long.valueOf(37)});
            m = department.getMethod("setDepartmentName", new Class[] { String.class });
            m.invoke(departmentInst, new Object[] { "hoihoi" });
            m = department.getMethod("setLocationId", new Class[] { Long.class });
            m.invoke(departmentInst, new Object[] {Long.valueOf(1700)});
            cache.put(Long.valueOf(37) ,departmentInst);
            System.out.println(department.getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception eee) {
                eee.printStackTrace();
        }
    }
}
