package com.perfmath.spring.soba.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;

import javax.net.ssl.HttpsURLConnection;

import com.perfmath.spring.soba.model.domain.BankingTx;
import com.perfmath.spring.soba.util.RandomID;

public class RestTxControllerTest
{

   public void testRestTxController() throws Exception
   {
      System.out.println("*** Create a new tx ***");
      BankingTx tx = new BankingTx ();
		tx.setTransactionId(Integer.parseInt((new RandomID(9)).getId()));
		tx.setTransDate ( new Timestamp(System.currentTimeMillis()));
		tx.setType ("rest");
		tx.setInitiator ("rest");
		tx.setDescription ("rest");
		tx.setAmount (10.50);
		tx.setBalance (0.0);
		tx.setAccountId ("658376327");
		tx.setStatus ("rest");
		String txId = (new RandomID(9)).getId();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
      String newTx = "<transaction>"
              + "<transactionId>" + txId + "</transactionId>"
              + "<transDate>" + ts + "</transDate>"
              + "<type>rest</type>"
              + "<initiator>rest</initiator>"
              + "<description>rest test</description>"
              + "<amount>1.23</amount>"
              + "<balance>0</balance>"
              + "<accountId>658376327</accountId>"
              + "<status>pending</status>"
              + "</transaction>";

      
   
      URL postUrl = new URL("http://localhost:8080/soba/restTx");
      HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
      connection.setDoOutput(true);
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/xml");
      OutputStream os = connection.getOutputStream();
      
      os.write(newTx.getBytes());
      os.flush();
      //Assert.assertEquals(HttpURLConnection.HTTP_CREATED, connection.getResponseCode());
      System.out.println("Location: " + connection.getHeaderField("Location"));
      connection.disconnect();

      txId = "196278643";
      System.out.println("*** GET Created Tx **");
      URL getUrl = new URL("http://localhost:8080/soba/restTx/" + txId);
      connection = (HttpURLConnection) getUrl.openConnection();
      connection.setRequestMethod("GET");
      System.out.println("Content-Type: " + connection.getContentType());

      BufferedReader reader = new BufferedReader(new
              InputStreamReader(connection.getInputStream()));

      String line = reader.readLine();
      while (line != null)
      {
         System.out.println(line);
         line = reader.readLine();
      }
      connection.disconnect();

     /* // Update the new customer.  Change Bill's name to William
      String updateCustomer = "<customer>"
              + "<first-name>William</first-name>"
              + "<last-name>Burke</last-name>"
              + "<street>256 Clarendon Street</street>"
              + "<city>Boston</city>"
              + "<state>MA</state>"
              + "<zip>02115</zip>"
              + "<country>USA</country>"
              + "</customer>";
      connection = (HttpURLConnection) getUrl.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("PUT");
      connection.setRequestProperty("Content-Type", "application/xml");
      os = connection.getOutputStream();
      os.write(updateCustomer.getBytes());
      os.flush();
      Assert.assertEquals(HttpURLConnection.HTTP_NO_CONTENT, connection.getResponseCode());
      connection.disconnect();

      // Show the update
      System.out.println("**** After Update ***");
      connection = (HttpURLConnection) getUrl.openConnection();
      connection.setRequestMethod("GET");

      System.out.println("Content-Type: " + connection.getContentType());
      reader = new BufferedReader(new
              InputStreamReader(connection.getInputStream()));

      line = reader.readLine();
      while (line != null)
      {
         System.out.println(line);
         line = reader.readLine();
      }
      Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
      connection.disconnect();
      */
   }
}

