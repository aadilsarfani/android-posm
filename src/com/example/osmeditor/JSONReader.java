package com.example.osmeditor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONReader {
	public JSONReader() {}
	
	public static JSONObject getJSONObject(InputStream fs) {
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
	
	public enum Level { Category, Subcategory, PointType };
	
	public static ArrayList<String> getList(InputStream fs, String category, String subcategory, String pointtype) {
		try {
			JSONObject jo = getJSONObject(fs);
			JSONArray ja = jo.getJSONArray("data");
			ArrayList<String> rval = new ArrayList<String>();
			int i = 0;
			for (i = 0; i < ja.length(); i++) {
				if (category == null)
					rval.add((ja.getJSONObject(i).getString("name")));
				else if (ja.getJSONObject(i).getString("name").equals(category))
					break;
			}
			if (category == null)
				return rval;
			else
			{
				jo = ja.getJSONObject(i);
				ja = jo.getJSONArray("values");
			}
			
			for (i = 0; i < ja.length(); i++) {
				if (subcategory == null)
					rval.add((ja.getJSONObject(i).getString("name")));
				else if (ja.getJSONObject(i).getString("name").equals(subcategory))
					break;
			}
			if (subcategory == null)
				return rval;
			else
			{
				jo = ja.getJSONObject(i);
				ja = jo.getJSONArray("values");
			}
			
			
			for (i = 0; i < ja.length(); i++) {
				if (pointtype == null)
					rval.add((ja.getJSONObject(i).getString("name")));
				else if (ja.getJSONObject(i).getString("name").equals(pointtype)) {
					rval.add(ja.getJSONObject(i).getString("keys"));
					return rval;
				}
			}
			
			return rval;
			
		} catch (Exception e) {
			return null;
		}
		
	}
}
