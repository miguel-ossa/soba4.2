package com.perfmath.spring.soba.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.Security;
import java.sql.Timestamp;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.StringEntity;

import com.perfmath.spring.soba.util.RandomID;

/**
 * This example demonstrates: 
 * 1) how to create secure connections with a custom SSL context 
 * 2) how to test RESTful web API
 */
public class RestWebAPITest {

	public final static void main(String[] args) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		// SSL setup begin
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream instream = new FileInputStream(new File(
				"C:\\Users\\henry\\.keystore"));
		try {
			trustStore.load(instream, "changeit".toCharArray());
		} finally {
			instream.close();
		}

		SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
		Scheme sch = new Scheme("https", socketFactory, 8443);
		httpclient.getConnectionManager().getSchemeRegistry().register(sch);
		// SSL setup end

		// set security provider
		String secProviderName = "com.sun.crypto.provider.SunJCE";
		java.security.Provider secProvider = (java.security.Provider) Class
				.forName(secProviderName).newInstance();
		Security.addProvider(secProvider);

		httpclient.getCredentialsProvider().setCredentials(
				new AuthScope("localhost", 8443),
				new UsernamePasswordCredentials("user1001", "sobauser"));

		// issue REST web API call
		HttpGet httpget = new HttpGet(
				"https://localhost:8443/soba/restTx/txID/149082904");
		httpget.addHeader("Accept", "application/json");
		System.out.println("Executing request: " + httpget.getRequestLine());

		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();

		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());

		if (entity != null) {
			System.out.println("Response content length: "
					+ entity.getContentLength());
			System.out.println("Response content type: "
					+ entity.getContentType().getValue());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
		}
		if (entity != null) {
			entity.consumeContent();
		}
		
		createRestTx(httpclient);

		// When HttpClient instance is no longer needed,
		// shut down the connection manager to ensure
		// immediate deallocation of all system resources
		httpclient.getConnectionManager().shutdown();
	}

	public static void createRestTx(DefaultHttpClient httpclient)
			throws Exception {
		StringEntity input = new StringEntity(composeATxInXML());
		HttpPost postUrl = new HttpPost("https://localhost:8443/soba/restTx");
		
		postUrl.addHeader("Content-Type", "application/xml");
		postUrl.setEntity(input);

		HttpResponse response = httpclient.execute(postUrl);
		System.out.println("Response status: " + response.getStatusLine());
		System.out.println("All response headers ----- begin ----------");
		Header[] httpHeaders = response.getAllHeaders();
		for (Header header : httpHeaders) {
			System.out.println ("header: name = " + header.getName() + ", value = " + header.getValue());
		}
		System.out.println("All response headers ----- end ----------");

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));

		String output;
		System.out.println("Response body ----- begin ----------");

		while ((output = reader.readLine()) != null) {
			System.out.println(output);
		}
		System.out.println("Response body ----- end ----------");
	}

	public static String composeATxInXML() {
		String txId = (new RandomID(9)).getId();
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		String newTx = "<bankingtx>" + "<transactionId>" + txId
				+ "</transactionId>" + "<transDate>" + ts + "</transDate>"
				+ "<type>rest</type>" + "<initiator>rest</initiator>"
				+ "<description>rest test</description>"
				+ "<amount>1.23</amount>" 
				+ "<balance>0</balance>"
				+ "<accountId>592186867</accountId>"
				+ "<status>pending</status>" + "</bankingtx>";
		return newTx;
	}
}
