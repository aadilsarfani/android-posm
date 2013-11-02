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

import org.apache.commons.codec.binary.Base64;

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
		this.username = "posmtest";
		this.password = "mikescott";
		// this.username = username;
		// this.password = password;
	}

	public boolean addPoint(String lat, String lon, String name,
			String housenumber, String street, String postcode, String city,
			String state, String country, String typekey, String typevalue) {
		// using the && operator will short-circuit and only execute subsequent calls if the previous one is successful
		changesetId = createChangeset(typekey, typevalue, name);
		return changesetId != null && createPoint(lat, lon, name, housenumber, street, postcode, city, state, country, typekey, typevalue) && closeChangeset();
	}

	public String createChangeset(String typekey, String typevalue, String name) {
		String filepath = "/api/0.6/changeset/create";
		String urlParameters = String.format("<osm><changeset><tag k=\"created_by\" v=\"POSM 0.01\"/><tag k=\"comment\" v=\"[POSM] added %s=%s: %s\"/></changeset></osm>", typekey, typevalue, name);

		URL api = null;
		HttpURLConnection connection = null;
		String result;
		try {
			// Create connection
			api = new URL(url + filepath);
			connection = (HttpURLConnection) api.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("Authorization", "Basic " + Base64.encodeBase64((username + ":" + password).getBytes()));

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			result =  response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
		return result;
	}

/*
 * PUT /api/0.6/node/create
<osm>
	<node changeset="{{changesetId}}" lat="{{lat}}" lon="{{lon}}">
		<tag k="name" v="{{name}}"/>
		<tag k="addr:housenumber" v="{{housenumber}}"/>
		<tag k="addr:street" v="{{street}}"/>
		<tag k="addr:postcode" v="{{postcode}}"/>
		<tag k="addr:city" v="{{city}}"/>
		<tag k="addr:state" v="{{state}}"/>
		<tag k="addr:country" v="{{country}}"/>
		<tag k="{{typekey}}" v="{{typevalue}}"/>
	</node>
</osm>
 */
	public boolean createPoint(String lat, String lon, String name,
			String housenumber, String street, String postcode, String city,
			String state, String country, String typekey, String typevalue) {
		return false;
	}

	//PUT /api/0.6/changeset/{{changesetId}}/close
	
	public boolean closeChangeset() {
		return false;
	}
}
