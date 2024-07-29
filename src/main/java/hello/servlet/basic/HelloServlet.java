package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    //servlet 이 호출되면 아래 service 가 호출된다.

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //호출 잘 된다. 빈화면 나오는데 응답이 없었기 때문이다.
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

//        아래와 같이 출력이 된다.
//        HelloServlet.service
//        request = org.apache.catalina.connector.RequestFacade@78ea5fea
//        response = org.apache.catalina.connector.ResponseFacade@74033055

        String username = request.getParameter("username");
        System.out.println("username = " + username);
//        username = mino

        //응답 메시지 생성해보기
        //아래는 content type : header 정보에 포함
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //아래는 body 정보에 포함
        response.getWriter().write("hello " +username);

        //servlet 을 통해 편하게 할 수 있다.
    }

}
