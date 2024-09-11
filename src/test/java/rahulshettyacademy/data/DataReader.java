package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.JsonMappingException;//manually typed
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.JsonProcessingException; //manually typed
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

public List<HashMap<String, String>> getJsonDataToMap() throws IOException
{
	//read json to String
	String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test///java//rahulshettyacademy//data//PurchaseOrder.json"), StandardCharsets.UTF_8);
			
	//convert String to HashMap-Jackson Databind
	//go to mvn repository and get it and paste it in pon.xml
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
	});
	return data;
	
	//{map,map1}
	}
			
	
	
}





