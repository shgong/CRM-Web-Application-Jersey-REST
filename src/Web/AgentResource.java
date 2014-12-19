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


public class AgentResource {

	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  String name;
	  
	  private java.sql.Connection conn =null;
	  private java.sql.Statement stat = null;
    //	  private ResultSet result = null;
	  
	  public AgentResource(UriInfo uriInfo, Request request, String name) {
	    this.uriInfo = uriInfo;
	    this.request = request;
	    this.name = name;
	  }
	  
	 // Application integration     
	  @GET
	  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public Agent getAgent() throws SQLException {
		  ResultSet result=null;
		  Agent Agent=null;
		  try{
				Class.forName("com.mysql.jdbc.Driver");

				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

				stat=conn.createStatement();
			    result=stat.executeQuery("select * from Agent where Name= '"+name+"'");
			    if(result.next())
			    	 Agent=new Agent(result.getString(2),result.getString(3),result.getString(1));
				  }catch(Exception e){
					  System.out.println("There is an Exception.");
				  }
	    return Agent;
	  }
	      
	  // for the browser
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public Agent getAgentHTML() throws SQLException {
		  ResultSet result=null;
		  Agent Agent=null;
		  try{
				Class.forName("com.mysql.jdbc.Driver");

				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

				stat=conn.createStatement();
			    result=stat.executeQuery("select * from Agent where Name= '"+name+"'");
			    if(result.next())
			    	 Agent=new Agent(result.getString(2),result.getString(3),result.getString(1));
				  }catch(Exception e){
					  System.out.println("There is an Exception.");
				  }
	    return Agent;
	  }
	  
	  @PUT
	  @Consumes(MediaType.APPLICATION_XML)
	  public Response putAgent(JAXBElement<Agent> Agent) {
	    Agent cus = Agent.getValue();
	   
	    return putAndGetResponse(cus);
	  }
	  
	  @DELETE
	  public void deleteAgent() {
		  try{
				Class.forName("com.mysql.jdbc.Driver");

				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

				stat=conn.createStatement();
			    stat.executeUpdate("delete from Agent where Name='"+name+"';");
			   // res = Response.noContent().build();
				  }catch(Exception e){
					  System.out.println("There is an Exception.");
				  }
		  return;
	  }
	  
	  private Response putAndGetResponse(Agent Agent) {
	    Response res=null;  
	    ResultSet result1=null;
		  try{
				Class.forName("com.mysql.jdbc.Driver");

				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

				stat=conn.createStatement();
			    result1=stat.executeQuery("select * from Agent where Name= '"+name+"'");
			    if(result1.next()){
			    	res = Response.noContent().build();
			    	stat.executeUpdate("update Agent Set Name= '"+Agent.getName()+"', Phone='"+Agent.getPhone()+"', Email='"+Agent.getEmail()+"' WHERE Name='" +Agent.getName()+"'");
			    	}
				  else {
					  res = Response.created(uriInfo.getAbsolutePath()).build();
					  stat.executeUpdate("insert into Agent values ('"+Agent.getName()+"', '"+ Agent.getPhone() +"', '" +Agent.getEmail()+"')");
				  }
				  }catch(Exception e){
					  System.out.println("There is an Exception.");
				  }
	    return res;
	  }
	  
	  
	  
	  
	  
}
