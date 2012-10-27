package nl.amis.rest.model.maps;

import java.util.ArrayList;
import java.util.List;


public class GeocoderResultList {

    private List geocoderResults;

    public GeocoderResultList() {
        if (geocoderResults == null) {
            geocoderResults = new ArrayList();
        }           
    }

    public GeocoderResult[] getGeocoderResults() {
        GeocoderResult e[] = null;
        e = (GeocoderResult[])geocoderResults.toArray(new GeocoderResult[geocoderResults.size()]);
        return e;
    }

    public int  getGeocoderResultCount() {
        return geocoderResults.size();
    }    

    public void AddGeocoderResult(GeocoderResult result) {
        geocoderResults.add(result);
    }

}
