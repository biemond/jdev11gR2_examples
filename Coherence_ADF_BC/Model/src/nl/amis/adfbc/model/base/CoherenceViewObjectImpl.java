package nl.amis.adfbc.model.base;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.ConverterCollections;
import com.tangosol.util.Filter;
import com.tangosol.util.filter.AllFilter;
import com.tangosol.util.filter.EqualsFilter;

import com.tangosol.util.filter.IsNotNullFilter;

import java.lang.reflect.Method;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.common.Diagnostic;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.server.ViewRowSetImpl;

public class CoherenceViewObjectImpl extends ViewObjectImpl {


    private NamedCache cache;
    private Iterator foundRowsIterator;

    /**
     * executeQueryForCollection - overridden for custom java data source support.
     */
    protected void executeQueryForCollection(Object qc, Object[] params,
                                             int noUserParams) {

        cache = CacheFactory.getCache(this.getViewDef().getName());

        List filterList = new ArrayList();
        Set foundRows = null;

        // get the currently set view criteria
        ViewCriteria vc = getViewCriteria();
        if (vc != null) {
            Row vcr = vc.first();
            // get all attributes and check which ones are filled
            for (AttributeDef attr : getAttributeDefs()) {
                Object s = vcr.getAttribute(attr.getName());
                if (s != null && s != "") {
                    // construct an EqualsFilter
                    EqualsFilter filter =
                        new EqualsFilter("get" + attr.getName(), s);
                    // add it to the list
                    filterList.add(filter);
                }
            }
        } else if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                Object[] s = (Object[])params[i];
                // construct an EqualsFilter
                String attribute = s[0].toString();
                attribute = attribute.substring(5);
                EqualsFilter filter =
                    new EqualsFilter("get" + attribute, s[1]);
                // add it to the list
                filterList.add(filter);

            }
        }
        if (filterList.size() > 0) {
            // create the final filter set, with
            // enclosing the single filters wit an AllFilter
            Filter finalEqualsFilterArray[] =
                (EqualsFilter[])filterList.toArray(new EqualsFilter[] { });
            AllFilter filter = new AllFilter(finalEqualsFilterArray);
            foundRows = cache.entrySet(filter);
        } else {
            IsNotNullFilter filter =
                new IsNotNullFilter("get" + this.getAttributeDef(0).getName());
            foundRows = cache.entrySet(filter);
        }
        setUserDataForCollection(qc, foundRows);

        ConverterCollections.ConverterEntrySet resultEntrySet =
            (ConverterCollections.ConverterEntrySet)getUserDataForCollection(qc);
        foundRowsIterator = resultEntrySet.iterator();

        super.executeQueryForCollection(qc, params, noUserParams);
    }

    protected boolean hasNextForCollection(Object qc) {

        boolean retVal = foundRowsIterator.hasNext();
        if (retVal == false) {
            setFetchCompleteForCollection(qc, true);
        }
        return retVal;
    }


    /**
     * createRowFromResultSet - overridden for custom java data source support.
     */
    protected ViewRowImpl createRowFromResultSet(Object qc,
                                                 ResultSet resultSet) {

        ViewRowImpl r = createNewRowForCollection(qc);
        AttributeDefImpl[] allDefs = (AttributeDefImpl[])getAttributeDefs();
        Map.Entry entry = (Map.Entry)foundRowsIterator.next();
        // loop through all attrs and fill them from the righ entity usage
        Object dynamicPojoFromCache = entry.getValue();
        Class dynamicPojoClass = dynamicPojoFromCache.getClass();
        for (int iAtts = 0; iAtts < allDefs.length; iAtts++) {
            AttributeDefImpl single = allDefs[iAtts];
            if (!single.getJavaType().getSimpleName().equalsIgnoreCase("RowIterator")) {
                try {
                    Method m =
                        dynamicPojoClass.getMethod("get" + single.getName(),
                                                   new Class[] { });
                    Object result =
                        result = m.invoke(dynamicPojoFromCache, null);
                    // populate the attributes
                    super.populateAttributeForRow(r, iAtts, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return r;
    }


    public long getQueryHitCount(ViewRowSetImpl viewRowSet) {
        if (viewRowSet.isFetchComplete()) {
            return viewRowSet.getFetchedRowCount();
        }
        Long result;
        if (cache != null) {
            result = Long.valueOf(cache.size());
        } else {
            cache = CacheFactory.getCache(this.getViewDef().getName());
            result = Long.valueOf(cache.size());
        }
        return result;
    }


}
