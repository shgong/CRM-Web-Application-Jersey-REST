
    
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK" %>

<%@ page import="java.sql.*" %>
<%@ page import="com.amazonaws.services.sqs.model.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="example.SimpleQueueService, example.Test, example.MessageQueue"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Text Success</title>
 <link href="styles/styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<%

MessageQueue queue=(MessageQueue)session.getAttribute("Queue");
List<Message> messages=queue.receive((String)session.getAttribute("QueueUrl"));
String Customer=null;
String Agent=null;

 if(messages.size()>0){
	  String mess=messages.get(0).getBody();
	  String[] mes=mess.split(" ");
	  Customer=mes[0];
	  Agent=mes[1];
     queue.deleteMessage(messages, (String)session.getAttribute("QueueUrl"));
}
  
   queue.deleteQueue((String)session.getAttribute("QueueUrl"));
 

%>

<center>
<td width="100"> Delete Text Successfully </td>

<table>




<br><br>


<tr>

<td width="200">Deleted Text Between </td>

</tr>

<tr>

<td width="200">Customer: <%=Customer %></td>

</tr>

<tr>

<td width="200">Agent: <%=Agent %></td>

</tr>

</table>
<br><br><br>
</center>

</body>
</html>