package com.example.osmeditor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class Finalizer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finalizer);
		
		Log.v("finalizer", getIntent().getExtras().toString());
		Log.v("finalizer", getIntent().getExtras().getDouble("latitude")+"");
		Log.v("finalizer", getIntent().getExtras().getDouble("longitude")+"");
		Log.v("finalizer", getIntent().getExtras().getString("category"));
		Log.v("finalizer", getIntent().getExtras().getString("subcategory"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finalizer, menu);
		return true;
	}

}
