

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpPostData {
	  

	//Http Objects
	        HttpClient client;
	        HttpRequest request;
	        HttpResponse<String> response;
	        
	  URL url;
	  HttpURLConnection con;
	  
	  HttpPostData() throws IOException, InterruptedException{
		  //initialize the HttpClient
		  client = HttpClient.newHttpClient();
		  //initialize the request
		  request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8009")).build();
		  //initialize the response
		  response = client.send(request,  HttpResponse.BodyHandlers.ofString());
		  
		  //print the response to test it
		  System.out.println(response.body());
		  
		  
		  url = new URL ("http://localhost:8009");
		  con = (HttpURLConnection)url.openConnection();
		  con.setRequestMethod("POST");
		  con.setRequestProperty("Content-Type", "application/json; utf-8");
		  con.setRequestProperty("Accept", "application/json");
		  con.setDoOutput(true);
		  String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";

		  try(OutputStream os = con.getOutputStream()) {
			    byte[] input = jsonInputString.getBytes("utf-8");
			    os.write(input, 0, input.length);			
			}
		  try(BufferedReader br = new BufferedReader(
				  new InputStreamReader(con.getInputStream(), "utf-8"))) {
				    StringBuilder response = new StringBuilder();
				    String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				        response.append(responseLine.trim());
				    }
				    System.out.println(response.toString());
				}
	  }
	  
	  public static void main(String args[]) throws IOException, InterruptedException
	  {
		  HttpEndpoint test1 = new HttpEndpoint();
		 HttpPostData test = new HttpPostData();
		  //post
//		  var values = new HashMap<String, String>() {{
//	            put("name", "John Doe");
//	            put ("occupation", "gardener");
//	        }};
//
//	        
//	        
//	        
//	        var objectMapper = new ObjectMapper();
//	        String requestBody = objectMapper
//	                .writeValueAsString(values);
//
//	        HttpClient client = HttpClient.newHttpClient();
//	        HttpRequest request = HttpRequest.newBuilder()
//	                .uri(URI.create("https://localhost:8009"))
//	                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//	                .build();
//
//	        HttpResponse<String> response = client.send(request,
//	                HttpResponse.BodyHandlers.ofString());
//
//	        System.out.println(response.body());
//		  
//	        
	        
	  }
}
