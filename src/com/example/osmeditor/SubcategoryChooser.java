package com.example.osmeditor;

import java.util.ArrayList;

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

	private ArrayList<String> subcategories = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_chooser);
	
		try {
			String category = getIntent().getExtras().getString("category");
			subcategories =
					JSONReader.getList(getResources().openRawResource(R.raw.data), category, null);
			
			ListView lv = (ListView) findViewById(R.id.list);
			lv.setAdapter(new ArrayAdapter<String>(SubcategoryChooser.this, R.layout.choice_button, subcategories));
			
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent intent = new Intent(SubcategoryChooser.this, PointTypeChooser.class);
					Bundle extras = getIntent().getExtras();
					/*double latitude = extras.getDouble("latitude");
					double longitude = extras.getDouble("longitude");
					
					intent.putExtra("latitude", latitude);
					intent.putExtra("longitude", longitude);*/
					//Instead
					intent.putExtras(extras);
					String subcategory = subcategories.get(position);
					
					intent.putExtra("subcategory", subcategory);
					
					startActivity(intent);
				}
			});
		} catch (Exception e) {
			Log.e("aadil", "json build/read from file failed");
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.subcategory_chooser, menu);
		return true;
	}

}
