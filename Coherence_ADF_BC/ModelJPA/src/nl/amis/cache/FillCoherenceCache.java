package nl.amis.cache;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import nl.amis.jpa.model.Departments;


public class FillCoherenceCache {
    public FillCoherenceCache()  {


        NamedCache employees = CacheFactory.getCache("Employees");
        for ( int i = 1 ; i < 1000 ; i++) {
           employees.get(Long.valueOf(i));
        }

        Departments dep =  new Departments();
        
        NamedCache departments = CacheFactory.getCache("Departments");

        for ( int i = 1 ; i < 300 ; i++) {
           departments.get(Long.valueOf(i));
        }


    }

    public static void main(String[] args) {
        FillCoherenceCache initCache = new FillCoherenceCache();
    }
}
