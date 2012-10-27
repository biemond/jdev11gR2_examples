package nl.amis.rest.model;



import nl.amis.rest.model.maps.AddressDetails;

import nl.amis.rest.model.maps.GeocodeResponse;

import nl.amis.rest.model.maps.GeocoderGeometry;
import nl.amis.rest.model.maps.GeocoderResult;
import nl.amis.rest.model.maps.GeocoderResultList;

import nl.amis.rest.model.maps.LatLng;

import nl.amis.rest.model.maps.LatLngBounds;

import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class GeocoderHelper {
    public GeocoderHelper() {
        super();
    }
    
    
    public static GeocodeResponse transformObject(ServiceResult service) {
        GeocodeResponse response = new GeocodeResponse();
        response.setStatus(service.getStatus());    
        
        GeocoderResultList results = new GeocoderResultList(); 
        response.setResults(results);

        JSONArray resultList = service.getResults();
        for ( int i = 0 ;  i <  resultList.length() ; i++ ) {
            try {
               GeocoderResult geoResult = new GeocoderResult();
               JSONObject result = resultList.getJSONObject(i);

               AddressDetails geoAddress = new AddressDetails();  

               JSONArray addresses = (JSONArray)result.get("address_components");
               for ( int m = 0 ;  m <  addresses.length() ; m++ ) {
                  JSONObject addressObj = addresses.getJSONObject(m); 
                  String vLongName = null;
                  String vShortName = null;
                  if (addressObj.getString("long_name") != null ) {
                      vLongName = addressObj.getString("long_name");
                  }                   
                  if (addressObj.getString("short_name") != null ) {
                      vShortName = addressObj.getString("short_name");
                  }                   

                  if ( addressObj.get("types") != null ) { 
                       JSONArray types = (JSONArray)addressObj.get("types");
                       for ( int p = 0 ;  p <  types.length() ; p++ ) {
                         String addressType = types.getString(p);
                         if ( "locality".equalsIgnoreCase(addressType) ) {
                             geoAddress.setSubLocality(vShortName);    
                         } else if ("administrative_area_level_1".equalsIgnoreCase(addressType)) {
                             geoAddress.setAdministrativeAreaLevel1(vShortName);     
                         } else if ("country".equalsIgnoreCase(addressType)) {
                             geoAddress.setCountry(vShortName);     
                         } else if ("administrative_area_level_2".equalsIgnoreCase(addressType)) {
                             geoAddress.setLocality(vShortName);     
                         } else if ("route".equalsIgnoreCase(addressType)) {
                             geoAddress.setRoute(vShortName);     
                         }
                       }
                   }   
               }
               geoResult.setAddressComponents(geoAddress);                

               if ( result.getString("formatted_address") != null ) { 
                 geoResult.setFormattedAddress(result.getString("formatted_address"));
               }                                   
               if ( result.get("types") != null ) { 
                  JSONArray types = (JSONArray)result.get("types");
                  String geoType = "";
                  for ( int p = 0 ;  p <  types.length() ; p++ ) {
                    geoType += types.get(p) + ",";
                  }
                  geoResult.setTypes(geoType);
               }   
               results.AddGeocoderResult(geoResult);
               if ( result.get("geometry") != null ) { 
                   JSONObject geometry = (JSONObject)result.get("geometry");
                   GeocoderGeometry geo = new GeocoderGeometry();

                   if (geometry.getString("location_type") != null ) {
                     geo.setLocationType(geometry.getString("location_type"));
                   }

                   if (geometry.get("location") != null ) {
                      JSONObject location = (JSONObject)geometry.get("location");
                      
                      LatLng latLng = new LatLng();
                      latLng.setLat( location.getDouble("lat") ); 
                      latLng.setLng( location.getDouble("lng") ); 
                      geo.setLocation(latLng);
                   }                   

                    if (geometry.get("viewport") != null ) {
                       LatLngBounds bounds = new LatLngBounds();
                       JSONObject viewport = (JSONObject)geometry.get("viewport");
                       
                       if ( viewport.get("northeast") != null ) {
                         JSONObject northeast = (JSONObject)viewport.get("northeast");
                         LatLng latLngNorth = new LatLng();
                         latLngNorth.setLat( northeast.getDouble("lat") ); 
                         latLngNorth.setLng( northeast.getDouble("lng") ); 
                         bounds.setNortheast(latLngNorth);
                       }
                       if ( viewport.get("southwest") != null ) {
                          JSONObject southWest = (JSONObject)viewport.get("southwest");
                          LatLng latLngSouth = new LatLng();
                          latLngSouth.setLat( southWest.getDouble("lat") ); 
                          latLngSouth.setLng( southWest.getDouble("lng") ); 
                          bounds.setSouthwest(latLngSouth);
                       }
                       geo.setViewport(bounds); 
                     }                   
 
                   geoResult.setGeometry(geo);
                } 

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        
        return response;
    }
}
