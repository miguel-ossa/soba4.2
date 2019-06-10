package com.perfmath.spring.soba.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.Security;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * This example demonstrates:
 * 1)how to create secure connections with a custom SSL context
 * 2)how to test RESTful web API
 */
public class ClientCustomSSL {

    public final static void main(String[] args) throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
     // SSL setup begin
        KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());        
        FileInputStream instream = new FileInputStream(new File("C:\\Users\\henry\\.keystore")); 
        try {
            trustStore.load(instream, "changeit".toCharArray());
        } finally {
            instream.close();
        }
        
        SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
        Scheme sch = new Scheme("https", socketFactory, 8443);
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
        // SSL setup end
        
        String secProviderName = "com.sun.crypto.provider.SunJCE";
        java.security.Provider secProvider = 
            (java.security.Provider)Class.forName(secProviderName).newInstance();
        Security.addProvider(secProvider);
        
        httpclient.getCredentialsProvider().setCredentials(
                new AuthScope("localhost", 8443), 
                new UsernamePasswordCredentials("user1001", "user1001"));
        
       HttpGet httpget = new HttpGet("https://localhost:8443/soba/restTx/txId/791111196");
        //HttpGet httpget = new HttpGet("https://p6620f:8443/soba/transactionList.htm?txId=227612809");
        httpget.addHeader("Accept", "application/json");
        System.out.println("executing request" + httpget.getRequestLine());
        
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();

        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());

        if (entity != null) {
            System.out.println("Response content length: " + entity.getContentLength());
            System.out.println("Response content type: " + entity.getContentType().getValue());
            BufferedReader reader = new BufferedReader 
            (new InputStreamReader (entity.getContent()));
            String line = reader.readLine();
            while (line != null) {
            	System.out.println (line);
            	line = reader.readLine ();
            }
        }
        if (entity != null) {
            entity.consumeContent();
        }

        // When HttpClient instance is no longer needed, 
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();        
    }

}
