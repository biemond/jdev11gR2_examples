package nl.amis.rest.model.maps;


import oracle.adfmf.json.JSONArray;

public class GeocoderResult{


    private String types;
    private String formattedAddress;
    private AddressDetails addressComponents;
    private GeocoderGeometry geometry;
    private boolean partialMatch;



    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }



    public void setGeometry(GeocoderGeometry geometry) {
        this.geometry = geometry;
    }

    public GeocoderGeometry getGeometry() {
        return geometry;
    }

    public void setPartialMatch(boolean partialMatch) {
        this.partialMatch = partialMatch;
    }

    public boolean isPartialMatch() {
        return partialMatch;
    }


    public void setTypes(String types) {
        this.types = types;
    }

    public String getTypes() {
        return types;
    }


    public void setAddressComponents(AddressDetails addressComponents) {
        this.addressComponents = addressComponents;
    }

    public AddressDetails getAddressComponents() {
        return addressComponents;
    }
}
