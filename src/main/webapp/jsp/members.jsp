<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mino
  Date: 2024. 8. 1.
  Time: 오후 1:46
  To change this template use File | Settings | File Templates.
--%>
<%
    //servlet 방식이라 크게 다른 것 같지는 않다

    MemberRepository memberRepository = MemberRepository.getInstance();

    List<Member> members = memberRepository.findAll();

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
            out.write("    <tr>");
            out.write(" <td>" + member.getId() + "</td>");
            out.write(" <td>" + member.getUsername() + "</td>");
            out.write(" <td>" + member.getAge() + "</td>");
            out.write("    </tr>");
        }
    %>
    </tbody>
</table>
</body>
</html>
