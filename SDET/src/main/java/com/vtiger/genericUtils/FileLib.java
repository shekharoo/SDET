package com.vtiger.genericUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileLib {
	
	public static String readPropertyFile(String key) throws IOException
	{
		FileInputStream fin = new FileInputStream("./resources/testData.properties");
		Properties prop = new Properties();
		prop.load(fin);
		String value = prop.getProperty(key);
		return value;
	}

}
