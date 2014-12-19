
    
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK" %>

<%@ page import="java.sql.*" %>
<%@ page import="com.amazonaws.services.sqs.model.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="example.SimpleQueueService, example.Test, example.MessageQueue"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="styles/styles.css" rel="stylesheet" type="text/css">
<title>Agent Create Success</title>
</head>
<body>

<%

MessageQueue queue=(MessageQueue)session.getAttribute("Queue");
List<Message> messages=queue.receive((String)session.getAttribute("QueueUrl"));
String uName=null;
String uPhone=null;
String uEmail=null;

 if(messages.size()>0){
	 String mes=messages.get(0).getBody();
	 String[] mess=mes.split(" ");
  uName=mess[0];
  uPhone=mess[1];
  uEmail=mess[2];
  queue.deleteMessage(messages, (String)session.getAttribute("QueueUrl"));
}
  
   queue.deleteQueue((String)session.getAttribute("QueueUrl"));
 

%>

<center>
<td width="100"> Create A new Agent Successfully </td>

<table>




<br><br>

<tr>

<td width="200">Name:  <%=uName %></td>

</tr>
<tr>
<td width="200">Phone:  <%=uPhone %></td>
</tr>

<tr>
<td width="200">Email:  <%=uEmail %></td>
</tr>
</table>
<br><br><br>
</center>

</body>
</html>