/**
 * 
 */
package com.example.osmeditor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import biz.source_code.base64Coder.Base64Coder;

/**
 * @author clay
 * 
 */
public class APIConnection {
	public static final String url = "http://api06.dev.openstreetmap.org";
	// public static final String url = "http://api.openstreetmap.org";

	private String username;
	private String password;
	private String changesetId;

	public APIConnection(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean addPoint(String lat, String lon, String name,
			String housenumber, String street, String postcode, String city,
			String state, String country, String typeId) {
		// using the && operator will short-circuit and only execute subsequent
		// calls if the previous one is successful
		
		Resources res = getResources();
		TypedArray ta = res.obtainTypedArray(res.getIdentifier(typeId, "array", "strings"));
		int n = ta.length();
		String[][] array = new String[n][];
		for (int i = 0; i < n; ++i) {
		    int id = ta.getResourceId(i, 0);
		    if (id > 0) {
		        array[i] = res.getStringArray(id);
		    } else {
		        // something wrong with the XML
		    }
		}
		ta.recycle();
		
		changesetId = createChangeset(pointType, name).trim();
		return (changesetId != null)
				&& createPoint(lat, lon, name, housenumber, street, postcode,
						city, state, country, keyString)
				&& closeChangeset();
	}

	public String createChangeset(String pointType, String name) {
		String filepath = "/api/0.6/changeset/create";
		String urlParameters = String
				.format("<osm><changeset><tag k=\"created_by\" v=\"POSM 0.01\"/><tag k=\"comment\" v=\"[POSM] added %s: %s\"/></changeset></osm>",
						pointType, name);

		URL api = null;
		HttpURLConnection connection = null;
		String result;
		try {
			// Create connection
			api = new URL(url + filepath);
			connection = (HttpURLConnection) api.openConnection();
			connection.setRequestMethod("PUT");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("Authorization", "Basic "
					+ Base64Coder.encodeString(username + ":" + password));

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK)
				result = null;
			else {
				InputStream is = connection.getInputStream();
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(is));
				String line;
				StringBuffer response = new StringBuffer();
				while ((line = rd.readLine()) != null) {
					response.append(line);
					response.append('\r');
				}
				rd.close();
				result = response.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return result;
	}

	public boolean createPoint(String lat, String lon, String name,
			String housenumber, String street, String postcode, String city,
			String state, String country, String keyString) {
		String filepath = "/api/0.6/node/create";
		StringBuilder changeXmlBuilder = new StringBuilder();
		changeXmlBuilder.append(String
				.format("<osm><node changeset=\"%s\" lat=\"%s\" lon=\"%s\"><tag k=\"name\" v=\"%s\"/><tag k=\"addr:housenumber\" v=\"%s\"/><tag k=\"addr:street\" v=\"%s\"/><tag k=\"addr:postcode\" v=\"%s\"/><tag k=\"addr:city\" v=\"%s\"/><tag k=\"addr:state\" v=\"%s\"/><tag k=\"addr:country\" v=\"%s\"/>",
						changesetId, lat, lon, name, housenumber, street,
						postcode, city, state, country));
		changeXmlBuilder.append(keyStringParse(keyString));
		changeXmlBuilder.append("</node></osm>");
		String urlParameters = changeXmlBuilder.toString();

		URL api = null;
		HttpURLConnection connection = null;
		boolean result;
		try {
			// Create connection
			api = new URL(url + filepath);
			connection = (HttpURLConnection) api.openConnection();
			connection.setRequestMethod("PUT");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("Authorization", "Basic "
					+ Base64Coder.encodeString(username + ":" + password));

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			int responseCode = connection.getResponseCode();
			result = (responseCode == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return result;
	}

	public boolean closeChangeset() {
		String filepath = String.format("/api/0.6/changeset/%s/close",
				changesetId);
		URL api = null;
		HttpURLConnection connection = null;
		boolean result;
		try {
			// Create connection
			api = new URL(url + filepath);
			connection = (HttpURLConnection) api.openConnection();
			connection.setRequestMethod("PUT");

			connection.setRequestProperty("Content-Length",
					"0");
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("Authorization", "Basic "
					+ Base64Coder.encodeString(username + ":" + password));

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(false);

			// Get Response
			int responseCode = connection.getResponseCode();
			result = (responseCode == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return result;
	}
	
	private static String keyStringParse(String keyString) {
		String[] tags = keyString.split("|");
		StringBuilder tagXmlBuilder = new StringBuilder();
		for(String tag: tags) {
			String[] kv = tag.split("=");
			tagXmlBuilder.append(String.format("<tag k=\"%s\" v=\"%s\"/>", kv[0], kv[1]));
		}
		return tagXmlBuilder.toString();
	}
}
