package com.example.osmeditor;

import org.osmdroid.views.MapView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class LocationChooser extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_chooser);
		
		
		ChooserMapView map = (ChooserMapView) findViewById(R.id.chooser_mapview);
		map.setBuiltInZoomControls(true);
		map.setMultiTouchControls(true);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location_chooser, menu);
		return true;
	}

}
