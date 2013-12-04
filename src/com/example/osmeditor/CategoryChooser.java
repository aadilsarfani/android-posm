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

public class CategoryChooser extends Activity {
	
	private ArrayList<String> categories = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_chooser);
	
		try {
			Log.d("aadil", "start");
			categories =
					JSONReader.getList(getResources().openRawResource(R.raw.categories), null, null, null);
			
			Log.d("aadil", "good good" + categories.size());
			
			ListView lv = (ListView) findViewById(R.id.list);
			lv.setAdapter(new ArrayAdapter<String>(CategoryChooser.this, R.layout.choice_button, categories));
			
			Log.d("aadil", "too good");
			
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent intent = new Intent(CategoryChooser.this, SubcategoryChooser.class);
					Bundle extras = getIntent().getExtras();
					
					intent.putExtras(extras);
					String category = categories.get(position);
					intent.putExtra("category", category);
					
					startActivity(intent);
				}
			});
		} catch (Exception e) {
			Log.e("aadil", "json build/read from file failed");
			e.printStackTrace();
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category_chooser, menu);
		return true;
	}

}