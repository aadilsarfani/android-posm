package com.example.osmeditor;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

public class LocationChooser extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_chooser);
		
		ChooserMapView map = (ChooserMapView) findViewById(R.id.chooser_mapview);
		map.setBuiltInZoomControls(true);
		map.setMultiTouchControls(true);
		IMapController mc = map.getController();
		mc.setZoom(15);
		GeoPoint gp = new GeoPoint(30.2862d, -97.7371d);
		mc.setCenter(gp);
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.map_frame_layout);
		int h = layout.getHeight();
		int targetHeight = height - 150;
		//layout.setScaleY(targetHeight * 1.0f / h);

//		ImageView iv = (ImageView) findViewById(R.id.pin_image);
//		Bitmap b = ((BitmapDrawable)getResources().getDrawable(R.drawable.pin)).getBitmap();
//		b = b.copy(Config.ARGB_8888, true);
//		int x = b.getWidth();
//		int y = b.getHeight();
//		for (int i = 0; i < x; i++)
//			for (int j = 0; j < y; j++)
//				if (b.getPixel(i, j) == Color.WHITE)
//					b.setPixel(i, j, Color.TRANSPARENT);
//		iv.setImageBitmap(b);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location_chooser, menu);
		return true;
	}
	
	public void goToChooseCategory(View view) {
		Intent intent = new Intent(LocationChooser.this, CategoryChooser.class);
		
		ChooserMapView map = (ChooserMapView) findViewById(R.id.chooser_mapview);
		IGeoPoint location = map.getMapCenter();
		intent.putExtra("latitude", location.getLatitude());
		intent.putExtra("longitude", location.getLongitude());
		
		startActivity(intent);
	}

}
