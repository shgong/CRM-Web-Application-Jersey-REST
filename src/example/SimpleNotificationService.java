package example;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;

public class SimpleNotificationService {

	 public static void send (String msg)  {

	    	//create a new SNS client and set endpoint
	    			AmazonSNSClient snsClient = new AmazonSNSClient(new ClasspathPropertiesFileCredentialsProvider());		                           
	    			snsClient.setRegion(Region.getRegion(Regions.US_WEST_2));

	    		/*	//create a new SNS topic
	    			CreateTopicRequest createTopicRequest = new CreateTopicRequest("MyNewTopic");
	    			CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);
	    			//print TopicArn
	    			System.out.println(createTopicResult);
	    			//get request id for CreateTopicRequest from SNS metadata		
	    			System.out.println("CreateTopicRequest - " + snsClient.getCachedResponseMetadata(createTopicRequest)); 
	    			*/
	    			
	    			String topicArn="arn:aws:sns:us-west-2:823392445141:MySNSTopic";
	    			//subscribe to an SNS topic
	    		/*	SubscribeRequest subRequest = new SubscribeRequest(topicArn, "email", "yangxiangliang59@gmail.com");
	    			snsClient.subscribe(subRequest);
	    			//get request id for SubscribeRequest from SNS metadata
	    			System.out.println("SubscribeRequest - " + snsClient.getCachedResponseMetadata(subRequest));
	    			System.out.println("Check your email and confirm subscription.");
	    		*/	
	    			
	    			//publish to an SNS topic
	    			//String msg = "Hello World! This is my first AWS SNS text!";
	    			PublishRequest publishRequest = new PublishRequest(topicArn, msg);
	    			PublishResult publishResult = snsClient.publish(publishRequest);
	    			//print MessageId of message published to SNS topic
	    			System.out.println("AWS SNS  MessageId - " + publishResult.getMessageId());
	    			
	    		/*	//delete an SNS topic
	    			DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(topicArn);
	    			snsClient.deleteTopic(deleteTopicRequest);
	    			//get request id for DeleteTopicRequest from SNS metadata
	    			System.out.println("DeleteTopicRequest - " + snsClient.getCachedResponseMetadata(deleteTopicRequest));
	    			*/
	    			
	    }
}
