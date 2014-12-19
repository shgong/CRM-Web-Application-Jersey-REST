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
	
	Customer=request.getParameter("CusName");
	Agent=request.getParameter("AgentName");
	
	if(Customer!=null){
		
		 MessageQueue queue=new MessageQueue("MyQueue10");
		 session.setAttribute("Queue",queue);
		 String myQueueUrl=queue.intialQueue();
		 session.setAttribute("QueueUrl",myQueueUrl);
		 String msm="";
		 msm=Customer+" "+Agent+" ";
	
		 System.out.println(Customer);
		 System.out.println(Agent);
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");
		Statement stat=conn.createStatement();
		   
		String query="select Model, TextSum from Contact where (Cus='"+Customer+"' AND Agent='"+Agent+"')";	
	
	    ResultSet set=stat.executeQuery(query);
	    
	    if(set.next()){
	      msm=msm+set.getString(1)+" "+set.getString(2);
	    
	   
	  //  System.out.println("TEST ");
	 
	    queue.send(myQueueUrl,msm);
	    
		response.sendRedirect("ConRetrieveSucc.jsp");
	    }
	    else response.sendRedirect("ConRetrieve.jsp");
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
<center><h3>Search Contact History</h3></center>
          </head>
    <center>
           <form>
           
          
           
          Customer £º<input type="Text" name="CusName"  ></input><br><br>
         
             Agent £º&nbsp;&nbsp; &nbsp; &nbsp;<input type="Text" name="AgentName"  ></input><br><br>
          
      
          <input  class="item" type="submit" name="submit"></input>
          </form>       
          
            </center>
</body>


</html>