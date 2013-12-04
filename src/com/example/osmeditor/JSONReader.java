package com.example.osmeditor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import android.app.Activity;

public class JSONReader {
	public JSONReader() {}
	
	public static JSONObject getJSONObject(FileInputStream fs) {
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(fs);
		    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		    StringBuilder sb = new StringBuilder();
		    String line;
	    
	    	while ((line = bufferedReader.readLine()) != null) {
	    		sb.append(line);
	    	}
	    	return new JSONObject(sb.toString());
	    } catch (Exception e) {
	    	return null;
	    }
	}
}
