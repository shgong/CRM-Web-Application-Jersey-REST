
package Web;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List; 

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


@Path("/Agent")
public class AgentsResources {
	
	 // Allows to insert contextual objects into the class,
	  // e.g. ServletContext, Request, Response, UriInfo
	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  
	  
	  private java.sql.Connection conn = null;
	  private java.sql.Statement stat = null;
	  private ResultSet result = null;
	  
	 // @GET
	//  @Produces(MediaType.TEXT_XML)
	  @Path("{name}")
	  public AgentResource getAgentBrowser(@PathParam("name") String name) {
	    return new AgentResource(uriInfo, request, name);
	  }
	  
	  @POST
	  @Produces(MediaType.TEXT_HTML)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public void newAgent(@FormParam("name") String name,
	      @FormParam("phone") String phone,
	      @FormParam("email") String email,
	      @Context HttpServletResponse servletResponse) throws IOException {
	    Agent Agent= new Agent(name, phone, email);
	    try{
			Class.forName("com.mysql.jdbc.Driver");

			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

			stat=conn.createStatement();
		    stat.executeUpdate("insert into Agent values ('"+Agent.getName()+"', '"+ Agent.getPhone() +"', '" +Agent.getEmail()+"')");
			  }catch(Exception e){
				  System.out.println("There is an Exception.");
			  }
	     //servletResponse.sendRedirect("../create_todo.html");
	  }
	  
	  
	  @PUT
	  @Produces(MediaType.TEXT_HTML)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public void updateAgent(@FormParam("name") String name,
	      @FormParam("phone") String phone,
	      @FormParam("email") String email,
	      @Context HttpServletResponse servletResponse) throws IOException {
	    Agent Agent= new Agent(name, phone, email);
	    try{
			Class.forName("com.mysql.jdbc.Driver");

			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

			stat=conn.createStatement();
		    stat.executeUpdate("update Agent set Phone='"+ phone +"', Email='" +email+"' where Name='"+name+"' ");
			  }catch(Exception e){
				  System.out.println("There is an Exception.");
			  }
	     //servletResponse.sendRedirect("../create_todo.html");
	  }


	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String HtmlAll(@PathParam("name") String name) {
		  String rst="<tr><td>Name</td><td>Phone</td><td>Email</td></tr>";
		  try{
		Class.forName("com.mysql.jdbc.Driver");

		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

		stat=conn.createStatement();
	    result=stat.executeQuery("select * from Agent");
	    	while(result.next())
	    		rst = rst +"<tr><td>"+ result.getString(1)+"</td><td>"+result.getString(2)+"</td><td>"+result.getString(3)+"</td></tr>";
		  }catch(Exception e){
			  System.out.println("There is an Exception.");
		  }
		
		  return "<table><tbody>"+rst+"</tbody></table>";
	    
	  }
	  
	  
	  @DELETE
	  @Produces(MediaType.TEXT_HTML)
	  @Path("{name}")
	  public String Htmldelete(@PathParam("name") String name) {
		  try{
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");
		
		stat=conn.createStatement();
	    stat.executeUpdate("DELETE FROM Agent where Name= '"+name+"'");
	    return "Delete Succeed";
		  }catch(Exception e){
			  System.out.println("There is an Exception.");
		  }
	 
		  return "Error";
	    
	  }
	  
	  
	  
		  @GET
		  @Produces(MediaType.TEXT_HTML)
		  @Path("{name}")
		  public String HtmlIndividual(@PathParam("name") String name) {
			  String rst="<tr><td>Name</td><td>Phone</td><td>Email</td></tr>";
			  try{
			Class.forName("com.mysql.jdbc.Driver");

			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

			stat=conn.createStatement();
		    result=stat.executeQuery("select * from Agent where Name= '"+name+"'");
		    if(result.next())
			rst= rst +"<tr><td>"+ result.getString(1)+"</td><td>"+result.getString(2)+"</td><td>"+result.getString(3)+"</td></tr>";
			  }catch(Exception e){
				  System.out.println("There is an Exception.");
			  }
			
			  return "<table><tbody>"+rst+"</tbody></table>";
		    
		  }
		  

}

/*
// Return the list of todos for applications
@GET
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public List<Agent> getAgents() {
	  List<Agent> Agents = new ArrayList<Agent>();
	    ResultSet result=null;
		  try{
				Class.forName("com.mysql.jdbc.Driver");

				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

				stat=conn.createStatement();
			    result=stat.executeQuery("select * from Cus");  
			    	while(result.next())
			    	   Agents.add(new Agent(result.getString(2),result.getString(3),result.getString(1)));
				  }catch(Exception e){
					  System.out.println("There is an Exception.");
				  }
	    return Agents;
}

*/
