package com.accify;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProvaDB {
	
	    public static void main(String[] args) throws Exception {
	    
	    }
    
    	public static String readProjectName() throws Exception{	
    	// load the sqlite-JDBC driver using the current class loader
    	Class.forName("org.sqlite.JDBC");
    	Connection conn = null;
    	String projectname = null;
           
        try{
        conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select * from projects_list;");
        projectname = rs.getString("name");
        rs.close();
        }
        catch(SQLException e)
        {
          // connection close failed.
          System.err.println(e.getMessage());
        }
        
        try
        {
          if(conn != null)
            conn.close();
        }
        catch(SQLException e)
        {
          // connection close failed.
          System.err.println(e);
        }
      
        return projectname;
    	}
    	
    	public static ArrayList<String> readProjectLabels() throws Exception{
    		String projectname = readProjectName();
            Class.forName("org.sqlite.JDBC");
            Connection conn = null;
            ArrayList <String> result = new ArrayList<String>();
            
            try{
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from project_label WHERE name='"+projectname+"';");
            
           
            for (int i=1; rs.next(); i++)
            {
               result.add( rs.getString("label"));
            }
            
            rs.close();
            } catch(SQLException e){System.out.println(e.getMessage());}
            
              try
              {
                if(conn != null)
                  conn.close();
              }
              catch(SQLException e)
              {
                // connection close failed.
                System.err.println(e);
              }
              
			return result;
        }
    	
    	public static void addLabel(String label, String projectname) throws Exception{	
        	// load the sqlite-JDBC driver using the current class loader
        	Class.forName("org.sqlite.JDBC");
        	Connection conn = null;
               
            try{
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement stat = conn.createStatement();
            stat.executeUpdate("INSERT into project_label values ('"+projectname+"','"+label+"');");
            
            }
            catch(SQLException e)
            {
              // connection close failed.
              System.err.println(e.getMessage());
            }
            
            try
            {
              if(conn != null)
                conn.close();
            }
            catch(SQLException e)
            {
              // connection close failed.
              System.err.println(e);
            }
 
        }
    	
	
     
  }