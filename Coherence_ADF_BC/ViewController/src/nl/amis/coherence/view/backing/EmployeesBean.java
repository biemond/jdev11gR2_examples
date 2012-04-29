package nl.amis.coherence.view.backing;

import javax.faces.model.ArrayDataModel;
import oracle.adf.view.rich.component.rich.data.RichTable;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import java.util.Collection;


import nl.amis.jpa.model.Departments;
import nl.amis.jpa.model.Employees;



public class EmployeesBean {

    private RichTable table1;
    private RichTable table2;

    private ArrayDataModel modelEmployees = null;
    private ArrayDataModel modelDepartments = null;
     
    public EmployeesBean() { 
        
        NamedCache employees = CacheFactory.getCache("Employees");
        Collection<Employees> colEmps = employees.values();
        Employees[] emps = (Employees[])colEmps.toArray(new Employees[colEmps.size()]);
        modelEmployees = new ArrayDataModel(emps); 

        NamedCache departments = CacheFactory.getCache("Departments");
        Collection<Departments> colDept = departments.values();
        Departments[] dept = (Departments[])colDept.toArray(new Departments[colDept.size()]);
        modelDepartments = new ArrayDataModel(dept); 
    }

    public void setTable1(RichTable table1) {
        this.table1 = table1;
    }

    public RichTable getTable1() {
        return table1;
    }

    public ArrayDataModel getModelEmployees() {
        return modelEmployees;
    }

    public ArrayDataModel getModelDepartments() {
        return modelDepartments;
    }

    public void setTable2(RichTable table2) {
        this.table2 = table2;
    }

    public RichTable getTable2() {
        return table2;
    }



}
