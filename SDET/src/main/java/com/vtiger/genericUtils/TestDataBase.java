package com.vtiger.genericUtils;

import java.io.IOException;
import java.sql.SQLException;

public class TestDataBase {

	public static void main(String[] args) throws SQLException, IOException {
		DataBaseLib dbl = new DataBaseLib();
		dbl.establishConn(FileLib.readPropertyFile("url"),FileLib.readPropertyFile("username"),FileLib.readPropertyFile("password"));
		dbl.selectQuery(FileLib.readPropertyFile("url"),FileLib.readPropertyFile("username"),FileLib.readPropertyFile("password"),FileLib.readPropertyFile("query"));
        //dbl.insertQuery(FileLib.readPropertyFile("url"),FileLib.readPropertyFile("username"),FileLib.readPropertyFile("password"),FileLib.readPropertyFile("insertquery"));
        dbl.executeAndValidateData(FileLib.readPropertyFile("url"),FileLib.readPropertyFile("username"),FileLib.readPropertyFile("password"),FileLib.readPropertyFile("query"), 1,FileLib.readPropertyFile("expValue") );	
	    //dbl.
	}

}
