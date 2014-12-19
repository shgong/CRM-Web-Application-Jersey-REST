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


String Cus="";
String error_msg="";
String Agent="";
String Model="";
String Text="";

try {
	
	Cus=request.getParameter("Customer");
	Agent=request.getParameter("Agent");
	Model=request.getParameter("Model");
	Text=request.getParameter("TextSummary");
	
	if(Cus!=null){
		
		 MessageQueue queue=new MessageQueue("MyQueue18");
		 session.setAttribute("Queue",queue);
		 String myQueueUrl=queue.intialQueue();
		 session.setAttribute("QueueUrl",myQueueUrl);
		 String msm=Cus+" "+Agent+" "+Model+" "+Text;
		  queue.send(myQueueUrl,msm);
	
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");
		Statement stat=conn.createStatement();
		   
		String query="insert into Contact values ('"+Cus+"', '"+ Agent +"', '" + Model +"','"+Text+"')";	
	
	    stat.executeUpdate(query);
	 
		response.sendRedirect("ConCreateSucc.jsp");

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
<center><h3>New Text</h3></center>
          </head>
    <center>
           <form>
        
          Customer £º<input type="Text" name="Customer"  ></input><br><br>
      
          Agent £º &nbsp; &nbsp; <input type="Text" name="Agent"  ></input><br><br> 
            
          Model £º   &nbsp; &nbsp; <input type="Text" name="Model"  ></input><br><br>
          
          Text £º    &nbsp; &nbsp; &nbsp;&nbsp; <input type="Text" name="TextSummary"  ></input><br><br>
                     
          <input  class="item" type="submit" name="submit"></input>
          </form>       
          
            </center>
</body>


</html>