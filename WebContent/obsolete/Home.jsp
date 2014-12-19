
    
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK" %>

<html>

<head>
 <meta http-equiv="Content-type" content="text/html; charset=utf-8">
 <title>Customer Relationship Management</title>
 <link href="styles/styles.css" rel="stylesheet" type="text/css">
<script src="./js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript">
function callagent(p)
{
    if(p!=0) {$("#CAgent").hide();}else{$("#CAgent").toggle();}
    if(p!=1) {$("#CCus").hide();}else{$("#CCus").toggle();}
    if(p!=2) {$("#CCon").hide();}else{$("#CCon").toggle();}
}

function open(url)
{
	document.getElementById('frame').src=url;
}

</script>
</head>

<body>
<img src="images/logo.png" height="200" width="445">
<br><br>
<td>

	<!--  <div class="title">Customer Relationship Management</div> -->
	<div>
		<a class="item" href="javascript:callagent(0);">Agent Info</a>
    	<a class="item" href="javascript:callagent(1);">Customer Info</a>
    	<a class="item" href="javascript:callagent(2);">Contact History</a>
	</div>
	</td>

<div class="height">
<div class="hide"  id="CAgent">
    	<a class="item" href="javascript:open('AgentRetrieve.jsp')">Retrieve AgentInfo</a>
       	<a class="item" href="javascript:open('AgentCreate.jsp')">New Agent</a>
		<a class="item" href="javascript:open('AgentUpdate.jsp')">Update AgentInfo</a>
		<a class="item" href="javascript:open('AgentDelete.jsp')">Delete Agent</a>
</div>
<div class="hide"  id="CCus">
    	<a class="item" href="javascript:open('CusRetrieve.jsp')">Retrieve CusInfo</a>
       	<a class="item" href="javascript:open('CusCreate.jsp')">New Cus</a>
		<a class="item" href="javascript:open('CusUpdate.jsp')">Update CusInfo</a>
		<a class="item" href="javascript:open('CusDelete.jsp')">Delete Cus</a>
</div>
<div class="hide" id="CCon">
    	<a class="item" href="javascript:open('ConRetrieve.jsp')">Retrieve Text</a>
       	<a class="item" href="javascript:open('ConCreate.jsp')">New Text</a>
		<a class="item" href="javascript:open('ConUpdate.jsp')">Update Text</a>
		<a class="item" href="javascript:open('ConDelete.jsp')">Delete Text</a>
</div>
</div>
</td>



<div id="mydiv"><iframe id="frame" src="" width="50%" height="270"></iframe></div>
<div class="copyrights">
<p>Project Members: Xiangliang Yang, Shuheng Gong, Guangyang Zhang, Guoqin Yao, Zhenying Zhu.</p>
<p>Logo Art by Giovana Milanezi is licensed under CC BY 2.0.</p>
<div>
</body>
</html>


  
