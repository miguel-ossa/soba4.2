package com.perfmath.spring.soba.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Security;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * A simple example that uses HttpClient to execute an HTTP request against
 * a target site that requires user authentication. 
 */
public class ClientAuthentication {

    public static void main(String[] args) throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String secProviderName = "com.sun.crypto.provider.SunJCE";
        java.security.Provider secProvider = 
            (java.security.Provider)Class.forName(secProviderName).newInstance();
        Security.addProvider(secProvider);

        httpclient.getCredentialsProvider().setCredentials(
                new AuthScope("localhost", 8443), 
                new UsernamePasswordCredentials("user1001", "user1001"));
        
        HttpGet httpget = new HttpGet("https://localhost:8443/soba/restTx/txId/791111196");
        httpget.addHeader("Accept", "application/xml");
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

