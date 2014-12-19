package example;

import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class MessageQueue {
	
	static AmazonSQS sqs;
	static String QueueName;
	
	public MessageQueue(String args){
		QueueName=args;
		AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider("default").getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (/Users/yangxiangliang/.aws/credentials), and is in valid format.",
                    e);
        }
        sqs = new AmazonSQSClient(credentials);
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
        sqs.setRegion(usWest2);

        System.out.println("===========================================");
        System.out.println("Getting Started with Amazon SQS");
        System.out.println("===========================================\n");
        }
	
     public  String intialQueue(){ 
         String myQueueUrl=null;
        try {
            // Create a queue
            System.out.println("Creating a new SQS queue called  "+QueueName+"\n");
            CreateQueueRequest createQueueRequest = new CreateQueueRequest(QueueName);
            myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
           // List queues
            System.out.println("Listing all queues in your account.\n");
            for (String queueUrl : sqs.listQueues().getQueueUrls()) {
                System.out.println("  QueueUrl: " + queueUrl);
            }
            System.out.println();
            }
        catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it " +
                    "to Amazon SQS, but was rejected with an error response for some reason.");
        } 
        return myQueueUrl;
	}

    public void send(String myQueueUrl, String str) throws Exception {

        try {
            // Send a message
            System.out.println("Sending a message to  "+QueueName+"\n");
            sqs.sendMessage(new SendMessageRequest(myQueueUrl, str));
            }
        catch (AmazonClientException ace) {
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
    
    public List<Message> receive(String myQueueUrl) throws Exception{
    	List<Message> messages=null;
    	try{
    	 System.out.println("Receiving messages from  "+QueueName+"\n");
         ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
         messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
         for (Message message : messages) {
             System.out.println("  Message");
             System.out.println("    MessageId:     " + message.getMessageId());
             System.out.println("    Body:          " + message.getBody());
             for (Entry<String, String> entry : message.getAttributes().entrySet()) {
                 System.out.println("  Attribute");
                 System.out.println("    Name:  " + entry.getKey());
                 System.out.println("    Value: " + entry.getValue());
             }
         }
         System.out.println();
    	}
    	catch(AmazonClientException ace){
    		  System.out.println("Error Message: " + ace.getMessage());
    	}
    	return messages;
    }
    
    public void deleteMessage(List<Message> messages, String myQueueUrl) throws Exception{
    	// Delete a message
    	try{
        System.out.println("Deleting a message.\n");
        String messageRecieptHandle = messages.get(0).getReceiptHandle();
        sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messageRecieptHandle));
    	}
    	catch(AmazonClientException ace){
   		 System.out.println("Error Message: " + ace.getMessage());
   	}
   }
    
    public void deleteQueue(String myQueueUrl) throws Exception{
    	// Delete a queue
    	try{
        System.out.println("Deleting the  "+ QueueName+"\n");
        sqs.deleteQueue(new DeleteQueueRequest(myQueueUrl));
    	}
    	catch(AmazonClientException ace){
    		 System.out.println("Error Message: " + ace.getMessage());
    	}
    }
}

