package nl.amis.rest.model;


import oracle.adfmf.json.JSONArray;

public class ServiceResult  {
    public ServiceResult() {
    }

    private String status;
    private JSONArray results;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setResults(JSONArray results) {
        this.results = results;
    }

    public JSONArray getResults() {
        return results;
    }


}
