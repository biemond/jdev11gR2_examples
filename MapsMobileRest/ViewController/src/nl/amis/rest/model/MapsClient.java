package nl.amis.rest.model;


import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.Model;

import com.sun.util.logging.Level;


import nl.amis.rest.model.maps.GeocoderResultList;

import oracle.adfmf.framework.api.JSONBeanSerializationHelper;


import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;


public class MapsClient {


    public MapsClient() {
    }

    private String search = "1600+Amphitheatre+Parkway,+Mountain+View,+CA";
    private String result = "empty";
    private GeocoderResultList geoResult = null;


    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void searchAction() {
        // Add event code here...

        Trace.log(Utility.ApplicationLogger, Level.INFO, MapsClient.class, "Mapsclient", "begin2");


        System.out.println("begin");
        this.result = "called";
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();

        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();

        // Set the connection name
        restServiceAdapter.setConnectionName("GoogleGeocodeJSON");

        // Specify the type of request
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_GET);
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        // Specify the number of retries
        restServiceAdapter.setRetryLimit(0);

        // Set the URI which is defined after the endpoint in the connections.xml.
        // The request is the endpoint + the URI being set
        restServiceAdapter.setRequestURI("?address="+search+"&sensor=true");

        String response = "not found";


        JSONBeanSerializationHelper jsonHelper = new JSONBeanSerializationHelper();
        
        try {
            // For GET request, there is no payload
            response = restServiceAdapter.send("");

            ServiceResult responseObject = (ServiceResult)jsonHelper.fromJSON(ServiceResult.class, response);
            if ( "OK".equalsIgnoreCase( responseObject.getStatus()) ) {
                geoResult  = GeocoderHelper.transformObject(responseObject).getResults();  
            }
            this.result = responseObject.getStatus();

        } catch (Exception e) {
            e.printStackTrace();
            this.result = "error";
        }
    }

    public void setGeoResult(GeocoderResultList geoResult) {
        this.geoResult = geoResult;
    }

    public GeocoderResultList getGeoResult() {
        return geoResult;
    }
}
