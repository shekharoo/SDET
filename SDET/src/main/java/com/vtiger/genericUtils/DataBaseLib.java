package com.vtiger.genericUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;
/**
 * 
 * @author SHEKHAR
 *
 */
public class DataBaseLib {
	
	Connection con;
	/**@author SHEKHAR
	 * Method is used to establish Database Connection
	 * @param url
	 * @param username
	 * @param psswd
	 * @return Statement
	 * @throws SQLException
	 */
	public Statement establishConn(String url,String username,String psswd) throws SQLException
	{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, username, psswd);
		Statement stmt = con.createStatement();
		return stmt;
	}
	/**@author SHEKHAR
	 * Method is used to execute Query
	 * @param url
	 * @param username
	 * @param psswd
	 * @param query
	 * @throws SQLException
	 */
	public void selectQuery(String url,String username,String psswd,String query) throws SQLException
	{
		Statement stmt = establishConn(url, username, psswd);
		ResultSet res = stmt.executeQuery(query);
		while(res.next())
		{
			System.out.println(res.getString(1)+"\t" +res.getString(2)+ "\t" +res.getString(3)+ "\t" +res.getInt(4));
		}
		con.close();
	}
	/**@author SHEKHAR
	 * This method is used to insert query
	 * @param url
	 * @param username
	 * @param psswd
	 * @param query
	 * @return int
	 * @throws SQLException
	 */
	public int insertQuery(String url,String username,String psswd,String query) throws SQLException
	{
		Statement stmt = establishConn(url, username, psswd);
		int statusCode = stmt.executeUpdate(query);
		System.out.println("Status code is: "+statusCode);
		con.close();
		return statusCode;
	}
	/**@author SHEKHAR
	 * This method is used to xecuteAndValidateData for String expected data
	 * @param url
	 * @param username
	 * @param psswd
	 * @param query
	 * @param colNo
	 * @param expValue
	 * @throws SQLException
	 */
	public void executeAndValidateData(String url,String username,String psswd,String query,int colNo,String expValue) throws SQLException
	{
		boolean flag = false;
		Statement stmt = establishConn(url, username, psswd);
		ResultSet res = stmt.executeQuery(query);
		while(res.next())
		{
			if(res.getString(colNo).equals(expValue))
			{
				flag = true;
				break;
			}
		}
		if(flag)
		{
			System.out.println("Expected value matches with the actual value");
		}
		else
		{
			System.out.println("Expected value does not matches with the actual value");
		}
	}
	/**@author SHEKHAR
	 * This method is used to xecuteAndValidateData for int expected data
	 * @param url
	 * @param username
	 * @param psswd
	 * @param query
	 * @param colNo
	 * @param expValue
	 * @throws SQLException
	 */
	public void executeAndValidateData(String url,String username,String psswd,String query,int colNo,int expValue) throws SQLException
	{
		boolean flag = false;
		Statement stmt = establishConn(url, username, psswd);
		ResultSet res = stmt.executeQuery(query);
		while(res.next())
		{
			if(res.getInt(colNo)==(expValue))
			{
				flag = true;
				break;
			}
		}
		if(flag)
		{
			System.out.println("Expected value matches with the actual value");
		}
		else
		{
			System.out.println("Expected value does not matches with the actual value");
		}
	}



}
