import java.io.IOException;

import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;

public class HttpEndpoint {
	
	
	
	HTTPServer server;
	
	
	 static final Gauge  inprogressRequests = Gauge.build().name("inprogress_requests").help("Inprogress requests.").register();
		     

		
	
		  
		  HttpEndpoint() throws IOException
		  {
			  server = new HTTPServer(8009);
			 
		  }
		  
		  void processRequest() {
			    inprogressRequests.inc();
			    // Your code here.
			    inprogressRequests.dec();
			  }
//	
//private	HttpServer httpServe;
//private	ThreadPoolExecutor threadPoolExecutor;
//private	Logger logger;
//	
//	// constructor
//	HttpEndpoint() {
//		try {
//			httpServe = HttpServer.create(new InetSocketAddress("localhost", 8009), 0);
//			threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
//		} catch (Exception e) {
//			System.err.println(e);
//		}
//
//		httpServe.createContext("/test", new MyHttpHandler());
//
//		httpServe.setExecutor(threadPoolExecutor);
//
//		httpServe.start();
//
//		logger = Logger.getLogger("http");
//		logger.info(" Server started on port 8009");
//
//	}
//
//	//getters 
//	public Logger getLogger()
//	{
//		return logger;
//	}
//	public HttpServer getServer()
//	{
//		return httpServe;
//	}
//	public ThreadPoolExecutor getThreadPoolExecutor()
//	{
//		return threadPoolExecutor;
//	}
//	
//	
//	
//	private class MyHttpHandler implements HttpHandler {
//
//		@Override
//
//		public void handle(HttpExchange httpExchange) throws IOException {
//
//			String requestParamValue = null;
//
//			if ("GET".equals(httpExchange.getRequestMethod())) {
//
//				requestParamValue = handleGetRequest(httpExchange);
//
//			} else if ("POST".equals(httpExchange)) {
//
//				requestParamValue = handlePostRequest(httpExchange);
//
//			}
//
//			handleResponse(httpExchange, requestParamValue);
//
//		}
//
//		private String handleGetRequest(HttpExchange httpExchange) {
//
//			return httpExchange.
//
//					getRequestURI()
//
//					.toString()
//
//					.split("\\?")[1]
//
//							.split("=")[1];
//
//		}
//
//		private String handlePostRequest(HttpExchange httpExchange) {
//
//			return httpExchange.getResponseBody().toString();
//
//		}
//
//		private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
//
//			OutputStream outputStream = httpExchange.getResponseBody();
//
//			StringBuilder htmlBuilder = new StringBuilder();
//
//			htmlBuilder.append("<html>").
//
//					append("<body>").
//
//					append("<h1>").
//
//					append("Hello ")
//
//					.append(requestParamValue)
//
//					.append("</h1>")
//
//					.append("</body>")
//
//					.append("</html>");
//
//			// encode HTML content
//
//			String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder.toString());
//
//			// this line is a must
//
//			httpExchange.sendResponseHeaders(200, htmlResponse.length());
//
//			outputStream.write(htmlResponse.getBytes());
//
//			outputStream.flush();
//
//			outputStream.close();
//
//		}
//
//	}

}
