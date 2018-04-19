package samueltoepke;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
public class TimeController {
	
    @RequestMapping("/gettime")
    public String getTips(@RequestParam(value="tz", defaultValue="GMT") String tz) throws IOException, ParseException {
        String proc = "  getTime: ";
		String togo = "";
		///System.out.println(proc + "STARTING.");

		///////////////////
		// 0. Error Checking
		
		// Check that tz is populated.
		if (StringUtils.isEmpty(tz) == true) 
			return populateErrorMessage("ERROR. tz for getTime() is not populated. Pass 'GMT' or proper tz from list: https://garygregory.wordpress.com/2013/06/18/what-are-the-java-timezone-ids/");
		
		// Check that tz is a legal value.
		boolean legalTZ = false;
		String[] ids = TimeZone.getAvailableIDs();
		for (String id : ids) {
			if (TimeZone.getTimeZone(id).getID().equals(tz)) {
				legalTZ = true;
				break;
			}
		}
		
		if (legalTZ == false)
			return populateErrorMessage("ERROR. tz for getTime() is of an incorrect format. Pass 'GMT' or proper tz from list: https://garygregory.wordpress.com/2013/06/18/what-are-the-java-timezone-ids/");
		
		///////////////////
		// 1. Get Time String		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(tz));

		Date now = new Date();
		String strDate = sdf.format(now);
		
		// Add time fields to JSON object to return.
		JsonObject jo = new JsonObject();
		jo.addProperty("time_string", strDate);
		jo.addProperty("tz", tz);
		togo = jo.toString();

		///System.out.println(proc + "ENDING.");
		return togo;
    }

	/*
	 * populateErrorMessage(). Takes a non-null String, and creates/returns
	 *   a JSON error message.
	 *   
	 * @author Samuel Lee Toepke
	 * @param Error message
	 * @return String representation in JSON.
	 * 
	 * @version 1.0
	 */
	private static String populateErrorMessage(String message) {
		String togo = "";
		
		if (StringUtils.isEmpty(message) ) { 
			System.err.println("ERROR. populateErrorMessage() contains an improper input value.");
			System.exit(1);
		}
			    
		JsonObject jo = new JsonObject();
		jo.addProperty("status", "400");
		jo.addProperty("error", "Bad Request");
		jo.addProperty("message", message);
		togo = jo.toString();
	
		return togo;
	}
}
