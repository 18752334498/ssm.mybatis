<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="/ssm.mybatis/jquery-3.3.1.min.js"></script>  
</head>
<body>
	<div style="border: 2px solid red;margin: auto;width: 300px;padding: 10px">
		<form action="" method="post">
			<p>用户名： <input type="text" id="username" /></p>
			<p>密 码： <input type="password" id="password"/></p>
			<p><input id="btn" type="button" value="登陆"></p>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(function(){

	});
	
	$("#btn").click(function(){
		var data = {
			username:$("#username").val().trim(),
			password:$("#password").val().trim()
		}
		$.ajax({
			url:'/ssm.mybatis/login/validate',
			data:data,
			type:'POST',
			dataType:'json',
			success:function(msg){
				console.log(msg);
				alert("返回信息： " + msg);
			}
		});
	});
</script>
</html>