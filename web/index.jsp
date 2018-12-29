<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2018/12/27
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form id="form" action="http://localhost:8080/diagens/survey/updateSurveyWithQuestion" method="post">
    <input type="text" name="questionBean[0].questionId" value="2"/>
    <input type="text" name="questionBean[1].questionId" value="3"/><br/>
    <input type="text" name="questionBean[0].questionTitle" value="你对人生的看法？"/>
    <input type="text" name="questionBean[1].questionTitle" value="你对工作的看法？"/><br/>
    <input type="submit">
</form>
</body>
</html>
