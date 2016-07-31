package com.example.officelocator;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class OfficeLocatorService extends Service implements LocationListener 
{
	//cdac office =18.558007,73.80752
	
	Location currentLoc = null;
	Location OfficeLoc = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		LocationManager locM = (LocationManager) getSystemService(LOCATION_SERVICE);
		locM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 200, this);
		//prepare office location object
		OfficeLoc = new Location(LocationManager.GPS_PROVIDER);
		OfficeLoc.setLatitude(18.558007);
		OfficeLoc.setLongitude(73.80752);
	}//eof ONcreate
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// compare locations i.e, of office and current location
		if(currentLoc!=null && OfficeLoc!=null){
			float d = currentLoc.distanceTo(OfficeLoc);
			if(d <=200){
				Toast.makeText(OfficeLocatorService.this, "about to reach office", 5).show();
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		currentLoc = location;
		Log.e("location:", location.getLatitude()+","+location.getLongitude());
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
