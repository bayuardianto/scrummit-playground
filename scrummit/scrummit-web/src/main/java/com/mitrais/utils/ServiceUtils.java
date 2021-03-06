package com.mitrais.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceUtils {
	public static final String contextRoot = "https://localhost:12001/";
	
	public static String call(String link, Map<String, String> map) throws MalformedURLException, IOException{
		URL url = new URL(contextRoot+link);
		HttpsURLConnection  conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("connection", "close");
		if(map!=null && !map.isEmpty()){
			for (Map.Entry<String,String> entry : map.entrySet()) {
				  String key = entry.getKey();
				  String value = entry.getValue();
				  conn.setRequestProperty(key, value);
			}
			
		}
		conn.setSSLSocketFactory(generateSslSocketFactory());
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		StringBuffer sb = new StringBuffer();
		
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}

		conn.disconnect();
		return sb.toString();
	
	}
	
	public static String postObject(Object o, String link) throws MalformedURLException,IOException{

		URL url = new URL(contextRoot+link);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("connection", "close");
		conn.setSSLSocketFactory(generateSslSocketFactory());
		
		
		String input = new ObjectMapper().writeValueAsString(o);

		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		StringBuffer sb = new StringBuffer();
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}

		conn.disconnect();
		
		return sb.toString();

	}
	/*public static void main(String[] args){
		try{
			Map<String,String> map = new HashMap<String,String>();
			map.put("username", "arifpurwandaru");
			String str = call("findByUsername",map);
			System.out.println(str);
			
			User user = new ObjectMapper().readValue(str, User.class);
			System.out.println(user.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	
	public static SSLSocketFactory generateSslSocketFactory() {
		SSLSocketFactory sslFactory = null;

		try{
			ClassPathResource classPathResource = new ClassPathResource("arifpurwandaru.keystore");

	        InputStream inputStream = classPathResource.getInputStream();
	        
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(inputStream, "password".toCharArray());
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ks);
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, tmf.getTrustManagers(), null);
			sslFactory = ctx.getSocketFactory();
		}catch(Exception e){
			e.printStackTrace();
		}
		return sslFactory;
	}
	
}
