<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>search engine [users-list.jsp]</title>
</head>
<body>
<c:forEach var="entry" items="${list}">
    <div <c:if test="${entry.key.gender.toString() == 'MAN'}">style="color: blue;"</c:if>>
        Name: <c:out value="${entry.key.name}"/><br/>
        Surname: <c:out value="${entry.key.surname}"/><br/>
        Login: <c:out value="${entry.key.login}"/><br/>
        Age: <c:out value="${entry.key.age}"/><br/>
        Gender: <c:out value="${entry.key.gender}"/><br/>
        Stats: <c:out value="${entry.value}"/><br/>

        <c:forEach var="groupEntry" items="${entry.key.groups}">
            Group: <c:out value="${groupEntry.id} (${groupEntry.name})"/><br/>
        </c:forEach>
    </div>
    <br/><br/>
</c:forEach>
</body>
</html>
