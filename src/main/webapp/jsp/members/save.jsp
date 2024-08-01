<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: mino
  Date: 2024. 8. 1.
  Time: 오후 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //이 부분으로 자바 실행할 수 있다.

    //request, response 사용 가능
    //servlet 으로 자동으로 변환돼서 사용 -> service logic 호출 된다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("JspClass.jsp_service_method");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html"> main </a>

</body>
</html>
