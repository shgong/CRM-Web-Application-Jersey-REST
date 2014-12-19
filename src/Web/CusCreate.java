package Web;
 
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

public class CusCreate {

	 public static void main(String[] args) {
		    ClientConfig config = new DefaultClientConfig();
		    Client client = Client.create(config);
		    WebResource service = client.resource(getBaseURI());
		    
		 
 	    //delete one customer
		   Customer customer2=new Customer("mary");
		 //  service.path("rest").path("customer").path(customer2.getName()).delete();
		   ClientResponse response3=service.path("rest").path("customer").path(customer2.getName()).delete(ClientResponse.class);
		   //Return code should be 204==Delete Success
		    System.out.println(response3.getStatus()); 

	
		    // create one customer
		   Customer customer = new Customer("mary", "567","mary@me.com");
		    ClientResponse response = service.path("rest").path("customer")
		          .path(customer.getName()).accept(MediaType.APPLICATION_XML)
		          .put(ClientResponse.class, customer);  
		    
		    // Return code should be 201 == created resource
		    System.out.println(response.getStatus()); 
		   
		    System.out.println(service.path("rest").path("customer").path("mary").accept(MediaType.APPLICATION_XML).get(String.class));
		   
		    
		    //update one customer
		    Customer customer3=new Customer("mary","123","mary@columbia.com");
		    ClientResponse response2 = service.path("rest").path("customer")
			        .path(customer3.getName()).accept(MediaType.APPLICATION_XML)
			        .put(ClientResponse.class, customer3);  
		    
			    // Return code should be 204 == updated resource
			    System.out.println(response2.getStatus());
		    
			   //retrieve one customer 
			   //Return code should be 200==OK with getting message body
			 ClientResponse response1=service.path("rest").path("customer").path("mary").accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
			 System.out.println(response1.getStatus());
		     System.out.println(service.path("rest").path("customer").path("mary").accept(MediaType.APPLICATION_XML).get(String.class));
		  }

		  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8080/Web").build();
		  }
		  
}
