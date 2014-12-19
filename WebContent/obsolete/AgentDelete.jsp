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


String uName="";
String error_msg="";

try {
	
	uName=request.getParameter("Name");
	
	if(uName!=null){
		
		 MessageQueue queue=new MessageQueue("MyQueue10");
		 session.setAttribute("Queue",queue);
		 String myQueueUrl=queue.intialQueue();
		 session.setAttribute("QueueUrl",myQueueUrl);
		  queue.send(myQueueUrl,uName);
	
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");
		Statement stat=conn.createStatement();
		   
		String query="delete from Agent where Name='"+uName+"'";	
	
	    stat.executeUpdate(query);
	 
		response.sendRedirect("AgentDeleteSucc.jsp");

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
<center><h3>Deleting Agent</h3></center>
          </head>
    <center>
           <form>
           
          
           
          Name £º<input type="Text" name="Name"  ></input><br><br>
         
          <input  class="item" type="submit" name="submit"></input>
          </form>       
          
            </center>
</body>


</html>