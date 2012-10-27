package nl.amis.rest.model.maps;

public class GeocoderGeometry  {

    private LatLng location;
    private String locationType;
    private LatLngBounds viewport;

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setViewport(LatLngBounds viewport) {
        this.viewport = viewport;
    }

    public LatLngBounds getViewport() {
        return viewport;
    }
}
