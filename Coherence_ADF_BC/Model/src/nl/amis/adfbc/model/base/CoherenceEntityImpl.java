package nl.amis.adfbc.model.base;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import java.lang.reflect.Method;

import oracle.jbo.AttributeDef;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;

public class CoherenceEntityImpl extends EntityImpl {


    protected void doSelect(boolean lock) {
    }

    protected void doDML(int operation, TransactionEvent e) {

        NamedCache cache =
            CacheFactory.getCache(this.getEntityDef().getName());

        if (operation == DML_INSERT || operation == DML_UPDATE) {

            try {
                Class clazz =
                    Class.forName("nl.amis.jpa.model." + this.getEntityDef().getName());
                Object clazzInst = clazz.newInstance();
                AttributeDef[] allDefs =
                    this.getEntityDef().getAttributeDefs();
                for (int iAtts = 0; iAtts < allDefs.length; iAtts++) {
                    AttributeDef single = allDefs[iAtts];
                    Method m =
                        clazz.getMethod("set" + single.getName(), new Class[] { single.getJavaType() });
                    m.invoke(clazzInst,
                             new Object[] { getAttribute(single.getName()) });
                }
                cache.put(getAttribute(0), clazzInst);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        } else if (operation == DML_DELETE) {
            cache.remove(getAttribute(0));
        }
    }

}
