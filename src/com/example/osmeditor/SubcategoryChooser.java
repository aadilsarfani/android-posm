package com.example.osmeditor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubcategoryChooser extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subcategory_chooser);
		
		String[] subcategories = getIntent().getExtras().getStringArray("subcategories");
		Log.v("finalizer", subcategories.length + "");
		ListView lv = (ListView) findViewById(R.id.list);
		lv.setAdapter(new ArrayAdapter<String>(SubcategoryChooser.this, R.layout.choice_button, subcategories));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(SubcategoryChooser.this, Finalizer.class);
				Bundle extras = getIntent().getExtras();
				intent.putExtras(extras);
				String[] subcategories = extras.getStringArray("subcategories");
				intent.putExtra("subcategory", subcategories[position]);
				intent.removeExtra("subcategories");
				
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.subcategory_chooser, menu);
		return true;
	}

}
