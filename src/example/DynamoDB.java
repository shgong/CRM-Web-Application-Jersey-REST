package example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;


public class DynamoDB{

	static AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider("default").getCredentials());
    static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    
    public static void main(String[] args) throws Exception {
         client.setRegion(Region.getRegion(Regions.US_WEST_2)); 
        try {
        // Get an item.
        getBook("101", "ProductCatalog");
        }  
        catch (AmazonServiceException ase) {
            System.err.println(ase.getMessage());
        }  
    }
    
    public static GetItemResult getBook(String id, String tableName) {
    	client.setRegion(Region.getRegion(Regions.US_WEST_2)); 
        Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("ID", new AttributeValue().withN(id));
        
        GetItemRequest getItemRequest = new GetItemRequest()
            .withTableName(tableName)
            .withKey(key)
            .withProjectionExpression("ID,Color, Description, Link, SKU");
        
        GetItemResult result = client.getItem(getItemRequest);
        
        // Check the response.
       //  System.out.println("SKU "+ getAttribute("SKU",result.getItem()));
       //  printItem(result.getItem());      
         
        return result;
    }
    
    public static void printItem(Map<String, AttributeValue> attributeList) {
        for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
            String attributeName = item.getKey();
            AttributeValue value = item.getValue();
            System.out.println(attributeName + " "
                    + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
                    + (value.getN() == null ? "" : "N=[" + value.getN() + "] \n"));
        }
    }
    
    public static String getAttribute(String attribute_name,Map<String, AttributeValue> attributeList){
    	 for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
             String attributeName = item.getKey();
             AttributeValue value = item.getValue();
             if(attributeName==attribute_name)
            	   return value.getS();
         }
    	 return "";
    }
}
