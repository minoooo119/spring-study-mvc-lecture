package hello.servlet.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private final MemberRepository memberRepository = MemberRepository.getInstance();
    private final ObjectMapper objectMapper= new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        Member member = new Member(username, age);
        memberRepository.save(member);

//        response.setContentType("text/json");
//        response.setCharacterEncoding("utf-8");
//        String s = objectMapper.writeValueAsString(member);
//
//        PrintWriter writer = response.getWriter();
//        writer.write(s);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        //servlet 이 다 좋음 request, response parsing 해주니까 -> html 을 하는게 별로다...
        //그래서 template engine 을 활용한다.
//        템플릿 엔진에는 JSP, Thymeleaf, Freemarker, Velocity등이 있다.

        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.getId()+"</li>\n" +
                "    <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" + "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                "</html>");
    }
}
