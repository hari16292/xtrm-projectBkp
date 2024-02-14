package com.xtrm.webapi;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;


public class Checkassertions {
	  public static String mail() {
		String err="OK";
		  System.setProperty("webdriver.chrome.driver", "D:\\Automation\\xtrm-webapi\\Drivers\\chromedriver.exe");
			WebDriver driver=(WebDriver) new ChromeDriver();
	    driver.get("https://www.mailinator.com/");
	    driver.manage().window().setSize(new Dimension(1282, 816));
	    driver.findElement(By.id("search")).click();
	    driver.findElement(By.id("search")).sendKeys("afexuser1@mailinator.com");
	    driver.findElement(By.cssSelector(".g5core-top-bar-desktop button")).click();
	    driver.findElement(By.cssSelector("#row_afexuser1-1644258374-19695412 > .ng-binding:nth-child(3)")).click();
	    driver.switchTo().frame(0);
	    driver.findElement(By.cssSelector("span")).click();
	    driver.findElement(By.cssSelector("span")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector("span"));
	      Actions builder = new Actions(driver);
	      builder.doubleClick(element).perform();
	    }
	    try {
		//	vars.put("window_handles", driver.getWindowHandles());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    driver.findElement(By.linkText("Help")).click();
	    System.out.println("Here"+ "_"+err);
		return err;
		
		
	   
	  }
	public static String PatternCheck(String parameters,String response)
	{
		
		  parameters = parameters.substring(parameters.lastIndexOf("("), parameters.indexOf(")"));
		    parameters = parameters.replace("(", "");
		    parameters = parameters.replace(")", "");
		    String splitParam[] = parameters.split(",");

		    String regex1 = splitParam[0];
		    String expected = splitParam[2];
		    
		// read files provided and call the checkPattern1 function
		String sErrorLog="";
		String regex="";
		String response1 = response;
		String regex1fileName= regex1.replace("\"", "");
		String expectedfileName= expected.replace("\"", "");
		if(regex1.equals("")||response.equals("")||expected.equals(""))
		{
			sErrorLog += "Input files not provided for regex1 or response or expected";
		}
		else
		{
		try {
			// retrive data of regex file
		BufferedReader reader = new BufferedReader(new FileReader(regex1fileName));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		
			reader.close();
			regex = stringBuilder.toString();
			
						
					// retrive data of expected json file
					BufferedReader reader2 = new BufferedReader(new FileReader(expectedfileName));
					StringBuilder stringBuilder2 = new StringBuilder();
					String line2 = null;
					String ls2 = System.getProperty("line.separator");
					while ((line2 = reader2.readLine()) != null) {
						stringBuilder2.append(line2);
						stringBuilder2.append(ls2);
					}
					// delete the last new line separator
					stringBuilder2.deleteCharAt(stringBuilder2.length()-2);
					
						reader2.close();
						expected = stringBuilder2.toString();
						
		
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		sErrorLog += CommonFunctions.compareResponsesJson(regex,response1, expected);
		}
		return sErrorLog;
	}

	  public static String checkPattern(String parameters, String response) throws IOException {
		    parameters = parameters.substring(parameters.lastIndexOf("("), parameters.indexOf(")"));
		    parameters = parameters.replace("(", "");
		    parameters = parameters.replace(")", "").replace("\"","");
		    String splitParam[] = parameters.split(",");

		    String schema = response;
		    		//splitParam[0].replace("\\", "");
		    String responsejson = splitParam[0];
		 	String sErrLog = "";
		   String path="D:\\schema.txt";
		   try {
	            String str = schema;
	            File newTextFile = new File(path);

	            FileWriter fw = new FileWriter(newTextFile);
	            fw.write(str);
	            fw.close();

	        } catch (IOException iox) {
	            //do stuff with exception
	            iox.printStackTrace();
	        }
		   
				    	
		 JsonSchema schema1 = null;
			ProcessingReport report1 = null;
			JsonNode fstabSchema = JsonLoader.fromFile(new File(responsejson));
		JsonNode good = JsonLoader.fromFile(new File(path));
		JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

			try {
				schema1 = factory.getJsonSchema(fstabSchema);

				report1 = schema1.validate(good, true);
				sErrLog=report1.toString();
			}
	
			 catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		 //  System.out.println(sErrLog);
			if(sErrLog.contains("success"))
			{
				sErrLog="";
			}
			
		return sErrLog;
		
		 }
	
public static String checkPattern1(String schemadef, String data) throws IOException {
	   String sErrLog = "";
			    	
	   JSONObject jsonObject = new JSONObject(data); 
	  JSONObject jsonSchema = new JSONObject(schemadef);
	    JSONObject jsonData = new JSONObject(jsonObject);
	    
	    Schema schema = SchemaLoader.load(jsonSchema);
	    schema.validate(jsonData);
	return sErrLog;
	
	 }

}
