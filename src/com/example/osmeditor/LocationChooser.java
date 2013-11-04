package com.example.osmeditor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class LocationChooser extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_chooser);
		
		ChooserMapView map = (ChooserMapView) findViewById(R.id.chooser_mapview);
		map.setBuiltInZoomControls(true);
		map.setMultiTouchControls(true);
		Log.v("aadil", "zzz");

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

}
