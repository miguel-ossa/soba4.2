package com.perfmath.spring.soba.test;
import java.sql.*;

public class JDBCConnectionTestPSQL
{
    public static void main (String[] args)
    {
        Connection conn = null;

        try
        {
            //String userName = "postgres";
            //String password = "postgres";
            String userName = "soba32admin";
            String password = "soba32admin";
            String url = "jdbc:postgresql://localhost:5432/soba32";
            Class.forName ("org.postgresql.Driver").newInstance ();
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("Database connection established");
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server: " + e.getMessage());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
    }
}
