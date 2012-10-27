package nl.amis.rest.model.maps;

import java.io.Serializable;

public class LatLngBounds  {

    private LatLng southwest, northeast;

    public LatLngBounds() {
    }

    public void setSouthwest(LatLng southwest) {
        this.southwest = southwest;
    }

    public LatLng getSouthwest() {
        return southwest;
    }

    public void setNortheast(LatLng northeast) {
        this.northeast = northeast;
    }

    public LatLng getNortheast() {
        return northeast;
    }
}
