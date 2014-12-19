
    
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
<title>Contact History</title>
</head>
<body>

<%

MessageQueue queue=(MessageQueue)session.getAttribute("Queue");
List<Message> messages=queue.receive((String)session.getAttribute("QueueUrl"));
String Cus=null;
String Agent=null;
String Model=null;
String Text=null;

 if(messages.size()>0){
	 String mes=messages.get(0).getBody();
	 String[] mess=mes.split(" ");
  Cus=mess[0];
  Agent=mess[1];
  Model=mess[2];
  Text=mess[3];
  for(int i=4;i<mess.length;i++)
    Text=Text+" "+mess[i];
  queue.deleteMessage(messages, (String)session.getAttribute("QueueUrl"));
}
  
   queue.deleteQueue((String)session.getAttribute("QueueUrl"));
 

%>

<center>
<td width="100">Contact History Summary</td>

<table>




<br><br>

<tr>

<td width="200">Customer: <%=Cus %></td>

</tr>
<tr>
<td width="200">Agent:  <%=Agent %></td>
</tr>

<tr>
<td width="200"> Model: <%=Model %></td>
</tr>

<tr>
<td width="200">Text:   <%=Text %></td>
</tr>
</table>
<br><br><br>
</center>

</body>
</html>