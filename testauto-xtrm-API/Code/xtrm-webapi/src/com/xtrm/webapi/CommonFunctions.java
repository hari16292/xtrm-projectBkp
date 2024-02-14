package com.xtrm.webapi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;



public class CommonFunctions {
	 public CommonFunctions() {
	  }

	  public static Matcher matchRegex(String inputStr, String regex) {
	    Matcher match = Pattern.compile(regex).matcher(inputStr);
	    if (match.find()) {
	      return match;
	    } else {
	      return null;
	    }
	  }

	  public static String doRegexMatch(String regexPattern, String sourceString, int groupId) {
	    try {
	      Matcher match = matchRegex(sourceString, regexPattern);
	      if(match!=null) {
	      regexPattern = match.group(groupId);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	      return regexPattern;
	  }

	  public static String compareString(String expectedString, String resultString, String compareElement) {
	    String sErrLog = "";
	    if (!resultString.equals(expectedString))
	      sErrLog = (new StringBuilder(String.valueOf(sErrLog))).append("Did not recieve the expected ").append(compareElement).append(". Expected ").append(expectedString).append(" but recieved ")
	          .append(resultString).toString()+". ";
	    return sErrLog;
	  }

	  public static String checkCallBack(String isCallBack, String response, String callBackFnName) {
	    String sErrLog = "";
	    if (isCallBack.equals("true")) {
	      if(!(response.toLowerCase().startsWith(callBackFnName.toLowerCase()))) {
	        sErrLog = (new StringBuilder(String.valueOf(sErrLog))).append("The response does not start with the callback function name. ").toString();
	      }
	    }
	    return sErrLog;
	  }

	  public static String doMultipleRegexMatch(String regexResults, String resource, int groupID1, int groupID2, int groupID3) {
	    String regex = "";
	    try {
	      String regex1 = doRegexMatch(regexResults, resource, groupID1);
	      String regex2 = doRegexMatch(regexResults, resource, groupID2);
	      String regex3 = doRegexMatch(regexResults, resource, groupID3);
	      regex = (new StringBuilder(String.valueOf(regex1))).append(",").append(regex2).append(",").append(regex3).toString();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return regex;
	  }

	  public static String compareMultipleRegexGroups(String regex, String expectedResult, String actualResult, String element1, String element2, String element3) {
	    String sErrLog = "";
	    sErrLog = (new StringBuilder(String.valueOf(sErrLog))).append(compareString(doRegexMatch(regex, expectedResult, 1), doRegexMatch(regex, actualResult, 1), element1)).toString();
	    sErrLog = (new StringBuilder(String.valueOf(sErrLog))).append(compareString(doRegexMatch(regex, expectedResult, 2), doRegexMatch(regex, actualResult, 2), element2)).toString();
	    sErrLog = (new StringBuilder(String.valueOf(sErrLog))).append(compareString(doRegexMatch(regex, expectedResult, 3), doRegexMatch(regex, actualResult, 3), element3)).toString();
	    return sErrLog;
	  }

	
	  
	  public static int findDuplicateWords(String splitter, String input, String findWord) {
	    int i = 0;
	    List<String> list = Arrays.asList(input.split(splitter));
	    Set<String> uniqueWords = new HashSet<String>(list);
	    for (String word : uniqueWords) {
	      if(word.equals(findWord)) {
	        i = Collections.frequency(list, word);
	      }
	    }
	    return i;
	}
	 
	

	  public static String compareResponsesJson(String regex, String response, String expectedResponse) {
	      String sErrorLog = "";
	      
	      regex = Pattern.quote(regex);
	      
	      Matcher match = Pattern.compile(regex).matcher(response);
	      
	      if(!match.find()) {
	          String substring = response.substring(response.indexOf("{\""), response.length()).replace("\\", "").replace("\n", "");
	         
	          String expectedSubString = expectedResponse.substring(expectedResponse.indexOf("{\""), expectedResponse.length()).replace("\\", "").replace("\n", "");
	          
	          if(substring.length()!=expectedSubString.length()) {
	              sErrorLog += "\nThe number of characters does not match "+substring.length()+", "+expectedSubString.length();
	          } else {
	              int storeDiffIndex = 0;
	              int diffIndex = 0;
	              while (diffIndex!=-1) {
	                  diffIndex = StringUtils.indexOfDifference(substring, expectedSubString);
	                  storeDiffIndex = storeDiffIndex + diffIndex;
	                  if(diffIndex!=-1) {
	                      String expectedChar = expectedSubString.substring(diffIndex, diffIndex+1);
	                      String diffChar = substring.substring(diffIndex, diffIndex+1);
	                      sErrorLog += ("\nAt char "+storeDiffIndex+" from the following pattern, expected : "+expectedChar+", actual : "+diffChar);
	                      substring = substring.substring(diffIndex+1, substring.length());
	                      expectedSubString = expectedSubString.substring(diffIndex+1, expectedSubString.length());
	                  }
	              }
	          }
	              
	              substring = response.substring(response.indexOf("{\""), response.length());
	              expectedSubString = expectedResponse.substring(expectedResponse.indexOf("{\""), expectedResponse.length());
	              
	              if(!sErrorLog.equals("")) {
	                  sErrorLog += "\n\nExpected pattern : "+expectedSubString;
	                  sErrorLog += "\n\nActual pattern : "+substring;
	                  
	                  sErrorLog += "\n\nUnmatched words : "+diff(substring, expectedSubString);
	                  
	              
	          }
	      }
	      return sErrorLog;
	  }
	    
	  
	 public static String diff(String str1, String str2) {
	     String sErrLog = "";
	     String[] strList1 = str1.split(" ");
	     String[] strList2 = str2.split(" ");

	     List<String> list1 = Arrays.asList(strList1);
	     List<String> list2 = Arrays.asList(strList2);

	     // Prepare a union
	     List<String> union = new ArrayList<>(list1);
	     union.addAll(list2);

	     // Prepare an intersection
	     List<String> intersection = new ArrayList<>(list1);
	     intersection.retainAll(list2);

	     // Subtract the intersection from the union
	     union.removeAll(intersection);

	     for (String s : union) {
	        sErrLog += ("\n"+s);
	     }
	     return sErrLog;
	 }

	 
	  /**
	   * Count the number of lines in a csv file except the header
	   * @param filePath
	   * @return
	   */
	  public static int countLinesInFile(String filePath){
	  BufferedReader reader;
	  int lines = 0;
	  try {
	    reader = new BufferedReader(new FileReader(filePath));
	    while (reader.readLine() != null) lines++;
	    reader.close();
	  } catch (FileNotFoundException e) {
	    e.printStackTrace();
	  } catch (IOException e) {
	    e.printStackTrace();
	  }
	  // skip first row as it is the header
	  return lines-1;
	  }
}
