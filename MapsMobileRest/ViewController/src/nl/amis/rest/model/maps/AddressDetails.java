package nl.amis.rest.model.maps;



public class AddressDetails { 

    private String country;
    private String administrativeAreaLevel1;
    private String locality;
    private String subLocality;
    private String route;
    private String streetAddress;
    private String subPremise;

    public AddressDetails() {
        }


    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setAdministrativeAreaLevel1(String administrativeAreaLevel1) {
        this.administrativeAreaLevel1 = administrativeAreaLevel1;
    }

    public String getAdministrativeAreaLevel1() {
        return administrativeAreaLevel1;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLocality() {
        return locality;
    }

    public void setSubLocality(String subLocality) {
        this.subLocality = subLocality;
    }

    public String getSubLocality() {
        return subLocality;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setSubPremise(String subPremise) {
        this.subPremise = subPremise;
    }

    public String getSubPremise() {
        return subPremise;
    }
}
