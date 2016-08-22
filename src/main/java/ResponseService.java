import static spark.Spark.post;

import java.util.Random;

import org.json.JSONObject;
public class ResponseService {
	static // Get request type from JSON request
	boolean GoneIn = false;
	static String amnt = "";
	static String year = "";
	static String previousIntent = "",OptionOne="",Optiontwo="";
	static String outputSpeechText;
	static String Answer = "";
	public static String getRequestType(JSONObject obj){
		
		JSONObject requestTypeObject = obj.getJSONObject("request");
		String requestType =  requestTypeObject.getString("type");
		return requestType;
	}
	// Get slot value for the IntentRequest
	public static String getSlotValue(JSONObject obj){
		JSONObject requestTypeObject = obj.getJSONObject("request");
		JSONObject intentObject = requestTypeObject.getJSONObject("intent");
		JSONObject slotsObject = intentObject.getJSONObject("slots");
		String name = slotsObject.getJSONObject("").getString("value");
		return name;
	}
	
	public static int ParseDouble(String g){//this method parses double values returned by alexa she usualyy returns amount in the form of 5,500 so we have to format this to 5500
		String [] no = g.split(",");
		String temp = "";
		for(int i =0; i <no.length;i++)temp+=no[i];
		System.out.println(temp);
		return Integer.parseInt(temp);
	}
	// Construct the JSON response sent back to the skill service
	public static JSONObject constructResponseObject(JSONObject obj, String requestType){
		
		
		String outputSpeechType = "SSML";	
		
		String Value="",Value2="";
		QuestionParser p = new QuestionParser();
		if (requestType.equals("LaunchRequest")){
			outputSpeechText = "What question do you want ask?";
			outputSpeechType = "PlainText";			
		}
		else if (requestType.equals("IntentRequest")){
			System.out.println(obj.getJSONObject("request"));
			JSONObject requestTypeObject = obj.getJSONObject("request");
			String intentName = requestTypeObject.getJSONObject("intent").getString("name");
			JSONObject slots = requestTypeObject.getJSONObject("intent").getJSONObject("slots");
			String [] k =slots.getNames(slots);
			for(int i =0; i < k.length;i++){System.out.println(k[i]);
			String V = slots.getJSONObject(k[i]).getString("value");//this gets the general response or the general value given by user where only one user input is required
			String Amount ="";
			if(i +1 < k.length) Amount =slots.getJSONObject(k[i+1]).getString("value"); 
			System.out.println("Years: "+V + " Amount " + Amount);	
			//Value = GetResponse(V);
			Value=V.toLowerCase();
			Value2=Amount.toLowerCase();
			//System.out.print("Value: " + Value + " Value2: " + Value2);
			break;
			}
			amnt=Value2;
			year=Value;
			if(intentName.equals("FAQ")){
				//String ssmlName = "<say-as interpret-as=\"spell-out\">" + Answer + "</say-as>";			
				FAQ(Value);
				previousIntent = "FAQ";
				outputSpeechText = "<speak>" +FAQ(Value)+"</speak>";
			}
			if(intentName.equals("Savings")){
			   // intentName = "InvestmentOptions"; 			 
				
				outputSpeechText = "<speak>" +Savings(Value2,Value)+"</speak>";
				GoneIn=true;
			}
			//Answer;
			System.out.println("I have answer: " + outputSpeechText);
			outputSpeechType = "SSML";
			previousIntent = "InvestmentOptions";
		}
		else{
//			"outputSpeech": {
//		    "type": "SSML",
//		    "ssml": "<speak>This output speech uses SSML.</speak>"
//		}
		
			// Blank response for SessionEndRequest
			//outputSpeechText = "";
			//outputSpeechType = "PlainText";	
		}
		// Construct JSON response package
		JSONObject outputSpeechElement = new JSONObject();
		outputSpeechElement.put("type", outputSpeechType);
		outputSpeechElement.put("ssml", outputSpeechText);
		JSONObject outputSpeech = new JSONObject();
		outputSpeech.put("outputSpeech", outputSpeechElement);
		JSONObject responseObject = new JSONObject();
		responseObject.put("response", outputSpeech);
			System.out.println("Got here bitch" + responseObject.toString());
		return responseObject;
}
	public static String FAQ(String Value) {
		String[] QandAs = QuestionParser.ReadQA("C:/Users/Developer/Desktop/finance.txt");
		String[] Questions = new String[QandAs.length/2];
		String[] Answers = new String[QandAs.length/2];
		for(int i = 0; i < QandAs.length; i++)
		{
			if(i%2==0)Questions[i/2] = QandAs[i];
			else Answers[i/2] = QandAs[i];
		}
		for(int i = 0; i < Questions.length; i++)
		{
			if(Value.equals(Questions[i])){
				Answer = Answers[i];
				System.out.println(Answer);
			}
		//	System.out.println("Questions[i] is " + Questions[i]);
			//System.out.println("Answers[i] is " + Answers[i]);
		}
		return Answer;
	}
	public static String Savings(String Value2,String Value){
		//outputSpeechText = "You co Possible Investment Option for the amount you just gave me";
	    //OutSpeak(GetRes(outputSpeechText));
	 Answer = "Based on the amount you have provided i have found several investment options" +"<p>"+"You could Invest the amount you have provided into a Normal Savings account "+"</p>" +WolframAlphaClone.compoundfinal(ParseDouble(Value2), Integer.parseInt(Value)) +WolframAlphaClone.compoundfinal2(ParseDouble(Value2), Integer.parseInt(Value))+WolframAlphaClone.compoundfinal3(ParseDouble(Value2), Integer.parseInt(Value)) ;
        System.out.println(Answer);
        return Answer;
	}
	public static JSONObject GetRes(String g){
		JSONObject outputSpeechElement = new JSONObject();
		outputSpeechElement.put("type", "SSML");
		outputSpeechElement.put("ssml",g);
		JSONObject outputSpeech = new JSONObject();
		outputSpeech.put("outputSpeech", outputSpeechElement);
		JSONObject responseObject = new JSONObject();
		responseObject.put("response", outputSpeech);
	//	System.out.println("Got Here");
		return responseObject;
	}
	public static void OutSpeak(JSONObject Output){
		//System.out.println(Output.toString() + "Response");
	post("/alexa", (request, response) -> {
    		// Construct the response
		GoneIn =true;
		    
    		//JSONObject responseJson =Output;    		                		
    		return Output;
    	});
	}
}
