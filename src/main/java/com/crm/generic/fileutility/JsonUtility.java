package com.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtility {
	public String getDataFromJsonFile(String key) throws Throwable
	{
		FileReader fileR=new FileReader("./configAppData/common.json");
		JSONParser parser=new JSONParser();
		 Object obj = parser.parse(fileR);
		
		//step 2: convert the object in to JSONobject using down casting
		JSONObject map = (JSONObject)obj;
		
		//step 3: get the value from json file using key
		//System.out.println("url");
		 String data = (String) map.get(key);//cast to string
		 
		 return data;
		
		
	}

}
