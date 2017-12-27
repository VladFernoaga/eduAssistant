package ro.unitbv.eduassistant.util;

import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

public class ParserUtil {

	private ParserUtil(){}
	
	private static JSONParser parser = null;

	public static JSONParser getJSONParser(){
		if(parser == null){
			parser = new JSONParser();
		}
		return parser;
	}
	
	private static Gson gson = null;
	public static Gson getGson(){
		if (gson ==  null) {
			gson  = new Gson();
		}
		return gson;
	}
	
}
