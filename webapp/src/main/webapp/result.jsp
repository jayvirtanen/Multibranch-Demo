<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<body>
<center>
<h1>
    Available Types
</h1>
<%
List result= (List) request.getAttribute("types");
Iterator it = result.iterator();
out.println("<br>We have <br><br>");

while(it.hasNext()){

out.println(it.next()+"<br>");

}

%>
</body>
</html>