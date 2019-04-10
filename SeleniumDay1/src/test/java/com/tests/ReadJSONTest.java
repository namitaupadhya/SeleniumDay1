package com.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class ReadJSONTest 
{
	@Test
	public static Object testJSON() 
	{
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		Object obj = null;
		
		try (FileReader reader = new FileReader("src\\test\\resources\\employees.json"))
		{
			//Read JSON file
             obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);
            
            //Iterate over employee array
           employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
        } 
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		catch (ParseException e) {
            e.printStackTrace();
        }
		return obj;
	}

	private static void parseEmployeeObject(JSONObject employee) 
	{
		//Get employee object within list
		JSONObject employeeObject =  (JSONObject) employee.get("users");
		
		//Get employee first name
		String email = (String) employeeObject.get("email");	
		System.out.println(email);
		
		//Get employee last name
		String password = (String) employeeObject.get("password");	
		System.out.println(password);
	
		JSONArray empphone=(JSONArray)employeeObject.get("phoneno");
		System.out.println(empphone.get(0) +","+empphone.get(1));
	}
}
