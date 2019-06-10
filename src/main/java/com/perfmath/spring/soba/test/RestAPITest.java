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
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class RestAPITest {

    public final static void main(String[] args) throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        /*
     // SSL setup begin
      
        KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());        
        //FileInputStream instream = new FileInputStream(new File("C:\\Documents and Settings\\HELIU\\.keystore"));
        FileInputStream instream = new FileInputStream(new File("C:\\CLM1.5\\CLM_certs\\bmcatriumwsclient.jks"));
        try {
            //trustStore.load(instream, "changeit".toCharArray());
            trustStore.load(instream, "atrium".toCharArray());
        } finally {
            instream.close();
        }
        
        SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
        Scheme sch = new Scheme("https", socketFactory, 8443);
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
        // SSL setup end
        */
        String secProviderName = "com.sun.crypto.provider.SunJCE";
        java.security.Provider secProvider = 
            (java.security.Provider)Class.forName(secProviderName).newInstance();
        Security.addProvider(secProvider);
       
        httpclient.getCredentialsProvider().setCredentials(
                new AuthScope("mi3", 8080), 
                new UsernamePasswordCredentials("csmadmin1", "csmadmin1"));
        HttpGet httpget = new HttpGet("http://mi3:8080/csm/Pod/6e513e63-cf70-4953-ab20-28e3b4b17656");
        /*
        httpclient.getCredentialsProvider().setCredentials(
                new AuthScope("mirabeau.eng.remedy.com", 8080), 
                new UsernamePasswordCredentials("csmadmin1", "csmadmin1"));
        
        HttpGet httpget = new HttpGet("http://mirabeau.eng.remedy.com:8080/csm/Pod/search");
        */
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
