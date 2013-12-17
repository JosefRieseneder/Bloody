package at.fhooe.mhs.bloody.locationservice;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

public class GeoCoderService {
	
	public static String getCompleteAddressString(Context ctx, double latitude, double longitude) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("Current loction address", "" + strReturnedAddress.toString());
            } else {
                Log.w("Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("Current loction address", "Canont get Address!");
        }
        return strAdd;
    }
	
	public static double[] getLocationFromString(Context ctx, String addressString){
		double[] latLong = new double[2];
		 Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
		 try {
			List<Address> addresses = geocoder.getFromLocationName(addressString, 1);
			if(addresses!=null){
				Address location = addresses.get(0);
			   latLong[0] = location.getLatitude();
			   latLong[1] = location.getLongitude();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return latLong;
	}

}
