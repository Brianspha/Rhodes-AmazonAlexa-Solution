import static spark.Spark.*;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {        

    	// Route called by skill interface
    	post("/alexa", (request, response) -> {
    		
    		// Get request type
    		JSONObject requestObject = new JSONObject(request.body()); 		
    		String requestType =  ResponseService.getRequestType(requestObject);
    
    		// Construct the response
    		JSONObject responseJson = ResponseService.constructResponseObject(requestObject, requestType);
    		                		
    		return responseJson;
    	});
    }
}