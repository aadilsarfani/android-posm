package com.example.osmeditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;

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

public class CategoryChooser extends Activity {

	private static Map<String, String[]> categories = null;
	private static List<String> level1categories = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_chooser);
		
		if (level1categories == null) {
			try {
				JSONObject jo = JSONReader.getJSONObject(openFileInput("Sdlk"));
				if (jo == null) Log.d("aadil", "SCREAM");
				else Log.d("aadil", "cool beans");
			} catch (Exception e) {
				Log.e("aadil", "json build/read from file failed");
			}
			
		}
		
		if (categories == null) {
			categories = new TreeMap<String, String[]>();
			categories.put("A",  new String[]{"a1", "a2"});
			categories.put("B", new String[]{"b1", "b2", "b1", "b2", "b1", "b2", "b1", "b2", "b1", "b2", "b1", "b2", "b1", "b2", "b1", "b2", "b1", "b2"});
			for (int i = 0; i < 20; i++) {
				categories.put(Character.valueOf((char)((int)'C' + i)).toString(), new String[]{"a", "b"});
			}
			level1categories = new ArrayList<String>(categories.keySet());
		}
		
		ListView lv = (ListView) findViewById(R.id.list);
		lv.setAdapter(new ArrayAdapter<String>(CategoryChooser.this, R.layout.choice_button, level1categories));
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(CategoryChooser.this, SubcategoryChooser.class);
				Bundle extras = getIntent().getExtras();
				/*double latitude = extras.getDouble("latitude");
				double longitude = extras.getDouble("longitude");
				
				intent.putExtra("latitude", latitude);
				intent.putExtra("longitude", longitude);*/
				//Instead
				intent.putExtras(extras);
				String category = level1categories.get(position);
				intent.putExtra("category", category);
				intent.putExtra("subcategories", categories.get(category));
				
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category_chooser, menu);
		return true;
	}

}