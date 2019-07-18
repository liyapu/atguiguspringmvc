<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<h2>success2 page </h2>
时间:${requestScope.time}
<br/>
names:${requestScope.names}
<br/>
<br/>
request student:${requestScope.studentZhangSan}<br/>
session student:${sessionScope.studentZhangSan}<br/>

request strType:${requestScope.strType}<br/>
session strType:${sessionScope.strType}<br/>
</body>
</html>
