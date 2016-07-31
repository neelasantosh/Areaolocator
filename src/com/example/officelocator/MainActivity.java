package com.example.officelocator;



import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements LocationListener {

	Button buttonStart;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button) findViewById(R.id.button1);
        LocationManager locM=(LocationManager) getSystemService(LOCATION_SERVICE);
		
		//set location listener
		locM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1000, MainActivity.this);
        buttonStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(MainActivity.this, OfficeLocatorService.class);
				PendingIntent pi = PendingIntent.getService(MainActivity.this,
						0, in, PendingIntent.FLAG_UPDATE_CURRENT);

				// prepare time to execute pi
				long timeSinceBoot = SystemClock.elapsedRealtime();// in
				long timeToExecute = timeSinceBoot + 10000;
				// pass pi and time to AlarmManager
				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				
				am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
						timeToExecute, 7000, pi);
			}
		});
        
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
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
