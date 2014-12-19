package Web;

import java.sql.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;


public class CustomerResource {

	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  String name;
	  
	  private java.sql.Connection conn =null;
	  private java.sql.Statement stat = null;
    //	  private ResultSet result = null;
	  
	  public CustomerResource(UriInfo uriInfo, Request request, String name) {
	    this.uriInfo = uriInfo;
	    this.request = request;
	    this.name = name;
	  }
	  
	 // Application integration     
	  @GET
	  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public Customer getCustomer() throws SQLException {
		  ResultSet result=null;
		  Customer customer=null;
		  try{
				Class.forName("com.mysql.jdbc.Driver");

				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

				stat=conn.createStatement();
			    result=stat.executeQuery("select * from Cus where Name= '"+name+"'");
			    if(result.next())
			    	 customer=new Customer(result.getString(2),result.getString(3),result.getString(1));
				  }catch(Exception e){
					  System.out.println("There is an Exception.");
				  }
	    return customer;
	  }
	      
	  // for the browser
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public Customer getCustomerHTML() throws SQLException {
		  ResultSet result=null;
		  Customer customer=null;
		  try{
				Class.forName("com.mysql.jdbc.Driver");

				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

				stat=conn.createStatement();
			    result=stat.executeQuery("select * from Cus where Name= '"+name+"'");
			    if(result.next())
			    	 customer=new Customer(result.getString(2),result.getString(3),result.getString(1));
				  }catch(Exception e){
					  System.out.println("There is an Exception.");
				  }
	    return customer;
	  }
	  
	  @PUT
	  @Consumes(MediaType.APPLICATION_XML)
	  public Response putCustomer(JAXBElement<Customer> customer) {
	    Customer cus = customer.getValue();
	   
	    return putAndGetResponse(cus);
	  }
	  
	  @DELETE
	  public void deleteCustomer() {
		  try{
				Class.forName("com.mysql.jdbc.Driver");

				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

				stat=conn.createStatement();
			    stat.executeUpdate("delete from Cus where Name='"+name+"'");
			   // res = Response.noContent().build();
				  }catch(Exception e){
					  System.out.println("There is an Exception.");
				  }
		  return;
	  }
	  
	  private Response putAndGetResponse(Customer customer) {
	    Response res=null;  
	    ResultSet result1=null;
		  try{
				Class.forName("com.mysql.jdbc.Driver");

				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

				stat=conn.createStatement();
			    result1=stat.executeQuery("select * from Cus where Name= '"+name+"'");
			    if(result1.next()){
			    	res = Response.noContent().build();
			    	stat.executeUpdate("update Cus Set Name= '"+customer.getName()+"', Phone='"+customer.getPhone()+"', Email='"+customer.getEmail()+"' WHERE Name='" +customer.getName()+"'");
			    	}
				  else {
					  res = Response.created(uriInfo.getAbsolutePath()).build();
					  stat.executeUpdate("insert into Cus values ('"+customer.getName()+"', '"+ customer.getPhone() +"', '" +customer.getEmail()+"')");
				  }
				  }catch(Exception e){
					  System.out.println("There is an Exception.");
				  }
	    return res;
	  }
	  
	  
	  
	  
	  
}
