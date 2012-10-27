package nl.amis.rest.model.maps;


import oracle.adfmf.json.JSONArray;

public class GeocodeResponse  {

    private String status;
    private GeocoderResultList results;

    public GeocodeResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setResults(GeocoderResultList results) {
        this.results = results;
    }

    public GeocoderResultList getResults() {
        return results;
    }
}
