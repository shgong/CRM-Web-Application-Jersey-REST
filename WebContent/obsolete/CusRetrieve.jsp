<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK" %>


<%@ page import="java.sql.*" %>
<%@ page language="java" import="java.util.List" %>
<%@ page import="example.SimpleQueueService, example.Test, example.MessageQueue"%>

<html>
<head>
 <link href="styles/styles.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">

<title> Customer Retrieve</title>
</head>

<body>

<%

Class.forName("com.mysql.jdbc.Driver");

Connection conn=null;//DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");

//Statement stat=conn.createStatement();

ResultSet rs=null;//=stat.executeQuery("select * from CusInfo");


String uName="";
String error_msg="";

try {
	
	uName=request.getParameter("Name");
	
	
	
	if(uName!=null){
		
		 MessageQueue queue=new MessageQueue("MyQueue4");
		 session.setAttribute("Queue",queue);
		 String myQueueUrl=queue.intialQueue();
		 session.setAttribute("QueueUrl",myQueueUrl);
		 queue.send(myQueueUrl,uName);
		
		//conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");
		//Statement stat=conn.createStatement();
	//	String query="select * from Cus where Name='" + uName +"'";	
		
		//SQS.getMessage(query);
			
	    //rs=stat.executeQuery(query);
	    
		response.sendRedirect("CusRetrieveSucc.jsp");
	/*	if (rs.next()){
			 
			//session.setAttribute("Name",uName);
			response.sendRedirect("CusRetrieveSucc.jsp");
		
     }
		else
		response.sendRedirect("CusRetrieve.jsp");*/
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

 // queue.receive(myQueueUrl);
 // queue.delete(myQueueUrl);
%>
</body>


<body>
<center><h3>Customer Retrieve</h3></center>
          </head>
    <center>
           <form><br>
          ID £º<input type="Text" name="Name"  ></input><br>
              <br>
               
       <input  class="item" type="submit" name="submit"></input>

          </form>       
            </center>
</body>


</html>