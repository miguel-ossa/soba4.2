package com.perfmath.spring.soba.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.perfmath.spring.soba.util.RandomID;

/**
 * This example demonstrates: 
 * 1) how to create an HTTP connection to a REST endpoint with bcrypted password; 
 * 2) how to consume RESTful web API
 */

public class RestWebAPIConsumer {

	public final static void main(String[] args) throws Exception {
		String txId = "210225211";
		String accountId = "298743065";

		// 1. set credentials
		CredentialsProvider credsProvider = new BasicCredentialsProvider();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String bcryptedPassword = passwordEncoder.encode("sobauser");

		credsProvider.setCredentials(new AuthScope("localhost", 8080),
				new UsernamePasswordCredentials("user1001", bcryptedPassword));

		// 2. create httpclient
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

		// 3. issue REST web API call

		HttpGet httpget = new HttpGet("http://localhost:8080/soba/restTx/txID/" + txId);
		httpget.addHeader("Accept", "application/json");
		System.out.println("Executing request: " + httpget.getRequestLine());

		CloseableHttpResponse response = httpclient.execute(httpget);
		
		//4. consume response
		try {
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());

			if (entity != null) {
				System.out.println("Response content length: " + entity.getContentLength());
				System.out.println("Response content type: " + entity.getContentType().getValue());
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					line = reader.readLine();
				}

				EntityUtils.consume(response.getEntity());
			}

		} finally {
			response.close();
		}

		// another POST REST API call
		createRestTx(httpclient, accountId);

		// When HttpClient instance is no longer needed,
		// close the connection manager to ensure
		// immediate deallocation of all system resources
		if (httpclient != null) {
			httpclient.close();
		}
	}

	public static void createRestTx(CloseableHttpClient httpclient, String accountId) throws Exception {
		StringEntity input = new StringEntity(composeATxInXML(accountId));
		HttpPost postUrl = new HttpPost("http://localhost:8080/soba/restTx");

		postUrl.addHeader("Content-Type", "application/xml");
		postUrl.setEntity(input);

		HttpResponse response = httpclient.execute(postUrl);
		System.out.println("Response status: " + response.getStatusLine());
		System.out.println("All response headers ----- begin ----------");
		Header[] httpHeaders = response.getAllHeaders();
		for (Header header : httpHeaders) {
			System.out.println("header: name = " + header.getName() + ", value = " + header.getValue());
		}
		System.out.println("All response headers ----- end ----------");

		BufferedReader reader = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

		String output;
		System.out.println("Response body ----- begin ----------");

		while ((output = reader.readLine()) != null) {
			System.out.println(output);
		}
		System.out.println("Response body ----- end ----------");
	}

	public static String composeATxInXML(String accountId) {
		String txId = (new RandomID(9)).getId();
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		String newTx = "<bankingtx>" + "<transactionId>" + txId + "</transactionId>" + "<transDate>" + ts
				+ "</transDate>" + "<type>rest</type>" + "<initiator>rest</initiator>"
				+ "<description>rest test</description>" + "<amount>1.23</amount>" + "<balance>0</balance>"
				+ "<accountId>" + accountId + "</accountId>" + "<status>pending</status>" + "</bankingtx>";
		return newTx;
	}
}
