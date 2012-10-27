package nl.amis.rest.model.maps;

import java.io.Serializable;

import java.util.List;

public class GeocoderAddressComponent implements Serializable {
    private static final long serialVersionUID = 1L;

    public GeocoderAddressComponent() {
    }


    private String longName;
    private String shortName;
    private List types;

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getLongName() {
        return longName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setTypes(List types) {
        this.types = types;
    }

    public List getTypes() {
        return types;
    }
}
