<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>first page!</h2>
<hr/>
<p>springmvc 是tomcat 部署deployment 时，配置的 application context</p>
<h2><a href="/springmvc/hi" > hi</a></h2>
<hr/>
<p>href 以 / 开头</p>
<h2><a href="/springmvc/smtest/testRequestMapping" > testRequestMapping</a></h2>
<p> href 没有以 / 开头</p>
<h2><a href="smtest/testRequestMapping" > testRequestMapping2</a></h2>
<hr/>
<h2><a href="smtest/testMethodParams?userName=aaa&age=20" > testMethodParams(满足方法参数要求，可以访问)</a></h2>
<h2><a href="smtest/testMethodParams?userName=aaa&age=10" > testMethodParams (不满足方法参数要求，不可以访问)(但是可以访问，不知道为啥)</a></h2>
<h2><a href="smtest/testMethodParams?userName=aaa" > testMethodParams(不满足方法参数要求，不可以访问)(但是可以访问，不知道为啥)</a></h2>
<h2><a href="smtest/testMethodParams?age=20" > testMethodParams(不满足方法参数要求，不可以访问)</a></h2>
<hr/>

<h2><a href="/springmvc/smtest/testPathVariable/userName/list" > testPathVariable</a></h2>
<br/>
<P>测试 restful 风格接口</P>
<p>保存订单</p>
<form action="/springmvc/v1/orders/" method="post">
    id:<input type="text" name="id"><br/>
    name:<input type="text" name="name"><br/>
    weight:<input type="text" name="weight"><br/>
    <button type="submit">提交</button>
</form>
<h2><a href="/springmvc/v1/orders/" > queryOrders</a></h2>
<h2><a href="/springmvc/v1/orders/1" > queryOrder</a></h2>
<br/>
<p>修改订单(全部属性)</p>
<form action="/springmvc/v1/orders/1" method="post">
    <input type="hidden" name="_method" value="put">
    name:<input type="text" name="name"><br/>
    weight:<input type="text" name="weight"><br/>
    <button type="submit">提交</button>
</form>
<br/>
<p>修改订单(部分属性)</p>
<form action="/springmvc/v1/orders/1" method="post">
    <input type="hidden" name="_method" value="patch">
    name:<input type="text" name="name"><br/>
    <button type="submit">提交</button>
</form>

<br/>
<p>删除订单</p>
<form action="/springmvc/v1/orders/1" method="post">
    <input type="hidden" name="_method" value="delete">
    <button type="submit">提交</button>
</form>



</body>
</html>
