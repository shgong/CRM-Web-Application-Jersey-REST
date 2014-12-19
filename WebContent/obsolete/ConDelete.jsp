<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK" %>


<%@ page import="java.sql.*" %>
<%@ page language="java" import="java.util.List" %>
<%@ page import="example.SimpleQueueService, example.Test, example.MessageQueue"%>

<html>
 <link href="styles/styles.css" rel="stylesheet" type="text/css">
<body>

<%

Class.forName("com.mysql.jdbc.Driver");

Connection conn=null;

ResultSet rs=null;


String Customer="";
String error_msg="";
String Agent="";

try {
	
	Customer=request.getParameter("Customer");
	Agent=request.getParameter("Agent");
	
	if(Customer!=null){
		
		 MessageQueue queue=new MessageQueue("MyQueue2");
		 session.setAttribute("Queue",queue);
		 String myQueueUrl=queue.intialQueue();
		 session.setAttribute("QueueUrl",myQueueUrl);
		 String mes=Customer+" "+Agent;
		  queue.send(myQueueUrl,mes);
	
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");
		Statement stat=conn.createStatement();
		   
		String query="delete from Contact where Cus='"+Customer+"' AND Agent='"+Agent+"'";	
	
	    stat.executeUpdate(query);
	 
		response.sendRedirect("ConDeleteSucc.jsp");

  }
}
catch (Exception e)
{
    error_msg = e.getMessage();
    if (conn != null)
    {
        conn.close();
    }
}

%>
</body>


<body>
<center><h3>Deleting Text</h3></center>
          </head>
    <center>
           <form>
           
          
           
          Customer £º<input type="Text" name="Customer"  ></input><br><br>
          Agent £º&nbsp; &nbsp; &nbsp;<input type="Text" name="Agent"  ></input><br><br>
         
          <input  class="item" type="submit" name="submit"></input>
          </form>       
          
            </center>
</body>


</html>