
    
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK" %>

<%@ page import="java.sql.*" %>
<%@ page import="com.amazonaws.services.sqs.model.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="example.SimpleQueueService, example.Test, example.MessageQueue"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Customer Successfully</title>
 <link href="styles/styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<%

MessageQueue queue=(MessageQueue)session.getAttribute("Queue");
List<Message> messages=queue.receive((String)session.getAttribute("QueueUrl"));
String uName=null;

if(messages.size()>0){
  uName=messages.get(0).getBody();
// String  uName=queue.receive((String)session.getAttribute("QueueUrl")).get(0).getBody();
// if(queue.receive((String)session.getAttribute("QueueUrl"))!=null)
   queue.deleteMessage(messages, (String)session.getAttribute("QueueUrl"));
}
   queue.deleteQueue((String)session.getAttribute("QueueUrl"));

//String uName="";
//if (session.getAttribute("Name")!=null);
if (uName!=null)
{
Class.forName("com.mysql.jdbc.Driver");

Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","");

Statement stat=conn.createStatement();

ResultSet rs=stat.executeQuery("select * from Cus where Name= '"+uName+"'");

if (rs.next())
{	
%>
<center>
<br><br><br>
<table>
<tr>

<td width="200">Name: <%=rs.getString(1) %></td>

</tr>
<tr>
<td width="200">Phone:  <%=rs.getString(2) %></td>
</tr>

<tr>
<td width="200">Email:  <%=rs.getString(3) %></td>
</tr>
</table>

<br><br>
</center>
<%
  }
}
%>

</body>
</html>