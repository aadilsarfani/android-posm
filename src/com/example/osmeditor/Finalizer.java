package com.example.osmeditor;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Finalizer extends Activity {

	RequestSender rs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finalizer);
		
		Log.v("finalizer", getIntent().getExtras().toString());
		Log.v("finalizer", getIntent().getExtras().getDouble("latitude")+"");
		Log.v("finalizer", getIntent().getExtras().getDouble("longitude")+"");
		Log.v("finalizer", getIntent().getExtras().getString("category"));
		Log.v("finalizer", getIntent().getExtras().getString("subcategory"));
		
		rs = new RequestSender();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finalizer, menu);
		return true;
	}
	
	public void onSubmit(View button) {
		String[] inputs = { "", "", "", "", "", "", "" };
		String verifyOutput = verify(inputs);
		if (!verifyOutput.isEmpty()) {
			Toast.makeText(getApplicationContext(), verifyOutput, Toast.LENGTH_SHORT).show();
			return;
		}
		
		rs.execute(
			((Double)getIntent().getExtras().getDouble("latitude")).toString(),
			((Double)getIntent().getExtras().getDouble("longitude")).toString(),
			inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5], inputs[6],
			getIntent().getExtras().getString("pointType"),
			JSONReader.getList(getResources().openRawResource(R.raw.data),
					getIntent().getExtras().getString("category"),
					getIntent().getExtras().getString("subcategory"),
					getIntent().getExtras().getString("pointType")).get(0));
		
		button.setEnabled(false);
	}

	private class RequestSender extends AsyncTask<String, Integer, Boolean> {
		public Boolean doInBackground(String... inputs) {
			APIConnection conn = new APIConnection("posmtest", "mikescott");
			
			return conn.addPoint(
					inputs[0],
					inputs[1],
					inputs[2],
					inputs[3],
					inputs[4],
					inputs[5],
					inputs[6],
					inputs[7],
					inputs[8],
					inputs[9],
					inputs[10] );
		}
		
		@Override
		public void onPostExecute(Boolean result) {
			Finalizer.this.findViewById(R.id.poi_details_submit).setEnabled(true);
			String toast = result ?
					"Your point of interest has been added successfully" :
					"The add was unsuccessful. Please try again";
			Toast.makeText(Finalizer.this.getApplicationContext(), toast, Toast.LENGTH_LONG).show();
			if (result) {
				Intent intent = new Intent(Finalizer.this, LocationChooser.class);
				Finalizer.this.startActivity(intent);
			}
		}
	}
	private String verify(String[] inputs) {
		final int[] ids = {
			R.id.poi_name_input,
			R.id.poi_house_number_input,
			R.id.poi_street_name_input,
			R.id.poi_postal_code_input,
			R.id.poi_city_input,
			R.id.poi_state_input,
			R.id.poi_country_input };
		
		EditText editText;
		Editable editable;
		
		for (int i = 0; i < ids.length; i++) {
			editText = (EditText)findViewById(ids[i]);
			editable = editText.getText();
			inputs[i] = editable.toString();
			if (editable.toString().isEmpty()) {
				return "Enter a valid " + editText.getHint().toString();
			}
		}
		
		return "";
	}
}